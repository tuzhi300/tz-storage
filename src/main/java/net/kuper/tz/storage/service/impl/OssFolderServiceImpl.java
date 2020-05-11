package net.kuper.tz.storage.service.impl;

import com.github.pagehelper.PageHelper;
import net.kuper.tz.core.constant.UserType;
import net.kuper.tz.core.controller.exception.ApiException;
import net.kuper.tz.core.mybatis.Pagination;
import net.kuper.tz.core.utils.RegexUtils;
import net.kuper.tz.core.utils.StringUtils;
import net.kuper.tz.storage.dao.OssFolderDao;
import net.kuper.tz.storage.entity.OssFolderEntity;
import net.kuper.tz.storage.entity.OssFolderQueryEntity;
import net.kuper.tz.storage.entity.OssFolderUpdateEntity;
import net.kuper.tz.storage.service.OssFolderService;
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
@Service("ossFolderService")
public class OssFolderServiceImpl implements OssFolderService {

    @Autowired
    private OssFolderDao ossFolderDao;

    @Override
    public Pagination<OssFolderEntity> queryList(OssFolderQueryEntity ossFolderQueryEntity) {
        PageHelper.startPage(ossFolderQueryEntity.getPage(), ossFolderQueryEntity.getPageSize());
        List<OssFolderEntity> ossFolderList = ossFolderDao.queryList(ossFolderQueryEntity);
        return new Pagination<OssFolderEntity>(ossFolderList);
    }

    @Override
    public OssFolderEntity queryObject(String id) {
        return ossFolderDao.queryObject(id);
    }

    @Override
    public OssFolderEntity save(OssFolderUpdateEntity ossFolderUpdateEntity) {
        ossFolderDao.save(ossFolderUpdateEntity);
        return ossFolderDao.queryObject(ossFolderUpdateEntity.getId());
    }

    @Override
    public OssFolderEntity queryByPath(String userId, UserType userType, String path, boolean autoMake, String createUserId, UserType createUserType) {
        String regx = "^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$";
        if (StringUtils.isEmpty(userId)) {
            throw new ApiException("用户Id不能为空");
        } else if (userType == null) {
            throw new ApiException("用户类型不能为空");
        } else if (StringUtils.isEmpty(path)) {
            throw new ApiException("路径不能为空不能为空");
        } else if (autoMake) {
            if (StringUtils.isEmpty(createUserId)) {
                throw new ApiException("创建用户Id不能为空");
            } else if (userType == null) {
                throw new ApiException("创建用户类型不能为空");
            }
        }
        String all = path;
        if (path.startsWith("/")) {
            all = path.substring(1);
        }
        String[] names = all.split("/");
        for (String name : names) {
            if (StringUtils.isEmpty(name)) {
                throw new ApiException("文件夹名称不能是空");
            } else if (name.length() > 50) {
                throw new ApiException("文件夹名称不能超过50个字符");
            } else if (!RegexUtils.isMatch(regx, name)) {
                throw new ApiException("文件夹名称格式错误，请输入字母，数字，中文或下划线");
            }
        }
        String parentId = null;
        int i = 0;
        do {
            String name = names[i];
            OssFolderEntity ossFolderEntity = ossFolderDao.queryByPath(userId, userType.value, name, parentId);
            if (ossFolderEntity != null) {
                parentId = ossFolderEntity.getId();
            } else if (autoMake) {
                OssFolderUpdateEntity updateEntity = new OssFolderUpdateEntity();
                updateEntity.setCreateUserId(createUserId);
                updateEntity.setCreateUserType(createUserType.value);
                updateEntity.setUserId(userId);
                updateEntity.setUserType(userType.value);
                updateEntity.setFolder(name);
                updateEntity.setFolderType("system");
                updateEntity.setParentId(parentId);
                updateEntity.setRightType(2);
                ossFolderDao.save(updateEntity);
                parentId = updateEntity.getId();
            } else {
                throw new ApiException("文件夹不存在");
            }
            i++;
        } while (i < names.length);
        return ossFolderDao.queryObject(parentId);
    }

    @Override
    public void update(OssFolderUpdateEntity ossFolderUpdateEntity) {
        ossFolderDao.update(ossFolderUpdateEntity);
    }

    @Override
    public void delete(String id) {
        ossFolderDao.delete(id);
    }

    @Override
    public void deleteBatch(String[] ids) {
        ossFolderDao.deleteBatch(ids);
    }
}
