package com.hachi.fashion.service;

import com.hachi.fashion.entity.BaseEntity;
import com.hachi.fashion.util.SearchBase;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.primefaces.model.SortOrder;

/**
 * @author Nguyen Trong Cuong
 * @param <T>
 * @since 09/10/2014
 * @version 1.0
 */

public interface SB_BaseLocal<T extends BaseEntity> extends Serializable {

    T find(long id);

    List<T> findAllBase(List<SearchBase> search, int start, int range, String sortField, SortOrder sortOrder, Map<String, Object> filters);

    int countBase(List<SearchBase> search, Map<String, Object> filters);

    T persist(T entity);

    T update(T entity);

    void remove(T entity);
    
    void removeListBase(List<T> listEntity);
    
    void mergeListEntity(List<T> listEntity);
}