package net.kuper.tz.storage.dao;

import net.kuper.tz.storage.entity.OssFileEntity;
import net.kuper.tz.storage.entity.OssFileQueryEntity;
import net.kuper.tz.storage.entity.OssFileUpdateEntity;

import java.util.List;

/**
 * 
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-30 11:55:49
 */
public interface OssFileDao {

    /**
     * 详情
     *
     * @param id
     * @return
     */
    OssFileEntity queryObject(String id);

    /**
     *  列表
     *
     * @param ossFileQueryEntity
     * @return
     */
    List<OssFileEntity> queryList(OssFileQueryEntity ossFileQueryEntity);

    /**
     * 新增
     *
     * @param ossFileUpdateEntity
     */
    void save(OssFileUpdateEntity ossFileUpdateEntity);

    /**
     *  修改
     *
     * @param ossFileUpdateEntity
     */
    void update(OssFileUpdateEntity ossFileUpdateEntity);

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
