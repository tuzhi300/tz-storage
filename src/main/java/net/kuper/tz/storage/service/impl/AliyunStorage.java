package net.kuper.tz.storage.service.impl;

import com.aliyun.oss.OSSClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.kuper.tz.core.utils.StringUtils;
import net.kuper.tz.storage.entity.CnfAliyunEntity;
import net.kuper.tz.storage.service.StorageService;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 阿里云存储
 */
@Service("aliyunStorage")
public class AliyunStorage extends BaseStorageCnf<CnfAliyunEntity> implements StorageService {

    private OSSClient client;
    private CnfAliyunEntity cnfAliyunEntity;

    private OSSClient getClient() {
        if (client == null) {
            CnfAliyunEntity cnfEntity = getCnf();
            if (cnfEntity != null && !StringUtils.isEmpty(cnfEntity.getEndPoint())
                    && !StringUtils.isEmpty(cnfEntity.getAccessKeyId())
                    && !StringUtils.isEmpty(cnfEntity.getAccessKeySecret())) {
                client = new OSSClient(cnfEntity.getEndPoint(), cnfEntity.getAccessKeyId(), cnfEntity.getAccessKeySecret());
            }
        }
        return client;
    }

    @Override
    public CnfAliyunEntity getCnf() {
        try {
            if (this.cnfAliyunEntity == null) {
                this.cnfAliyunEntity = getCnf(CnfAliyunEntity.class);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
        return this.cnfAliyunEntity;
    }

    @Override
    protected String getKey() {
        return getParentKey() + ".aliyun";
    }

    @Override
    protected void onCnfChange() {
        this.cnfAliyunEntity = null;
        this.client = null;
        this.getClient();
    }

    @Override
    public void writeToStorage(String file, String key) {
        if (key.startsWith(File.separator)) {
            key = key.substring(1);
        }
        getClient().putObject(getCnf().getBucket(), key, new File(file));
    }


    @Override
    public String getUrl(String key) {
        String url = null;
        if (this.getCnf().getDomain().endsWith("/")) {
            url = this.getCnf().getDomain() + key;
        } else {
            url = this.getCnf().getDomain() + "/" + key;
        }
        return url;
    }

    @Override
    public void delete(String key) {
        if (key.startsWith(File.separator)) {
            key = key.substring(1);
        }
        getClient().deleteObject(getCnf().getBucket(), key);
    }
}
