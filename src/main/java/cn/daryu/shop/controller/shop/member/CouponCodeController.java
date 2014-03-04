// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop.member;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.controller.shop.BaseController;
import net.shopxx.entity.Coupon;
import net.shopxx.entity.Member;
import net.shopxx.service.*;
import org.springframework.ui.ModelMap;

public class CouponCodeController extends BaseController
{

    public CouponCodeController()
    {
    }

    public String exchange(Integer pageNumber, ModelMap model)
    {
        Pageable pageable = new Pageable(pageNumber, Integer.valueOf(10));
        model.addAttribute("page", IIIlllll.findPage(Boolean.valueOf(true), Boolean.valueOf(true), Boolean.valueOf(false), pageable));
        return "shop/member/coupon_code/exchange";
    }

    public Message exchange(Long id)
    {
        Coupon coupon = (Coupon)IIIlllll.find(id);
        if(coupon == null || !coupon.getIsEnabled().booleanValue() || !coupon.getIsExchange().booleanValue() || coupon.hasExpired())
            return IIIllIll;
        Member member = IIIllllI.getCurrent();
        if(member.getPoint().longValue() < (long)coupon.getPoint().intValue())
        {
            return Message.warn("shop.member.couponCode.point", new Object[0]);
        } else
        {
            IIlIIIII.exchange(coupon, member);
            return IIIlllII;
        }
    }

    public String list(Integer pageNumber, ModelMap model)
    {
        Member member = IIIllllI.getCurrent();
        Pageable pageable = new Pageable(pageNumber, Integer.valueOf(10));
        model.addAttribute("page", IIlIIIII.findPage(member, pageable));
        return "shop/member/coupon_code/list";
    }

    private static final int IIIlllIl = 10;
    private MemberService IIIllllI;
    private CouponService IIIlllll;
    private CouponCodeService IIlIIIII;
}
