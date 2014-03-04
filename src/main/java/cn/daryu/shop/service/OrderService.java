// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.entity.*;

// Referenced classes of package net.shopxx.service:
//            BaseService

public interface OrderService
    extends BaseService
{

    public abstract Order findBySn(String s);

    public abstract List findList(Member member, Integer integer, List list, List list1);

    public abstract Page findPage(Member member, Pageable pageable);

    public abstract Page findPage(net.shopxx.entity.Order.OrderStatus orderstatus, net.shopxx.entity.Order.PaymentStatus paymentstatus, net.shopxx.entity.Order.ShippingStatus shippingstatus, Boolean boolean1, Pageable pageable);

    public abstract Long count(net.shopxx.entity.Order.OrderStatus orderstatus, net.shopxx.entity.Order.PaymentStatus paymentstatus, net.shopxx.entity.Order.ShippingStatus shippingstatus, Boolean boolean1);

    public abstract Long waitingPaymentCount(Member member);

    public abstract Long waitingShippingCount(Member member);

    public abstract BigDecimal getSalesAmount(Date date, Date date1);

    public abstract Integer getSalesVolume(Date date, Date date1);

    public abstract void releaseStock();

    public abstract Order build(Cart cart, Receiver receiver, PaymentMethod paymentmethod, ShippingMethod shippingmethod, CouponCode couponcode, boolean flag, String s, 
            boolean flag1, String s1);

    public abstract Order create(Cart cart, Receiver receiver, PaymentMethod paymentmethod, ShippingMethod shippingmethod, CouponCode couponcode, boolean flag, String s, 
            boolean flag1, String s1, Admin admin);

    public abstract void update(Order order, Admin admin);

    public abstract void confirm(Order order, Admin admin);

    public abstract void complete(Order order, Admin admin);

    public abstract void cancel(Order order, Admin admin);

    public abstract void payment(Order order, Payment payment1, Admin admin);

    public abstract void refunds(Order order, Refunds refunds1, Admin admin);

    public abstract void shipping(Order order, Shipping shipping1, Admin admin);

    public abstract void returns(Order order, Returns returns1, Admin admin);
}
