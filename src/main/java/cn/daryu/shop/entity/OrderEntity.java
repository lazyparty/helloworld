// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import org.apache.commons.lang.builder.CompareToBuilder;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity

public abstract class OrderEntity extends BaseEntity
    implements Comparable
{

    public OrderEntity()
    {
    }

    public Integer getOrder()
    {
        return IIIllIlI;
    }

    public void setOrder(Integer order)
    {
        IIIllIlI = order;
    }

    public int compareTo(OrderEntity orderEntity)
    {
        return (new CompareToBuilder()).append(getOrder(), orderEntity.getOrder()).append(getId(), orderEntity.getId()).toComparison();
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((OrderEntity)obj);
    }

    private static final long serialVersionUID = 0x5332909338966bc3L;
    public static final String ORDER_PROPERTY_NAME = "order";
    private Integer IIIllIlI;
}
