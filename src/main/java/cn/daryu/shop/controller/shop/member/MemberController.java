// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop.member;

import net.shopxx.controller.shop.BaseController;
import net.shopxx.service.*;
import org.springframework.ui.ModelMap;

public class MemberController extends BaseController
{

    public MemberController()
    {
    }

    public String index(Integer pageNumber, ModelMap model)
    {
        net.shopxx.entity.Member member = IIIllllI.getCurrent();
        model.addAttribute("waitingPaymentOrderCount", IIIlllll.waitingPaymentCount(member));
        model.addAttribute("waitingShippingOrderCount", IIIlllll.waitingShippingCount(member));
        model.addAttribute("messageCount", IIlIIIIl.count(member, Boolean.valueOf(false)));
        model.addAttribute("couponCodeCount", IIlIIIII.count(null, member, null, Boolean.valueOf(false), Boolean.valueOf(false)));
        model.addAttribute("favoriteCount", IIlIIIlI.count(member, null, null, null, null, null, null));
        model.addAttribute("productNotifyCount", IIlIIIll.count(member, null, null, null));
        model.addAttribute("reviewCount", IIlIIlII.count(member, null, null, null));
        model.addAttribute("consultationCount", IIlIIlIl.count(member, null, null));
        model.addAttribute("newOrders", IIIlllll.findList(member, Integer.valueOf(6), null, null));
        return "shop/member/index";
    }

    private static final int IIIlllIl = 6;
    private MemberService IIIllllI;
    private OrderService IIIlllll;
    private CouponCodeService IIlIIIII;
    private MessageService IIlIIIIl;
    private ProductService IIlIIIlI;
    private ProductNotifyService IIlIIIll;
    private ReviewService IIlIIlII;
    private ConsultationService IIlIIlIl;
}
