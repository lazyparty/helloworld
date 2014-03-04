// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntity
    implements Serializable
{

    public BaseEntity()
    {
    }

    public Long getId()
    {
        return IIIllIlI;
    }

    public void setId(Long id)
    {
        IIIllIlI = id;
    }

    public Date getCreateDate()
    {
        return IIIllIll;
    }

    public void setCreateDate(Date createDate)
    {
        IIIllIll = createDate;
    }

    public Date getModifyDate()
    {
        return IIIlllII;
    }

    public void setModifyDate(Date modifyDate)
    {
        IIIlllII = modifyDate;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(this == obj)
            return true;
        if(!net/shopxx/entity/BaseEntity.isAssignableFrom(obj.getClass()))
        {
            return false;
        } else
        {
            BaseEntity baseentity = (BaseEntity)obj;
            return getId() == null ? false : getId().equals(baseentity.getId());
        }
    }

    public int hashCode()
    {
        int i = 17;
        i += getId() != null ? getId().hashCode() * 31 : 0;
        return i;
    }

    private static final long serialVersionUID = 0xff114c844df2b640L;
    public static final String ID_PROPERTY_NAME = "id";
    public static final String CREATE_DATE_PROPERTY_NAME = "createDate";
    public static final String MODIFY_DATE_PROPERTY_NAME = "modifyDate";
    private Long IIIllIlI;
    private Date IIIllIll;
    private Date IIIlllII;
}
