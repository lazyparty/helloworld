// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.math.BigDecimal;
import java.util.*;
import net.shopxx.Setting;
import net.shopxx.util.SettingUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.time.DateUtils;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Brand, CartItem, Coupon, 
//            GiftItem, Member, Product, ProductCategory, 
//            Promotion

public class Cart extends BaseEntity
{

    public Cart()
    {
        IIIlllII = new HashSet();
    }

    public String getKey()
    {
        return IIIllIlI;
    }

    public void setKey(String key)
    {
        IIIllIlI = key;
    }

    public Member getMember()
    {
        return IIIllIll;
    }

    public void setMember(Member member)
    {
        IIIllIll = member;
    }

    public Set getCartItems()
    {
        return IIIlllII;
    }

    public void setCartItems(Set cartItems)
    {
        IIIlllII = cartItems;
    }

    public int getPoint()
    {
        int i = 0;
        if(getCartItems() != null)
        {
            for(Iterator iterator = getCartItems().iterator(); iterator.hasNext();)
            {
                CartItem cartitem = (CartItem)iterator.next();
                if(cartitem != null)
                    i = (int)((long)i + cartitem.getPoint());
            }

        }
        for(Iterator iterator1 = getPromotions().iterator(); iterator1.hasNext();)
        {
            Promotion promotion = (Promotion)iterator1.next();
            i = promotion.calculatePoint(Integer.valueOf(i)).intValue();
        }

        return i;
    }

    public int getWeight()
    {
        int i = 0;
        if(getCartItems() != null)
        {
            for(Iterator iterator = getCartItems().iterator(); iterator.hasNext();)
            {
                CartItem cartitem = (CartItem)iterator.next();
                if(cartitem != null)
                    i += cartitem.getWeight();
            }

        }
        return i;
    }

    public int getQuantity()
    {
        int i = 0;
        if(getCartItems() != null)
        {
            for(Iterator iterator = getCartItems().iterator(); iterator.hasNext();)
            {
                CartItem cartitem = (CartItem)iterator.next();
                if(cartitem != null && cartitem.getQuantity() != null)
                    i += cartitem.getQuantity().intValue();
            }

        }
        return i;
    }

    public BigDecimal getPrice()
    {
        BigDecimal bigdecimal = new BigDecimal(0);
        if(getCartItems() != null)
        {
            for(Iterator iterator = getCartItems().iterator(); iterator.hasNext();)
            {
                CartItem cartitem = (CartItem)iterator.next();
                if(cartitem != null && cartitem.getSubtotal() != null)
                    bigdecimal = bigdecimal.add(cartitem.getSubtotal());
            }

        }
        return bigdecimal;
    }

    public BigDecimal getAmount()
    {
        Setting setting = SettingUtils.get();
        BigDecimal bigdecimal = getPrice();
        for(Iterator iterator = getPromotions().iterator(); iterator.hasNext();)
        {
            Promotion promotion = (Promotion)iterator.next();
            bigdecimal = promotion.calculatePrice(bigdecimal);
        }

        return setting.setScale(bigdecimal);
    }

    public BigDecimal getDiscount()
    {
        BigDecimal bigdecimal = getPrice().subtract(getAmount());
        return bigdecimal.compareTo(new BigDecimal(0)) <= 0 ? new BigDecimal(0) : bigdecimal;
    }

    public Set getGiftItems()
    {
        HashSet hashset = new HashSet();
        for(Iterator iterator = getPromotions().iterator(); iterator.hasNext();)
        {
            Promotion promotion = (Promotion)iterator.next();
            if(promotion.getGiftItems() != null)
            {
                for(Iterator iterator1 = promotion.getGiftItems().iterator(); iterator1.hasNext();)
                {
                    GiftItem giftitem = (GiftItem)iterator1.next();
                    GiftItem giftitem1 = (GiftItem)CollectionUtils.find(hashset, new _cls1(giftitem));
                    if(giftitem1 != null)
                        giftitem1.setQuantity(Integer.valueOf(giftitem1.getQuantity().intValue() + giftitem.getQuantity().intValue()));
                    else
                        hashset.add(giftitem);
                }

            }
        }

        return hashset;
    }

    public Set getPromotions()
    {
        HashSet hashset = new HashSet();
        if(getCartItems() != null)
        {
            for(Iterator iterator = getCartItems().iterator(); iterator.hasNext();)
            {
                CartItem cartitem = (CartItem)iterator.next();
                if(cartitem != null && cartitem.getProduct() != null)
                    hashset.addAll(cartitem.getProduct().getValidPromotions());
            }

        }
        TreeSet treeset = new TreeSet();
        for(Iterator iterator1 = hashset.iterator(); iterator1.hasNext();)
        {
            Promotion promotion = (Promotion)iterator1.next();
            if(IIIllIlI(promotion))
                treeset.add(promotion);
        }

        return treeset;
    }

