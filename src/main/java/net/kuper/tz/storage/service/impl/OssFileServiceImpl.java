package net.kuper.tz.storage.service.impl;

import com.github.pagehelper.PageHelper;
import net.kuper.tz.core.mybatis.Pagination;
import net.kuper.tz.storage.dao.OssFileDao;
import net.kuper.tz.storage.entity.OssFileEntity;
import net.kuper.tz.storage.entity.OssFileQueryEntity;
import net.kuper.tz.storage.entity.OssFileUpdateEntity;
import net.kuper.tz.storage.service.OssFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service实现类
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-30 11:55:49
 */
@Service("ossFileService" )
public class OssFileServiceImpl implements OssFileService {

    @Autowired
    private OssFileDao ossFileDao;

    @Override
    public Pagination<OssFileEntity> queryList(OssFileQueryEntity ossFileQueryEntity) {
        PageHelper.startPage(ossFileQueryEntity.getPage(), ossFileQueryEntity.getPageSize());
        List<OssFileEntity> ossFileList = ossFileDao.queryList(ossFileQueryEntity);
        return new Pagination<OssFileEntity>(ossFileList);
    }

    @Override
    public OssFileEntity queryObject(String id) {
        return ossFileDao.queryObject(id);
    }

    @Override
    public OssFileEntity save(OssFileUpdateEntity ossFileUpdateEntity) {
        ossFileDao.save(ossFileUpdateEntity);
        return ossFileDao.queryObject(ossFileUpdateEntity.getId());
    }

    @Override
    public void update(OssFileUpdateEntity ossFileUpdateEntity) {
        ossFileDao.update(ossFileUpdateEntity);
    }

    @Override
    public void delete(String id) {
        ossFileDao.delete(id);
    }

    @Override
    public void deleteBatch(String[] ids) {
        ossFileDao.deleteBatch(ids);
    }
}
