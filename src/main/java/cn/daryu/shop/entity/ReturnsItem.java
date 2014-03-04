// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;


// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Returns

public class ReturnsItem extends BaseEntity
{

    public ReturnsItem()
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

    public Returns getReturns()
    {
        return IIIlllIl;
    }

    public void setReturns(Returns returns)
    {
        IIIlllIl = returns;
    }

    private static final long serialVersionUID = 0xc6ede917d8e38b7eL;
    private String IIIllIlI;
    private String IIIllIll;
    private Integer IIIlllII;
    private Returns IIIlllIl;
}
