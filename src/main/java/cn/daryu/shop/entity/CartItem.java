// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.math.BigDecimal;
import java.util.Map;
import net.shopxx.Setting;
import net.shopxx.util.SettingUtils;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Cart, Member, MemberRank, 
//            Product

public class CartItem extends BaseEntity
{

    public CartItem()
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

    public Product getProduct()
    {
        return IIIllIll;
    }

    public void setProduct(Product product)
    {
        IIIllIll = product;
    }

    public Cart getCart()
    {
        return IIIlllII;
    }

    public void setCart(Cart cart)
    {
        IIIlllII = cart;
    }

    public long getPoint()
    {
        if(getProduct() != null && getProduct().getPoint() != null && getQuantity() != null)
            return getProduct().getPoint().longValue() * (long)getQuantity().intValue();
        else
            return 0L;
    }

    public int getWeight()
    {
        if(getProduct() != null && getProduct().getWeight() != null && getQuantity() != null)
            return getProduct().getWeight().intValue() * getQuantity().intValue();
        else
            return 0;
    }

    public BigDecimal getUnitPrice()
    {
        if(getProduct() != null && getProduct().getPrice() != null)
        {
            Setting setting = SettingUtils.get();
            if(getCart() != null && getCart().getMember() != null && getCart().getMember().getMemberRank() != null)
            {
                MemberRank memberrank = getCart().getMember().getMemberRank();
                Map map = getProduct().getMemberPrice();
                if(map != null && !map.isEmpty() && map.containsKey(memberrank))
                    return setting.setScale((BigDecimal)map.get(memberrank));
                if(memberrank.getScale() != null)
                    return setting.setScale(getProduct().getPrice().multiply(new BigDecimal(memberrank.getScale().doubleValue())));
            }
            return setting.setScale(getProduct().getPrice());
        } else
        {
            return new BigDecimal(0);
        }
    }

    public BigDecimal getSubtotal()
    {
        if(getQuantity() != null)
            return getUnitPrice().multiply(new BigDecimal(getQuantity().intValue()));
        else
            return new BigDecimal(0);
    }

    public boolean getIsLowStock()
    {
        return getQuantity() != null && getProduct() != null && getProduct().getStock() != null && getQuantity().intValue() > getProduct().getAvailableStock().intValue();
    }

    public void add(int quantity)
    {
        if(quantity > 0)
            if(getQuantity() != null)
                setQuantity(Integer.valueOf(getQuantity().intValue() + quantity));
            else
                setQuantity(Integer.valueOf(quantity));
    }

    private static final long serialVersionUID = 0x295896a51a740008L;
    public static final Integer MAX_QUANTITY = Integer.valueOf(10000);
    private Integer IIIllIlI;
    private Product IIIllIll;
    private Cart IIIlllII;

}
