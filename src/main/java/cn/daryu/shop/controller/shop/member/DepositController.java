// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop.member;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.shopxx.Pageable;
import net.shopxx.Setting;
import net.shopxx.controller.shop.BaseController;
import net.shopxx.entity.Payment;
import net.shopxx.plugin.PaymentPlugin;
import net.shopxx.service.*;
import net.shopxx.util.SettingUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.ui.ModelMap;

public class DepositController extends BaseController
{

    public DepositController()
    {
    }

    public String recharge(ModelMap model)
    {
        List list1 = IIlIIIII.getPaymentPlugins(true);
        if(!list1.isEmpty())
        {
            model.addAttribute("defaultPaymentPlugin", list1.get(0));
            model.addAttribute("paymentPlugins", list1);
        }
        return "shop/member/deposit/recharge";
    }

    public String recharge(BigDecimal amount, String paymentPluginId, HttpServletRequest request, ModelMap model)
    {
        PaymentPlugin paymentplugin = IIlIIIII.getPaymentPlugin(paymentPluginId);
        if(paymentplugin == null || !paymentplugin.getIsEnabled())
            return "/shop/common/error";
        Setting setting = SettingUtils.get();
        if(amount == null || amount.compareTo(new BigDecimal(0)) <= 0 || amount.precision() > 15 || amount.scale() > setting.getPriceScale().intValue())
        {
            return "/shop/common/error";
        } else
        {
            BigDecimal bigdecimal = paymentplugin.getFee(amount);
            amount = amount.add(bigdecimal);
            Payment payment = new Payment();
            payment.setSn(IIlIIIlI.generate(net.shopxx.entity.Sn.Type.payment));
            payment.setType(net.shopxx.entity.Payment.Type.online);
            payment.setStatus(net.shopxx.entity.Payment.Status.wait);
            payment.setPaymentMethod(paymentplugin.getPaymentName());
            payment.setFee(bigdecimal);
            payment.setAmount(amount);
            payment.setPaymentPluginId(paymentPluginId);
            payment.setExpire(paymentplugin.getTimeout() == null ? null : DateUtils.addMinutes(new Date(), paymentplugin.getTimeout().intValue()));
            payment.setMember(IIIllllI.getCurrent());
            IIlIIIIl.save(payment);
            model.addAttribute("url", paymentplugin.getUrl());
            model.addAttribute("method", paymentplugin.getMethod());
            model.addAttribute("parameterMap", paymentplugin.getParameterMap(payment.getSn(), amount, IIIllIlI("shop.member.deposit.recharge", new Object[0]), request));
            return "shop/payment/submit";
        }
    }

    public String list(Integer pageNumber, ModelMap model)
    {
        net.shopxx.entity.Member member = IIIllllI.getCurrent();
        Pageable pageable = new Pageable(pageNumber, Integer.valueOf(10));
        model.addAttribute("page", IIIlllll.findPage(member, pageable));
        return "shop/member/deposit/list";
    }

    private static final int IIIlllIl = 10;
    private MemberService IIIllllI;
    private DepositService IIIlllll;
    private PluginService IIlIIIII;
    private PaymentService IIlIIIIl;
    private SnService IIlIIIlI;
}
