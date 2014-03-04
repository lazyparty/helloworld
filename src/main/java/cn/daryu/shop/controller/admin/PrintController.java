// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.entity.DeliveryCenter;
import net.shopxx.entity.DeliveryTemplate;
import net.shopxx.service.*;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class PrintController extends BaseController
{

    public PrintController()
    {
    }

    public String order(Long id, ModelMap model)
    {
        model.addAttribute("order", IIIlllIl.find(id));
        return "/admin/print/order";
    }

    public String product(Long id, ModelMap model)
    {
        model.addAttribute("order", IIIlllIl.find(id));
        return "/admin/print/product";
    }

    public String shipping(Long id, ModelMap model)
    {
        model.addAttribute("order", IIIlllIl.find(id));
        return "/admin/print/shipping";
    }

    public String delivery(Long orderId, Long deliveryTemplateId, Long deliveryCenterId, ModelMap model)
    {
        DeliveryTemplate deliverytemplate = (DeliveryTemplate)IIIllllI.find(deliveryTemplateId);
        DeliveryCenter deliverycenter = (DeliveryCenter)IIIlllll.find(deliveryCenterId);
        if(deliverytemplate == null)
            deliverytemplate = IIIllllI.findDefault();
        if(deliverycenter == null)
            deliverycenter = IIIlllll.findDefault();
        model.addAttribute("deliveryTemplates", IIIllllI.findAll());
        model.addAttribute("deliveryCenters", IIIlllll.findAll());
        model.addAttribute("order", IIIlllIl.find(orderId));
        model.addAttribute("deliveryTemplate", deliverytemplate);
        model.addAttribute("deliveryCenter", deliverycenter);
        return "/admin/print/delivery";
    }

    private OrderService IIIlllIl;
    private DeliveryTemplateService IIIllllI;
    private DeliveryCenterService IIIlllll;
}
