// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.DeliveryCorp;
import net.shopxx.entity.ShippingMethod;
import net.shopxx.service.DeliveryCorpService;
import net.shopxx.service.ShippingMethodService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class ShippingMethodController extends BaseController
{

    public ShippingMethodController()
    {
    }

    public String add(ModelMap model)
    {
        model.addAttribute("deliveryCorps", IIIllllI.findAll());
        return "/admin/shipping_method/add";
    }

    public String save(ShippingMethod shippingMethod, Long defaultDeliveryCorpId, RedirectAttributes redirectAttributes)
    {
        shippingMethod.setDefaultDeliveryCorp((DeliveryCorp)IIIllllI.find(defaultDeliveryCorpId));
        if(!IIIllIlI(shippingMethod, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            shippingMethod.setPaymentMethods(null);
            shippingMethod.setOrders(null);
            IIIlllIl.save(shippingMethod);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("deliveryCorps", IIIllllI.findAll());
        model.addAttribute("shippingMethod", IIIlllIl.find(id));
        return "/admin/shipping_method/edit";
    }

    public String update(ShippingMethod shippingMethod, Long defaultDeliveryCorpId, RedirectAttributes redirectAttributes)
    {
        shippingMethod.setDefaultDeliveryCorp((DeliveryCorp)IIIllllI.find(defaultDeliveryCorpId));
        if(!IIIllIlI(shippingMethod, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.update(shippingMethod, new String[] {
                "paymentMethods", "orders"
            });
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/shipping_method/list";
    }

    public Message delete(Long ids[])
    {
        if((long)ids.length >= IIIlllIl.count())
        {
            return Message.error("admin.common.deleteAllNotAllowed", new Object[0]);
        } else
        {
            IIIlllIl.delete(ids);
            return IIIlllII;
        }
    }

    private ShippingMethodService IIIlllIl;
    private DeliveryCorpService IIIllllI;
}
