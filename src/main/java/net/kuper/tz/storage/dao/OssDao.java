package net.kuper.tz.storage.dao;

import net.kuper.tz.storage.entity.OssEntity;
import net.kuper.tz.storage.entity.OssQueryEntity;
import net.kuper.tz.storage.entity.OssUpdateEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文件上传
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-30 11:55:49
 */
public interface OssDao {

    /**
     * 文件上传详情
     *
     * @param id
     * @return
     */
    OssEntity queryObject(String id);

    /**
     * 文件上传列表
     *
     * @param ossQueryEntity
     * @return
     */
    List<OssEntity> queryList(OssQueryEntity ossQueryEntity);

    /**
     * 根据文件签名信息查询文件是否存在
     *
     * @param md5
     * @param sha1
     * @return
     */
    OssEntity queryBySign(@Param("md5") String md5, @Param("sha1") String sha1, @Param("planform") Integer planform, @Param("folder") String folder);

    /**
     * 根据文件签名信息查询文件是否存在
     *
     * @param originalMd5
     * @param originalSha1
     * @return
     */
    OssEntity queryByOriginalSign(@Param("originalMd5") String originalMd5, @Param("originalSha1") String originalSha1, @Param("planform") Integer planform, @Param("folder") String folder);

    /**
     * 文件上传新增
     *
     * @param ossUpdateEntity
     */
    void save(OssUpdateEntity ossUpdateEntity);

    /**
     * 文件上传修改
     *
     * @param ossUpdateEntity
     */
    void update(OssUpdateEntity ossUpdateEntity);

    /**
     * 文件上传单条删除
     *
     * @param id
     */
    void delete(String id);

    /**
     * 文件上传批量删除
     *
     * @param ids
     */
    void deleteBatch(String[] ids);
}
