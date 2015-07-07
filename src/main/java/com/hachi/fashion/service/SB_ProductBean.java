/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachi.fashion.service;

import com.hachi.fashion.entity.Product;
import javax.ejb.Stateless;

/**
 *
 * @author MinhDT
 */
@Stateless
public class SB_ProductBean extends SB_BaseBean<Product> implements SB_ProductLocal{

    public SB_ProductBean() {
        super(Product.class);
    }
    
}
