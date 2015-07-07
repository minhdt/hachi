/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachi.fashion.controller.convertor;

import com.hachi.fashion.entity.BaseEntity;
import com.hachi.fashion.service.SB_BaseLocal;
import com.hachi.fashion.util.JsfUtil;
import java.lang.reflect.ParameterizedType;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author NamTA
 * @param <T>
 */
@SuppressWarnings("serial")
public abstract class BaseEntityConvertor<T extends BaseEntity> implements
        Converter
{
    /* Factory method */
    protected abstract SB_BaseLocal<T> getBaseService();

    protected Long getKey(String keyStr)
    {
        return Long.parseLong(keyStr);
    }

    protected String getStringKey(T entity)
    {
        return String.valueOf(((BaseEntity) entity).getId());
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value)
    {
        if (value == null || value.trim().isEmpty())
        {
            return null;
        }
        if (JsfUtil.isDummySelectItem(component, value))
        {
            return null;
        }
        return getBaseService().find(getKey(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value)
    {
        if (value == null)
        {
            return null;
        }
        if (value.getClass().equals(getEntityClass()))
        {
            T entity = (T) value;
            return getStringKey(entity);
        }
        else
        {
            throw new IllegalArgumentException("object " + value
                    + " is of type " + value.getClass()
                    .getName() + "; expected type: " + getEntityClass().
                    getName());
        }
    }

    protected Class<T> getEntityClass()
    {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).
                getActualTypeArguments()[0];
    }

}
