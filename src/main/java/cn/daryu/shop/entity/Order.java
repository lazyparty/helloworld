// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.math.BigDecimal;
import java.util.*;
import net.shopxx.Setting;
import net.shopxx.util.SettingUtils;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Area, Deposit, OrderItem, 
//            PaymentMethod, ShippingMethod, Admin, Member, 
//            CouponCode

public class Order extends BaseEntity
{

    public Order()
    {
        IIlllIIl = new ArrayList();
        IIlllIlI = new ArrayList();
        IIlllIll = new HashSet();
        IIllllII = new HashSet();
        IIllllIl = new HashSet();
        IIlllllI = new HashSet();
        IIllllll = new HashSet();
        IlIIIIII = new HashSet();
    }

    public String getSn()
    {
        return IIIllIll;
    }

    public void setSn(String sn)
    {
        IIIllIll = sn;
    }

    public OrderStatus getOrderStatus()
    {
        return IIIlllII;
    }

    public void setOrderStatus(OrderStatus orderStatus)
    {
        IIIlllII = orderStatus;
    }

    public PaymentStatus getPaymentStatus()
    {
        return IIIlllIl;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus)
    {
        IIIlllIl = paymentStatus;
    }

    public ShippingStatus getShippingStatus()
    {
        return IIIllllI;
    }

    public void setShippingStatus(ShippingStatus shippingStatus)
    {
        IIIllllI = shippingStatus;
    }

    public BigDecimal getFee()
    {
        return IIIlllll;
    }

    public void setFee(BigDecimal fee)
    {
        IIIlllll = fee;
    }

    public BigDecimal getFreight()
    {
        return IIlIIIII;
    }

    public void setFreight(BigDecimal freight)
    {
        IIlIIIII = freight;
    }

    public BigDecimal getDiscount()
    {
        return IIlIIIIl;
    }

    public void setDiscount(BigDecimal discount)
    {
        IIlIIIIl = discount;
    }

    public BigDecimal getAmountPaid()
    {
        return IIlIIIlI;
    }

    public void setAmountPaid(BigDecimal amountPaid)
    {
        IIlIIIlI = amountPaid;
    }

    public Integer getPoint()
    {
        return IIlIIIll;
    }

    public void setPoint(Integer point)
    {
        IIlIIIll = point;
    }

    public String getConsignee()
    {
        return IIlIIlII;
    }

    public void setConsignee(String consignee)
    {
        IIlIIlII = consignee;
    }

    public String getAreaName()
    {
        return IIlIIlIl;
    }

    public void setAreaName(String areaName)
    {
        IIlIIlIl = areaName;
    }

    public String getAddress()
    {
        return IIlIIllI;
    }

    public void setAddress(String address)
    {
        IIlIIllI = address;
    }

    public String getZipCode()
    {
        return IIlIIlll;
    }

    public void setZipCode(String zipCode)
    {
        IIlIIlll = zipCode;
    }

    public String getPhone()
    {
        return IIlIlIII;
    }

    public void setPhone(String phone)
    {
        IIlIlIII = phone;
    }

    public Boolean getIsInvoice()
    {
        return IIlIlIIl;
    }

    public void setIsInvoice(Boolean isInvoice)
    {
        IIlIlIIl = isInvoice;
    }

    public String getInvoiceTitle()
    {
        return IIlIlIlI;
    }

    public void setInvoiceTitle(String invoiceTitle)
    {
        IIlIlIlI = invoiceTitle;
    }

    public BigDecimal getTax()
    {
        return IIlIlIll;
    }

    public void setTax(BigDecimal tax)
    {
        IIlIlIll = tax;
    }

    public String getMemo()
    {
        return IIlIllII;
    }

    public void setMemo(String memo)
    {
        IIlIllII = memo;
    }

    public String getPromotion()
    {
        return IIlIllIl;
    }

    public void setPromotion(String promotion)
    {
        IIlIllIl = promotion;
    }

    public Date getExpire()
    {
        return IIlIlllI;
    }

    public void setExpire(Date expire)
    {
        IIlIlllI = expire;
    }

    public Date getLockExpire()
    {
        return IIlIllll;
    }

    public void setLockExpire(Date lockExpire)
    {
        IIlIllll = lockExpire;
    }

    public Boolean getIsAllocatedStock()
    {
        return IIllIIII;
    }

    public void setIsAllocatedStock(Boolean isAllocatedStock)
    {
        IIllIIII = isAllocatedStock;
    }

    public String getPaymentMethodName()
    {
        return IIllIIIl;
    }

    public void setPaymentMethodName(String paymentMethodName)
    {
        IIllIIIl = paymentMethodName;
    }

