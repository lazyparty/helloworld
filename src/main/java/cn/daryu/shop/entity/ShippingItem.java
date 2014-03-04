// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;


// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Shipping

public class ShippingItem extends BaseEntity
{

    public ShippingItem()
    {
    }

    public String getSn()
    {
        return IIIllIlI;
    }

    public void setSn(String sn)
    {
        IIIllIlI = sn;
    }

    public String getName()
    {
        return IIIllIll;
    }

    public void setName(String name)
    {
        IIIllIll = name;
    }

    public Integer getQuantity()
    {
        return IIIlllII;
    }

    public void setQuantity(Integer quantity)
    {
        IIIlllII = quantity;
    }

    public Shipping getShipping()
    {
        return IIIlllIl;
    }

    public void setShipping(Shipping shipping)
    {
        IIIlllIl = shipping;
    }

    private static final long serialVersionUID = 0x2640af1de908d3deL;
    private String IIIllIlI;
    private String IIIllIll;
    private Integer IIIlllII;
    private Shipping IIIlllIl;
}
