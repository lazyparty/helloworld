// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop.member;

import java.util.*;
import net.shopxx.*;
import net.shopxx.controller.shop.BaseController;
import net.shopxx.entity.*;
import net.shopxx.plugin.PaymentPlugin;
import net.shopxx.service.*;
import net.shopxx.util.SettingUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.ui.ModelMap;

public class OrderController extends BaseController
{

    public OrderController()
    {
    }

    public Map saveReceiver(Receiver receiver, Long areaId)
    {
        HashMap hashmap = new HashMap();
        receiver.setArea((Area)IIIlllll.find(areaId));
        if(!IIIllIlI(receiver, new Class[0]))
        {
            hashmap.put("message", IIIllIll);
            return hashmap;
        }
        Member member = IIIllllI.getCurrent();
        if(Receiver.MAX_RECEIVER_COUNT != null && member.getReceivers().size() >= Receiver.MAX_RECEIVER_COUNT.intValue())
        {
            hashmap.put("message", Message.error("shop.order.addReceiverCountNotAllowed", new Object[] {
                Receiver.MAX_RECEIVER_COUNT
            }));
            return hashmap;
        } else
        {
            receiver.setMember(member);
            IIlIIIII.save(receiver);
            hashmap.put("message", IIIlllII);
            hashmap.put("receiver", receiver);
            return hashmap;
        }
    }

    public Message checkLock(String sn)
    {
        Order order = IIlIIlIl.findBySn(sn);
        if(order != null && order.getMember() == IIIllllI.getCurrent() && !order.isExpired() && order.getPaymentMethod() != null && order.getPaymentMethod().getType() == net.shopxx.entity.PaymentMethod.Type.online && (order.getPaymentStatus() == net.shopxx.entity.Order.PaymentStatus.unpaid || order.getPaymentStatus() == net.shopxx.entity.Order.PaymentStatus.partialPayment))
        {
            if(order.isLocked(null))
            {
                return Message.warn("shop.order.locked", new Object[0]);
            } else
            {
                order.setLockExpire(DateUtils.addSeconds(new Date(), 60));
                order.setOperator(null);
                IIlIIlIl.update(order);
                return IIIlllII;
            }
        } else
        {
            return IIIllIll;
        }
    }

    public boolean checkPayment(String sn)
    {
        Order order = IIlIIlIl.findBySn(sn);
        return order != null && order.getMember() == IIIllllI.getCurrent() && order.getPaymentStatus() == net.shopxx.entity.Order.PaymentStatus.paid;
    }

    public Map couponInfo(String code)
    {
        HashMap hashmap = new HashMap();
        Cart cart = IIlIIIIl.getCurrent();
        if(cart == null || cart.isEmpty())
        {
            hashmap.put("message", Message.warn("shop.order.cartNotEmpty", new Object[0]));
            return hashmap;
        }
        if(!cart.isCouponAllowed())
        {
            hashmap.put("message", Message.warn("shop.order.couponNotAllowed", new Object[0]));
            return hashmap;
        }
        CouponCode couponcode = IIlIIlII.findByCode(code);
        if(couponcode != null && couponcode.getCoupon() != null)
        {
            Coupon coupon = couponcode.getCoupon();
            if(!coupon.getIsEnabled().booleanValue())
            {
                hashmap.put("message", Message.warn("shop.order.couponDisabled", new Object[0]));
                return hashmap;
            }
            if(!coupon.hasBegun())
            {
                hashmap.put("message", Message.warn("shop.order.couponNotBegin", new Object[0]));
                return hashmap;
            }
            if(coupon.hasExpired())
            {
                hashmap.put("message", Message.warn("shop.order.couponHasExpired", new Object[0]));
                return hashmap;
            }
            if(!cart.isValid(coupon))
            {
                hashmap.put("message", Message.warn("shop.order.couponInvalid", new Object[0]));
                return hashmap;
            }
            if(couponcode.getIsUsed().booleanValue())
            {
                hashmap.put("message", Message.warn("shop.order.couponCodeUsed", new Object[0]));
                return hashmap;
            } else
            {
                hashmap.put("message", IIIlllII);
                hashmap.put("couponName", coupon.getName());
                return hashmap;
            }
        } else
        {
            hashmap.put("message", Message.warn("shop.order.couponCodeNotExist", new Object[0]));
            return hashmap;
        }
    }

