// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package cn.daryu.shop.entity;

import java.util.*;

// Referenced classes of package net.shopxx.entity:
//            OrderEntity, DeliveryCenter, Member, Order, 
//            Receiver

public class Area extends OrderEntity
{

    public Area()
    {
        IIIlllll = new HashSet();
        IIlIIIII = new HashSet();
        IIlIIIIl = new HashSet();
        IIlIIIlI = new HashSet();
        IIlIIIll = new HashSet();
    }

    public String getName()
    {
        return IIIllIll;
    }

    public void setName(String name)
    {
        IIIllIll = name;
    }

    public String getFullName()
    {
        return IIIlllII;
    }

    public void setFullName(String fullName)
    {
        IIIlllII = fullName;
    }

    public String getTreePath()
    {
        return IIIlllIl;
    }

    public void setTreePath(String treePath)
    {
        IIIlllIl = treePath;
    }

    public Area getParent()
    {
        return IIIllllI;
    }

    public void setParent(Area parent)
    {
        IIIllllI = parent;
    }

    public Set getChildren()
    {
        return IIIlllll;
    }

    public void setChildren(Set children)
    {
        IIIlllll = children;
    }

    public Set getMembers()
    {
        return IIlIIIII;
    }

    public void setMembers(Set members)
    {
        IIlIIIII = members;
    }

    public Set getReceivers()
    {
        return IIlIIIIl;
    }

    public void setReceivers(Set receivers)
    {
        IIlIIIIl = receivers;
    }

    public Set getOrders()
    {
        return IIlIIIlI;
    }

    public void setOrders(Set orders)
    {
        IIlIIIlI = orders;
    }

    public Set getDeliveryCenters()
    {
        return IIlIIIll;
    }

    public void setDeliveryCenters(Set deliveryCenters)
    {
        IIlIIIll = deliveryCenters;
    }

    public void prePersist()
    {
        Area area = getParent();
        if(area != null)
        {
            setFullName((new StringBuilder(String.valueOf(area.getFullName()))).append(getName()).toString());
            setTreePath((new StringBuilder(String.valueOf(area.getTreePath()))).append(area.getId()).append(",").toString());
        } else
        {
            setFullName(getName());
            setTreePath(",");
        }
    }

    public void preUpdate()
    {
        Area area = getParent();
        if(area != null)
            setFullName((new StringBuilder(String.valueOf(area.getFullName()))).append(getName()).toString());
        else
            setFullName(getName());
    }

    public void preRemove()
    {
        Set set = getMembers();
        if(set != null)
        {
            Member member;
            for(Iterator iterator = set.iterator(); iterator.hasNext(); member.setArea(null))
                member = (Member)iterator.next();

        }
        Set set1 = getReceivers();
        if(set1 != null)
        {
            Receiver receiver;
            for(Iterator iterator1 = set1.iterator(); iterator1.hasNext(); receiver.setArea(null))
                receiver = (Receiver)iterator1.next();

        }
        Set set2 = getOrders();
        if(set2 != null)
        {
            Order order;
            for(Iterator iterator2 = set2.iterator(); iterator2.hasNext(); order.setArea(null))
                order = (Order)iterator2.next();

        }
        Set set3 = getDeliveryCenters();
        if(set3 != null)
        {
            DeliveryCenter deliverycenter;
            for(Iterator iterator3 = set3.iterator(); iterator3.hasNext(); deliverycenter.setArea(null))
                deliverycenter = (DeliveryCenter)iterator3.next();

        }
    }

    public String toString()
    {
        return getFullName();
    }

    private static final long serialVersionUID = 0xe20cdae18e8f9cd9L;
    private static final String IIIllIlI = ",";
    private String IIIllIll;
    private String IIIlllII;
    private String IIIlllIl;
    private Area IIIllllI;
    private Set IIIlllll;
    private Set IIlIIIII;
    private Set IIlIIIIl;
    private Set IIlIIIlI;
    private Set IIlIIIll;
}
