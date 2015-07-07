/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachi.fashion.service;

import com.hachi.fashion.entity.ProductSize;
import javax.ejb.Stateless;

/**
 *
 * @author MinhDT
 */
@Stateless
public class SB_ProductSizeBean extends SB_BaseBean<ProductSize> implements SB_ProductSizeLocal{

    public SB_ProductSizeBean() {
        super(ProductSize.class);
    }
    
}
