package net.kuper.tz.storage.entity;

import lombok.Getter;
import lombok.Setter;
import net.kuper.tz.storage.service.StorageType;
import retrofit2.http.GET;

@Getter
@Setter
public class FileEntity {
    private StorageType storageType;
    private String key;
    private String url;
}
