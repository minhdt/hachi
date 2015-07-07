package com.hachi.fashion.entity;

import com.hachi.fashion.entity.ProductCampaign;
import com.hachi.fashion.entity.ProductImage;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-07-06T14:58:21")
@StaticMetamodel(Product.class)
public class Product_ extends BaseEntity_ {

    public static volatile SingularAttribute<Product, String> manufacture;
    public static volatile ListAttribute<Product, ProductImage> images;
    public static volatile SingularAttribute<Product, Integer> totalQuantity;
    public static volatile SingularAttribute<Product, Integer> curQuantity;
    public static volatile ListAttribute<Product, ProductCampaign> listProductCampaign;
    public static volatile SingularAttribute<Product, String> title;
    public static volatile SingularAttribute<Product, Double> prices;

}