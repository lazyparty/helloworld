// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;


// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Product, Promotion

public class GiftItem extends BaseEntity
{

    public GiftItem()
    {
    }

    public Integer getQuantity()
    {
        return IIIllIlI;
    }

    public void setQuantity(Integer quantity)
    {
        IIIllIlI = quantity;
    }

    public Product getGift()
    {
        return IIIllIll;
    }

    public void setGift(Product gift)
    {
        IIIllIll = gift;
    }

    public Promotion getPromotion()
    {
        return IIIlllII;
    }

    public void setPromotion(Promotion promotion)
    {
        IIIlllII = promotion;
    }

    private static final long serialVersionUID = 0x5b8160c587ee4c25L;
    private Integer IIIllIlI;
    private Product IIIllIll;
    private Promotion IIIlllII;
}
