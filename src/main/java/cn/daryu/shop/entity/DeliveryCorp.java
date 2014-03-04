// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.*;

// Referenced classes of package net.shopxx.entity:
//            OrderEntity, ShippingMethod

public class DeliveryCorp extends OrderEntity
{

    public DeliveryCorp()
    {
        IIIlllIl = new HashSet();
    }

    public String getName()
    {
        return IIIllIlI;
    }

    public void setName(String name)
    {
        IIIllIlI = name;
    }

    public String getUrl()
    {
        return IIIllIll;
    }

    public void setUrl(String url)
    {
        IIIllIll = url;
    }

    public String getCode()
    {
        return IIIlllII;
    }

    public void setCode(String code)
    {
        IIIlllII = code;
    }

    public Set getShippingMethods()
    {
        return IIIlllIl;
    }

    public void setShippingMethods(Set shippingMethods)
    {
        IIIlllIl = shippingMethods;
    }

    public void preRemove()
    {
        Set set = getShippingMethods();
        if(set != null)
        {
            ShippingMethod shippingmethod;
            for(Iterator iterator = set.iterator(); iterator.hasNext(); shippingmethod.setDefaultDeliveryCorp(null))
                shippingmethod = (ShippingMethod)iterator.next();

        }
    }

    private static final long serialVersionUID = 0x25a4bc5e96db2eL;
    private String IIIllIlI;
    private String IIIllIll;
    private String IIIlllII;
    private Set IIIlllIl;
}
