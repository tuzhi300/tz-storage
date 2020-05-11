package net.kuper.tz.storage.service.impl;

import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import net.kuper.tz.core.cache.Cache;
import net.kuper.tz.core.constant.Time;
import net.kuper.tz.core.constant.UserType;
import net.kuper.tz.core.controller.exception.ApiException;
import net.kuper.tz.core.mybatis.Pagination;
import net.kuper.tz.core.properties.StorageProperties;
import net.kuper.tz.core.utils.FileUtils;
import net.kuper.tz.core.utils.StringUtils;
import net.kuper.tz.storage.dao.OssDao;
import net.kuper.tz.storage.dao.OssFileDao;
import net.kuper.tz.storage.entity.*;
import net.kuper.tz.storage.service.OssService;
import net.kuper.tz.storage.service.StorageService;
import net.kuper.tz.storage.service.StorageType;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * 文件上传Service实现类
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-30 11:55:49
 */
@Service("ossService")
public class OssServiceImpl implements OssService {

    @Autowired
    private OssDao ossDao;
    @Autowired
    private OssFileDao ossFileDao;
    @Autowired
    private Cache cache;
    @Autowired
    private OssTool ossTool;
    @Autowired
    private StorageProperties storageProperties;
    @Autowired
    @Qualifier("aliyunStorage")
    private StorageService ali;
    @Autowired
    @Qualifier("tencentStorage")
    private StorageService tencent;
    @Autowired
    @Qualifier("qiniuStorage")
    private StorageService qiniu;
    @Autowired
    @Qualifier("localStorage")
    private StorageService local;

    @Override
    public Pagination<OssEntity> queryList(OssQueryEntity ossQueryEntity) {
        PageHelper.startPage(ossQueryEntity.getPage(), ossQueryEntity.getPageSize());
        List<OssEntity> ossList = ossDao.queryList(ossQueryEntity);
        return new Pagination<OssEntity>(ossList);
    }

    @Override
    public OssEntity queryObject(String id) {
        return ossDao.queryObject(id);
    }

    @Override
    public FileEntity getFile(String id) {
        Integer type = cache.get("oss.type." + id, Integer.class);
        String key = cache.get("oss.key." + id, String.class);
        if (type == null) {
            OssEntity ossEntity = this.queryObject(id);
            if (ossEntity == null) {
                throw new ApiException("文件不存在");
            }
            type = ossEntity.getPlanform();
            key = ossEntity.getKey();
            cache.set("oss.type." + id, type, 6 * Time.HOUR);
            cache.set("oss.key." + id, key, 6 * Time.HOUR);
        }
        StorageType storageType = null;
        for (StorageType value : StorageType.values()) {
            if (value.value == type) {
                storageType = value;
                break;
            }
        }
        String url = null;
        switch (storageType) {
            case LOCALE:
                url = local.getUrl(key);
                break;
            case TENCENT:
                url = tencent.getUrl(key);
                break;
            case QINIU:
                url = qiniu.getUrl(key);
                break;
            case ALIYUN:
                url = ali.getUrl(key);
                break;
        }

        FileEntity entity = new FileEntity();
        entity.setKey(key);
        entity.setStorageType(storageType);
        entity.setUrl(url);
        return entity;
    }

    private String getFileMD5(String file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        String md5 = DigestUtils.md5Hex(inputStream);
        inputStream.close();
        return md5;
    }

    private String getFileSHA1(String file) throws IOException {
        InputStream inputStream = new FileInputStream(file);
        String sha1 = DigestUtils.sha1Hex(inputStream);
        inputStream.close();
        return sha1;
    }