    public String getShippingMethodName()
    {
        return IIllIIlI;
    }

    public void setShippingMethodName(String shippingMethodName)
    {
        IIllIIlI = shippingMethodName;
    }

    public Area getArea()
    {
        return IIllIIll;
    }

    public void setArea(Area area)
    {
        IIllIIll = area;
    }

    public PaymentMethod getPaymentMethod()
    {
        return IIllIlII;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod)
    {
        IIllIlII = paymentMethod;
    }

    public ShippingMethod getShippingMethod()
    {
        return IIllIlIl;
    }

    public void setShippingMethod(ShippingMethod shippingMethod)
    {
        IIllIlIl = shippingMethod;
    }

    public Admin getOperator()
    {
        return IIllIllI;
    }

    public void setOperator(Admin operator)
    {
        IIllIllI = operator;
    }

    public Member getMember()
    {
        return IIllIlll;
    }

    public void setMember(Member member)
    {
        IIllIlll = member;
    }

    public CouponCode getCouponCode()
    {
        return IIlllIII;
    }

    public void setCouponCode(CouponCode couponCode)
    {
        IIlllIII = couponCode;
    }

    public List getCoupons()
    {
        return IIlllIIl;
    }

    public void setCoupons(List coupons)
    {
        IIlllIIl = coupons;
    }

    public List getOrderItems()
    {
        return IIlllIlI;
    }

    public void setOrderItems(List orderItems)
    {
        IIlllIlI = orderItems;
    }

    public Set getOrderLogs()
    {
        return IIlllIll;
    }

    public void setOrderLogs(Set orderLogs)
    {
        IIlllIll = orderLogs;
    }

    public Set getDeposits()
    {
        return IIllllII;
    }

    public void setDeposits(Set deposits)
    {
        IIllllII = deposits;
    }

    public Set getPayments()
    {
        return IIllllIl;
    }

    public void setPayments(Set payments)
    {
        IIllllIl = payments;
    }

    public Set getRefunds()
    {
        return IIlllllI;
    }

    public void setRefunds(Set refunds)
    {
        IIlllllI = refunds;
    }

    public Set getShippings()
    {
        return IIllllll;
    }

    public void setShippings(Set shippings)
    {
        IIllllll = shippings;
    }

    public Set getReturns()
    {
        return IlIIIIII;
    }

    public void setReturns(Set returns)
    {
        IlIIIIII = returns;
    }

