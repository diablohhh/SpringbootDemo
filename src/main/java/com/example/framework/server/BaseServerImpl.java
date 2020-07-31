package com.example.framework.server;

import com.example.framework.entity.BaseEntity;
import com.example.framework.repository.DefaultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * @Author ZhangQiangLong
 * @Date 2020/7/3 10:43
 * @Text: 服务层基类实现类
 **/
public class BaseServerImpl<T> implements  BaseServer<T>{


    private static final Logger logger = LoggerFactory.getLogger(BaseServerImpl.class);
    private final DefaultRepository<T, String> defaultRepository;

    public BaseServerImpl(DefaultRepository<T, String> defaultRepository) {
        this.defaultRepository = defaultRepository;
    }

    @Override
    public T getBySystemid(String systemid) {
        return defaultRepository.getBySystemid(systemid);
    }

    @Override
    public List getByFieldName(String fieldName, Object fieldValue) {
        return defaultRepository.getByFieldName(fieldName,fieldValue);
    }

    @Override
    public int getCountByFieldName(String fieldName, Object fieldValue) {
        return defaultRepository.getCountByFieldName(fieldName, fieldValue);
    }

    @Override
    public List getByFieldName(String fieldName, Object fieldValue, int page, int size) {
        return defaultRepository.getByFieldName(fieldName, fieldValue, page, size);
    }

    @Override
    public List getByFieldName(String fieldName, Object fieldValue, String orderField, Sort.Direction direction) {
        return defaultRepository.getByFieldName(fieldName, fieldValue, orderField, direction);
    }

    @Override
    public List getByFieldName(String fieldName, Object fieldValue, int page, int size, String orderField, Sort.Direction direction) {
        return defaultRepository.getByFieldName(fieldName, fieldValue, page, size, orderField, direction);
    }

    @Override
    public void remove(T entity) {
        defaultRepository.delete(entity);
    }

    @Override
    public void remove(String systemid) {
        defaultRepository.deleteById(systemid);
    }

    @Override
    public void logicRemove(String... ids) {
        defaultRepository.logicRemove(ids);
    }

    @Override
    public T update(T entity) {
        return defaultRepository.save(entity);
    }

    @Override
    public T save(T entity) {
        return defaultRepository.save(entity);
    }

    @Override
    public List<T> batchSave(List<T> entityList) {
        return defaultRepository.saveAll(entityList);
    }

    @Override
    public boolean isExistEntity(String systemid) {
        return defaultRepository.existsById(systemid);
    }

    @Override
    public boolean isExistEntity(Object entity) {
        BaseEntity baseEntity = (BaseEntity) entity;
        return defaultRepository.existsById(baseEntity.getSystemid());
    }

    @Override
    public List queryForList(String nativeSql, Object[] args, Class returnClazz) {
        return defaultRepository.queryForList(nativeSql, args, returnClazz);
    }

    @Override
    public List<Map<String, Object>> queryForList(String nativeSql, Object[] args) {
        return defaultRepository.queryForList(nativeSql, args);
    }
}
