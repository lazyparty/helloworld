// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import net.shopxx.Pageable;
import net.shopxx.ResourceNotFoundException;
import net.shopxx.entity.Brand;
import net.shopxx.service.BrandService;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.shop:
//            BaseController

public class BrandController extends BaseController
{

    public BrandController()
    {
    }

    public String list(Integer pageNumber, ModelMap model)
    {
        Pageable pageable = new Pageable(pageNumber, Integer.valueOf(40));
        model.addAttribute("page", IIIllllI.findPage(pageable));
        return "/shop/brand/list";
    }

    public String content(Long id, ModelMap model)
    {
        Brand brand = (Brand)IIIllllI.find(id);
        if(brand == null)
        {
            throw new ResourceNotFoundException();
        } else
        {
            model.addAttribute("brand", brand);
            return "/shop/brand/content";
        }
    }

    private static final int IIIlllIl = 40;
    private BrandService IIIllllI;
}
