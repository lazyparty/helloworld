// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.AdPosition;
import net.shopxx.service.AdPositionService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class AdPositionController extends BaseController
{

    public AdPositionController()
    {
    }

    public String add(ModelMap model)
    {
        return "/admin/ad_position/add";
    }

    public String save(AdPosition adPosition, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(adPosition, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            adPosition.setAds(null);
            IIIlllIl.save(adPosition);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("adPosition", IIIlllIl.find(id));
        return "/admin/ad_position/edit";
    }

    public String update(AdPosition adPosition, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(adPosition, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.update(adPosition, new String[] {
                "ads"
            });
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/ad_position/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private AdPositionService IIIlllIl;
}
