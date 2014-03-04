// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.math.BigDecimal;
import java.util.*;
import net.shopxx.Setting;
import net.shopxx.util.SettingUtils;

// Referenced classes of package net.shopxx.entity:
//            OrderEntity, Order, PaymentMethod, DeliveryCorp

public class ShippingMethod extends OrderEntity
{

    public ShippingMethod()
    {
        IIlIIIlI = new HashSet();
        IIlIIIll = new HashSet();
    }

    public String getName()
    {
        return IIIllIlI;
    }

    public void setName(String name)
    {
        IIIllIlI = name;
    }

    public Integer getFirstWeight()
    {
        return IIIllIll;
    }

    public void setFirstWeight(Integer firstWeight)
    {
        IIIllIll = firstWeight;
    }

    public Integer getContinueWeight()
    {
        return IIIlllII;
    }

    public void setContinueWeight(Integer continueWeight)
    {
        IIIlllII = continueWeight;
    }

    public BigDecimal getFirstPrice()
    {
        return IIIlllIl;
    }

    public void setFirstPrice(BigDecimal firstPrice)
    {
        IIIlllIl = firstPrice;
    }

    public BigDecimal getContinuePrice()
    {
        return IIIllllI;
    }

    public void setContinuePrice(BigDecimal continuePrice)
    {
        IIIllllI = continuePrice;
    }

    public String getIcon()
    {
        return IIIlllll;
    }

    public void setIcon(String icon)
    {
        IIIlllll = icon;
    }

    public String getDescription()
    {
        return IIlIIIII;
    }

    public void setDescription(String description)
    {
        IIlIIIII = description;
    }

    public DeliveryCorp getDefaultDeliveryCorp()
    {
        return IIlIIIIl;
    }

    public void setDefaultDeliveryCorp(DeliveryCorp defaultDeliveryCorp)
    {
        IIlIIIIl = defaultDeliveryCorp;
    }

    public Set getPaymentMethods()
    {
        return IIlIIIlI;
    }

    public void setPaymentMethods(Set paymentMethods)
    {
        IIlIIIlI = paymentMethods;
    }

    public Set getOrders()
    {
        return IIlIIIll;
    }

    public void setOrders(Set orders)
    {
        IIlIIIll = orders;
    }

    public BigDecimal calculateFreight(Integer weight)
    {
        Setting setting = SettingUtils.get();
        BigDecimal bigdecimal = new BigDecimal(0);
        if(weight != null)
            if(weight.intValue() <= getFirstWeight().intValue() || getContinuePrice().compareTo(new BigDecimal(0)) == 0)
            {
                bigdecimal = getFirstPrice();
            } else
            {
                double d = Math.ceil((double)(weight.intValue() - getFirstWeight().intValue()) / (double)getContinueWeight().intValue());
                bigdecimal = getFirstPrice().add(getContinuePrice().multiply(new BigDecimal(d)));
            }
        return setting.setScale(bigdecimal);
    }

    public void preRemove()
    {
        Set set = getPaymentMethods();
        if(set != null)
        {
            PaymentMethod paymentmethod;
            for(Iterator iterator = set.iterator(); iterator.hasNext(); paymentmethod.getShippingMethods().remove(this))
                paymentmethod = (PaymentMethod)iterator.next();

        }
        Set set1 = getOrders();
        if(set1 != null)
        {
            Order order;
            for(Iterator iterator1 = set1.iterator(); iterator1.hasNext(); order.setShippingMethod(null))
                order = (Order)iterator1.next();

        }
    }

    private static final long serialVersionUID = 0x5181aadad891a3fdL;
    private String IIIllIlI;
    private Integer IIIllIll;
    private Integer IIIlllII;
    private BigDecimal IIIlllIl;
    private BigDecimal IIIllllI;
    private String IIIlllll;
    private String IIlIIIII;
    private DeliveryCorp IIlIIIIl;
    private Set IIlIIIlI;
    private Set IIlIIIll;
}
