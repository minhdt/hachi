package com.hachi.fashion.entity;

import com.hachi.fashion.entity.ProductCampaign;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-07-07T14:00:42")
@StaticMetamodel(Campaign.class)
public class Campaign_ extends BaseEntity_ {

    public static volatile ListAttribute<Campaign, ProductCampaign> listProductCampaign;
    public static volatile SingularAttribute<Campaign, String> title;
    public static volatile SingularAttribute<Campaign, Integer> type;
    public static volatile SingularAttribute<Campaign, Double> value;

}