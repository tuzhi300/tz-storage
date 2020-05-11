package net.kuper.tz.storage.service;

public enum StorageType {
    LOCALE(0), QINIU(1), TENCENT(2), ALIYUN(3);

    StorageType(int value) {
        this.value = value;
    }

    public int value;
}
