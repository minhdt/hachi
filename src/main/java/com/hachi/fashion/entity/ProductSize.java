/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachi.fashion.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author MinhDT
 */
@Entity
@Table(name = "HC_SIZE")
public class ProductSize extends BaseEntity{
    
    @Column(name="TITLE", length = 200, nullable = false)
    protected String title;
    
    @Column(name="VALUE", length = 100, nullable = false)
    protected String value;
    
    @ManyToOne
    @JoinColumn(name = "PRODUCT_TYPE_ID", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    protected ProductType productType;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