    public String getProductName()
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(getOrderItems() != null)
        {
            for(Iterator iterator = getOrderItems().iterator(); iterator.hasNext();)
            {
                OrderItem orderitem = (OrderItem)iterator.next();
                if(orderitem != null && orderitem.getFullName() != null)
                    stringbuffer.append(" ").append(orderitem.getFullName());
            }

            if(stringbuffer.length() > 0)
                stringbuffer.deleteCharAt(0);
        }
        return stringbuffer.toString();
    }

    public int getWeight()
    {
        int i = 0;
        if(getOrderItems() != null)
        {
            for(Iterator iterator = getOrderItems().iterator(); iterator.hasNext();)
            {
                OrderItem orderitem = (OrderItem)iterator.next();
                if(orderitem != null)
                    i += orderitem.getTotalWeight();
            }

        }
        return i;
    }

    public int getQuantity()
    {
        int i = 0;
        if(getOrderItems() != null)
        {
            for(Iterator iterator = getOrderItems().iterator(); iterator.hasNext();)
            {
                OrderItem orderitem = (OrderItem)iterator.next();
                if(orderitem != null && orderitem.getQuantity() != null)
                    i += orderitem.getQuantity().intValue();
            }

        }
        return i;
    }

    public int getShippedQuantity()
    {
        int i = 0;
        if(getOrderItems() != null)
        {
            for(Iterator iterator = getOrderItems().iterator(); iterator.hasNext();)
            {
                OrderItem orderitem = (OrderItem)iterator.next();
                if(orderitem != null && orderitem.getShippedQuantity() != null)
                    i += orderitem.getShippedQuantity().intValue();
            }

        }
        return i;
    }

    public int getReturnQuantity()
    {
        int i = 0;
        if(getOrderItems() != null)
        {
            for(Iterator iterator = getOrderItems().iterator(); iterator.hasNext();)
            {
                OrderItem orderitem = (OrderItem)iterator.next();
                if(orderitem != null && orderitem.getReturnQuantity() != null)
                    i += orderitem.getReturnQuantity().intValue();
            }

        }
        return i;
    }

    public BigDecimal getPrice()
    {
        BigDecimal bigdecimal = new BigDecimal(0);
        if(getOrderItems() != null)
        {
            for(Iterator iterator = getOrderItems().iterator(); iterator.hasNext();)
            {
                OrderItem orderitem = (OrderItem)iterator.next();
                if(orderitem != null && orderitem.getSubtotal() != null)
                    bigdecimal = bigdecimal.add(orderitem.getSubtotal());
            }

        }
        return bigdecimal;
    }

    public BigDecimal getAmount()
    {
        BigDecimal bigdecimal = getPrice().subtract(getDiscount() == null ? new BigDecimal(0) : getDiscount()).add(getFreight() == null ? new BigDecimal(0) : getFreight()).add(getFee() == null ? new BigDecimal(0) : getFee()).add(getTax() == null ? new BigDecimal(0) : getTax());
        return bigdecimal.compareTo(new BigDecimal(0)) <= 0 ? new BigDecimal(0) : bigdecimal;
    }

    public BigDecimal getAmountPayable()
    {
        BigDecimal bigdecimal = getAmount().subtract(getAmountPaid());
        return bigdecimal.compareTo(new BigDecimal(0)) <= 0 ? new BigDecimal(0) : bigdecimal;
    }

    public boolean isExpired()
    {
        return getExpire() != null && (new Date()).after(getExpire());
    }

    public OrderItem getOrderItem(String sn)
    {
        if(sn != null && getOrderItems() != null)
        {
            for(Iterator iterator = getOrderItems().iterator(); iterator.hasNext();)
            {
                OrderItem orderitem = (OrderItem)iterator.next();
                if(orderitem != null && sn.equalsIgnoreCase(orderitem.getSn()))
                    return orderitem;
            }

        }
        return null;
    }

    public boolean isLocked(Admin operator)
    {
        return getLockExpire() != null && (new Date()).before(getLockExpire()) && getOperator() != operator;
    }

    public BigDecimal calculateTax()
    {
        Setting setting = SettingUtils.get();
        BigDecimal bigdecimal;
        if(setting.getIsTaxPriceEnabled().booleanValue())
            bigdecimal = getPrice().subtract(getDiscount()).multiply(new BigDecimal(setting.getTaxRate().toString()));
        else
            bigdecimal = new BigDecimal(0);
        return setting.setScale(bigdecimal);
    }

    public void prePersist()
    {
        if(getArea() != null)
            setAreaName(getArea().getFullName());
        if(getPaymentMethod() != null)
            setPaymentMethodName(getPaymentMethod().getName());
        if(getShippingMethod() != null)
            setShippingMethodName(getShippingMethod().getName());
    }

    public void preUpdate()
    {
        if(getArea() != null)
            setAreaName(getArea().getFullName());
        if(getPaymentMethod() != null)
            setPaymentMethodName(getPaymentMethod().getName());
        if(getShippingMethod() != null)
            setShippingMethodName(getShippingMethod().getName());
    }

    public void preRemove()
    {
        Set set = getDeposits();
        if(set != null)
        {
            Deposit deposit;
            for(Iterator iterator = set.iterator(); iterator.hasNext(); deposit.setOrder(null))
                deposit = (Deposit)iterator.next();

        }
    }

    private static final long serialVersionUID = 0x742b8fda582c8dbcL;
    private static final String IIIllIlI = " ";
    private String IIIllIll;
    private OrderStatus IIIlllII;
    private PaymentStatus IIIlllIl;
    private ShippingStatus IIIllllI;
    private BigDecimal IIIlllll;
    private BigDecimal IIlIIIII;
    private BigDecimal IIlIIIIl;
    private BigDecimal IIlIIIlI;
    private Integer IIlIIIll;
    private String IIlIIlII;
    private String IIlIIlIl;
    private String IIlIIllI;
    private String IIlIIlll;
    private String IIlIlIII;
    private Boolean IIlIlIIl;
    private String IIlIlIlI;
    private BigDecimal IIlIlIll;
    private String IIlIllII;
    private String IIlIllIl;
    private Date IIlIlllI;
    private Date IIlIllll;
    private Boolean IIllIIII;
    private String IIllIIIl;
    private String IIllIIlI;
    private Area IIllIIll;
    private PaymentMethod IIllIlII;
    private ShippingMethod IIllIlIl;
    private Admin IIllIllI;
    private Member IIllIlll;
    private CouponCode IIlllIII;
    private List IIlllIIl;
    private List IIlllIlI;
    private Set IIlllIll;
    private Set IIllllII;
    private Set IIllllIl;
    private Set IIlllllI;
    private Set IIllllll;
    private Set IlIIIIII;
}
