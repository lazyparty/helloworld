// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop.member;

import net.shopxx.Pageable;
import net.shopxx.controller.shop.BaseController;
import net.shopxx.service.ConsultationService;
import net.shopxx.service.MemberService;
import org.springframework.ui.ModelMap;

public class ConsultationController extends BaseController
{

    public ConsultationController()
    {
    }

    public String list(Integer pageNumber, ModelMap model)
    {
        net.shopxx.entity.Member member = IIIllllI.getCurrent();
        Pageable pageable = new Pageable(pageNumber, Integer.valueOf(10));
        model.addAttribute("page", IIIlllll.findPage(member, null, null, pageable));
        return "shop/member/consultation/list";
    }

    private static final int IIIlllIl = 10;
    private MemberService IIIllllI;
    private ConsultationService IIIlllll;
}
