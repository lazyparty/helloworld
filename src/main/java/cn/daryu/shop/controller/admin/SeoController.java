// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.Pageable;
import net.shopxx.entity.Seo;
import net.shopxx.service.SeoService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class SeoController extends BaseController
{

    public SeoController()
    {
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("seo", IIIlllIl.find(id));
        return "/admin/seo/edit";
    }

    public String update(Seo seo, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(seo, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.update(seo, new String[] {
                "type"
            });
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/seo/list";
    }

    private SeoService IIIlllIl;
}