    private boolean IIIllIlI(Promotion promotion)
    {
        if(promotion == null || !promotion.hasBegun() || promotion.hasEnded())
            return false;
        if(promotion.getMemberRanks() == null || getMember() == null || getMember().getMemberRank() == null || !promotion.getMemberRanks().contains(getMember().getMemberRank()))
            return false;
        BigDecimal bigdecimal = new BigDecimal(0);
        if(getCartItems() != null)
        {
            for(Iterator iterator = getCartItems().iterator(); iterator.hasNext();)
            {
                CartItem cartitem = (CartItem)iterator.next();
                if(cartitem != null)
                {
                    Product product = cartitem.getProduct();
                    if(product != null)
                        if(product.getPromotions() != null && product.getPromotions().contains(promotion))
                            bigdecimal = bigdecimal.add(cartitem.getSubtotal());
                        else
                        if(product.getProductCategory() != null && product.getProductCategory().getPromotions().contains(promotion))
                            bigdecimal = bigdecimal.add(cartitem.getSubtotal());
                        else
                        if(product.getBrand() != null && product.getBrand().getPromotions().contains(promotion))
                            bigdecimal = bigdecimal.add(cartitem.getSubtotal());
                }
            }

        }
        return (promotion.getStartPrice() == null || promotion.getStartPrice().compareTo(bigdecimal) <= 0) && (promotion.getEndPrice() == null || promotion.getEndPrice().compareTo(bigdecimal) >= 0);
    }

    public boolean isValid(Coupon coupon)
    {
        if(coupon == null || !coupon.getIsEnabled().booleanValue() || !coupon.hasBegun() || coupon.hasExpired())
            return false;
        return (coupon.getStartPrice() == null || coupon.getStartPrice().compareTo(getAmount()) <= 0) && (coupon.getEndPrice() == null || coupon.getEndPrice().compareTo(getAmount()) >= 0);
    }

    public CartItem getCartItem(Product product)
    {
        if(product != null && getCartItems() != null)
        {
            for(Iterator iterator = getCartItems().iterator(); iterator.hasNext();)
            {
                CartItem cartitem = (CartItem)iterator.next();
                if(cartitem != null && cartitem.getProduct() == product)
                    return cartitem;
            }

        }
        return null;
    }

    public boolean contains(Product product)
    {
        if(product != null && getCartItems() != null)
        {
            for(Iterator iterator = getCartItems().iterator(); iterator.hasNext();)
            {
                CartItem cartitem = (CartItem)iterator.next();
                if(cartitem != null && cartitem.getProduct() == product)
                    return true;
            }

        }
        return false;
    }

    public String getToken()
    {
        HashCodeBuilder hashcodebuilder = (new HashCodeBuilder(17, 37)).append(getKey());
        if(getCartItems() != null)
        {
            CartItem cartitem;
            for(Iterator iterator = getCartItems().iterator(); iterator.hasNext(); hashcodebuilder.append(cartitem.getProduct()).append(cartitem.getQuantity()).append(cartitem.getUnitPrice()))
                cartitem = (CartItem)iterator.next();

        }
        return DigestUtils.md5Hex(hashcodebuilder.toString());
    }

    public boolean getIsLowStock()
    {
        if(getCartItems() != null)
        {
            for(Iterator iterator = getCartItems().iterator(); iterator.hasNext();)
            {
                CartItem cartitem = (CartItem)iterator.next();
                if(cartitem != null && cartitem.getIsLowStock())
                    return true;
            }

        }
        return false;
    }

    public boolean hasExpired()
    {
        return (new Date()).after(DateUtils.addSeconds(getModifyDate(), 0x93a80));
    }

    public boolean isCouponAllowed()
    {
        for(Iterator iterator = getPromotions().iterator(); iterator.hasNext();)
        {
            Promotion promotion = (Promotion)iterator.next();
            if(promotion != null && !promotion.getIsCouponAllowed().booleanValue())
                return false;
        }

        return true;
    }

    public boolean isEmpty()
    {
        return getCartItems() == null || getCartItems().isEmpty();
    }

    private static final long serialVersionUID = 0xa4e0ffc11d0131ffL;
    public static final int TIMEOUT = 0x93a80;
    public static final Integer MAX_PRODUCT_COUNT = Integer.valueOf(100);
    public static final String ID_COOKIE_NAME = "cartId";
    public static final String KEY_COOKIE_NAME = "cartKey";
    private String IIIllIlI;
    private Member IIIllIll;
    private Set IIIlllII;


    private class _cls1
        implements Predicate
    {

        public boolean evaluate(Object object)
        {
            GiftItem giftitem = (GiftItem)object;
            return giftitem != null && giftitem.getGift().equals(IIIllIll.getGift());
        }

        final Cart IIIllIlI;
        private final GiftItem IIIllIll;

        _cls1(GiftItem giftitem)
        {
            IIIllIlI = Cart.this;
            IIIllIll = giftitem;
            super();
        }
    }

}
