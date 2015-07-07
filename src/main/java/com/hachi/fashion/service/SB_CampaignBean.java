/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hachi.fashion.service;

import com.hachi.fashion.entity.Campaign;
import javax.ejb.Stateless;

/**
 *
 * @author MinhDT
 */
@Stateless
public class SB_CampaignBean extends SB_BaseBean<Campaign> implements SB_CampaignLocal{

    public SB_CampaignBean() {
        super(Campaign.class);
    }
    
}
