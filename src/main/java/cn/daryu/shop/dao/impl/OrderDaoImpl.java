// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.*;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.OrderDao;
import net.shopxx.entity.*;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class OrderDaoImpl extends BaseDaoImpl
    implements OrderDao
{

    public OrderDaoImpl()
    {
    }

    public Order findBySn(String sn)
    {
        if(sn == null)
            return null;
        String s = "select orders from Order orders where lower(orders.sn) = lower(:sn)";
        try
        {
            return (Order)IIIllIlI.createQuery(s, net/shopxx/entity/Order).setFlushMode(FlushModeType.COMMIT).setParameter("sn", sn).getSingleResult();
        }
        catch(NoResultException noresultexception)
        {
            return null;
        }
    }

    public List findList(Member member, Integer count, List filters, List orders)
    {
        if(member == null)
        {
            return Collections.emptyList();
        } else
        {
            CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
            CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Order);
            Root root = criteriaquery.from(net/shopxx/entity/Order);
            criteriaquery.select(root);
            criteriaquery.where(criteriabuilder.equal(root.get("member"), member));
            return super.IIIllIlI(criteriaquery, null, count, filters, orders);
        }
    }

    public Page findPage(Member member, Pageable pageable)
    {
        if(member == null)
        {
            return new Page(Collections.emptyList(), 0L, pageable);
        } else
        {
            CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
            CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Order);
            Root root = criteriaquery.from(net/shopxx/entity/Order);
            criteriaquery.select(root);
            criteriaquery.where(criteriabuilder.equal(root.get("member"), member));
            return super.IIIllIlI(criteriaquery, pageable);
        }
    }

    public Page findPage(net.shopxx.entity.Order.OrderStatus orderStatus, net.shopxx.entity.Order.PaymentStatus paymentStatus, net.shopxx.entity.Order.ShippingStatus shippingStatus, Boolean hasExpired, Pageable pageable)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Order);
        Root root = criteriaquery.from(net/shopxx/entity/Order);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        if(orderStatus != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("orderStatus"), orderStatus));
        if(paymentStatus != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("paymentStatus"), paymentStatus));
        if(shippingStatus != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("shippingStatus"), shippingStatus));
        if(hasExpired != null)
            if(hasExpired.booleanValue())
                predicate = criteriabuilder.and(new Predicate[] {
                    predicate, root.get("expire").isNotNull(), criteriabuilder.lessThan(root.get("expire"), new Date())
                });
            else
                predicate = criteriabuilder.and(predicate, criteriabuilder.or(root.get("expire").isNull(), criteriabuilder.greaterThanOrEqualTo(root.get("expire"), new Date())));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, pageable);
    }

    public Long count(net.shopxx.entity.Order.OrderStatus orderStatus, net.shopxx.entity.Order.PaymentStatus paymentStatus, net.shopxx.entity.Order.ShippingStatus shippingStatus, Boolean hasExpired)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Order);
        Root root = criteriaquery.from(net/shopxx/entity/Order);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        if(orderStatus != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("orderStatus"), orderStatus));
        if(paymentStatus != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("paymentStatus"), paymentStatus));
        if(shippingStatus != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("shippingStatus"), shippingStatus));
        if(hasExpired != null)
            if(hasExpired.booleanValue())
                predicate = criteriabuilder.and(new Predicate[] {
                    predicate, root.get("expire").isNotNull(), criteriabuilder.lessThan(root.get("expire"), new Date())
                });
            else
                predicate = criteriabuilder.and(predicate, criteriabuilder.or(root.get("expire").isNull(), criteriabuilder.greaterThanOrEqualTo(root.get("expire"), new Date())));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, null);
    }

    public Long waitingPaymentCount(Member member)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Order);
        Root root = criteriaquery.from(net/shopxx/entity/Order);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        predicate = criteriabuilder.and(new Predicate[] {
            predicate, criteriabuilder.notEqual(root.get("orderStatus"), net.shopxx.entity.Order.OrderStatus.completed), criteriabuilder.notEqual(root.get("orderStatus"), net.shopxx.entity.Order.OrderStatus.cancelled)
        });
        predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.equal(root.get("paymentStatus"), net.shopxx.entity.Order.PaymentStatus.unpaid), criteriabuilder.equal(root.get("paymentStatus"), net.shopxx.entity.Order.PaymentStatus.partialPayment)));
        predicate = criteriabuilder.and(predicate, criteriabuilder.or(root.get("expire").isNull(), criteriabuilder.greaterThanOrEqualTo(root.get("expire"), new Date())));
        if(member != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("member"), member));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, null);
    }

    public Long waitingShippingCount(Member member)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Order);
        Root root = criteriaquery.from(net/shopxx/entity/Order);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        predicate = criteriabuilder.and(new Predicate[] {
            predicate, criteriabuilder.notEqual(root.get("orderStatus"), net.shopxx.entity.Order.OrderStatus.completed), criteriabuilder.notEqual(root.get("orderStatus"), net.shopxx.entity.Order.OrderStatus.cancelled), criteriabuilder.equal(root.get("paymentStatus"), net.shopxx.entity.Order.PaymentStatus.paid), criteriabuilder.equal(root.get("shippingStatus"), net.shopxx.entity.Order.ShippingStatus.unshipped)
        });
        predicate = criteriabuilder.and(predicate, criteriabuilder.or(root.get("expire").isNull(), criteriabuilder.greaterThanOrEqualTo(root.get("expire"), new Date())));
        if(member != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("member"), member));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, null);
    }

    public BigDecimal getSalesAmount(Date beginDate, Date endDate)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(java/math/BigDecimal);
        Root root = criteriaquery.from(net/shopxx/entity/Order);
        criteriaquery.select(criteriabuilder.sum(root.get("amountPaid")));
        Predicate predicate = criteriabuilder.conjunction();
        predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("orderStatus"), net.shopxx.entity.Order.OrderStatus.completed));
        if(beginDate != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.greaterThanOrEqualTo(root.get("createDate"), beginDate));
        if(endDate != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.lessThanOrEqualTo(root.get("createDate"), endDate));
        criteriaquery.where(predicate);
        return (BigDecimal)IIIllIlI.createQuery(criteriaquery).setFlushMode(FlushModeType.COMMIT).getSingleResult();
    }

    public Integer getSalesVolume(Date beginDate, Date endDate)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(java/lang/Integer);
        Root root = criteriaquery.from(net/shopxx/entity/Order);
        criteriaquery.select(criteriabuilder.sum(root.join("orderItems").get("shippedQuantity")));
        Predicate predicate = criteriabuilder.conjunction();
        predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("orderStatus"), net.shopxx.entity.Order.OrderStatus.completed));
        if(beginDate != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.greaterThanOrEqualTo(root.get("createDate"), beginDate));
        if(endDate != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.lessThanOrEqualTo(root.get("createDate"), endDate));
        criteriaquery.where(predicate);
        return (Integer)IIIllIlI.createQuery(criteriaquery).setFlushMode(FlushModeType.COMMIT).getSingleResult();
    }

    public void releaseStock()
    {
        String s = "select orders from Order orders where orders.isAllocatedStock = :isAllocatedStock and orders.expire is not null and orders.expire <= :now";
        List list = IIIllIlI.createQuery(s, net/shopxx/entity/Order).setParameter("isAllocatedStock", Boolean.valueOf(true)).setParameter("now", new Date()).getResultList();
        if(list != null)
        {
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                Order order = (Order)iterator.next();
                if(order != null && order.getOrderItems() != null)
                {
                    for(Iterator iterator1 = order.getOrderItems().iterator(); iterator1.hasNext();)
                    {
                        OrderItem orderitem = (OrderItem)iterator1.next();
                        if(orderitem != null)
                        {
                            Product product = orderitem.getProduct();
                            if(product != null)
                            {
                                IIIllIlI.lock(product, LockModeType.PESSIMISTIC_WRITE);
                                product.setAllocatedStock(Integer.valueOf(product.getAllocatedStock().intValue() - (orderitem.getQuantity().intValue() - orderitem.getShippedQuantity().intValue())));
                            }
                        }
                    }

                    order.setIsAllocatedStock(Boolean.valueOf(false));
                }
            }

        }
    }
}
