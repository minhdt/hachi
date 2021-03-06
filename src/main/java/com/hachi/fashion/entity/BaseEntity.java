package com.hachi.fashion.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Nguyen Trong Cuong
 * @since 09/10/2014
 * @version 1.0
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable, Cloneable
{

    private static final long serialVersionUID = 7096442574647885460L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(name = "DESCRIPTION", length = 200)
    protected String description;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date modifiedDate;
    
    @Column(name = "STATUS", length = 1)
    protected Integer status;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate()
    {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate)
    {
        this.modifiedDate = modifiedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        BaseEntity entity = (BaseEntity) super.clone();

        if (this.createdDate != null)
        {
            entity.createdDate = (Date) this.createdDate.clone();
        }
        if (this.modifiedDate != null)
        {
            entity.modifiedDate = (Date) this.modifiedDate.clone();
        }

        return entity;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final BaseEntity other = (BaseEntity) obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        return true;
    }

    @PrePersist
    protected void onPrePersist()
    {
        Date now = new Date();
        createdDate = now;
        modifiedDate = now;
        status = 1;
    }

    @PreUpdate
    protected void onPreUpdate()
    {
        modifiedDate = new Date();
    }
}
