// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import java.math.BigDecimal;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import net.shopxx.entity.*;
import net.shopxx.plugin.PaymentPlugin;
import net.shopxx.service.*;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.shop:
//            BaseController

public class PaymentController extends BaseController
{

    public PaymentController()
    {
    }

    public String submit(String sn, String paymentPluginId, HttpServletRequest request, ModelMap model)
    {
        Order order = IIIlllIl.findBySn(sn);
        if(order == null)
            return "/shop/common/error";
        net.shopxx.entity.Member member = IIIllllI.getCurrent();
        if(member == null || order.getMember() != member || order.isExpired())
            return "/shop/common/error";
        if(order.getPaymentMethod() == null || order.getPaymentMethod().getType() == net.shopxx.entity.PaymentMethod.Type.offline)
            return "/shop/common/error";
        if(order.getPaymentStatus() != net.shopxx.entity.Order.PaymentStatus.unpaid && order.getPaymentStatus() != net.shopxx.entity.Order.PaymentStatus.partialPayment)
            return "/shop/common/error";
        if(order.getAmountPayable().compareTo(new BigDecimal(0)) <= 0)
            return "/shop/common/error";
        PaymentPlugin paymentplugin = IIIlllll.getPaymentPlugin(paymentPluginId);
        if(paymentplugin == null || !paymentplugin.getIsEnabled())
        {
            return "/shop/common/error";
        } else
        {
            BigDecimal bigdecimal = paymentplugin.getFee(order.getAmountPayable());
            BigDecimal bigdecimal1 = order.getAmountPayable().add(bigdecimal);
            Payment payment = new Payment();
            payment.setSn(IIlIIIIl.generate(net.shopxx.entity.Sn.Type.payment));
            payment.setType(net.shopxx.entity.Payment.Type.online);
            payment.setStatus(net.shopxx.entity.Payment.Status.wait);
            payment.setPaymentMethod((new StringBuilder(String.valueOf(order.getPaymentMethodName()))).append("-").append(paymentplugin.getPaymentName()).toString());
            payment.setFee(bigdecimal);
            payment.setAmount(bigdecimal1);
            payment.setPaymentPluginId(paymentPluginId);
            payment.setExpire(paymentplugin.getTimeout() == null ? null : DateUtils.addMinutes(new Date(), paymentplugin.getTimeout().intValue()));
            payment.setMember(null);
            payment.setOrder(order);
            IIlIIIII.save(payment);
            model.addAttribute("url", paymentplugin.getUrl());
            model.addAttribute("method", paymentplugin.getMethod());
            model.addAttribute("parameterMap", paymentplugin.getParameterMap(payment.getSn(), bigdecimal1, order.getProductName(), request));
            return "shop/payment/submit";
        }
    }

    public String returns(String sn, HttpServletRequest request, ModelMap model)
    {
        Payment payment = IIlIIIII.findBySn(sn);
        if(payment == null)
            return "/shop/common/error";
        if(payment.getStatus() == net.shopxx.entity.Payment.Status.wait)
        {
            PaymentPlugin paymentplugin = IIIlllll.getPaymentPlugin(payment.getPaymentPluginId());
            if(paymentplugin != null && paymentplugin.verify(sn, request))
            {
                BigDecimal bigdecimal = paymentplugin.getAmount(sn, request);
                if(bigdecimal.compareTo(payment.getAmount()) >= 0)
                {
                    Order order = payment.getOrder();
                    if(order != null)
                    {
                        if(bigdecimal.compareTo(order.getAmountPayable()) >= 0)
                            IIIlllIl.payment(order, payment, null);
                    } else
                    {
                        net.shopxx.entity.Member member = payment.getMember();
                        if(member != null)
                        {
                            BigDecimal bigdecimal1 = payment.getAmount().subtract(payment.getFee());
                            IIIllllI.update(member, null, bigdecimal1, IIIllIlI("shop.payment.paymentName", new Object[] {
                                paymentplugin.getPaymentName()
                            }), null);
                        }
                    }
                }
                payment.setStatus(net.shopxx.entity.Payment.Status.success);
                payment.setAmount(bigdecimal);
                payment.setPaymentDate(new Date());
            } else
            {
                payment.setStatus(net.shopxx.entity.Payment.Status.failure);
                payment.setPaymentDate(new Date());
            }
            IIlIIIII.update(payment);
        }
        model.addAttribute("payment", payment);
        return "shop/payment/return";
    }

    public String notify(String sn, HttpServletRequest request, ModelMap model)
    {
        Payment payment = IIlIIIII.findBySn(sn);
        if(payment != null)
        {
            PaymentPlugin paymentplugin = IIIlllll.getPaymentPlugin(payment.getPaymentPluginId());
            if(paymentplugin != null)
            {
                if(payment.getStatus() == net.shopxx.entity.Payment.Status.wait && paymentplugin.verify(sn, request))
                {
                    BigDecimal bigdecimal = paymentplugin.getAmount(sn, request);
                    if(bigdecimal.compareTo(payment.getAmount()) >= 0)
                    {
                        Order order = payment.getOrder();
                        if(order != null)
                        {
                            if(bigdecimal.compareTo(order.getAmountPayable()) >= 0)
                                IIIlllIl.payment(order, payment, null);
                        } else
                        {
                            net.shopxx.entity.Member member = payment.getMember();
                            if(member != null)
                            {
                                BigDecimal bigdecimal1 = payment.getAmount().subtract(payment.getFee());
                                IIIllllI.update(member, null, bigdecimal1, IIIllIlI("shop.payment.paymentName", new Object[] {
                                    paymentplugin.getPaymentName()
                                }), null);
                            }
                        }
                    }
                    payment.setStatus(net.shopxx.entity.Payment.Status.success);
                    payment.setAmount(bigdecimal);
                    payment.setPaymentDate(new Date());
                    IIlIIIII.update(payment);
                }
                model.addAttribute("notifyContext", paymentplugin.getNotifyContext(sn, request));
            }
        }
        return "shop/payment/notify";
    }

    private OrderService IIIlllIl;
    private MemberService IIIllllI;
    private PluginService IIIlllll;
    private PaymentService IIlIIIII;
    private SnService IIlIIIIl;
}
