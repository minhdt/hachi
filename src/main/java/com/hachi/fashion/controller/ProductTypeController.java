/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachi.fashion.controller;

import com.hachi.fashion.entity.ProductType;
import com.hachi.fashion.service.SB_BaseLocal;
import com.hachi.fashion.service.SB_ProductTypeLocal;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author MinhDT
 */
@Named
@ViewScoped
public class ProductTypeController extends BaseController<ProductType> {

    @EJB
    private SB_ProductTypeLocal service;
    
    @Override
    protected SB_BaseLocal<ProductType> getBaseService()
    {
        return service;
    }
}
