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
@Table(name = "HC_CAMPAIGN")
public class Campaign extends BaseEntity{
    
    @Column(name = "TITLE", length = 200, nullable = false)
    protected String title;
    
    @Column(name = "CAMPAIGN_TYPE", length = 1, nullable = false)
    protected Integer type;
    
    @Column(name = "CAMPAIGN_VALUE", length = 30)
    protected Double value;
    
    @OneToMany(mappedBy = "campaign", cascade = {CascadeType.ALL}, orphanRemoval = true)
    protected List<ProductCampaign> listProductCampaign;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public List<ProductCampaign> getListProductCampaign() {
        return listProductCampaign;
    }

    public void setListProductCampaign(List<ProductCampaign> listProductCampaign) {
        this.listProductCampaign = listProductCampaign;
    }
}
