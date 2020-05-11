package net.kuper.tz.storage.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.kuper.tz.core.cache.Cache;
import net.kuper.tz.core.constant.Time;
import net.kuper.tz.core.constant.UserType;
import net.kuper.tz.core.controller.Res;
import net.kuper.tz.core.controller.auth.IgnoreAuth;
import net.kuper.tz.core.controller.auth.UserId;
import net.kuper.tz.core.controller.exception.ApiException;
import net.kuper.tz.core.controller.log.Log;
import net.kuper.tz.core.controller.log.LogType;
import net.kuper.tz.core.properties.AdminProperties;
import net.kuper.tz.core.properties.StorageProperties;
import net.kuper.tz.core.validator.ValidatorUtils;
import net.kuper.tz.core.validator.group.UpdateGroup;
import net.kuper.tz.storage.entity.*;
import net.kuper.tz.storage.service.OssFolderService;
import net.kuper.tz.storage.service.OssService;
import net.kuper.tz.storage.service.StorageCnfService;
import net.kuper.tz.storage.service.StorageType;
import net.kuper.tz.storage.service.impl.OssTool;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 文件上传
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-30 11:55:49
 */
@Api(value = "文件上传", description = "文件上传")
@RestController
@RequestMapping("storage")
public class StorageController {

    @Autowired
    private OssService ossService;
    @Autowired
    private OssFolderService ossFolderService;
    @Autowired
    private OssTool tool;
    @Autowired
    private AdminProperties adminProperties;
    @Autowired
    @Qualifier("aliyunStorage")
    private StorageCnfService<CnfAliyunEntity> aliCnf;
    @Autowired
    @Qualifier("tencentStorage")
    private StorageCnfService<CnfTencentEntity> tencentCnf;
    @Autowired
    @Qualifier("qiniuStorage")
    private StorageCnfService<CnfQiniuEntity> qiniuCnf;

    private final String path = "oss";

    @Log(type = LogType.QUERY, value = "腾讯云存储配置查询")
    @ApiOperation("腾讯云存储配置查询")
    @RequiresPermissions("oss:storage:cnf:tencent:detail")
    @ResponseBody
    @GetMapping("/cnf/tencent")
    public Res<CnfTencentEntity> cnfTencent() {
        return Res.ok(tencentCnf.getCnf());
    }

    @Log(type = LogType.UPDATE, value = "腾讯云存储配置修改")
    @ApiOperation("腾讯云存储配置修改")
    @RequiresPermissions("oss:storage:cnf:tencent:update")
    @ResponseBody
    @PutMapping("/cnf/tencent")
    public Res cnfTencent(@RequestBody CnfTencentEntity cnfTencentEntity) {
        ValidatorUtils.validateEntity(cnfTencentEntity, UpdateGroup.class);
        tencentCnf.updateCnf(cnfTencentEntity);
        return Res.ok();
    }


    @Log(type = LogType.QUERY, value = "阿里云存储配置查询")
    @ApiOperation("阿里云存储配置查询")
    @RequiresPermissions("oss:storage:cnf:aliyun:detail")
    @ResponseBody
    @GetMapping("/cnf/aliyun")
    public Res<CnfAliyunEntity> cnfAliyun() {
        return Res.ok(aliCnf.getCnf());
    }

    @Log(type = LogType.UPDATE, value = "阿里云存储配置修改")
    @ApiOperation("阿里云存储配置修改")
    @RequiresPermissions("oss:storage:cnf:aliyun:update")
    @ResponseBody
    @PutMapping("/cnf/aliyun")
    public Res cnfAliyun(@RequestBody CnfAliyunEntity aliyunEntity) {
        ValidatorUtils.validateEntity(aliyunEntity, UpdateGroup.class);
        aliCnf.updateCnf(aliyunEntity);
        return Res.ok();
    }


    @Log(type = LogType.QUERY, value = "七牛云存储配置查询")
    @ApiOperation("七牛云存储配置查询")
    @RequiresPermissions("oss:storage:cnf:qiniu:detail")
    @ResponseBody
    @GetMapping("/cnf/qiniu")
    public Res<CnfQiniuEntity> cnfQiniu() {
        return Res.ok(qiniuCnf.getCnf());
    }

