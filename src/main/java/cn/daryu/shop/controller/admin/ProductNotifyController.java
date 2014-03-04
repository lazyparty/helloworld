// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.service.ProductNotifyService;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class ProductNotifyController extends BaseController
{

    public ProductNotifyController()
    {
    }

    public Message send(Long ids[])
    {
        int i = IIIlllIl.send(ids);
        return Message.success("admin.productNotify.sentSuccess", new Object[] {
            Integer.valueOf(i)
        });
    }

    public String list(Boolean isMarketable, Boolean isOutOfStock, Boolean hasSent, Pageable pageable, ModelMap model)
    {
        model.addAttribute("isMarketable", isMarketable);
        model.addAttribute("isOutOfStock", isOutOfStock);
        model.addAttribute("hasSent", hasSent);
        model.addAttribute("page", IIIlllIl.findPage(null, isMarketable, isOutOfStock, hasSent, pageable));
        return "/admin/product_notify/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private ProductNotifyService IIIlllIl;
}
