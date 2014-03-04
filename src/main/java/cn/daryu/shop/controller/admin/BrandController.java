// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Brand;
import net.shopxx.service.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class BrandController extends BaseController
{

    public BrandController()
    {
    }

    public String add(ModelMap model)
    {
        model.addAttribute("types", net.shopxx.entity.Brand.Type.values());
        return "/admin/brand/add";
    }

    public String save(Brand brand, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(brand, new Class[0]))
            return "/admin/common/error";
        if(brand.getType() == net.shopxx.entity.Brand.Type.text)
            brand.setLogo(null);
        else
        if(StringUtils.isEmpty(brand.getLogo()))
            return "/admin/common/error";
        brand.setProducts(null);
        brand.setProductCategories(null);
        brand.setPromotions(null);
        IIIlllIl.save(brand);
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("types", net.shopxx.entity.Brand.Type.values());
        model.addAttribute("brand", IIIlllIl.find(id));
        return "/admin/brand/edit";
    }

    public String update(Brand brand, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(brand, new Class[0]))
            return "/admin/common/error";
        if(brand.getType() == net.shopxx.entity.Brand.Type.text)
            brand.setLogo(null);
        else
        if(StringUtils.isEmpty(brand.getLogo()))
            return "/admin/common/error";
        IIIlllIl.update(brand, new String[] {
            "products", "productCategories", "promotions"
        });
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/brand/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private BrandService IIIlllIl;
}
