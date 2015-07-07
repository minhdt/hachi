package com.hachi.fashion.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-07-06T14:58:21")
@StaticMetamodel(BaseEntity.class)
public abstract class BaseEntity_ { 

    public static volatile SingularAttribute<BaseEntity, Date> createdDate;
    public static volatile SingularAttribute<BaseEntity, Date> modifiedDate;
    public static volatile SingularAttribute<BaseEntity, String> description;
    public static volatile SingularAttribute<BaseEntity, Long> id;
    public static volatile SingularAttribute<BaseEntity, Integer> status;

}