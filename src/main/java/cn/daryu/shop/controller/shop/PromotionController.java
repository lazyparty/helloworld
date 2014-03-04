// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import net.shopxx.ResourceNotFoundException;
import net.shopxx.entity.Promotion;
import net.shopxx.service.PromotionService;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.shop:
//            BaseController

public class PromotionController extends BaseController
{

    public PromotionController()
    {
    }

    public String content(Long id, ModelMap model)
    {
        Promotion promotion = (Promotion)IIIlllIl.find(id);
        if(promotion == null)
        {
            throw new ResourceNotFoundException();
        } else
        {
            model.addAttribute("promotion", promotion);
            return "/shop/promotion/content";
        }
    }

    private PromotionService IIIlllIl;
}
