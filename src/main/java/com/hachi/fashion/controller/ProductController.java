/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachi.fashion.controller;

import com.hachi.fashion.entity.Product;
import com.hachi.fashion.service.SB_BaseLocal;
import com.hachi.fashion.service.SB_ProductLocal;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author MinhDT
 */
@Named
@ViewScoped
public class ProductController extends BaseController<Product>{

    @EJB
    private SB_ProductLocal service;
    
    @Override
    protected SB_BaseLocal<Product> getBaseService() {
        return service;
    }
    
}
