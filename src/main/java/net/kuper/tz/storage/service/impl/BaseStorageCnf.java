package net.kuper.tz.storage.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.kuper.tz.common.dao.MacroDao;
import net.kuper.tz.common.entity.MacroEntity;
import net.kuper.tz.common.entity.MacroUpdateEntity;
import net.kuper.tz.core.controller.exception.ApiException;
import net.kuper.tz.core.utils.StringUtils;
import net.kuper.tz.storage.service.StorageCnfService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


@Slf4j
public abstract class BaseStorageCnf<T> implements StorageCnfService<T> {

    @Autowired
    private MacroDao macroDao;
    @Autowired
    private ObjectMapper objectMapper;


    protected T getCnf(Class<T> clazz) throws IllegalAccessException, InstantiationException, JsonProcessingException {
        T cnfEntity = null;
        if (!StringUtils.isEmpty(getKey())) {
            MacroEntity macroEntity = macroDao.queryObjectByKey(getKey());
            if (macroEntity == null) {
                MacroUpdateEntity updateEntity = new MacroUpdateEntity();
                updateEntity.setType(1);
                updateEntity.setOrderNum(1);
                updateEntity.setKey(getKey());
                updateEntity.setDisplayName(getKey());
                updateEntity.setValue(objectMapper.writeValueAsString(clazz.newInstance()));
                updateEntity.setParentKey(getParentKey());
                macroDao.save(updateEntity);
            }
            if (macroEntity != null && !StringUtils.isEmpty(macroEntity.getValue())) {
                try {
                    cnfEntity = objectMapper.readValue(macroEntity.getValue(), clazz);
                } catch (IOException e) {
                    log.info(e.getMessage(), e);
                    throw new ApiException(e, "获取配置失败");
                }
            }
        }
        return cnfEntity;
    }

    @Override
    public void updateCnf(T t) {
        try {
            MacroEntity macroEntity = macroDao.queryObjectByKey(getKey());
            if (macroEntity != null) {
                MacroUpdateEntity updateEntity = new MacroUpdateEntity();
                updateEntity.setId(macroEntity.getId());
                updateEntity.setKey(getKey());
                updateEntity.setDisplayName(getKey());
                updateEntity.setValue(objectMapper.writeValueAsString(t));
                macroDao.update(updateEntity);
            } else {
                MacroUpdateEntity updateEntity = new MacroUpdateEntity();
                updateEntity.setType(1);
                updateEntity.setOrderNum(1);
                updateEntity.setKey(getKey());
                updateEntity.setDisplayName(getKey());
                updateEntity.setId(macroEntity.getId());
                updateEntity.setValue(objectMapper.writeValueAsString(t));
                macroDao.save(updateEntity);
            }
            this.onCnfChange();
        } catch (JsonProcessingException e) {
            log.info(e.getMessage(), e);
            throw new ApiException(e, "修改配置失败");
        }
    }

    protected String getParentKey() {
        return "cloud_storage";
    }

    protected abstract String getKey();

    protected abstract void onCnfChange();

}
