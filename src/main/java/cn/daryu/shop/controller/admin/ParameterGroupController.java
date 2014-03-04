// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.Iterator;
import java.util.List;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.*;
import net.shopxx.service.ParameterGroupService;
import net.shopxx.service.ProductCategoryService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class ParameterGroupController extends BaseController
{

    public ParameterGroupController()
    {
    }

    public String add(ModelMap model)
    {
        model.addAttribute("productCategoryTree", IIIllllI.findTree());
        return "/admin/parameter_group/add";
    }

    public String save(ParameterGroup parameterGroup, Long productCategoryId, RedirectAttributes redirectAttributes)
    {
        for(Iterator iterator = parameterGroup.getParameters().iterator(); iterator.hasNext();)
        {
            Parameter parameter = (Parameter)iterator.next();
            if(parameter == null || parameter.getName() == null)
                iterator.remove();
            else
                parameter.setParameterGroup(parameterGroup);
        }

        parameterGroup.setProductCategory((ProductCategory)IIIllllI.find(productCategoryId));
        if(!IIIllIlI(parameterGroup, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.save(parameterGroup);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("parameterGroup", IIIlllIl.find(id));
        model.addAttribute("productCategoryTree", IIIllllI.findTree());
        return "/admin/parameter_group/edit";
    }

    public String update(ParameterGroup parameterGroup, Long productCategoryId, RedirectAttributes redirectAttributes)
    {
        for(Iterator iterator = parameterGroup.getParameters().iterator(); iterator.hasNext();)
        {
            Parameter parameter = (Parameter)iterator.next();
            if(parameter == null || parameter.getName() == null)
                iterator.remove();
            else
                parameter.setParameterGroup(parameterGroup);
        }

        parameterGroup.setProductCategory((ProductCategory)IIIllllI.find(productCategoryId));
        if(!IIIllIlI(parameterGroup, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.update(parameterGroup);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/parameter_group/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private ParameterGroupService IIIlllIl;
    private ProductCategoryService IIIllllI;
}
