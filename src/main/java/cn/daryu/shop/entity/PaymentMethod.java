// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.*;

// Referenced classes of package net.shopxx.entity:
//            OrderEntity, Order

public class PaymentMethod extends OrderEntity
{

    public PaymentMethod()
    {
        IIlIIIII = new HashSet();
        IIlIIIIl = new HashSet();
    }

    public String getName()
    {
        return IIIllIlI;
    }

    public void setName(String name)
    {
        IIIllIlI = name;
    }

    public Type getType()
    {
        return IIIllIll;
    }

    public void setType(Type type)
    {
        IIIllIll = type;
    }

    public Integer getTimeout()
    {
        return IIIlllII;
    }

    public void setTimeout(Integer timeout)
    {
        IIIlllII = timeout;
    }

    public String getIcon()
    {
        return IIIlllIl;
    }

    public void setIcon(String icon)
    {
        IIIlllIl = icon;
    }

    public String getDescription()
    {
        return IIIllllI;
    }

    public void setDescription(String description)
    {
        IIIllllI = description;
    }

    public String getContent()
    {
        return IIIlllll;
    }

    public void setContent(String content)
    {
        IIIlllll = content;
    }

    public Set getShippingMethods()
    {
        return IIlIIIII;
    }

    public void setShippingMethods(Set shippingMethods)
    {
        IIlIIIII = shippingMethods;
    }

    public Set getOrders()
    {
        return IIlIIIIl;
    }

    public void setOrders(Set orders)
    {
        IIlIIIIl = orders;
    }

    public void preRemove()
    {
        Set set = getOrders();
        if(set != null)
        {
            Order order;
            for(Iterator iterator = set.iterator(); iterator.hasNext(); order.setPaymentMethod(null))
                order = (Order)iterator.next();

        }
    }

    private static final long serialVersionUID = 0x6072b548ff98eb4fL;
    private String IIIllIlI;
    private Type IIIllIll;
    private Integer IIIlllII;
    private String IIIlllIl;
    private String IIIllllI;
    private String IIIlllll;
    private Set IIlIIIII;
    private Set IIlIIIIl;
}
