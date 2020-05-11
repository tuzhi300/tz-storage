package net.kuper.tz.storage.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.DelFileRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;
import net.kuper.tz.core.controller.exception.ApiException;
import net.kuper.tz.core.utils.StringUtils;
import net.kuper.tz.storage.entity.CnfTencentEntity;
import net.kuper.tz.storage.service.StorageService;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("tencentStorage")
public class TencentStorageCnf extends BaseStorageCnf<CnfTencentEntity> implements StorageService {

    private COSClient client;
    private CnfTencentEntity cnfTencentEntity;

    @Override
    public CnfTencentEntity getCnf() {
        try {
            if (this.cnfTencentEntity == null) {
                this.cnfTencentEntity = getCnf(CnfTencentEntity.class);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
        return this.cnfTencentEntity;
    }


    private COSClient getClient() {
        CnfTencentEntity cnfEntity = getCnf();
        if (cnfEntity != null && cnfEntity.getAppId() != null
                && !StringUtils.isEmpty(cnfEntity.getSecretId())
                && !StringUtils.isEmpty(cnfEntity.getSecretKey())) {
            Credentials credentials = new Credentials(Integer.valueOf(cnfEntity.getAppId()), cnfEntity.getSecretId(), cnfEntity.getSecretKey());
            //初始化客户端配置
            ClientConfig clientConfig = new ClientConfig();
            //设置bucket所在的区域，华南：gz 华北：tj 华东：sh
            clientConfig.setRegion(cnfEntity.getRegion());
            client = new COSClient(clientConfig, credentials);
        }
        return client;
    }

    @Override
    public void writeToStorage(String file, String key) {
        //上传到腾讯云
        UploadFileRequest request = new UploadFileRequest(cnfTencentEntity.getBucket(), key, file);
        String response = getClient().uploadFile(request);
        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.getInt("code") != 0) {
            throw new ApiException("文件上传失败，" + jsonObject.getString("message"));
        }
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
        DelFileRequest request = new DelFileRequest(cnfTencentEntity.getBucket(), key);
        String response = getClient().delFile(request);
        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.getInt("code") != 0) {
            throw new ApiException("删除文件失败，" + jsonObject.getString("message"));
        }
    }

    @Override
    protected String getKey() {
        return getParentKey() + ".tencent";
    }

    @Override
    protected void onCnfChange() {
        this.client = null;
        this.cnfTencentEntity = null;
        this.getClient();
    }
}
