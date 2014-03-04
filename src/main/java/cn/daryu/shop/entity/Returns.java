// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.math.BigDecimal;
import java.util.*;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, ReturnsItem, Order

public class Returns extends BaseEntity
{

    public Returns()
    {
        IIlIIlll = new ArrayList();
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

    public String getTrackingNo()
    {
        return IIIlllIl;
    }

    public void setTrackingNo(String trackingNo)
    {
        IIIlllIl = trackingNo;
    }

    public BigDecimal getFreight()
    {
        return IIIllllI;
    }

    public void setFreight(BigDecimal freight)
    {
        IIIllllI = freight;
    }

    public String getShipper()
    {
        return IIIlllll;
    }

    public void setShipper(String shipper)
    {
        IIIlllll = shipper;
    }

    public String getArea()
    {
        return IIlIIIII;
    }

    public void setArea(String area)
    {
        IIlIIIII = area;
    }

    public String getAddress()
    {
        return IIlIIIIl;
    }

    public void setAddress(String address)
    {
        IIlIIIIl = address;
    }

    public String getZipCode()
    {
        return IIlIIIlI;
    }

    public void setZipCode(String zipCode)
    {
        IIlIIIlI = zipCode;
    }

    public String getPhone()
    {
        return IIlIIIll;
    }

    public void setPhone(String phone)
    {
        IIlIIIll = phone;
    }

    public String getOperator()
    {
        return IIlIIlII;
    }

    public void setOperator(String operator)
    {
        IIlIIlII = operator;
    }

    public String getMemo()
    {
        return IIlIIlIl;
    }

    public void setMemo(String memo)
    {
        IIlIIlIl = memo;
    }

    public Order getOrder()
    {
        return IIlIIllI;
    }

    public void setOrder(Order order)
    {
        IIlIIllI = order;
    }

    public List getReturnsItems()
    {
        return IIlIIlll;
    }

    public void setReturnsItems(List returnsItems)
    {
        IIlIIlll = returnsItems;
    }

    public int getQuantity()
    {
        int i = 0;
        if(getReturnsItems() != null)
        {
            for(Iterator iterator = getReturnsItems().iterator(); iterator.hasNext();)
            {
                ReturnsItem returnsitem = (ReturnsItem)iterator.next();
                if(returnsitem != null && returnsitem.getQuantity() != null)
                    i += returnsitem.getQuantity().intValue();
            }

        }
        return i;
    }

    private static final long serialVersionUID = 0x90b6869306a09714L;
    private String IIIllIlI;
    private String IIIllIll;
    private String IIIlllII;
    private String IIIlllIl;
    private BigDecimal IIIllllI;
    private String IIIlllll;
    private String IIlIIIII;
    private String IIlIIIIl;
    private String IIlIIIlI;
    private String IIlIIIll;
    private String IIlIIlII;
    private String IIlIIlIl;
    private Order IIlIIllI;
    private List IIlIIlll;
}
