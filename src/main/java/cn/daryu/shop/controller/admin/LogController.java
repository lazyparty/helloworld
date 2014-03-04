// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.service.LogService;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class LogController extends BaseController
{

    public LogController()
    {
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/log/list";
    }

    public String view(Long id, ModelMap model)
    {
        model.addAttribute("log", IIIlllIl.find(id));
        return "/admin/log/view";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    public Message clear()
    {
        IIIlllIl.clear();
        return IIIlllII;
    }

    private LogService IIIlllIl;
}
