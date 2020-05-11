package net.kuper.tz.storage.service;

import net.kuper.tz.core.mybatis.Pagination;
import net.kuper.tz.storage.entity.OssFileEntity;
import net.kuper.tz.storage.entity.OssFileQueryEntity;
import net.kuper.tz.storage.entity.OssFileUpdateEntity;

/**
 * 
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-30 11:55:49
 */
public interface OssFileService {

    /**
     * 分页查询:
     *
     * @param ossFileQueryEntity 分页查询参数
     * @return Pagination
     */
    Pagination<OssFileEntity> queryList(OssFileQueryEntity ossFileQueryEntity);

    /**
      * 详情查询
      *
      * @param id
      * @return 
      */
    OssFileEntity queryObject(String id);

    /**
     * 新增：
     *
     * @param ossFileUpdateEntity
     * @return 
     */
     OssFileEntity save(OssFileUpdateEntity ossFileUpdateEntity);

    /**
     * 修改 
     *
     * @param ossFileUpdateEntity
     * @return
     */
    void update(OssFileUpdateEntity ossFileUpdateEntity);

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

