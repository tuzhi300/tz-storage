package net.kuper.tz.storage.service;

import net.kuper.tz.core.constant.UserType;
import net.kuper.tz.core.mybatis.Pagination;
import net.kuper.tz.storage.entity.FileEntity;
import net.kuper.tz.storage.entity.OssEntity;
import net.kuper.tz.storage.entity.OssQueryEntity;
import net.kuper.tz.storage.entity.OssUpdateEntity;
import net.kuper.tz.storage.service.impl.FileOperationAdapter;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-30 11:55:49
 */
public interface OssService {

    /**
     * 分页查询:文件上传
     *
     * @param ossQueryEntity 分页查询参数
     * @return Pagination
     */
    Pagination<OssEntity> queryList(OssQueryEntity ossQueryEntity);

    /**
     * 文件上传详情查询
     *
     * @param id
     * @return 文件上传
     */
    OssEntity queryObject(String id);

    /**
     * 获取文件信息
     *
     * @param id
     * @return
     */
    FileEntity getFile(String id);

    /**
     * 文件上传
     *
     * @param multipartFile 上传的文件内容
     * @param adapter       自定义文件处理
     * @param type          存储方式
     * @param folder        物理目录
     * @param folderId      拥有者文件夹Id
     * @param createUserId  创建用户
     * @param userType      创建用户的类型
     * @return
     */
    OssEntity save(MultipartFile multipartFile, FileOperationAdapter adapter, StorageType type, String folder, String folderId, String createUserId, UserType userType);

    /**
     * 修改 文件上传
     *
     * @param ossUpdateEntity
     * @return
     */
    void update(OssUpdateEntity ossUpdateEntity);

    /**
     * 文件上传单条根据删除
     *
     * @param id
     * @return
     */
    void delete(String id);

    /**
     * 文件上传批量删除
     *
     * @param ids
     * @return
     */
    void deleteBatch(String[] ids);

}

