/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachi.fashion.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author MinhDT
 */
@Entity
@Table(name = "HC_PRODUCT")
public class Product extends BaseEntity {

    @Column(length = 200, nullable = false)
    protected String code;

    @Column(length = 200, nullable = false)
    protected String title;

    @Column(length = 200)
    protected String manufacture;

    @Column(name = "PRODUCT_PRICE")
    protected Double price;

    @Column(name = "PRODUCT_COST")
    protected Double cost;

    @Column(name = "TOTAL_QUANTITY")
    protected Integer totalQuantity;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL}, orphanRemoval = true)
    protected List<ProductImage> images;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.ALL}, orphanRemoval = true)
    protected List<ProductCampaign> listProductCampaign;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public List<ProductCampaign> getListProductCampaign() {
        return listProductCampaign;
    }

    public void setListProductCampaign(List<ProductCampaign> listProductCampaign) {
        this.listProductCampaign = listProductCampaign;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