    @Log(type = LogType.UPDATE, value = "七牛云存储配置修改")
    @ApiOperation("七牛云存储配置修改")
    @RequiresPermissions("oss:storage:cnf:qiniu:update")
    @ResponseBody
    @PutMapping("/cnf/qiniu")
    public Res cnfQiniu(@RequestBody CnfQiniuEntity qiniuEntity) {
        ValidatorUtils.validateEntity(qiniuEntity, UpdateGroup.class);
        qiniuCnf.updateCnf(qiniuEntity);
        return Res.ok();
    }

    /**
     * 上传文件
     *
     * @param file        文件
     * @param userId      当前用户
     * @param storageType 存储方式
     * @return
     */
    private OssEntity upload(MultipartFile file, String userId, StorageType storageType) {
        if (file.isEmpty()) {
            throw new ApiException("上传文件不能为空");
        }
        OssFolderEntity folderEntity = ossFolderService.queryByPath(adminProperties.getSystemUserId(), UserType.ADMIN, path, true, userId, UserType.ADMIN);
        String folderId = folderEntity.getId();
        OssEntity ossEntity = ossService.save(file, null, storageType, "oss", folderId, userId, UserType.ADMIN);
        return ossEntity;
    }

    @Log(type = LogType.UPLOAD, value = "上传腾讯云")
    @ApiOperation("上传腾讯云")
    @RequiresPermissions("oss:storage:upload:tencent")
    @ResponseBody
    @PostMapping("/upload/tencent")
    public Res<OssEntity> tencentUpload(@RequestParam(value = "file") MultipartFile file, @ApiIgnore @UserId String userId) {
        OssEntity ossEntity = upload(file, userId, StorageType.TENCENT);
        return Res.ok(ossEntity);
    }

    @Log(type = LogType.UPLOAD, value = "上传阿里云")
    @ApiOperation("上传阿里云")
    @RequiresPermissions("oss:storage:upload:aliyun")
    @ResponseBody
    @PostMapping("/upload/aliyun")
    public Res<OssEntity> aliyunUpload(@RequestParam(value = "file") MultipartFile file, @ApiIgnore @UserId String userId) {
        OssEntity ossEntity = upload(file, userId, StorageType.ALIYUN);
        return Res.ok(ossEntity);
    }

    @Log(type = LogType.UPLOAD, value = "上传七牛云")
    @ApiOperation("上传七牛云")
    @RequiresPermissions("oss:storage:upload:qiniu")
    @ResponseBody
    @PostMapping("/upload/qiniu")
    public Res<OssEntity> qiniuUpload(@RequestParam(value = "file") MultipartFile file, @ApiIgnore @UserId String userId) {
        OssEntity ossEntity = upload(file, userId, StorageType.ALIYUN);
        return Res.ok(ossEntity);
    }

    @Log(type = LogType.UPLOAD, value = "上传本地服务")
    @ApiOperation("上传本地服务")
    @RequiresPermissions("oss:storage:upload:local")
    @ResponseBody
    @PostMapping("/upload/local")
    public Res<OssEntity> localUpload(@RequestParam(value = "file") MultipartFile file, @ApiIgnore @UserId String userId) {
        OssEntity ossEntity = upload(file, userId, StorageType.LOCALE);
        return Res.ok(ossEntity);
    }

    @IgnoreAuth
    @Log(type = LogType.DOWNLOAD, value = "访问文件资源")
    @ApiOperation("访问文件资源，仅在Oss管理时可使用该接口，其他地方请单独开发接口")
//    @RequiresPermissions("oss:storage:file:down")
    @GetMapping("/file/{id}")
    public void getFile(@PathVariable("id") String ossId, HttpServletResponse response) {
        try {
            FileEntity fileEntity = ossService.getFile(ossId);
            switch (fileEntity.getStorageType()) {
                case LOCALE:
                    File file = new File(fileEntity.getUrl());
                    InputStream in = new FileInputStream(file);
                    OutputStream out = response.getOutputStream();
                    int len;
                    byte[] buffer = new byte[1024];
                    while ((len = in.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    IOUtils.closeQuietly(out);
                    IOUtils.closeQuietly(in);
                    break;
                case TENCENT:
                case QINIU:
                case ALIYUN:
                    response.sendRedirect(fileEntity.getUrl());
                    break;
            }
        } catch (IOException e) {
            throw new ApiException(e, "文件访问失败");
        }
    }
}
