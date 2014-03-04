// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.*;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Attribute;
import net.shopxx.entity.ProductCategory;
import net.shopxx.service.AttributeService;
import net.shopxx.service.ProductCategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class AttributeController extends BaseController
{

    public AttributeController()
    {
    }

    public String add(ModelMap model)
    {
        model.addAttribute("productCategoryTree", IIIllllI.findTree());
        model.addAttribute("attributeValuePropertyCount", Integer.valueOf(20));
        return "/admin/attribute/add";
    }

    public String save(Attribute attribute, Long productCategoryId, RedirectAttributes redirectAttributes)
    {
        for(Iterator iterator = attribute.getOptions().iterator(); iterator.hasNext();)
        {
            String s = (String)iterator.next();
            if(StringUtils.isEmpty(s))
                iterator.remove();
        }

        attribute.setProductCategory((ProductCategory)IIIllllI.find(productCategoryId));
        if(!IIIllIlI(attribute, new Class[] {
    net/shopxx/entity/BaseEntity$Save
}))
            return "/admin/common/error";
        if(attribute.getProductCategory().getAttributes().size() >= 20)
        {
            IIIllIlI(redirectAttributes, Message.error("admin.attribute.addCountNotAllowed", new Object[] {
                Integer.valueOf(20)
            }));
        } else
        {
            attribute.setPropertyIndex(null);
            IIIlllIl.save(attribute);
            IIIllIlI(redirectAttributes, IIIlllII);
        }
        return "redirect:list.jhtml";
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("productCategoryTree", IIIllllI.findTree());
        model.addAttribute("attributeValuePropertyCount", Integer.valueOf(20));
        model.addAttribute("attribute", IIIlllIl.find(id));
        return "/admin/attribute/edit";
    }

    public String update(Attribute attribute, RedirectAttributes redirectAttributes)
    {
        for(Iterator iterator = attribute.getOptions().iterator(); iterator.hasNext();)
        {
            String s = (String)iterator.next();
            if(StringUtils.isEmpty(s))
                iterator.remove();
        }

        if(!IIIllIlI(attribute, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.update(attribute, new String[] {
                "propertyIndex", "productCategory"
            });
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/attribute/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private AttributeService IIIlllIl;
    private ProductCategoryService IIIllllI;
}
