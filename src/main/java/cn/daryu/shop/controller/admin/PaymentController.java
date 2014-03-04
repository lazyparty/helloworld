// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Payment;
import net.shopxx.service.PaymentService;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class PaymentController extends BaseController
{

    public PaymentController()
    {
    }

    public String view(Long id, ModelMap model)
    {
        model.addAttribute("payment", IIIlllIl.find(id));
        return "/admin/payment/view";
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/payment/list";
    }

    public Message delete(Long ids[])
    {
        if(ids != null)
        {
            Long along[];
            int j = (along = ids).length;
            for(int i = 0; i < j; i++)
            {
                Long long1 = along[i];
                Payment payment = (Payment)IIIlllIl.find(long1);
                if(payment != null && payment.getExpire() != null && !payment.hasExpired())
                    return Message.error("admin.payment.deleteUnexpiredNotAllowed", new Object[0]);
            }

            IIIlllIl.delete(ids);
        }
        return IIIlllII;
    }

    private PaymentService IIIlllIl;
}
