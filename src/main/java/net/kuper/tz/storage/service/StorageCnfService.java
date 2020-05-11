package net.kuper.tz.storage.service;

public interface StorageCnfService<T> {
    /**
     * 获取配置
     *
     * @return
     */
    T getCnf();

    /**
     * 修改配置
     *
     * @param t
     */
    void updateCnf(T t);
}
