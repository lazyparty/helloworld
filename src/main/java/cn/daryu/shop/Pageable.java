// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Pageable
    implements Serializable
{

    public Pageable()
    {
        IIIlllIl = 1;
        IIIllllI = 20;
        IIlIIIll = new ArrayList();
        IIlIIlII = new ArrayList();
    }

    public Pageable(Integer pageNumber, Integer pageSize)
    {
        IIIlllIl = 1;
        IIIllllI = 20;
        IIlIIIll = new ArrayList();
        IIlIIlII = new ArrayList();
        if(pageNumber != null && pageNumber.intValue() >= 1)
            IIIlllIl = pageNumber.intValue();
        if(pageSize != null && pageSize.intValue() >= 1 && pageSize.intValue() <= 1000)
            IIIllllI = pageSize.intValue();
    }

    public int getPageNumber()
    {
        return IIIlllIl;
    }

    public void setPageNumber(int pageNumber)
    {
        if(pageNumber < 1)
            pageNumber = 1;
        IIIlllIl = pageNumber;
    }

    public int getPageSize()
    {
        return IIIllllI;
    }

    public void setPageSize(int pageSize)
    {
        if(pageSize < 1 || pageSize > 1000)
            pageSize = 20;
        IIIllllI = pageSize;
    }

    public String getSearchProperty()
    {
        return IIIlllll;
    }

    public void setSearchProperty(String searchProperty)
    {
        IIIlllll = searchProperty;
    }

    public String getSearchValue()
    {
        return IIlIIIII;
    }

    public void setSearchValue(String searchValue)
    {
        IIlIIIII = searchValue;
    }

    public String getOrderProperty()
    {
        return IIlIIIIl;
    }

    public void setOrderProperty(String orderProperty)
    {
        IIlIIIIl = orderProperty;
    }

    public Order.Direction getOrderDirection()
    {
        return IIlIIIlI;
    }

    public void setOrderDirection(Order.Direction orderDirection)
    {
        IIlIIIlI = orderDirection;
    }

    public List getFilters()
    {
        return IIlIIIll;
    }

    public void setFilters(List filters)
    {
        IIlIIIll = filters;
    }

    public List getOrders()
    {
        return IIlIIlII;
    }

    public void setOrders(List orders)
    {
        IIlIIlII = orders;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        if(this == obj)
        {
            return true;
        } else
        {
            Pageable pageable = (Pageable)obj;
            return (new EqualsBuilder()).append(getPageNumber(), pageable.getPageNumber()).append(getPageSize(), pageable.getPageSize()).append(getSearchProperty(), pageable.getSearchProperty()).append(getSearchValue(), pageable.getSearchValue()).append(getOrderProperty(), pageable.getOrderProperty()).append(getOrderDirection(), pageable.getOrderDirection()).append(getFilters(), pageable.getFilters()).append(getOrders(), pageable.getOrders()).isEquals();
        }
    }

    public int hashCode()
    {
        return (new HashCodeBuilder(17, 37)).append(getPageNumber()).append(getPageSize()).append(getSearchProperty()).append(getSearchValue()).append(getOrderProperty()).append(getOrderDirection()).append(getFilters()).append(getOrders()).toHashCode();
    }

    private static final long serialVersionUID = 0xc97531c47f62ff95L;
    private static final int IIIllIlI = 1;
    private static final int IIIllIll = 20;
    private static final int IIIlllII = 1000;
    private int IIIlllIl;
    private int IIIllllI;
    private String IIIlllll;
    private String IIlIIIII;
    private String IIlIIIIl;
    private Order.Direction IIlIIIlI;
    private List IIlIIIll;
    private List IIlIIlII;
}