    @Override
    public OssEntity save(MultipartFile multipartFile, FileOperationAdapter adapter, StorageType type, String folder, String folderId, String createUserId, UserType userType) {
        String cachePath = null;
        String operationPath = null;
        String targetFile = null;

        OssUpdateEntity updateEntity = null;
        try {
            String suffix = ossTool.getSuffix(multipartFile);
            cachePath = ossTool.generationCachePath(suffix);
            File cacheFile = new File(cachePath);
            if (!cacheFile.getParentFile().exists()) {
                cacheFile.getParentFile().mkdirs();
            }
            cacheFile = cacheFile.getAbsoluteFile();
            multipartFile.transferTo(cacheFile);
            if (folder.startsWith("/")) {
                folder = folder.substring(1);
            }
            if (folder.endsWith("/")) {
                folder = folder.substring(0, folder.length() - 1);
            }
            if (StringUtils.isEmpty(folder) || StringUtils.isTrimEmpty(folder)) {
                throw new ApiException("目录不能为空");
            }
            String key = ossTool.generationKey(folder, suffix);
            updateEntity = new OssUpdateEntity();
            updateEntity.setCreateUserId(createUserId);
            updateEntity.setCreateUserType(userType.value);
            updateEntity.setOriginalMd5(getFileMD5(cachePath));
            updateEntity.setOriginalSha1(getFileSHA1(cachePath));
            updateEntity.setFormat(suffix);
            updateEntity.setSize(multipartFile.getSize());
            updateEntity.setKey(key);
            updateEntity.setFolder(folder);
            if (adapter != null) {
                operationPath = ossTool.generationCachePath(suffix);
                operationPath = new File(operationPath).getAbsolutePath();
                FileAttEntity fileAtt = new FileAttEntity();
                fileAtt.setName(multipartFile.getName());
                fileAtt.setOriginalName(multipartFile.getOriginalFilename());
                fileAtt.setContentType(multipartFile.getContentType());
                fileAtt.setMd5(updateEntity.getMd5());
                fileAtt.setSha1(updateEntity.getSha1());
                adapter.operation(cacheFile.getAbsolutePath(), fileAtt, operationPath);
                targetFile = operationPath;
                updateEntity.setMd5(getFileMD5(targetFile));
                updateEntity.setSha1(getFileSHA1(targetFile));
            } else {
                updateEntity.setMd5(updateEntity.getOriginalMd5());
                updateEntity.setSha1(updateEntity.getOriginalSha1());
                targetFile = cachePath;
            }
            // 去重
            OssEntity ossEntity = ossDao.queryBySign(updateEntity.getMd5(), updateEntity.getSha1(), type.value, null);
            if (ossEntity != null) {
                // 已经存在文件
                updateEntity.setId(ossEntity.getId());
            } else {
                // 没有相同文件
                switch (type) {
                    case LOCALE:
                        local.writeToStorage(targetFile, key);
                        updateEntity.setPlanform(StorageType.LOCALE.value);
                        break;
                    case QINIU:
                        qiniu.writeToStorage(targetFile, key);
                        updateEntity.setPlanform(StorageType.QINIU.value);
                        break;
                    case ALIYUN:
                        ali.writeToStorage(targetFile, key);
                        updateEntity.setPlanform(StorageType.ALIYUN.value);
                        break;
                    case TENCENT:
                        tencent.writeToStorage(targetFile, key);
                        updateEntity.setPlanform(StorageType.TENCENT.value);
                        break;
                }
                ossDao.save(updateEntity);
            }
            // 保存文件拥有关系
            OssFileUpdateEntity ossFileUpdateEntity = new OssFileUpdateEntity();
            ossFileUpdateEntity.setOssId(updateEntity.getId());
            ossFileUpdateEntity.setFolderId(folderId);
            if (!StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
                ossFileUpdateEntity.setDisplayName(multipartFile.getOriginalFilename());
            } else {
                ossFileUpdateEntity.setDisplayName(new File(targetFile).getName());
            }
            ossFileDao.save(ossFileUpdateEntity);
        } catch (IOException e) {
            throw new ApiException(e, "文件保存失败");
        } finally {
            FileUtils.deleteFile(targetFile);
            FileUtils.deleteFile(cachePath);
            FileUtils.deleteFile(operationPath);
        }
        return ossDao.queryObject(updateEntity.getId());
    }

    @Override
    public void update(OssUpdateEntity ossUpdateEntity) {
//        ossDao.update(ossUpdateEntity);
    }

    @Override
    public void delete(String id) {
//        ossDao.delete(id);
    }

    @Override
    public void deleteBatch(String[] ids) {
//        ossDao.deleteBatch(ids);
    }

}
