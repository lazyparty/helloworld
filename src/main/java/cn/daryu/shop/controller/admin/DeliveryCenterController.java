// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Area;
import net.shopxx.entity.DeliveryCenter;
import net.shopxx.service.AreaService;
import net.shopxx.service.DeliveryCenterService;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class DeliveryCenterController extends BaseController
{

    public DeliveryCenterController()
    {
    }

    public String add()
    {
        return "/admin/delivery_center/add";
    }

    public String save(DeliveryCenter deliveryCenter, Long areaId, Model model, RedirectAttributes redirectAttributes)
    {
        deliveryCenter.setArea((Area)IIIllllI.find(areaId));
        if(!IIIllIlI(deliveryCenter, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            deliveryCenter.setAreaName(null);
            IIIlllIl.save(deliveryCenter);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, Model model)
    {
        model.addAttribute("deliveryCenter", IIIlllIl.find(id));
        return "/admin/delivery_center/edit";
    }

    public String update(DeliveryCenter deliveryCenter, Long areaId, RedirectAttributes redirectAttributes)
    {
        deliveryCenter.setArea((Area)IIIllllI.find(areaId));
        if(!IIIllIlI(deliveryCenter, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.update(deliveryCenter, new String[] {
                "areaName"
            });
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(Model model, Pageable pageable)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/delivery_center/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private DeliveryCenterService IIIlllIl;
    private AreaService IIIllllI;
}
