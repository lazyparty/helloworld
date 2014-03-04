// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.math.BigDecimal;
import java.util.*;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.*;
import net.shopxx.service.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class OrderController extends BaseController
{

    public OrderController()
    {
    }

    public Message checkLock(Long id)
    {
        Order order = (Order)IIlIIIII.find(id);
        if(order == null)
            return Message.warn("admin.common.invalid", new Object[0]);
        Admin admin = IIIlllIl.getCurrent();
        if(order.isLocked(admin))
        {
            if(order.getOperator() != null)
                return Message.warn("admin.order.adminLocked", new Object[] {
                    order.getOperator().getUsername()
                });
            else
                return Message.warn("admin.order.memberLocked", new Object[0]);
        } else
        {
            order.setLockExpire(DateUtils.addSeconds(new Date(), 60));
            order.setOperator(admin);
            IIlIIIII.update(order);
            return IIIlllII;
        }
    }

    public String view(Long id, ModelMap model)
    {
        model.addAttribute("types", net.shopxx.entity.Payment.Type.values());
        model.addAttribute("refundsTypes", net.shopxx.entity.Refunds.Type.values());
        model.addAttribute("paymentMethods", IIlIIlII.findAll());
        model.addAttribute("shippingMethods", IIlIIIlI.findAll());
        model.addAttribute("deliveryCorps", IIlIIIll.findAll());
        model.addAttribute("order", IIlIIIII.find(id));
        return "/admin/order/view";
    }

    public String confirm(Long id, RedirectAttributes redirectAttributes)
    {
        Order order = (Order)IIlIIIII.find(id);
        Admin admin = IIIlllIl.getCurrent();
        if(order != null && !order.isExpired() && order.getOrderStatus() == net.shopxx.entity.Order.OrderStatus.unconfirmed && !order.isLocked(admin))
        {
            IIlIIIII.confirm(order, admin);
            IIIllIlI(redirectAttributes, IIIlllII);
        } else
        {
            IIIllIlI(redirectAttributes, Message.warn("admin.common.invalid", new Object[0]));
        }
        return (new StringBuilder("redirect:view.jhtml?id=")).append(id).toString();
    }

    public String complete(Long id, RedirectAttributes redirectAttributes)
    {
        Order order = (Order)IIlIIIII.find(id);
        Admin admin = IIIlllIl.getCurrent();
        if(order != null && !order.isExpired() && order.getOrderStatus() == net.shopxx.entity.Order.OrderStatus.confirmed && !order.isLocked(admin))
        {
            IIlIIIII.complete(order, admin);
            IIIllIlI(redirectAttributes, IIIlllII);
        } else
        {
            IIIllIlI(redirectAttributes, Message.warn("admin.common.invalid", new Object[0]));
        }
        return (new StringBuilder("redirect:view.jhtml?id=")).append(id).toString();
    }

    public String cancel(Long id, RedirectAttributes redirectAttributes)
    {
        Order order = (Order)IIlIIIII.find(id);
        Admin admin = IIIlllIl.getCurrent();
        if(order != null && !order.isExpired() && order.getOrderStatus() == net.shopxx.entity.Order.OrderStatus.unconfirmed && !order.isLocked(admin))
        {
            IIlIIIII.cancel(order, admin);
            IIIllIlI(redirectAttributes, IIIlllII);
        } else
        {
            IIIllIlI(redirectAttributes, Message.warn("admin.common.invalid", new Object[0]));
        }
        return (new StringBuilder("redirect:view.jhtml?id=")).append(id).toString();
    }

    public String payment(Long orderId, Long paymentMethodId, Payment payment, RedirectAttributes redirectAttributes)
    {
        Order order = (Order)IIlIIIII.find(orderId);
        payment.setOrder(order);
        PaymentMethod paymentmethod = (PaymentMethod)IIlIIlII.find(paymentMethodId);
        payment.setPaymentMethod(paymentmethod == null ? null : paymentmethod.getName());
        if(!IIIllIlI(payment, new Class[0]))
            return "/admin/common/error";
        if(order.isExpired() || order.getOrderStatus() != net.shopxx.entity.Order.OrderStatus.confirmed)
            return "/admin/common/error";
        if(order.getPaymentStatus() != net.shopxx.entity.Order.PaymentStatus.unpaid && order.getPaymentStatus() != net.shopxx.entity.Order.PaymentStatus.partialPayment)
            return "/admin/common/error";
        if(payment.getAmount().compareTo(new BigDecimal(0)) <= 0 || payment.getAmount().compareTo(order.getAmountPayable()) > 0)
            return "/admin/common/error";
        Member member = order.getMember();
        if(payment.getType() == net.shopxx.entity.Payment.Type.deposit && payment.getAmount().compareTo(member.getBalance()) > 0)
            return "/admin/common/error";
        Admin admin = IIIlllIl.getCurrent();
        if(order.isLocked(admin))
        {
            return "/admin/common/error";
        } else
        {
            payment.setSn(IIlIIlIl.generate(net.shopxx.entity.Sn.Type.payment));
            payment.setStatus(net.shopxx.entity.Payment.Status.success);
            payment.setFee(new BigDecimal(0));
            payment.setOperator(admin.getUsername());
            payment.setPaymentDate(new Date());
            payment.setPaymentPluginId(null);
            payment.setExpire(null);
            payment.setDeposit(null);
            payment.setMember(null);
            IIlIIIII.payment(order, payment, admin);
            IIIllIlI(redirectAttributes, IIIlllII);
            return (new StringBuilder("redirect:view.jhtml?id=")).append(orderId).toString();
        }
    }

    public String refunds(Long orderId, Long paymentMethodId, Refunds refunds, RedirectAttributes redirectAttributes)
    {
        Order order = (Order)IIlIIIII.find(orderId);
        refunds.setOrder(order);
        PaymentMethod paymentmethod = (PaymentMethod)IIlIIlII.find(paymentMethodId);
        refunds.setPaymentMethod(paymentmethod == null ? null : paymentmethod.getName());
        if(!IIIllIlI(refunds, new Class[0]))
            return "/admin/common/error";
        if(order.isExpired() || order.getOrderStatus() != net.shopxx.entity.Order.OrderStatus.confirmed)
            return "/admin/common/error";
        if(order.getPaymentStatus() != net.shopxx.entity.Order.PaymentStatus.paid && order.getPaymentStatus() != net.shopxx.entity.Order.PaymentStatus.partialPayment && order.getPaymentStatus() != net.shopxx.entity.Order.PaymentStatus.partialRefunds)
            return "/admin/common/error";
        if(refunds.getAmount().compareTo(new BigDecimal(0)) <= 0 || refunds.getAmount().compareTo(order.getAmountPaid()) > 0)
            return "/admin/common/error";
        Admin admin = IIIlllIl.getCurrent();
        if(order.isLocked(admin))
        {
            return "/admin/common/error";
        } else
        {
            refunds.setSn(IIlIIlIl.generate(net.shopxx.entity.Sn.Type.refunds));
            refunds.setOperator(admin.getUsername());
            IIlIIIII.refunds(order, refunds, admin);
            IIIllIlI(redirectAttributes, IIIlllII);
            return (new StringBuilder("redirect:view.jhtml?id=")).append(orderId).toString();
        }
    }

    public String shipping(Long orderId, Long shippingMethodId, Long deliveryCorpId, Long areaId, Shipping shipping, RedirectAttributes redirectAttributes)
    {
        Order order = (Order)IIlIIIII.find(orderId);
        if(order == null)
            return "/admin/common/error";
        for(Iterator iterator = shipping.getShippingItems().iterator(); iterator.hasNext();)
        {
            ShippingItem shippingitem = (ShippingItem)iterator.next();
            if(shippingitem == null || StringUtils.isEmpty(shippingitem.getSn()) || shippingitem.getQuantity() == null || shippingitem.getQuantity().intValue() <= 0)
            {
                iterator.remove();
            } else
            {
                OrderItem orderitem = order.getOrderItem(shippingitem.getSn());
                if(orderitem == null || shippingitem.getQuantity().intValue() > orderitem.getQuantity().intValue() - orderitem.getShippedQuantity().intValue())
                    return "/admin/common/error";
                if(orderitem.getProduct() != null && orderitem.getProduct().getStock() != null && shippingitem.getQuantity().intValue() > orderitem.getProduct().getStock().intValue())
                    return "/admin/common/error";
                shippingitem.setName(orderitem.getFullName());
                shippingitem.setShipping(shipping);
            }
        }

        shipping.setOrder(order);
        ShippingMethod shippingmethod = (ShippingMethod)IIlIIIlI.find(shippingMethodId);
        shipping.setShippingMethod(shippingmethod == null ? null : shippingmethod.getName());
        DeliveryCorp deliverycorp = (DeliveryCorp)IIlIIIll.find(deliveryCorpId);
        shipping.setDeliveryCorp(deliverycorp == null ? null : deliverycorp.getName());
        shipping.setDeliveryCorpUrl(deliverycorp == null ? null : deliverycorp.getUrl());
        shipping.setDeliveryCorpCode(deliverycorp == null ? null : deliverycorp.getCode());
        Area area = (Area)IIIllllI.find(areaId);
        shipping.setArea(area == null ? null : area.getFullName());
        if(!IIIllIlI(shipping, new Class[0]))
            return "/admin/common/error";
        if(order.isExpired() || order.getOrderStatus() != net.shopxx.entity.Order.OrderStatus.confirmed)
            return "/admin/common/error";
        if(order.getShippingStatus() != net.shopxx.entity.Order.ShippingStatus.unshipped && order.getShippingStatus() != net.shopxx.entity.Order.ShippingStatus.partialShipment)
            return "/admin/common/error";
        Admin admin = IIIlllIl.getCurrent();
        if(order.isLocked(admin))
        {
            return "/admin/common/error";
        } else
        {
            shipping.setSn(IIlIIlIl.generate(net.shopxx.entity.Sn.Type.shipping));
            shipping.setOperator(admin.getUsername());
            IIlIIIII.shipping(order, shipping, admin);
            IIIllIlI(redirectAttributes, IIIlllII);
            return (new StringBuilder("redirect:view.jhtml?id=")).append(orderId).toString();
        }
    }

    public String returns(Long orderId, Long shippingMethodId, Long deliveryCorpId, Long areaId, Returns returns, RedirectAttributes redirectAttributes)
    {
        Order order = (Order)IIlIIIII.find(orderId);
        if(order == null)
            return "/admin/common/error";
        for(Iterator iterator = returns.getReturnsItems().iterator(); iterator.hasNext();)
        {
            ReturnsItem returnsitem = (ReturnsItem)iterator.next();
            if(returnsitem == null || StringUtils.isEmpty(returnsitem.getSn()) || returnsitem.getQuantity() == null || returnsitem.getQuantity().intValue() <= 0)
            {
                iterator.remove();
            } else
            {
                OrderItem orderitem = order.getOrderItem(returnsitem.getSn());
                if(orderitem == null || returnsitem.getQuantity().intValue() > orderitem.getShippedQuantity().intValue() - orderitem.getReturnQuantity().intValue())
                    return "/admin/common/error";
                returnsitem.setName(orderitem.getFullName());
                returnsitem.setReturns(returns);
            }
        }

        returns.setOrder(order);
        ShippingMethod shippingmethod = (ShippingMethod)IIlIIIlI.find(shippingMethodId);
        returns.setShippingMethod(shippingmethod == null ? null : shippingmethod.getName());
        DeliveryCorp deliverycorp = (DeliveryCorp)IIlIIIll.find(deliveryCorpId);
        returns.setDeliveryCorp(deliverycorp == null ? null : deliverycorp.getName());
        Area area = (Area)IIIllllI.find(areaId);
        returns.setArea(area == null ? null : area.getFullName());
        if(!IIIllIlI(returns, new Class[0]))
            return "/admin/common/error";
        if(order.isExpired() || order.getOrderStatus() != net.shopxx.entity.Order.OrderStatus.confirmed)
            return "/admin/common/error";
        if(order.getShippingStatus() != net.shopxx.entity.Order.ShippingStatus.shipped && order.getShippingStatus() != net.shopxx.entity.Order.ShippingStatus.partialShipment && order.getShippingStatus() != net.shopxx.entity.Order.ShippingStatus.partialReturns)
            return "/admin/common/error";
        Admin admin = IIIlllIl.getCurrent();
        if(order.isLocked(admin))
        {
            return "/admin/common/error";
        } else
        {
            returns.setSn(IIlIIlIl.generate(net.shopxx.entity.Sn.Type.returns));
            returns.setOperator(admin.getUsername());
            IIlIIIII.returns(order, returns, admin);
            IIIllIlI(redirectAttributes, IIIlllII);
            return (new StringBuilder("redirect:view.jhtml?id=")).append(orderId).toString();
        }
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("paymentMethods", IIlIIlII.findAll());
        model.addAttribute("shippingMethods", IIlIIIlI.findAll());
        model.addAttribute("order", IIlIIIII.find(id));
        return "/admin/order/edit";
    }

    public Map orderItemAdd(String productSn)
    {
        HashMap hashmap = new HashMap();
        Product product = IIIlllll.findBySn(productSn);
        if(product == null)
        {
            hashmap.put("message", Message.warn("admin.order.productNotExist", new Object[0]));
            return hashmap;
        }
        if(!product.getIsMarketable().booleanValue())
        {
            hashmap.put("message", Message.warn("admin.order.productNotMarketable", new Object[0]));
            return hashmap;
        }
        if(product.getIsOutOfStock().booleanValue())
        {
            hashmap.put("message", Message.warn("admin.order.productOutOfStock", new Object[0]));
            return hashmap;
        } else
        {
            hashmap.put("sn", product.getSn());
            hashmap.put("fullName", product.getFullName());
            hashmap.put("price", product.getPrice());
            hashmap.put("weight", product.getWeight());
            hashmap.put("isGift", product.getIsGift());
            hashmap.put("message", IIIlllII);
            return hashmap;
        }
    }

    public Map calculate(Order order, Long areaId, Long paymentMethodId, Long shippingMethodId)
    {
        HashMap hashmap = new HashMap();
        for(Iterator iterator = order.getOrderItems().iterator(); iterator.hasNext();)
        {
            OrderItem orderitem = (OrderItem)iterator.next();
            if(orderitem == null || StringUtils.isEmpty(orderitem.getSn()))
                iterator.remove();
        }

        order.setArea((Area)IIIllllI.find(areaId));
        order.setPaymentMethod((PaymentMethod)IIlIIlII.find(paymentMethodId));
        order.setShippingMethod((ShippingMethod)IIlIIIlI.find(shippingMethodId));
        if(!IIIllIlI(order, new Class[0]))
        {
            hashmap.put("message", Message.warn("admin.common.invalid", new Object[0]));
            return hashmap;
        }
        Order order1 = (Order)IIlIIIII.find(order.getId());
        if(order1 == null)
        {
            hashmap.put("message", Message.error("admin.common.invalid", new Object[0]));
            return hashmap;
        }
        for(Iterator iterator1 = order.getOrderItems().iterator(); iterator1.hasNext();)
        {
            OrderItem orderitem1 = (OrderItem)iterator1.next();
            if(orderitem1.getId() != null)
            {
                OrderItem orderitem3 = (OrderItem)IIlIIIIl.find(orderitem1.getId());
                if(orderitem3 == null || !order1.equals(orderitem3.getOrder()))
                {
                    hashmap.put("message", Message.error("admin.common.invalid", new Object[0]));
                    return hashmap;
                }
                Product product1 = orderitem3.getProduct();
                if(product1 != null && product1.getStock() != null)
                    if(order1.getIsAllocatedStock().booleanValue())
                    {
                        if(orderitem1.getQuantity().intValue() > product1.getAvailableStock().intValue() + orderitem3.getQuantity().intValue())
                        {
                            hashmap.put("message", Message.warn("admin.order.lowStock", new Object[0]));
                            return hashmap;
                        }
                    } else
                    if(orderitem1.getQuantity().intValue() > product1.getAvailableStock().intValue())
                    {
                        hashmap.put("message", Message.warn("admin.order.lowStock", new Object[0]));
                        return hashmap;
                    }
            } else
            {
                Product product = IIIlllll.findBySn(orderitem1.getSn());
                if(product == null)
                {
                    hashmap.put("message", Message.error("admin.common.invalid", new Object[0]));
                    return hashmap;
                }
                if(product.getStock() != null && orderitem1.getQuantity().intValue() > product.getAvailableStock().intValue())
                {
                    hashmap.put("message", Message.warn("admin.order.lowStock", new Object[0]));
                    return hashmap;
                }
            }
        }

        HashMap hashmap1 = new HashMap();
        OrderItem orderitem2;
        for(Iterator iterator2 = order.getOrderItems().iterator(); iterator2.hasNext(); hashmap1.put(orderitem2.getSn(), orderitem2))
            orderitem2 = (OrderItem)iterator2.next();

        hashmap.put("weight", Integer.valueOf(order.getWeight()));
        hashmap.put("price", order.getPrice());
        hashmap.put("quantity", Integer.valueOf(order.getQuantity()));
        hashmap.put("amount", order.getAmount());
        hashmap.put("orderItems", hashmap1);
        hashmap.put("message", IIIlllII);
        return hashmap;
    }

    public String update(Order order, Long areaId, Long paymentMethodId, Long shippingMethodId, RedirectAttributes redirectAttributes)
    {
        for(Iterator iterator = order.getOrderItems().iterator(); iterator.hasNext();)
        {
            OrderItem orderitem = (OrderItem)iterator.next();
            if(orderitem == null || StringUtils.isEmpty(orderitem.getSn()))
                iterator.remove();
        }

        order.setArea((Area)IIIllllI.find(areaId));
        order.setPaymentMethod((PaymentMethod)IIlIIlII.find(paymentMethodId));
        order.setShippingMethod((ShippingMethod)IIlIIIlI.find(shippingMethodId));
        if(!IIIllIlI(order, new Class[0]))
            return "/admin/common/error";
        Order order1 = (Order)IIlIIIII.find(order.getId());
        if(order1 == null)
            return "/admin/common/error";
        if(order1.isExpired() || order1.getOrderStatus() != net.shopxx.entity.Order.OrderStatus.unconfirmed)
            return "/admin/common/error";
        Admin admin = IIIlllIl.getCurrent();
        if(order1.isLocked(admin))
            return "/admin/common/error";
        if(!order.getIsInvoice().booleanValue())
        {
            order.setInvoiceTitle(null);
            order.setTax(new BigDecimal(0));
        }
        for(Iterator iterator1 = order.getOrderItems().iterator(); iterator1.hasNext();)
        {
            OrderItem orderitem1 = (OrderItem)iterator1.next();
            if(orderitem1.getId() != null)
            {
                OrderItem orderitem2 = (OrderItem)IIlIIIIl.find(orderitem1.getId());
                if(orderitem2 == null || !order1.equals(orderitem2.getOrder()))
                    return "/admin/common/error";
                Product product1 = orderitem2.getProduct();
                if(product1 != null && product1.getStock() != null)
                    if(order1.getIsAllocatedStock().booleanValue())
                    {
                        if(orderitem1.getQuantity().intValue() > product1.getAvailableStock().intValue() + orderitem2.getQuantity().intValue())
                            return "/admin/common/error";
                    } else
                    if(orderitem1.getQuantity().intValue() > product1.getAvailableStock().intValue())
                        return "/admin/common/error";
                BeanUtils.copyProperties(orderitem2, orderitem1, new String[] {
                    "price", "quantity"
                });
                if(orderitem2.getIsGift().booleanValue())
                    orderitem1.setPrice(new BigDecimal(0));
            } else
            {
                Product product = IIIlllll.findBySn(orderitem1.getSn());
                if(product == null)
                    return "/admin/common/error";
                if(product.getStock() != null && orderitem1.getQuantity().intValue() > product.getAvailableStock().intValue())
                    return "/admin/common/error";
                orderitem1.setName(product.getName());
                orderitem1.setFullName(product.getFullName());
                if(product.getIsGift().booleanValue())
                    orderitem1.setPrice(new BigDecimal(0));
                orderitem1.setWeight(product.getWeight());
                orderitem1.setThumbnail(product.getThumbnail());
                orderitem1.setIsGift(product.getIsGift());
                orderitem1.setShippedQuantity(Integer.valueOf(0));
                orderitem1.setReturnQuantity(Integer.valueOf(0));
                orderitem1.setProduct(product);
                orderitem1.setOrder(order1);
            }
        }

        order.setSn(order1.getSn());
        order.setOrderStatus(order1.getOrderStatus());
        order.setPaymentStatus(order1.getPaymentStatus());
        order.setShippingStatus(order1.getShippingStatus());
        order.setFee(order1.getFee());
        order.setAmountPaid(order1.getAmountPaid());
        order.setPromotion(order1.getPromotion());
        order.setExpire(order1.getExpire());
        order.setLockExpire(null);
        order.setIsAllocatedStock(order1.getIsAllocatedStock());
        order.setOperator(null);
        order.setMember(order1.getMember());
        order.setCouponCode(order1.getCouponCode());
        order.setCoupons(order1.getCoupons());
        order.setOrderLogs(order1.getOrderLogs());
        order.setDeposits(order1.getDeposits());
        order.setPayments(order1.getPayments());
        order.setRefunds(order1.getRefunds());
        order.setShippings(order1.getShippings());
        order.setReturns(order1.getReturns());
        IIlIIIII.update(order, admin);
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String list(net.shopxx.entity.Order.OrderStatus orderStatus, net.shopxx.entity.Order.PaymentStatus paymentStatus, net.shopxx.entity.Order.ShippingStatus shippingStatus, Boolean hasExpired, Pageable pageable, ModelMap model)
    {
        model.addAttribute("orderStatus", orderStatus);
        model.addAttribute("paymentStatus", paymentStatus);
        model.addAttribute("shippingStatus", shippingStatus);
        model.addAttribute("hasExpired", hasExpired);
        model.addAttribute("page", IIlIIIII.findPage(orderStatus, paymentStatus, shippingStatus, hasExpired, pageable));
        return "/admin/order/list";
    }

    public Message delete(Long ids[])
    {
        if(ids != null)
        {
            Admin admin = IIIlllIl.getCurrent();
            Long along[];
            int j = (along = ids).length;
            for(int i = 0; i < j; i++)
            {
                Long long1 = along[i];
                Order order = (Order)IIlIIIII.find(long1);
                if(order != null && order.isLocked(admin))
                    return Message.error("admin.order.deleteLockedNotAllowed", new Object[] {
                        order.getSn()
                    });
            }

            IIlIIIII.delete(ids);
        }
        return IIIlllII;
    }

    private AdminService IIIlllIl;
    private AreaService IIIllllI;
    private ProductService IIIlllll;
    private OrderService IIlIIIII;
    private OrderItemService IIlIIIIl;
    private ShippingMethodService IIlIIIlI;
    private DeliveryCorpService IIlIIIll;
    private PaymentMethodService IIlIIlII;
    private SnService IIlIIlIl;
}
