// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import net.shopxx.service.ProductCategoryService;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.shop:
//            BaseController

public class ProductCategoryController extends BaseController
{

    public ProductCategoryController()
    {
    }

    public String index(ModelMap model)
    {
        model.addAttribute("rootProductCategories", IIIlllIl.findRoots());
        return "/shop/product_category/index";
    }

    private ProductCategoryService IIIlllIl;
}
