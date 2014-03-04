// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.math.BigDecimal;
import java.util.*;
import javax.persistence.LockModeType;
import net.shopxx.*;
import net.shopxx.dao.*;
import net.shopxx.entity.*;
import net.shopxx.service.OrderService;
import net.shopxx.service.StaticService;
import net.shopxx.util.SettingUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.util.Assert;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class OrderServiceImpl extends BaseServiceImpl
    implements OrderService
{

    public OrderServiceImpl()
    {
    }

    public void setBaseDao(OrderDao orderDao)
    {
        super.setBaseDao(orderDao);
    }

    public Order findBySn(String sn)
    {
        return IIIllIlI.findBySn(sn);
    }

    public List findList(Member member, Integer count, List filters, List orders)
    {
        return IIIllIlI.findList(member, count, filters, orders);
    }

    public Page findPage(Member member, Pageable pageable)
    {
        return IIIllIlI.findPage(member, pageable);
    }

    public Page findPage(net.shopxx.entity.Order.OrderStatus orderStatus, net.shopxx.entity.Order.PaymentStatus paymentStatus, net.shopxx.entity.Order.ShippingStatus shippingStatus, Boolean hasExpired, Pageable pageable)
    {
        return IIIllIlI.findPage(orderStatus, paymentStatus, shippingStatus, hasExpired, pageable);
    }

    public Long count(net.shopxx.entity.Order.OrderStatus orderStatus, net.shopxx.entity.Order.PaymentStatus paymentStatus, net.shopxx.entity.Order.ShippingStatus shippingStatus, Boolean hasExpired)
    {
        return IIIllIlI.count(orderStatus, paymentStatus, shippingStatus, hasExpired);
    }

    public Long waitingPaymentCount(Member member)
    {
        return IIIllIlI.waitingPaymentCount(member);
    }

    public Long waitingShippingCount(Member member)
    {
        return IIIllIlI.waitingShippingCount(member);
    }

    public BigDecimal getSalesAmount(Date beginDate, Date endDate)
    {
        return IIIllIlI.getSalesAmount(beginDate, endDate);
    }

    public Integer getSalesVolume(Date beginDate, Date endDate)
    {
        return IIIllIlI.getSalesVolume(beginDate, endDate);
    }

    public void releaseStock()
    {
        IIIllIlI.releaseStock();
    }

    public Order build(Cart cart, Receiver receiver, PaymentMethod paymentMethod, ShippingMethod shippingMethod, CouponCode couponCode, boolean isInvoice, String invoiceTitle, 
            boolean useBalance, String memo)
    {
        Assert.notNull(cart);
        Assert.notNull(cart.getMember());
        Assert.notEmpty(cart.getCartItems());
        Order order = new Order();
        order.setShippingStatus(net.shopxx.entity.Order.ShippingStatus.unshipped);
        order.setFee(new BigDecimal(0));
        order.setDiscount(cart.getDiscount());
        order.setPoint(Integer.valueOf(cart.getPoint()));
        order.setMemo(memo);
        order.setMember(cart.getMember());
        if(receiver != null)
        {
            order.setConsignee(receiver.getConsignee());
            order.setAreaName(receiver.getAreaName());
            order.setAddress(receiver.getAddress());
            order.setZipCode(receiver.getZipCode());
            order.setPhone(receiver.getPhone());
            order.setArea(receiver.getArea());
        }
        if(!cart.getPromotions().isEmpty())
        {
            StringBuffer stringbuffer = new StringBuffer();
            for(Iterator iterator = cart.getPromotions().iterator(); iterator.hasNext();)
            {
                Promotion promotion = (Promotion)iterator.next();
                if(promotion != null && promotion.getName() != null)
                    stringbuffer.append((new StringBuilder(" ")).append(promotion.getName()).toString());
            }

            if(stringbuffer.length() > 0)
                stringbuffer.deleteCharAt(0);
            order.setPromotion(stringbuffer.toString());
        }
        order.setPaymentMethod(paymentMethod);
        if(shippingMethod != null && paymentMethod != null && paymentMethod.getShippingMethods().contains(shippingMethod))
        {
            BigDecimal bigdecimal = shippingMethod.calculateFreight(Integer.valueOf(cart.getWeight()));
            for(Iterator iterator1 = cart.getPromotions().iterator(); iterator1.hasNext();)
            {
                Promotion promotion1 = (Promotion)iterator1.next();
                if(promotion1.getIsFreeShipping().booleanValue())
                {
                    bigdecimal = new BigDecimal(0);
                    break;
                }
            }

            order.setFreight(bigdecimal);
            order.setShippingMethod(shippingMethod);
        } else
        {
            order.setFreight(new BigDecimal(0));
        }
        if(couponCode != null && cart.isCouponAllowed())
        {
            IIIllllI.lock(couponCode, LockModeType.PESSIMISTIC_READ);
            if(!couponCode.getIsUsed().booleanValue() && couponCode.getCoupon() != null && cart.isValid(couponCode.getCoupon()))
            {
                BigDecimal bigdecimal1 = couponCode.getCoupon().calculatePrice(cart.getAmount());
                BigDecimal bigdecimal2 = cart.getAmount().subtract(bigdecimal1);
                if(bigdecimal2.compareTo(new BigDecimal(0)) > 0)
                    order.setDiscount(cart.getDiscount().add(bigdecimal2));
                order.setCouponCode(couponCode);
            }
        }
        List list = order.getOrderItems();
        for(Iterator iterator2 = cart.getCartItems().iterator(); iterator2.hasNext();)
        {
            CartItem cartitem = (CartItem)iterator2.next();
            if(cartitem != null && cartitem.getProduct() != null)
            {
                Product product = cartitem.getProduct();
                OrderItem orderitem = new OrderItem();
                orderitem.setSn(product.getSn());
                orderitem.setName(product.getName());
                orderitem.setFullName(product.getFullName());
                orderitem.setPrice(cartitem.getUnitPrice());
                orderitem.setWeight(product.getWeight());
                orderitem.setThumbnail(product.getThumbnail());
                orderitem.setIsGift(Boolean.valueOf(false));
                orderitem.setQuantity(cartitem.getQuantity());
                orderitem.setShippedQuantity(Integer.valueOf(0));
                orderitem.setReturnQuantity(Integer.valueOf(0));
                orderitem.setProduct(product);
                orderitem.setOrder(order);
                list.add(orderitem);
            }
        }

        for(Iterator iterator3 = cart.getGiftItems().iterator(); iterator3.hasNext();)
        {
            GiftItem giftitem = (GiftItem)iterator3.next();
            if(giftitem != null && giftitem.getGift() != null)
            {
                Product product1 = giftitem.getGift();
                OrderItem orderitem1 = new OrderItem();
                orderitem1.setSn(product1.getSn());
                orderitem1.setName(product1.getName());
                orderitem1.setFullName(product1.getFullName());
                orderitem1.setPrice(new BigDecimal(0));
                orderitem1.setWeight(product1.getWeight());
                orderitem1.setThumbnail(product1.getThumbnail());
                orderitem1.setIsGift(Boolean.valueOf(true));
                orderitem1.setQuantity(giftitem.getQuantity());
                orderitem1.setShippedQuantity(Integer.valueOf(0));
                orderitem1.setReturnQuantity(Integer.valueOf(0));
                orderitem1.setProduct(product1);
                orderitem1.setOrder(order);
                list.add(orderitem1);
            }
        }

        Setting setting = SettingUtils.get();
        if(setting.getIsInvoiceEnabled().booleanValue() && isInvoice && StringUtils.isNotEmpty(invoiceTitle))
        {
            order.setIsInvoice(Boolean.valueOf(true));
            order.setInvoiceTitle(invoiceTitle);
            order.setTax(order.calculateTax());
        } else
        {
            order.setIsInvoice(Boolean.valueOf(false));
            order.setTax(new BigDecimal(0));
        }
        if(useBalance)
        {
            Member member = cart.getMember();
            if(member.getBalance().compareTo(order.getAmount()) >= 0)
                order.setAmountPaid(order.getAmount());
            else
                order.setAmountPaid(member.getBalance());
        } else
        {
            order.setAmountPaid(new BigDecimal(0));
        }
        if(order.getAmountPayable().compareTo(new BigDecimal(0)) == 0)
        {
            order.setOrderStatus(net.shopxx.entity.Order.OrderStatus.confirmed);
            order.setPaymentStatus(net.shopxx.entity.Order.PaymentStatus.paid);
        } else
        if(order.getAmountPayable().compareTo(new BigDecimal(0)) > 0 && order.getAmountPaid().compareTo(new BigDecimal(0)) > 0)
        {
            order.setOrderStatus(net.shopxx.entity.Order.OrderStatus.confirmed);
            order.setPaymentStatus(net.shopxx.entity.Order.PaymentStatus.partialPayment);
        } else
        {
            order.setOrderStatus(net.shopxx.entity.Order.OrderStatus.unconfirmed);
            order.setPaymentStatus(net.shopxx.entity.Order.PaymentStatus.unpaid);
        }
        if(paymentMethod != null && paymentMethod.getTimeout() != null && order.getPaymentStatus() == net.shopxx.entity.Order.PaymentStatus.unpaid)
            order.setExpire(DateUtils.addMinutes(new Date(), paymentMethod.getTimeout().intValue()));
        return order;
    }

    public Order create(Cart cart, Receiver receiver, PaymentMethod paymentMethod, ShippingMethod shippingMethod, CouponCode couponCode, boolean isInvoice, String invoiceTitle, 
            boolean useBalance, String memo, Admin operator)
    {
        Assert.notNull(cart);
        Assert.notNull(cart.getMember());
        Assert.notEmpty(cart.getCartItems());
        Assert.notNull(receiver);
        Assert.notNull(paymentMethod);
        Assert.notNull(shippingMethod);
        Order order = build(cart, receiver, paymentMethod, shippingMethod, couponCode, isInvoice, invoiceTitle, useBalance, memo);
        order.setSn(IIIlllll.generate(net.shopxx.entity.Sn.Type.order));
        if(paymentMethod.getType() == net.shopxx.entity.PaymentMethod.Type.online)
        {
            order.setLockExpire(DateUtils.addSeconds(new Date(), 10));
            order.setOperator(operator);
        }
        if(order.getCouponCode() != null)
        {
            couponCode.setIsUsed(Boolean.valueOf(true));
            couponCode.setUsedDate(new Date());
            IIIllllI.merge(couponCode);
        }
        for(Iterator iterator = cart.getPromotions().iterator(); iterator.hasNext();)
        {
            Promotion promotion = (Promotion)iterator.next();
            Coupon coupon;
            for(Iterator iterator1 = promotion.getCoupons().iterator(); iterator1.hasNext(); order.getCoupons().add(coupon))
                coupon = (Coupon)iterator1.next();

        }

        Setting setting = SettingUtils.get();
        if(setting.getStockAllocationTime() == net.shopxx.Setting.StockAllocationTime.order || setting.getStockAllocationTime() == net.shopxx.Setting.StockAllocationTime.payment && (order.getPaymentStatus() == net.shopxx.entity.Order.PaymentStatus.partialPayment || order.getPaymentStatus() == net.shopxx.entity.Order.PaymentStatus.paid))
            order.setIsAllocatedStock(Boolean.valueOf(true));
        else
            order.setIsAllocatedStock(Boolean.valueOf(false));
        IIIllIlI.persist(order);
        OrderLog orderlog = new OrderLog();
        orderlog.setType(net.shopxx.entity.OrderLog.Type.create);
        orderlog.setOperator(operator == null ? null : operator.getUsername());
        orderlog.setOrder(order);
        IIIlllII.persist(orderlog);
        Member member = cart.getMember();
        if(order.getAmountPaid().compareTo(new BigDecimal(0)) > 0)
        {
            IIlIIIII.lock(member, LockModeType.PESSIMISTIC_WRITE);
            member.setBalance(member.getBalance().subtract(order.getAmountPaid()));
            IIlIIIII.merge(member);
            Deposit deposit = new Deposit();
            deposit.setType(operator == null ? net.shopxx.entity.Deposit.Type.memberPayment : net.shopxx.entity.Deposit.Type.adminPayment);
            deposit.setCredit(new BigDecimal(0));
            deposit.setDebit(order.getAmountPaid());
            deposit.setBalance(member.getBalance());
            deposit.setOperator(operator == null ? null : operator.getUsername());
            deposit.setMember(member);
            deposit.setOrder(order);
            IIlIIIll.persist(deposit);
        }
        if(setting.getStockAllocationTime() == net.shopxx.Setting.StockAllocationTime.order || setting.getStockAllocationTime() == net.shopxx.Setting.StockAllocationTime.payment && (order.getPaymentStatus() == net.shopxx.entity.Order.PaymentStatus.partialPayment || order.getPaymentStatus() == net.shopxx.entity.Order.PaymentStatus.paid))
        {
            for(Iterator iterator2 = order.getOrderItems().iterator(); iterator2.hasNext();)
            {
                OrderItem orderitem = (OrderItem)iterator2.next();
                if(orderitem != null)
                {
                    Product product = orderitem.getProduct();
                    IIlIIIlI.lock(product, LockModeType.PESSIMISTIC_WRITE);
                    if(product != null && product.getStock() != null)
                    {
                        product.setAllocatedStock(Integer.valueOf(product.getAllocatedStock().intValue() + (orderitem.getQuantity().intValue() - orderitem.getShippedQuantity().intValue())));
                        IIlIIIlI.merge(product);
                        IIIllIlI.flush();
                        IIlIlIII.build(product);
                    }
                }
            }

        }
        IIIlllIl.remove(cart);
        return order;
    }

    public void update(Order order, Admin operator)
    {
        Assert.notNull(order);
        Order order1 = (Order)IIIllIlI.find(order.getId());
        if(order1.getIsAllocatedStock().booleanValue())
        {
            for(Iterator iterator = order1.getOrderItems().iterator(); iterator.hasNext();)
            {
                OrderItem orderitem = (OrderItem)iterator.next();
                if(orderitem != null)
                {
                    Product product = orderitem.getProduct();
                    IIlIIIlI.lock(product, LockModeType.PESSIMISTIC_WRITE);
                    if(product != null && product.getStock() != null)
                    {
                        product.setAllocatedStock(Integer.valueOf(product.getAllocatedStock().intValue() - (orderitem.getQuantity().intValue() - orderitem.getShippedQuantity().intValue())));
                        IIlIIIlI.merge(product);
                        IIIllIlI.flush();
                        IIlIlIII.build(product);
                    }
                }
            }

            for(Iterator iterator1 = order.getOrderItems().iterator(); iterator1.hasNext();)
            {
                OrderItem orderitem1 = (OrderItem)iterator1.next();
                if(orderitem1 != null)
                {
                    Product product1 = orderitem1.getProduct();
                    IIlIIIlI.lock(product1, LockModeType.PESSIMISTIC_WRITE);
                    if(product1 != null && product1.getStock() != null)
                    {
                        product1.setAllocatedStock(Integer.valueOf(product1.getAllocatedStock().intValue() + (orderitem1.getQuantity().intValue() - orderitem1.getShippedQuantity().intValue())));
                        IIlIIIlI.merge(product1);
                        IIlIIIlI.flush();
                        IIlIlIII.build(product1);
                    }
                }
            }

        }
        IIIllIlI.merge(order);
        OrderLog orderlog = new OrderLog();
        orderlog.setType(net.shopxx.entity.OrderLog.Type.modify);
        orderlog.setOperator(operator == null ? null : operator.getUsername());
        orderlog.setOrder(order);
        IIIlllII.persist(orderlog);
    }

    public void confirm(Order order, Admin operator)
    {
        Assert.notNull(order);
        order.setOrderStatus(net.shopxx.entity.Order.OrderStatus.confirmed);
        IIIllIlI.merge(order);
        OrderLog orderlog = new OrderLog();
        orderlog.setType(net.shopxx.entity.OrderLog.Type.confirm);
        orderlog.setOperator(operator == null ? null : operator.getUsername());
        orderlog.setOrder(order);
        IIIlllII.persist(orderlog);
    }

    public void complete(Order order, Admin operator)
    {
        Assert.notNull(order);
        Member member = order.getMember();
        IIlIIIII.lock(member, LockModeType.PESSIMISTIC_WRITE);
        if(order.getShippingStatus() == net.shopxx.entity.Order.ShippingStatus.partialShipment || order.getShippingStatus() == net.shopxx.entity.Order.ShippingStatus.shipped)
        {
            member.setPoint(Long.valueOf(member.getPoint().longValue() + (long)order.getPoint().intValue()));
            Coupon coupon;
            for(Iterator iterator = order.getCoupons().iterator(); iterator.hasNext(); IIIllllI.build(coupon, member))
                coupon = (Coupon)iterator.next();

        }
        if(order.getShippingStatus() == net.shopxx.entity.Order.ShippingStatus.unshipped || order.getShippingStatus() == net.shopxx.entity.Order.ShippingStatus.returned)
        {
            CouponCode couponcode = order.getCouponCode();
            if(couponcode != null)
            {
                couponcode.setIsUsed(Boolean.valueOf(false));
                couponcode.setUsedDate(null);
                IIIllllI.merge(couponcode);
                order.setCouponCode(null);
                IIIllIlI.merge(order);
            }
        }
        member.setAmount(member.getAmount().add(order.getAmountPaid()));
        if(!member.getMemberRank().getIsSpecial().booleanValue())
        {
            MemberRank memberrank = IIlIIIIl.findByAmount(member.getAmount());
            if(memberrank != null && memberrank.getAmount().compareTo(member.getMemberRank().getAmount()) > 0)
                member.setMemberRank(memberrank);
        }
        IIlIIIII.merge(member);
        if(order.getIsAllocatedStock().booleanValue())
        {
            for(Iterator iterator1 = order.getOrderItems().iterator(); iterator1.hasNext();)
            {
                OrderItem orderitem = (OrderItem)iterator1.next();
                if(orderitem != null)
                {
                    Product product = orderitem.getProduct();
                    IIlIIIlI.lock(product, LockModeType.PESSIMISTIC_WRITE);
                    if(product != null && product.getStock() != null)
                    {
                        product.setAllocatedStock(Integer.valueOf(product.getAllocatedStock().intValue() - (orderitem.getQuantity().intValue() - orderitem.getShippedQuantity().intValue())));
                        IIlIIIlI.merge(product);
                        IIIllIlI.flush();
                        IIlIlIII.build(product);
                    }
                }
            }

            order.setIsAllocatedStock(Boolean.valueOf(false));
        }
        for(Iterator iterator2 = order.getOrderItems().iterator(); iterator2.hasNext();)
        {
            OrderItem orderitem1 = (OrderItem)iterator2.next();
            if(orderitem1 != null)
            {
                Product product1 = orderitem1.getProduct();
                IIlIIIlI.lock(product1, LockModeType.PESSIMISTIC_WRITE);
                if(product1 != null)
                {
                    Integer integer = orderitem1.getQuantity();
                    Calendar calendar = Calendar.getInstance();
                    Calendar calendar1 = DateUtils.toCalendar(product1.getWeekSalesDate());
                    Calendar calendar2 = DateUtils.toCalendar(product1.getMonthSalesDate());
                    if(calendar.get(1) != calendar1.get(1) || calendar.get(3) > calendar1.get(3))
                        product1.setWeekSales(Long.valueOf(integer.intValue()));
                    else
                        product1.setWeekSales(Long.valueOf(product1.getWeekSales().longValue() + (long)integer.intValue()));
                    if(calendar.get(1) != calendar2.get(1) || calendar.get(2) > calendar2.get(2))
                        product1.setMonthSales(Long.valueOf(integer.intValue()));
                    else
                        product1.setMonthSales(Long.valueOf(product1.getMonthSales().longValue() + (long)integer.intValue()));
                    product1.setSales(Long.valueOf(product1.getSales().longValue() + (long)integer.intValue()));
                    product1.setWeekSalesDate(new Date());
                    product1.setMonthSalesDate(new Date());
                    IIlIIIlI.merge(product1);
                    IIIllIlI.flush();
                    IIlIlIII.build(product1);
                }
            }
        }

        order.setOrderStatus(net.shopxx.entity.Order.OrderStatus.completed);
        order.setExpire(null);
        IIIllIlI.merge(order);
        OrderLog orderlog = new OrderLog();
        orderlog.setType(net.shopxx.entity.OrderLog.Type.complete);
        orderlog.setOperator(operator == null ? null : operator.getUsername());
        orderlog.setOrder(order);
        IIIlllII.persist(orderlog);
    }

    public void cancel(Order order, Admin operator)
    {
        Assert.notNull(order);
        CouponCode couponcode = order.getCouponCode();
        if(couponcode != null)
        {
            couponcode.setIsUsed(Boolean.valueOf(false));
            couponcode.setUsedDate(null);
            IIIllllI.merge(couponcode);
            order.setCouponCode(null);
            IIIllIlI.merge(order);
        }
        if(order.getIsAllocatedStock().booleanValue())
        {
            for(Iterator iterator = order.getOrderItems().iterator(); iterator.hasNext();)
            {
                OrderItem orderitem = (OrderItem)iterator.next();
                if(orderitem != null)
                {
                    Product product = orderitem.getProduct();
                    IIlIIIlI.lock(product, LockModeType.PESSIMISTIC_WRITE);
                    if(product != null && product.getStock() != null)
                    {
                        product.setAllocatedStock(Integer.valueOf(product.getAllocatedStock().intValue() - (orderitem.getQuantity().intValue() - orderitem.getShippedQuantity().intValue())));
                        IIlIIIlI.merge(product);
                        IIIllIlI.flush();
                        IIlIlIII.build(product);
                    }
                }
            }

            order.setIsAllocatedStock(Boolean.valueOf(false));
        }
        order.setOrderStatus(net.shopxx.entity.Order.OrderStatus.cancelled);
        order.setExpire(null);
        IIIllIlI.merge(order);
        OrderLog orderlog = new OrderLog();
        orderlog.setType(net.shopxx.entity.OrderLog.Type.cancel);
        orderlog.setOperator(operator == null ? null : operator.getUsername());
        orderlog.setOrder(order);
        IIIlllII.persist(orderlog);
    }

    public void payment(Order order, Payment payment, Admin operator)
    {
        Assert.notNull(order);
        Assert.notNull(payment);
        IIIllIlI.lock(order, LockModeType.PESSIMISTIC_WRITE);
        payment.setOrder(order);
        IIlIIlII.merge(payment);
        if(payment.getType() == net.shopxx.entity.Payment.Type.deposit)
        {
            Member member = order.getMember();
            IIlIIIII.lock(member, LockModeType.PESSIMISTIC_WRITE);
            member.setBalance(member.getBalance().subtract(payment.getAmount()));
            IIlIIIII.merge(member);
            Deposit deposit = new Deposit();
            deposit.setType(operator == null ? net.shopxx.entity.Deposit.Type.memberPayment : net.shopxx.entity.Deposit.Type.adminPayment);
            deposit.setCredit(new BigDecimal(0));
            deposit.setDebit(payment.getAmount());
            deposit.setBalance(member.getBalance());
            deposit.setOperator(operator == null ? null : operator.getUsername());
            deposit.setMember(member);
            deposit.setOrder(order);
            IIlIIIll.persist(deposit);
        }
        Setting setting = SettingUtils.get();
        if(!order.getIsAllocatedStock().booleanValue() && setting.getStockAllocationTime() == net.shopxx.Setting.StockAllocationTime.payment)
        {
            for(Iterator iterator = order.getOrderItems().iterator(); iterator.hasNext();)
            {
                OrderItem orderitem = (OrderItem)iterator.next();
                if(orderitem != null)
                {
                    Product product = orderitem.getProduct();
                    IIlIIIlI.lock(product, LockModeType.PESSIMISTIC_WRITE);
                    if(product != null && product.getStock() != null)
                    {
                        product.setAllocatedStock(Integer.valueOf(product.getAllocatedStock().intValue() + (orderitem.getQuantity().intValue() - orderitem.getShippedQuantity().intValue())));
                        IIlIIIlI.merge(product);
                        IIIllIlI.flush();
                        IIlIlIII.build(product);
                    }
                }
            }

            order.setIsAllocatedStock(Boolean.valueOf(true));
        }
        order.setAmountPaid(order.getAmountPaid().add(payment.getAmount()));
        order.setFee(payment.getFee());
        order.setExpire(null);
        if(order.getAmountPaid().compareTo(order.getAmount()) >= 0)
        {
            order.setOrderStatus(net.shopxx.entity.Order.OrderStatus.confirmed);
            order.setPaymentStatus(net.shopxx.entity.Order.PaymentStatus.paid);
        } else
        if(order.getAmountPaid().compareTo(new BigDecimal(0)) > 0)
        {
            order.setOrderStatus(net.shopxx.entity.Order.OrderStatus.confirmed);
            order.setPaymentStatus(net.shopxx.entity.Order.PaymentStatus.partialPayment);
        }
        IIIllIlI.merge(order);
        OrderLog orderlog = new OrderLog();
        orderlog.setType(net.shopxx.entity.OrderLog.Type.payment);
        orderlog.setOperator(operator == null ? null : operator.getUsername());
        orderlog.setOrder(order);
        IIIlllII.persist(orderlog);
    }

    public void refunds(Order order, Refunds refunds, Admin operator)
    {
        Assert.notNull(order);
        Assert.notNull(refunds);
        IIIllIlI.lock(order, LockModeType.PESSIMISTIC_WRITE);
        refunds.setOrder(order);
        IIlIIlIl.persist(refunds);
        if(refunds.getType() == net.shopxx.entity.Refunds.Type.deposit)
        {
            Member member = order.getMember();
            IIlIIIII.lock(member, LockModeType.PESSIMISTIC_WRITE);
            member.setBalance(member.getBalance().add(refunds.getAmount()));
            IIlIIIII.merge(member);
            Deposit deposit = new Deposit();
            deposit.setType(net.shopxx.entity.Deposit.Type.adminRefunds);
            deposit.setCredit(refunds.getAmount());
            deposit.setDebit(new BigDecimal(0));
            deposit.setBalance(member.getBalance());
            deposit.setOperator(operator == null ? null : operator.getUsername());
            deposit.setMember(member);
            deposit.setOrder(order);
            IIlIIIll.persist(deposit);
        }
        order.setAmountPaid(order.getAmountPaid().subtract(refunds.getAmount()));
        order.setExpire(null);
        if(order.getAmountPaid().compareTo(new BigDecimal(0)) == 0)
            order.setPaymentStatus(net.shopxx.entity.Order.PaymentStatus.refunded);
        else
        if(order.getAmountPaid().compareTo(new BigDecimal(0)) > 0)
            order.setPaymentStatus(net.shopxx.entity.Order.PaymentStatus.partialRefunds);
        IIIllIlI.merge(order);
        OrderLog orderlog = new OrderLog();
        orderlog.setType(net.shopxx.entity.OrderLog.Type.refunds);
        orderlog.setOperator(operator == null ? null : operator.getUsername());
        orderlog.setOrder(order);
        IIIlllII.persist(orderlog);
    }

    public void shipping(Order order, Shipping shipping, Admin operator)
    {
        Assert.notNull(order);
        Assert.notNull(shipping);
        Assert.notEmpty(shipping.getShippingItems());
        IIIllIlI.lock(order, LockModeType.PESSIMISTIC_WRITE);
        Setting setting = SettingUtils.get();
        if(!order.getIsAllocatedStock().booleanValue() && setting.getStockAllocationTime() == net.shopxx.Setting.StockAllocationTime.ship)
        {
            for(Iterator iterator = order.getOrderItems().iterator(); iterator.hasNext();)
            {
                OrderItem orderitem = (OrderItem)iterator.next();
                if(orderitem != null)
                {
                    Product product = orderitem.getProduct();
                    IIlIIIlI.lock(product, LockModeType.PESSIMISTIC_WRITE);
                    if(product != null && product.getStock() != null)
                    {
                        product.setAllocatedStock(Integer.valueOf(product.getAllocatedStock().intValue() + (orderitem.getQuantity().intValue() - orderitem.getShippedQuantity().intValue())));
                        IIlIIIlI.merge(product);
                        IIIllIlI.flush();
                        IIlIlIII.build(product);
                    }
                }
            }

            order.setIsAllocatedStock(Boolean.valueOf(true));
        }
        shipping.setOrder(order);
        IIlIIllI.persist(shipping);
        for(Iterator iterator1 = shipping.getShippingItems().iterator(); iterator1.hasNext();)
        {
            ShippingItem shippingitem = (ShippingItem)iterator1.next();
            OrderItem orderitem1 = order.getOrderItem(shippingitem.getSn());
            if(orderitem1 != null)
            {
                Product product1 = orderitem1.getProduct();
                IIlIIIlI.lock(product1, LockModeType.PESSIMISTIC_WRITE);
                if(product1 != null)
                {
                    if(product1.getStock() != null)
                    {
                        product1.setStock(Integer.valueOf(product1.getStock().intValue() - shippingitem.getQuantity().intValue()));
                        if(order.getIsAllocatedStock().booleanValue())
                            product1.setAllocatedStock(Integer.valueOf(product1.getAllocatedStock().intValue() - shippingitem.getQuantity().intValue()));
                    }
                    IIlIIIlI.merge(product1);
                    IIIllIlI.flush();
                    IIlIlIII.build(product1);
                }
                IIIllIll.lock(orderitem1, LockModeType.PESSIMISTIC_WRITE);
                orderitem1.setShippedQuantity(Integer.valueOf(orderitem1.getShippedQuantity().intValue() + shippingitem.getQuantity().intValue()));
            }
        }

        if(order.getShippedQuantity() >= order.getQuantity())
        {
            order.setShippingStatus(net.shopxx.entity.Order.ShippingStatus.shipped);
            order.setIsAllocatedStock(Boolean.valueOf(false));
        } else
        if(order.getShippedQuantity() > 0)
            order.setShippingStatus(net.shopxx.entity.Order.ShippingStatus.partialShipment);
        order.setExpire(null);
        IIIllIlI.merge(order);
        OrderLog orderlog = new OrderLog();
        orderlog.setType(net.shopxx.entity.OrderLog.Type.shipping);
        orderlog.setOperator(operator == null ? null : operator.getUsername());
        orderlog.setOrder(order);
        IIIlllII.persist(orderlog);
    }

    public void returns(Order order, Returns returns, Admin operator)
    {
        Assert.notNull(order);
        Assert.notNull(returns);
        Assert.notEmpty(returns.getReturnsItems());
        IIIllIlI.lock(order, LockModeType.PESSIMISTIC_WRITE);
        returns.setOrder(order);
        IIlIIlll.persist(returns);
        for(Iterator iterator = returns.getReturnsItems().iterator(); iterator.hasNext();)
        {
            ReturnsItem returnsitem = (ReturnsItem)iterator.next();
            OrderItem orderitem = order.getOrderItem(returnsitem.getSn());
            if(orderitem != null)
            {
                IIIllIll.lock(orderitem, LockModeType.PESSIMISTIC_WRITE);
                orderitem.setReturnQuantity(Integer.valueOf(orderitem.getReturnQuantity().intValue() + returnsitem.getQuantity().intValue()));
            }
        }

        if(order.getReturnQuantity() >= order.getShippedQuantity())
            order.setShippingStatus(net.shopxx.entity.Order.ShippingStatus.returned);
        else
        if(order.getReturnQuantity() > 0)
            order.setShippingStatus(net.shopxx.entity.Order.ShippingStatus.partialReturns);
        order.setExpire(null);
        IIIllIlI.merge(order);
        OrderLog orderlog = new OrderLog();
        orderlog.setType(net.shopxx.entity.OrderLog.Type.returns);
        orderlog.setOperator(operator == null ? null : operator.getUsername());
        orderlog.setOrder(order);
        IIIlllII.persist(orderlog);
    }

    public void delete(Order order)
    {
        if(order.getIsAllocatedStock().booleanValue())
        {
            for(Iterator iterator = order.getOrderItems().iterator(); iterator.hasNext();)
            {
                OrderItem orderitem = (OrderItem)iterator.next();
                if(orderitem != null)
                {
                    Product product = orderitem.getProduct();
                    IIlIIIlI.lock(product, LockModeType.PESSIMISTIC_WRITE);
                    if(product != null && product.getStock() != null)
                    {
                        product.setAllocatedStock(Integer.valueOf(product.getAllocatedStock().intValue() - (orderitem.getQuantity().intValue() - orderitem.getShippedQuantity().intValue())));
                        IIlIIIlI.merge(product);
                        IIIllIlI.flush();
                        IIlIlIII.build(product);
                    }
                }
            }

        }
        super.delete(order);
    }

    public volatile void delete(Object obj)
    {
        delete((Order)obj);
    }

    private OrderDao IIIllIlI;
    private OrderItemDao IIIllIll;
    private OrderLogDao IIIlllII;
    private CartDao IIIlllIl;
    private CouponCodeDao IIIllllI;
    private SnDao IIIlllll;
    private MemberDao IIlIIIII;
    private MemberRankDao IIlIIIIl;
    private ProductDao IIlIIIlI;
    private DepositDao IIlIIIll;
    private PaymentDao IIlIIlII;
    private RefundsDao IIlIIlIl;
    private ShippingDao IIlIIllI;
    private ReturnsDao IIlIIlll;
    private StaticService IIlIlIII;
}
