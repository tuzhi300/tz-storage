package net.kuper.tz.storage.dao;

import net.kuper.tz.storage.entity.OssFolderEntity;
import net.kuper.tz.storage.entity.OssFolderQueryEntity;
import net.kuper.tz.storage.entity.OssFolderUpdateEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-30 11:55:49
 */
public interface OssFolderDao {

    /**
     * 详情
     *
     * @param id
     * @return
     */
    OssFolderEntity queryObject(String id);

    /**
     * 列表
     *
     * @param ossFolderQueryEntity
     * @return
     */
    List<OssFolderEntity> queryList(OssFolderQueryEntity ossFolderQueryEntity);

    /**
     * 按路径查询文件夹信息
     *
     * @param userId
     * @param userType
     * @param folder
     * @param parentId
     * @return
     */
    OssFolderEntity queryByPath(@Param("userId") String userId, @Param("userType") Integer userType, @Param("folder") String folder, @Param("parentId") String parentId);

    /**
     * 新增
     *
     * @param ossFolderUpdateEntity
     */
    void save(OssFolderUpdateEntity ossFolderUpdateEntity);

    /**
     * 修改
     *
     * @param ossFolderUpdateEntity
     */
    void update(OssFolderUpdateEntity ossFolderUpdateEntity);

    /**
     * 单条删除
     *
     * @param id
     */
    void delete(String id);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteBatch(String[] ids);
}
