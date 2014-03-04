// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.Pageable;
import net.shopxx.entity.Member;
import net.shopxx.service.DepositService;
import net.shopxx.service.MemberService;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class DepositController extends BaseController
{

    public DepositController()
    {
    }

    public String list(Long memberId, Pageable pageable, ModelMap model)
    {
        Member member = (Member)IIIllllI.find(memberId);
        if(member != null)
        {
            model.addAttribute("member", member);
            model.addAttribute("page", IIIlllIl.findPage(member, pageable));
        } else
        {
            model.addAttribute("page", IIIlllIl.findPage(pageable));
        }
        return "/admin/deposit/list";
    }

    private DepositService IIIlllIl;
    private MemberService IIIllllI;
}
