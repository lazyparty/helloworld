// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.math.BigDecimal;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Product, Order

public class OrderItem extends BaseEntity
{

    public OrderItem()
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

    public String getFullName()
    {
        return IIIlllII;
    }

    public void setFullName(String fullName)
    {
        IIIlllII = fullName;
    }

    public BigDecimal getPrice()
    {
        return IIIlllIl;
    }

    public void setPrice(BigDecimal price)
    {
        IIIlllIl = price;
    }

    public Integer getWeight()
    {
        return IIIllllI;
    }

    public void setWeight(Integer weight)
    {
        IIIllllI = weight;
    }

    public String getThumbnail()
    {
        return IIIlllll;
    }

    public void setThumbnail(String thumbnail)
    {
        IIIlllll = thumbnail;
    }

    public Boolean getIsGift()
    {
        return IIlIIIII;
    }

    public void setIsGift(Boolean isGift)
    {
        IIlIIIII = isGift;
    }

    public Integer getQuantity()
    {
        return IIlIIIIl;
    }

    public void setQuantity(Integer quantity)
    {
        IIlIIIIl = quantity;
    }

    public Integer getShippedQuantity()
    {
        return IIlIIIlI;
    }

    public void setShippedQuantity(Integer shippedQuantity)
    {
        IIlIIIlI = shippedQuantity;
    }

    public Integer getReturnQuantity()
    {
        return IIlIIIll;
    }

    public void setReturnQuantity(Integer returnQuantity)
    {
        IIlIIIll = returnQuantity;
    }

    public Product getProduct()
    {
        return IIlIIlII;
    }

    public void setProduct(Product product)
    {
        IIlIIlII = product;
    }

    public Order getOrder()
    {
        return IIlIIlIl;
    }

    public void setOrder(Order order)
    {
        IIlIIlIl = order;
    }

    public int getTotalWeight()
    {
        if(getWeight() != null && getQuantity() != null)
            return getWeight().intValue() * getQuantity().intValue();
        else
            return 0;
    }

    public BigDecimal getSubtotal()
    {
        if(getPrice() != null && getQuantity() != null)
            return getPrice().multiply(new BigDecimal(getQuantity().intValue()));
        else
            return new BigDecimal(0);
    }

    private static final long serialVersionUID = 0xba9cb1c5ef6da49aL;
    private String IIIllIlI;
    private String IIIllIll;
    private String IIIlllII;
    private BigDecimal IIIlllIl;
    private Integer IIIllllI;
    private String IIIlllll;
    private Boolean IIlIIIII;
    private Integer IIlIIIIl;
    private Integer IIlIIIlI;
    private Integer IIlIIIll;
    private Product IIlIIlII;
    private Order IIlIIlIl;
}
