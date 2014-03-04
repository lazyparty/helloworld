// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.service.ReturnsService;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class ReturnsController extends BaseController
{

    public ReturnsController()
    {
    }

    public String view(Long id, ModelMap model)
    {
        model.addAttribute("returns", IIIlllIl.find(id));
        return "/admin/returns/view";
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/returns/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private ReturnsService IIIlllIl;
}
