// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.math.BigDecimal;
import java.util.*;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, ShippingItem, Order

public class Shipping extends BaseEntity
{

    public Shipping()
    {
        IIlIlIIl = new ArrayList();
    }

    public String getSn()
    {
        return IIIllIlI;
    }

    public void setSn(String sn)
    {
        IIIllIlI = sn;
    }

    public String getShippingMethod()
    {
        return IIIllIll;
    }

    public void setShippingMethod(String shippingMethod)
    {
        IIIllIll = shippingMethod;
    }

    public String getDeliveryCorp()
    {
        return IIIlllII;
    }

    public void setDeliveryCorp(String deliveryCorp)
    {
        IIIlllII = deliveryCorp;
    }

    public String getDeliveryCorpUrl()
    {
        return IIIlllIl;
    }

    public void setDeliveryCorpUrl(String deliveryCorpUrl)
    {
        IIIlllIl = deliveryCorpUrl;
    }

    public String getDeliveryCorpCode()
    {
        return IIIllllI;
    }

    public void setDeliveryCorpCode(String deliveryCorpCode)
    {
        IIIllllI = deliveryCorpCode;
    }

    public String getTrackingNo()
    {
        return IIIlllll;
    }

    public void setTrackingNo(String trackingNo)
    {
        IIIlllll = trackingNo;
    }

    public BigDecimal getFreight()
    {
        return IIlIIIII;
    }

    public void setFreight(BigDecimal freight)
    {
        IIlIIIII = freight;
    }

    public String getConsignee()
    {
        return IIlIIIIl;
    }

    public void setConsignee(String consignee)
    {
        IIlIIIIl = consignee;
    }

    public String getArea()
    {
        return IIlIIIlI;
    }

    public void setArea(String area)
    {
        IIlIIIlI = area;
    }

    public String getAddress()
    {
        return IIlIIIll;
    }

    public void setAddress(String address)
    {
        IIlIIIll = address;
    }

    public String getZipCode()
    {
        return IIlIIlII;
    }

    public void setZipCode(String zipCode)
    {
        IIlIIlII = zipCode;
    }

    public String getPhone()
    {
        return IIlIIlIl;
    }

    public void setPhone(String phone)
    {
        IIlIIlIl = phone;
    }

    public String getOperator()
    {
        return IIlIIllI;
    }

    public void setOperator(String operator)
    {
        IIlIIllI = operator;
    }

    public String getMemo()
    {
        return IIlIIlll;
    }

    public void setMemo(String memo)
    {
        IIlIIlll = memo;
    }

    public Order getOrder()
    {
        return IIlIlIII;
    }

    public void setOrder(Order order)
    {
        IIlIlIII = order;
    }

    public List getShippingItems()
    {
        return IIlIlIIl;
    }

    public void setShippingItems(List shippingItems)
    {
        IIlIlIIl = shippingItems;
    }

    public int getQuantity()
    {
        int i = 0;
        if(getShippingItems() != null)
        {
            for(Iterator iterator = getShippingItems().iterator(); iterator.hasNext();)
            {
                ShippingItem shippingitem = (ShippingItem)iterator.next();
                if(shippingitem != null && shippingitem.getQuantity() != null)
                    i += shippingitem.getQuantity().intValue();
            }

        }
        return i;
    }

    private static final long serialVersionUID = 0xfc5e1f89bd51dfd1L;
    private String IIIllIlI;
    private String IIIllIll;
    private String IIIlllII;
    private String IIIlllIl;
    private String IIIllllI;
    private String IIIlllll;
    private BigDecimal IIlIIIII;
    private String IIlIIIIl;
    private String IIlIIIlI;
    private String IIlIIIll;
    private String IIlIIlII;
    private String IIlIIlIl;
    private String IIlIIllI;
    private String IIlIIlll;
    private Order IIlIlIII;
    private List IIlIlIIl;
}
