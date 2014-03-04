// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.*;
import net.shopxx.Message;
import net.shopxx.entity.ProductCategory;
import net.shopxx.service.BrandService;
import net.shopxx.service.ProductCategoryService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class ProductCategoryController extends BaseController
{

    public ProductCategoryController()
    {
    }

    public String add(ModelMap model)
    {
        model.addAttribute("productCategoryTree", IIIlllIl.findTree());
        model.addAttribute("brands", IIIllllI.findAll());
        return "/admin/product_category/add";
    }

    public String save(ProductCategory productCategory, Long parentId, Long brandIds[], RedirectAttributes redirectAttributes)
    {
        productCategory.setParent((ProductCategory)IIIlllIl.find(parentId));
        productCategory.setBrands(new HashSet(IIIllllI.findList(brandIds)));
        if(!IIIllIlI(productCategory, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            productCategory.setTreePath(null);
            productCategory.setGrade(null);
            productCategory.setChildren(null);
            productCategory.setProducts(null);
            productCategory.setParameterGroups(null);
            productCategory.setAttributes(null);
            productCategory.setPromotions(null);
            IIIlllIl.save(productCategory);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, ModelMap model)
    {
        ProductCategory productcategory = (ProductCategory)IIIlllIl.find(id);
        model.addAttribute("productCategoryTree", IIIlllIl.findTree());
        model.addAttribute("brands", IIIllllI.findAll());
        model.addAttribute("productCategory", productcategory);
        model.addAttribute("children", IIIlllIl.findChildren(productcategory));
        return "/admin/product_category/edit";
    }

    public String update(ProductCategory productCategory, Long parentId, Long brandIds[], RedirectAttributes redirectAttributes)
    {
        productCategory.setParent((ProductCategory)IIIlllIl.find(parentId));
        productCategory.setBrands(new HashSet(IIIllllI.findList(brandIds)));
        if(!IIIllIlI(productCategory, new Class[0]))
            return "/admin/common/error";
        if(productCategory.getParent() != null)
        {
            ProductCategory productcategory = productCategory.getParent();
            if(productcategory.equals(productCategory))
                return "/admin/common/error";
            List list1 = IIIlllIl.findChildren(productcategory);
            if(list1 != null && list1.contains(productcategory))
                return "/admin/common/error";
        }
        IIIlllIl.update(productCategory, new String[] {
            "treePath", "grade", "children", "products", "parameterGroups", "attributes", "promotions"
        });
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String list(ModelMap model)
    {
        model.addAttribute("productCategoryTree", IIIlllIl.findTree());
        return "/admin/product_category/list";
    }

    public Message delete(Long id)
    {
        ProductCategory productcategory = (ProductCategory)IIIlllIl.find(id);
        if(productcategory == null)
            return IIIllIll;
        Set set = productcategory.getChildren();
        if(set != null && !set.isEmpty())
            return Message.error("admin.productCategory.deleteExistChildrenNotAllowed", new Object[0]);
        Set set1 = productcategory.getProducts();
        if(set1 != null && !set1.isEmpty())
        {
            return Message.error("admin.productCategory.deleteExistProductNotAllowed", new Object[0]);
        } else
        {
            IIIlllIl.delete(id);
            return IIIlllII;
        }
    }

    private ProductCategoryService IIIlllIl;
    private BrandService IIIllllI;
}
