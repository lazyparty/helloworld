// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.DeliveryTemplate;
import net.shopxx.service.DeliveryTemplateService;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class DeliveryTemplateController extends BaseController
{

    public DeliveryTemplateController()
    {
    }

    public String add(Pageable pageable)
    {
        return "/admin/delivery_template/add";
    }

    public String save(DeliveryTemplate deliveryTemplate, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(deliveryTemplate, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.save(deliveryTemplate);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String eidt(Long id, Model model)
    {
        model.addAttribute("deliveryTemplate", IIIlllIl.find(id));
        return "/admin/delivery_template/edit";
    }

    public String udpate(DeliveryTemplate deliveryTemplate, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(deliveryTemplate, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.update(deliveryTemplate);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(Pageable pageable, Model model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/delivery_template/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private DeliveryTemplateService IIIlllIl;
}
