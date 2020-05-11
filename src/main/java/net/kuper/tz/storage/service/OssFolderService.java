package net.kuper.tz.storage.service;

import net.kuper.tz.core.constant.UserType;
import net.kuper.tz.core.mybatis.Pagination;
import net.kuper.tz.storage.entity.OssFolderEntity;
import net.kuper.tz.storage.entity.OssFolderQueryEntity;
import net.kuper.tz.storage.entity.OssFolderUpdateEntity;

/**
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-30 11:55:49
 */
public interface OssFolderService {

    /**
     * 分页查询:
     *
     * @param ossFolderQueryEntity 分页查询参数
     * @return Pagination
     */
    Pagination<OssFolderEntity> queryList(OssFolderQueryEntity ossFolderQueryEntity);

    /**
     * 详情查询
     *
     * @param id
     * @return
     */
    OssFolderEntity queryObject(String id);

    /**
     * 新增：
     *
     * @param ossFolderUpdateEntity
     * @return
     */
    OssFolderEntity save(OssFolderUpdateEntity ossFolderUpdateEntity);

    /**
     * 获取用户用户某路径的文件信息
     *
     * @param userId
     * @param userType
     * @param path
     * @param autoMake 没有时自动创建
     * @return
     */
    OssFolderEntity queryByPath(String userId, UserType userType, String path, boolean autoMake, String createUserId, UserType createUserType);

    /**
     * 修改
     *
     * @param ossFolderUpdateEntity
     * @return
     */
    void update(OssFolderUpdateEntity ossFolderUpdateEntity);

    /**
     * 单条根据删除
     *
     * @param id
     * @return
     */
    void delete(String id);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    void deleteBatch(String[] ids);

}

