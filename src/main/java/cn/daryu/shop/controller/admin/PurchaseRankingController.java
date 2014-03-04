// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.Date;
import net.shopxx.Pageable;
import net.shopxx.service.MemberService;
import org.springframework.ui.Model;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class PurchaseRankingController extends BaseController
{

    public PurchaseRankingController()
    {
    }

    public String list(Date beginDate, Date endDate, Pageable pageable, Model model)
    {
        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("page", IIIlllIl.findPurchasePage(beginDate, endDate, pageable));
        return "/admin/purchase_ranking/list";
    }

    private MemberService IIIlllIl;
}
