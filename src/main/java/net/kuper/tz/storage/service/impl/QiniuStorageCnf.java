package net.kuper.tz.storage.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import net.kuper.tz.core.controller.exception.ApiException;
import net.kuper.tz.storage.entity.CnfQiniuEntity;
import net.kuper.tz.storage.service.StorageService;
import org.springframework.stereotype.Service;

@Slf4j
@Service("qiniuStorage")
public class QiniuStorageCnf extends BaseStorageCnf<CnfQiniuEntity> implements StorageService {

    //    @Autowired
//    private ObjectMapper objectMapper;
    private CnfQiniuEntity cnfQiniuEntity = null;

    @Override
    public CnfQiniuEntity getCnf() {
        try {
            if (this.cnfQiniuEntity == null) {
                this.cnfQiniuEntity = getCnf(CnfQiniuEntity.class);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
        return this.cnfQiniuEntity;
    }

    @Override
    public void writeToStorage(String file, String key) {
        CnfQiniuEntity cnfQiniuEntity = getCnf();
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(cnfQiniuEntity.getAccessKey(), cnfQiniuEntity.getSecretKey());
        String upToken = auth.uploadToken(cnfQiniuEntity.getBucket());
        try {
            uploadManager.put(file, key, upToken);
//            Response response = uploadManager.put(file, key, upToken);
//            //解析上传成功的结果
//            DefaultPutRet putRet = objectMapper.readValue(response.bodyString(), DefaultPutRet.class);
//            System.out.println(putRet.key);
//            System.out.println(putRet.hash);
        } catch (QiniuException e) {
            log.error(e.getMessage(), e);
            throw new ApiException(e, "上传文件失败");
        } catch (Exception e) {
            throw new ApiException(e, "上传文件失败");
        }

    }

    @Override
    public String getUrl(String key) {
        String url = null;
        if (this.getCnf().getDomain().endsWith("/")) {
            url = this.getCnf().getDomain() + key;
        } else {
            url = this.getCnf().getDomain() + "/" + key;
        }
        return url;
    }

    @Override
    public void delete(String key) {
        CnfQiniuEntity cnfQiniuEntity = getCnf();
//构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        Auth auth = Auth.create(cnfQiniuEntity.getAccessKey(), cnfQiniuEntity.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(cnfQiniuEntity.getBucket(), key);
        } catch (QiniuException ex) {
            throw new ApiException(ex, "删除文件失败");
        }

    }

    @Override
    protected String getKey() {
        return getParentKey() + ".qiniu";
    }

    @Override
    protected void onCnfChange() {
        this.cnfQiniuEntity = null;
        this.getCnf();
    }
}
