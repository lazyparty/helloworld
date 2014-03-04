// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.Template;
import net.shopxx.service.StaticService;
import net.shopxx.service.TemplateService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class SitemapController extends BaseController
{

    public SitemapController()
    {
    }

    public String build(ModelMap model)
    {
        Template template = IIIlllIl.get("sitemapIndex");
        model.addAttribute("sitemapIndexPath", template.getStaticPath());
        return "/admin/sitemap/build";
    }

    public String build(RedirectAttributes redirectAttributes)
    {
        IIIllllI.buildSitemap();
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:build.jhtml";
    }

    private TemplateService IIIlllIl;
    private StaticService IIIllllI;
}
