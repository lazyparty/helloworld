// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.entity.Member;
import net.shopxx.entity.Order;

// Referenced classes of package net.shopxx.dao:
//            BaseDao

public interface OrderDao
    extends BaseDao
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
}
