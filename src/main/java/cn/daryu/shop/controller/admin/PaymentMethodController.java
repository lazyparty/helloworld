// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.HashSet;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.PaymentMethod;
import net.shopxx.service.PaymentMethodService;
import net.shopxx.service.ShippingMethodService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class PaymentMethodController extends BaseController
{

    public PaymentMethodController()
    {
    }

    public String add(ModelMap model)
    {
        model.addAttribute("types", net.shopxx.entity.PaymentMethod.Type.values());
        model.addAttribute("shippingMethods", IIIllllI.findAll());
        return "/admin/payment_method/add";
    }

    public String save(PaymentMethod paymentMethod, Long shippingMethodIds[], RedirectAttributes redirectAttributes)
    {
        paymentMethod.setShippingMethods(new HashSet(IIIllllI.findList(shippingMethodIds)));
        if(!IIIllIlI(paymentMethod, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            paymentMethod.setOrders(null);
            IIIlllIl.save(paymentMethod);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("types", net.shopxx.entity.PaymentMethod.Type.values());
        model.addAttribute("shippingMethods", IIIllllI.findAll());
        model.addAttribute("paymentMethod", IIIlllIl.find(id));
        return "/admin/payment_method/edit";
    }

    public String update(PaymentMethod paymentMethod, Long shippingMethodIds[], RedirectAttributes redirectAttributes)
    {
        paymentMethod.setShippingMethods(new HashSet(IIIllllI.findList(shippingMethodIds)));
        if(!IIIllIlI(paymentMethod, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.update(paymentMethod, new String[] {
                "orders"
            });
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/payment_method/list";
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

    private PaymentMethodService IIIlllIl;
    private ShippingMethodService IIIllllI;
}
