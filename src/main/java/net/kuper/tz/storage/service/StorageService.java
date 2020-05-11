package net.kuper.tz.storage.service;

public interface StorageService {

    /**
     * 保存文件
     *
     * @param file
     */
    void writeToStorage(String file,String key);

    /**
     * 获取文件
     *
     * @param key
     */
    String getUrl(String key);

    /**
     * @param key
     */
    void delete(String key);


}
