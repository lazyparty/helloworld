// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.math.BigDecimal;
import java.util.*;
import net.shopxx.Setting;
import net.shopxx.util.SettingUtils;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Order, Promotion

public class Coupon extends BaseEntity
{

    public Coupon()
    {
        IIlIIllI = new HashSet();
        IIlIIlll = new HashSet();
        IIlIlIII = new ArrayList();
    }

    public String getName()
    {
        return IIIllIlI;
    }

    public void setName(String name)
    {
        IIIllIlI = name;
    }

    public String getPrefix()
    {
        return IIIllIll;
    }

    public void setPrefix(String prefix)
    {
        IIIllIll = prefix;
    }

    public Date getBeginDate()
    {
        return IIIlllII;
    }

    public void setBeginDate(Date beginDate)
    {
        IIIlllII = beginDate;
    }

    public Date getEndDate()
    {
        return IIIlllIl;
    }

    public void setEndDate(Date endDate)
    {
        IIIlllIl = endDate;
    }

    public BigDecimal getStartPrice()
    {
        return IIIllllI;
    }

    public void setStartPrice(BigDecimal startPrice)
    {
        IIIllllI = startPrice;
    }

    public BigDecimal getEndPrice()
    {
        return IIIlllll;
    }

    public void setEndPrice(BigDecimal endPrice)
    {
        IIIlllll = endPrice;
    }

    public Boolean getIsEnabled()
    {
        return IIlIIIII;
    }

    public void setIsEnabled(Boolean isEnabled)
    {
        IIlIIIII = isEnabled;
    }

    public Boolean getIsExchange()
    {
        return IIlIIIIl;
    }

    public void setIsExchange(Boolean isExchange)
    {
        IIlIIIIl = isExchange;
    }

    public Integer getPoint()
    {
        return IIlIIIlI;
    }

    public void setPoint(Integer point)
    {
        IIlIIIlI = point;
    }

    public Operator getPriceOperator()
    {
        return IIlIIIll;
    }

    public void setPriceOperator(Operator priceOperator)
    {
        IIlIIIll = priceOperator;
    }

    public BigDecimal getPriceValue()
    {
        return IIlIIlII;
    }

    public void setPriceValue(BigDecimal priceValue)
    {
        IIlIIlII = priceValue;
    }

    public String getIntroduction()
    {
        return IIlIIlIl;
    }

    public void setIntroduction(String introduction)
    {
        IIlIIlIl = introduction;
    }

    public Set getCouponCodes()
    {
        return IIlIIllI;
    }

    public void setCouponCodes(Set couponCodes)
    {
        IIlIIllI = couponCodes;
    }

    public Set getPromotions()
    {
        return IIlIIlll;
    }

    public void setPromotions(Set promotions)
    {
        IIlIIlll = promotions;
    }

    public List getOrders()
    {
        return IIlIlIII;
    }

    public void setOrders(List orders)
    {
        IIlIlIII = orders;
    }

    public boolean hasBegun()
    {
        return getBeginDate() == null || (new Date()).after(getBeginDate());
    }

    public boolean hasExpired()
    {
        return getEndDate() != null && (new Date()).after(getEndDate());
    }

    public BigDecimal calculatePrice(BigDecimal price)
    {
        if(price != null && getPriceOperator() != null && getPriceValue() != null)
        {
            Setting setting = SettingUtils.get();
            BigDecimal bigdecimal;
            if(getPriceOperator() == Operator.add)
                bigdecimal = price.add(getPriceValue());
            else
            if(getPriceOperator() == Operator.subtract)
                bigdecimal = price.subtract(getPriceValue());
            else
            if(getPriceOperator() == Operator.multiply)
                bigdecimal = price.multiply(getPriceValue());
            else
                bigdecimal = price.divide(getPriceValue());
            bigdecimal = setting.setScale(bigdecimal);
            return bigdecimal.compareTo(new BigDecimal(0)) <= 0 ? new BigDecimal(0) : bigdecimal;
        } else
        {
            return price;
        }
    }

    public void preRemove()
    {
        Set set = getPromotions();
        if(set != null)
        {
            Promotion promotion;
            for(Iterator iterator = set.iterator(); iterator.hasNext(); promotion.getCoupons().remove(this))
                promotion = (Promotion)iterator.next();

        }
        List list = getOrders();
        if(list != null)
        {
            Order order;
            for(Iterator iterator1 = list.iterator(); iterator1.hasNext(); order.getCoupons().remove(this))
                order = (Order)iterator1.next();

        }
    }

    private static final long serialVersionUID = 0x9241d1dbe303f5e6L;
    private String IIIllIlI;
    private String IIIllIll;
    private Date IIIlllII;
    private Date IIIlllIl;
    private BigDecimal IIIllllI;
    private BigDecimal IIIlllll;
    private Boolean IIlIIIII;
    private Boolean IIlIIIIl;
    private Integer IIlIIIlI;
    private Operator IIlIIIll;
    private BigDecimal IIlIIlII;
    private String IIlIIlIl;
    private Set IIlIIllI;
    private Set IIlIIlll;
    private List IIlIlIII;

    private class Operator extends Enum
    {

        public static Operator[] values()
        {
            Operator aoperator[];
            int i;
            Operator aoperator1[];
            System.arraycopy(aoperator = ENUM$VALUES, 0, aoperator1 = new Operator[i = aoperator.length], 0, i);
            return aoperator1;
        }

        public static Operator valueOf(String s)
        {
            return (Operator)Enum.valueOf(net/shopxx/entity/Coupon$Operator, s);
        }

        public static final Operator add;
        public static final Operator subtract;
        public static final Operator multiply;
        public static final Operator divide;
        private static final Operator ENUM$VALUES[];

        static 
        {
            add = new Operator("add", 0);
            subtract = new Operator("subtract", 1);
            multiply = new Operator("multiply", 2);
            divide = new Operator("divide", 3);
            ENUM$VALUES = (new Operator[] {
                add, subtract, multiply, divide
            });
        }

        private Operator(String s, int i)
        {
            super(s, i);
        }
    }

}
