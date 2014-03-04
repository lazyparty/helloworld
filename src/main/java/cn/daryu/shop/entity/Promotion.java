// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.math.BigDecimal;
import java.util.*;

// Referenced classes of package net.shopxx.entity:
//            OrderEntity

public class Promotion extends OrderEntity
{

    public Promotion()
    {
        IIlIlIIl = new HashSet();
        IIlIlIlI = new HashSet();
        IIlIlIll = new HashSet();
        IIlIllII = new HashSet();
        IIlIllIl = new HashSet();
        IIlIlllI = new ArrayList();
    }

    public String getName()
    {
        return IIIlllII;
    }

    public void setName(String name)
    {
        IIIlllII = name;
    }

    public String getTitle()
    {
        return IIIlllIl;
    }

    public void setTitle(String title)
    {
        IIIlllIl = title;
    }

    public Date getBeginDate()
    {
        return IIIllllI;
    }

    public void setBeginDate(Date beginDate)
    {
        IIIllllI = beginDate;
    }

    public Date getEndDate()
    {
        return IIIlllll;
    }

    public void setEndDate(Date endDate)
    {
        IIIlllll = endDate;
    }

    public BigDecimal getStartPrice()
    {
        return IIlIIIII;
    }

    public void setStartPrice(BigDecimal startPrice)
    {
        IIlIIIII = startPrice;
    }

    public BigDecimal getEndPrice()
    {
        return IIlIIIIl;
    }

    public void setEndPrice(BigDecimal endPrice)
    {
        IIlIIIIl = endPrice;
    }

    public Operator getPriceOperator()
    {
        return IIlIIIlI;
    }

    public void setPriceOperator(Operator priceOperator)
    {
        IIlIIIlI = priceOperator;
    }

    public BigDecimal getPriceValue()
    {
        return IIlIIIll;
    }

    public void setPriceValue(BigDecimal priceValue)
    {
        IIlIIIll = priceValue;
    }

    public Operator getPointOperator()
    {
        return IIlIIlII;
    }

    public void setPointOperator(Operator pointOperator)
    {
        IIlIIlII = pointOperator;
    }

    public BigDecimal getPointValue()
    {
        return IIlIIlIl;
    }

    public void setPointValue(BigDecimal pointValue)
    {
        IIlIIlIl = pointValue;
    }

    public Boolean getIsFreeShipping()
    {
        return IIlIIllI;
    }

    public void setIsFreeShipping(Boolean isFreeShipping)
    {
        IIlIIllI = isFreeShipping;
    }

    public Boolean getIsCouponAllowed()
    {
        return IIlIIlll;
    }

    public void setIsCouponAllowed(Boolean isCouponAllowed)
    {
        IIlIIlll = isCouponAllowed;
    }

    public String getIntroduction()
    {
        return IIlIlIII;
    }

    public void setIntroduction(String introduction)
    {
        IIlIlIII = introduction;
    }

    public Set getMemberRanks()
    {
        return IIlIlIIl;
    }

    public void setMemberRanks(Set memberRanks)
    {
        IIlIlIIl = memberRanks;
    }

    public Set getProductCategories()
    {
        return IIlIlIlI;
    }

    public void setProductCategories(Set productCategories)
    {
        IIlIlIlI = productCategories;
    }

    public Set getProducts()
    {
        return IIlIlIll;
    }

    public void setProducts(Set products)
    {
        IIlIlIll = products;
    }

    public Set getBrands()
    {
        return IIlIllII;
    }

    public void setBrands(Set brands)
    {
        IIlIllII = brands;
    }

    public Set getCoupons()
    {
        return IIlIllIl;
    }

    public void setCoupons(Set coupons)
    {
        IIlIllIl = coupons;
    }

    public List getGiftItems()
    {
        return IIlIlllI;
    }

    public void setGiftItems(List giftItems)
    {
        IIlIlllI = giftItems;
    }

    public boolean hasBegun()
    {
        return getBeginDate() == null || (new Date()).after(getBeginDate());
    }

    public boolean hasEnded()
    {
        return getEndDate() != null && (new Date()).after(getEndDate());
    }

    public String getPath()
    {
        if(getId() != null)
            return (new StringBuilder("/promotion/content/")).append(getId()).append(".jhtml").toString();
        else
            return null;
    }

    public BigDecimal calculatePrice(BigDecimal price)
    {
        if(price != null && getPriceOperator() != null && getPriceValue() != null)
        {
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
            return bigdecimal.compareTo(new BigDecimal(0)) <= 0 ? new BigDecimal(0) : bigdecimal;
        } else
        {
            return price;
        }
    }

    public Integer calculatePoint(Integer point)
    {
        if(point != null && getPointOperator() != null && getPointValue() != null)
        {
            BigDecimal bigdecimal;
            if(getPointOperator() == Operator.add)
                bigdecimal = (new BigDecimal(point.intValue())).add(getPointValue());
            else
            if(getPointOperator() == Operator.subtract)
                bigdecimal = (new BigDecimal(point.intValue())).subtract(getPointValue());
            else
            if(getPointOperator() == Operator.multiply)
                bigdecimal = (new BigDecimal(point.intValue())).multiply(getPointValue());
            else
                bigdecimal = (new BigDecimal(point.intValue())).divide(getPointValue());
            return Integer.valueOf(bigdecimal.compareTo(new BigDecimal(0)) <= 0 ? 0 : bigdecimal.intValue());
        } else
        {
            return point;
        }
    }

    private static final long serialVersionUID = 0x3115ece16a7ffda7L;
    private static final String IIIllIlI = "/promotion/content";
    private static final String IIIllIll = ".jhtml";
    private String IIIlllII;
    private String IIIlllIl;
    private Date IIIllllI;
    private Date IIIlllll;
    private BigDecimal IIlIIIII;
    private BigDecimal IIlIIIIl;
    private Operator IIlIIIlI;
    private BigDecimal IIlIIIll;
    private Operator IIlIIlII;
    private BigDecimal IIlIIlIl;
    private Boolean IIlIIllI;
    private Boolean IIlIIlll;
    private String IIlIlIII;
    private Set IIlIlIIl;
    private Set IIlIlIlI;
    private Set IIlIlIll;
    private Set IIlIllII;
    private Set IIlIllIl;
    private List IIlIlllI;

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
            return (Operator)Enum.valueOf(net/shopxx/entity/Promotion$Operator, s);
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
