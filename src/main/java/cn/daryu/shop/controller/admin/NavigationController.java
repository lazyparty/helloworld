// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Navigation;
import net.shopxx.service.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class NavigationController extends BaseController
{

    public NavigationController()
    {
    }

    public String add(ModelMap model)
    {
        model.addAttribute("positions", net.shopxx.entity.Navigation.Position.values());
        model.addAttribute("articleCategoryTree", IIIllllI.findTree());
        model.addAttribute("productCategoryTree", IIIlllll.findTree());
        return "/admin/navigation/add";
    }

    public String save(Navigation navigation, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(navigation, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.save(navigation);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("positions", net.shopxx.entity.Navigation.Position.values());
        model.addAttribute("articleCategoryTree", IIIllllI.findTree());
        model.addAttribute("productCategoryTree", IIIlllll.findTree());
        model.addAttribute("navigation", IIIlllIl.find(id));
        return "/admin/navigation/edit";
    }

    public String update(Navigation navigation, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(navigation, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.update(navigation);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("topNavigations", IIIlllIl.findList(net.shopxx.entity.Navigation.Position.top));
        model.addAttribute("middleNavigations", IIIlllIl.findList(net.shopxx.entity.Navigation.Position.middle));
        model.addAttribute("bottomNavigations", IIIlllIl.findList(net.shopxx.entity.Navigation.Position.bottom));
        return "/admin/navigation/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private NavigationService IIIlllIl;
    private ArticleCategoryService IIIllllI;
    private ProductCategoryService IIIlllll;
}
