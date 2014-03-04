// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.DeliveryCorp;
import net.shopxx.service.DeliveryCorpService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class DeliveryCorpController extends BaseController
{

    public DeliveryCorpController()
    {
    }

    public String add()
    {
        return "/admin/delivery_corp/add";
    }

    public String save(DeliveryCorp deliveryCorp, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(deliveryCorp, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            deliveryCorp.setShippingMethods(null);
            IIIlllIl.save(deliveryCorp);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("deliveryCorp", IIIlllIl.find(id));
        return "/admin/delivery_corp/edit";
    }

    public String update(DeliveryCorp deliveryCorp, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(deliveryCorp, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.update(deliveryCorp, new String[] {
                "shippingMethods"
            });
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/delivery_corp/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private DeliveryCorpService IIIlllIl;
}
