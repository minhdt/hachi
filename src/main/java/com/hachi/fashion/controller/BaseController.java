/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachi.fashion.controller;

import com.hachi.fashion.entity.BaseEntity;
import com.hachi.fashion.model.AbstractLazyDataModel;
import com.hachi.fashion.service.SB_BaseLocal;
import com.hachi.fashion.util.JsfUtil;
import com.hachi.fashion.util.MessageUtil;
import com.hachi.fashion.util.SearchBase;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.SelectItem;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author MinhDT
 * @param <T>
 */
public abstract class BaseController<T extends BaseEntity> implements Serializable {

    private static final long serialVersionUID = -1374442757454130534L;

    protected T current;
    private List<SearchBase> itemSearch;
    protected LazyDataModel<T> model;
    protected SelectItem[] selectItems;

    public BaseController() {
    }

    public void resetEntity() {
        current = null;
    }

    public void searchItem() {
        resetModel();
    }

    private void resetModel() {
        model = null;
    }

    public void prepareEntity(T entity) {
        current = entity;
        System.out.println(current.toString());
    }

    /**
     * Call back method persist action
     */
    protected void onBeforePersist() {
    }

    /**
     * Call back method persist action
     */
    protected void onAfterPersist() {
        current = initEntity();
    }

    /**
     * Call back method persist action
     */
    protected void onPersistSuccess() {
        MessageUtil.addGlobalInfoMessage("success");
    }

    /**
     * Call back method persist action
     *
     * @param t
     */
    protected void onPersistError(Throwable t) {
        MessageUtil.addGlobalErrorMessage("error", t);
    }

    /**
     * Persist entity to db
     */
    public void persist() {
        onBeforePersist();
        try {
            getBaseService().persist(current);
            onPersistSuccess();
        } catch (Exception e) {
            onPersistError(e);
        }
        onAfterPersist();
    }

    /**
     * Call back method update action
     */
    protected void onBeforeUpdate() {
    }

    /**
     * Call back method update action
     */
    protected void onAfterUpdate() {
        current = initEntity();
    }

    /**
     * Call back method update action
     */
    protected void onSuccessUpdate() {
        MessageUtil.addGlobalInfoMessage("success");
    }

    /**
     * Call back method update action
     *
     * @param t
     */
    protected void onErrorUpdate(Throwable t) {
        MessageUtil.addGlobalErrorMessage("error", t);
    }

    /**
     * Update entity and save to db
     */
    public void update() {
        onBeforeUpdate();
        try {
            getBaseService().update(current);
            onSuccessUpdate();
        } catch (Exception e) {
            onErrorUpdate(e);
        }
        onAfterUpdate();
    }

    /**
     * Call back method remove action
     *
     * @param entity
     */
    protected void onBeforeRemove(T entity) {
    }

    /**
     * Call back method remove action
     *
     * @param entity
     */
    protected void onAfterRemove(T entity) {
    }

    /**
     * Call back method remove action
     *
     * @param entity
     */
    protected void onSuccessRemove(T entity) {
        MessageUtil.addGlobalInfoMessage("success");
    }

    /**
     * Call back method remove action
     *
     * @param entity
     * @param t
     */
    protected void onErrorRemove(T entity, Throwable t) {
        MessageUtil.addGlobalErrorMessage("error", t);
    }

    /**
     * Remove entity from db
     *
     * @param entity entity instance for removing
     */
    public void remove(T entity) {
        onBeforeRemove(entity);
        try {
            getBaseService().remove(entity);
            onSuccessRemove(entity);
        } catch (Exception e) {
            onErrorRemove(entity, e);
        }
        onAfterRemove(entity);
    }

    /**
     * Initilize a new instance of the entity;
     *
     * @return new instanse of entity
     */
    protected T initEntity() {
        try {
            return getEntityClass().newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, "Can not instantilize entity.", ex);
        }
        return null;
    }

    /**
     * Factory method for initilize a new instance of LazyDataModel (Primefaces)
     *
     * @return new instance of LazyDataModel for the entity.
     */
    protected LazyDataModel<T> initDataModel() {
        return new AbstractLazyDataModel<T>(getItemSearch()) {
            private static final long serialVersionUID = 1L;

            @Override
            protected SB_BaseLocal<T> getService() {
                return getBaseService();
            }
        };
    }

    /**
     * Factoty method for BasicService EJB
     *
     * @return BasicSerivce instanse
     */
    protected abstract SB_BaseLocal<T> getBaseService();

    /* getters and setters */
    public T getCurrent() {

        if (current == null) {
            current = initEntity();
        }

        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public LazyDataModel<T> getModel() {
        if (model == null) {
            model = initDataModel();
        }
        return model;
    }

    public void setModel(LazyDataModel<T> model) {
        this.model = model;
    }

    protected Class<T> getEntityClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public SelectItem[] getSelectItems() {
        if (selectItems == null) {
            selectItems = JsfUtil.getSelectItems(getBaseService().findAllBase(null, 0, 0, null, SortOrder.UNSORTED, null), false);
        }
        return selectItems;
    }

    public void setSelectItems(SelectItem[] selectItems) {
        this.selectItems = selectItems;
    }

    public List<SearchBase> getItemSearch() {
        if (itemSearch == null) {
            itemSearch = new ArrayList<>();
        }

        return itemSearch;
    }

    public void setItemSearch(List<SearchBase> itemSearch) {
        this.itemSearch = itemSearch;
    }
}
