// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.service.CacheService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class CacheController extends BaseController
{

    public CacheController()
    {
    }

    public String clear(ModelMap model)
    {
        Long long1 = null;
        Long long2 = null;
        Long long3 = null;
        try
        {
            long1 = Long.valueOf(Runtime.getRuntime().totalMemory() / 1024L / 1024L);
            long2 = Long.valueOf(Runtime.getRuntime().maxMemory() / 1024L / 1024L);
            long3 = Long.valueOf(Runtime.getRuntime().freeMemory() / 1024L / 1024L);
        }
        catch(Exception exception) { }
        model.addAttribute("totalMemory", long1);
        model.addAttribute("maxMemory", long2);
        model.addAttribute("freeMemory", long3);
        model.addAttribute("cacheSize", Integer.valueOf(IIIlllIl.getCacheSize()));
        model.addAttribute("diskStorePath", IIIlllIl.getDiskStorePath());
        return "/admin/cache/clear";
    }

    public String clear(RedirectAttributes redirectAttributes)
    {
        IIIlllIl.clear();
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:clear.jhtml";
    }

    private CacheService IIIlllIl;
}
