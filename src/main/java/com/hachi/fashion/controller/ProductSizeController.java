/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachi.fashion.controller;

import com.hachi.fashion.entity.ProductSize;
import com.hachi.fashion.service.SB_BaseLocal;
import com.hachi.fashion.service.SB_ProductSizeLocal;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author MinhDT
 */
@Named
@ViewScoped
public class ProductSizeController extends BaseController<ProductSize>{

    @EJB
    private SB_ProductSizeLocal service;
    
    @Override
    protected SB_BaseLocal<ProductSize> getBaseService() {
        return service;
    }
    
}
