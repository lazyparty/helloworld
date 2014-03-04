// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.Date;
import net.shopxx.Pageable;
import net.shopxx.service.ProductService;
import org.springframework.ui.Model;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class SalesRankingController extends BaseController
{

    public SalesRankingController()
    {
    }

    public String list(Date beginDate, Date endDate, Pageable pageable, Model model)
    {
        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("page", IIIlllIl.findSalesPage(beginDate, endDate, pageable));
        return "/admin/sales_ranking/list";
    }

    private ProductService IIIlllIl;
}
