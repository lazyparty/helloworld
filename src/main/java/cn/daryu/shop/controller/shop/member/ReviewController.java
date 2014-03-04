// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop.member;

import net.shopxx.Pageable;
import net.shopxx.controller.shop.BaseController;
import net.shopxx.service.MemberService;
import net.shopxx.service.ReviewService;
import org.springframework.ui.ModelMap;

public class ReviewController extends BaseController
{

    public ReviewController()
    {
    }

    public String list(Integer pageNumber, ModelMap model)
    {
        net.shopxx.entity.Member member = IIIllllI.getCurrent();
        Pageable pageable = new Pageable(pageNumber, Integer.valueOf(10));
        model.addAttribute("page", IIIlllll.findPage(member, null, null, null, pageable));
        return "shop/member/review/list";
    }

    private static final int IIIlllIl = 10;
    private MemberService IIIllllI;
    private ReviewService IIIlllll;
}