    public String info(ModelMap model)
    {
        Cart cart = IIlIIIIl.getCurrent();
        if(cart == null || cart.isEmpty())
            return "redirect:/cart/list.jhtml";
        if(!IIIllIlI(cart, new Class[0]))
        {
            return "/shop/common/error";
        } else
        {
            Order order = IIlIIlIl.build(cart, null, null, null, null, false, null, false, null);
            model.addAttribute("order", order);
            model.addAttribute("cartToken", cart.getToken());
            model.addAttribute("paymentMethods", IIlIIIlI.findAll());
            model.addAttribute("shippingMethods", IIlIIIll.findAll());
            return "/shop/member/order/info";
        }
    }

    public Map calculate(Long paymentMethodId, Long shippingMethodId, String code, Boolean isInvoice, String invoiceTitle, Boolean useBalance, String memo)
    {
        HashMap hashmap = new HashMap();
        Cart cart = IIlIIIIl.getCurrent();
        if(cart == null || cart.isEmpty())
        {
            hashmap.put("message", Message.error("shop.order.cartNotEmpty", new Object[0]));
            return hashmap;
        } else
        {
            PaymentMethod paymentmethod = (PaymentMethod)IIlIIIlI.find(paymentMethodId);
            ShippingMethod shippingmethod = (ShippingMethod)IIlIIIll.find(shippingMethodId);
            CouponCode couponcode = IIlIIlII.findByCode(code);
            Order order = IIlIIlIl.build(cart, null, paymentmethod, shippingmethod, couponcode, isInvoice.booleanValue(), invoiceTitle, useBalance.booleanValue(), memo);
            hashmap.put("message", IIIlllII);
            hashmap.put("quantity", Integer.valueOf(order.getQuantity()));
            hashmap.put("price", order.getPrice());
            hashmap.put("freight", order.getFreight());
            hashmap.put("tax", order.getTax());
            hashmap.put("amountPayable", order.getAmountPayable());
            return hashmap;
        }
    }

    public Message create(String cartToken, Long receiverId, Long paymentMethodId, Long shippingMethodId, String code, Boolean isInvoice, String invoiceTitle, 
            Boolean useBalance, String memo)
    {
        Cart cart = IIlIIIIl.getCurrent();
        if(cart == null || cart.isEmpty())
            return Message.warn("shop.order.cartNotEmpty", new Object[0]);
        if(!StringUtils.equals(cart.getToken(), cartToken))
            return Message.warn("shop.order.cartHasChanged", new Object[0]);
        if(cart.getIsLowStock())
            return Message.warn("shop.order.cartLowStock", new Object[0]);
        Receiver receiver = (Receiver)IIlIIIII.find(receiverId);
        if(receiver == null)
            return Message.error("shop.order.receiverNotExsit", new Object[0]);
        PaymentMethod paymentmethod = (PaymentMethod)IIlIIIlI.find(paymentMethodId);
        if(paymentmethod == null)
            return Message.error("shop.order.paymentMethodNotExsit", new Object[0]);
        ShippingMethod shippingmethod = (ShippingMethod)IIlIIIll.find(shippingMethodId);
        if(shippingmethod == null)
            return Message.error("shop.order.shippingMethodNotExsit", new Object[0]);
        if(!paymentmethod.getShippingMethods().contains(shippingmethod))
        {
            return Message.error("shop.order.deliveryUnsupported", new Object[0]);
        } else
        {
            CouponCode couponcode = IIlIIlII.findByCode(code);
            Order order = IIlIIlIl.create(cart, receiver, paymentmethod, shippingmethod, couponcode, isInvoice.booleanValue(), invoiceTitle, useBalance.booleanValue(), memo, null);
            return Message.success(order.getSn(), new Object[0]);
        }
    }

