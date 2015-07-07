/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachi.fashion.model;

import com.hachi.fashion.entity.BaseEntity;
import com.hachi.fashion.service.SB_BaseLocal;
import com.hachi.fashion.util.SearchBase;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author MinhDT
 * @param <T>
 */
public abstract class AbstractLazyDataModel<T extends BaseEntity> extends LazyDataModel<T> {

    private static final long serialVersionUID = -6241362556725345679L;
    
    protected abstract SB_BaseLocal<T> getService();

    private List<SearchBase> search;
            
    public AbstractLazyDataModel(List<SearchBase> search) {
        this.search = search;
    }
    
    protected long parserRowKey(String rowKey) {
        return Long.parseLong(rowKey);
    }

    @Override
    public T getRowData(String rowKey) {
        return getService().find(parserRowKey(rowKey));
    }

    @Override
    public Object getRowKey(T object) {
        return ((BaseEntity) object).getId();
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        this.setRowCount(getService().countBase(search, filters));
        return getService().findAllBase(search, first, first, sortField, sortOrder, filters);
    }

}
