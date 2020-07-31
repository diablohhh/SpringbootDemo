package com.example.framework.server;

import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/3 10:16
 * @Text: 服务类基类
 **/
public interface BaseServer<T> {
    /**
     * 根据systemid返回内容
     *
     * @param systemid
     * @return
     */
    T getBySystemid(String systemid);

    /**
     * 根据 某属性返回 列表
     *
     * @param fieldName
     * @param fieldValue
     * @return
     */
    List<T> getByFieldName(String fieldName, Object fieldValue);

    /**
     * 根据属性返回统计数据
     *
     * @param fieldName
     * @param fieldValue
     * @return
     */
    int getCountByFieldName(String fieldName, Object fieldValue);

    /**
     * 分页查询
     *
     * @param fieldName
     * @param fieldValue
     * @param page
     * @param size
     * @return
     */
    List<T> getByFieldName(String fieldName, Object fieldValue, int page, int size);

    /**
     * 排序查询
     *
     * @param fieldName
     * @param fieldValue
     * @param orderField
     * @param direction
     * @return
     */
    List<T> getByFieldName(String fieldName, Object fieldValue, String orderField, Sort.Direction direction);

    /**
     * 分页排序查询
     *
     * @param fieldName
     * @param fieldValue
     * @param page
     * @param size
     * @param orderField
     * @param direction
     * @return
     */
    List<T> getByFieldName(String fieldName, Object fieldValue, int page, int size, String orderField, Sort.Direction direction);

    /**
     * 删除实体
     *
     * @param entity
     */
    void remove(T entity);

    /**
     * 删除实体
     *
     * @param systemid
     */
    void remove(String systemid);

    /**
     * 逻辑删除
     *
     * @param ids
     */
    void logicRemove(String... ids);

    /**
     * 修改实体
     *
     * @param entity
     * @return
     */
    T update(T entity);

    /**
     * 保存实体
     *
     * @param entity
     * @return
     */
    T save(T entity);

    /**
     * 批量保存
     *
     * @param entity
     * @return
     */
    List<T> batchSave(List<T> entity);

    /**
     * 实体是否存在
     *
     * @param systemid
     * @return
     */
    boolean isExistEntity(String systemid);

    /**
     * 实体是否存在
     *
     * @param entity
     * @return
     */
    boolean isExistEntity(T entity);

    /**
     * sql查询
     *
     * @param nativeSql
     * @param args
     * @param returnClazz
     * @return
     */
    List<T> queryForList(String nativeSql, Object[] args, Class<T> returnClazz);

    /**
     * sql查询
     *
     * @param nativeSql
     * @param args
     * @return
     */
    List<Map<String, Object>> queryForList(String nativeSql, Object[] args);
}