    public String payment(String sn, ModelMap model)
    {
        Order order = IIlIIlIl.findBySn(sn);
        if(order == null || order.getMember() != IIIllllI.getCurrent() || order.isExpired() || order.getPaymentMethod() == null)
            return "/shop/common/error";
        if(order.getPaymentMethod().getType() == net.shopxx.entity.PaymentMethod.Type.online)
        {
            List list1 = IIlIIlll.getPaymentPlugins(true);
            if(!list1.isEmpty())
            {
                PaymentPlugin paymentplugin = (PaymentPlugin)list1.get(0);
                order.setFee(paymentplugin.getFee(order.getAmountPayable()));
                model.addAttribute("defaultPaymentPlugin", paymentplugin);
                model.addAttribute("paymentPlugins", list1);
            }
        }
        model.addAttribute("order", order);
        return "/shop/member/order/payment";
    }

    public Map paymentPluginSelect(String sn, String paymentPluginId)
    {
        HashMap hashmap = new HashMap();
        Order order = IIlIIlIl.findBySn(sn);
        PaymentPlugin paymentplugin = IIlIIlll.getPaymentPlugin(paymentPluginId);
        if(order == null || order.getMember() != IIIllllI.getCurrent() || order.isExpired() || order.isLocked(null) || order.getPaymentMethod() == null || order.getPaymentMethod().getType() == net.shopxx.entity.PaymentMethod.Type.offline || paymentplugin == null || !paymentplugin.getIsEnabled())
        {
            hashmap.put("message", IIIllIll);
            return hashmap;
        } else
        {
            order.setFee(paymentplugin.getFee(order.getAmountPayable()));
            hashmap.put("message", IIIlllII);
            hashmap.put("fee", order.getFee());
            hashmap.put("amountPayable", order.getAmountPayable());
            return hashmap;
        }
    }

    public String list(Integer pageNumber, ModelMap model)
    {
        Member member = IIIllllI.getCurrent();
        Pageable pageable = new Pageable(pageNumber, Integer.valueOf(10));
        model.addAttribute("page", IIlIIlIl.findPage(member, pageable));
        return "shop/member/order/list";
    }

    public String view(String sn, ModelMap model)
    {
        Order order = IIlIIlIl.findBySn(sn);
        if(order == null)
            return "/shop/common/error";
        Member member = IIIllllI.getCurrent();
        if(!member.getOrders().contains(order))
        {
            return "/shop/common/error";
        } else
        {
            model.addAttribute("order", order);
            return "shop/member/order/view";
        }
    }

    public Message cancel(String sn)
    {
        Order order = IIlIIlIl.findBySn(sn);
        if(order != null && order.getMember() == IIIllllI.getCurrent() && !order.isExpired() && order.getOrderStatus() == net.shopxx.entity.Order.OrderStatus.unconfirmed && order.getPaymentStatus() == net.shopxx.entity.Order.PaymentStatus.unpaid)
        {
            if(order.isLocked(null))
            {
                return Message.warn("shop.member.order.locked", new Object[0]);
            } else
            {
                IIlIIlIl.cancel(order, null);
                return IIIlllII;
            }
        } else
        {
            return IIIllIll;
        }
    }

    public Map deliveryQuery(String sn)
    {
        Object obj = new HashMap();
        Shipping shipping = IIlIIllI.findBySn(sn);
        Setting setting = SettingUtils.get();
        if(shipping != null && shipping.getOrder() != null && shipping.getOrder().getMember() == IIIllllI.getCurrent() && StringUtils.isNotEmpty(setting.getKuaidi100Key()) && StringUtils.isNotEmpty(shipping.getDeliveryCorpCode()) && StringUtils.isNotEmpty(shipping.getTrackingNo()))
            obj = IIlIIllI.query(shipping);
        return ((Map) (obj));
    }

    private static final int IIIlllIl = 10;
    private MemberService IIIllllI;
    private AreaService IIIlllll;
    private ReceiverService IIlIIIII;
    private CartService IIlIIIIl;
    private PaymentMethodService IIlIIIlI;
    private ShippingMethodService IIlIIIll;
    private CouponCodeService IIlIIlII;
    private OrderService IIlIIlIl;
    private ShippingService IIlIIllI;
    private PluginService IIlIIlll;
}
