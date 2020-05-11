package net.kuper.tz.storage.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileAttEntity {
    private String contentType;
    private String name;
    private String originalName;
    private String md5;
    private String sha1;
}
