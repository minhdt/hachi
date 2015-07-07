/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachi.fashion.service;

import com.hachi.fashion.entity.ProductType;
import javax.ejb.Stateless;

/**
 *
 * @author MinhDT
 */
@Stateless
public class SB_ProductTypeBean extends SB_BaseBean<ProductType> implements SB_ProductTypeLocal {

    public SB_ProductTypeBean() {
        super(ProductType.class);
    }

}
