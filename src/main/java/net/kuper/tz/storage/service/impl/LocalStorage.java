package net.kuper.tz.storage.service.impl;

import net.kuper.tz.core.properties.StorageProperties;
import net.kuper.tz.core.utils.FileUtils;
import net.kuper.tz.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service("localStorage")
public class LocalStorage implements StorageService {

    @Autowired
    private StorageProperties storageProperties;


    @Override
    public void writeToStorage(String file, String key) {
        String path = storageProperties.getLocalFilePath() + "/" + key;
        if (key.startsWith("/")) {
            path = storageProperties.getLocalFilePath() + key;
        }
        File target = new File(path);
        if (!target.getParentFile().exists()) {
            target.getParentFile().mkdirs();
        }
        FileUtils.moveFile(file, path);
    }

    @Override
    public String getUrl(String key) {
        String url = null;
        if (storageProperties.getLocalFilePath().endsWith("/")) {
            url = storageProperties.getLocalFilePath() + key;
        } else {
            url = storageProperties.getLocalFilePath() + "/" + key;
        }
        return url;
    }

    @Override
    public void delete(String key) {
        String path = storageProperties.getLocalFilePath() + File.separator + key;
        if (key.startsWith("/")) {
            path = storageProperties.getLocalFilePath() + key;
        }
        FileUtils.deleteFile(path);
    }
}
