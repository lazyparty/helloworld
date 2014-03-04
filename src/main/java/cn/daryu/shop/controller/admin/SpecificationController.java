// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.*;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Specification;
import net.shopxx.entity.SpecificationValue;
import net.shopxx.service.SpecificationService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class SpecificationController extends BaseController
{

    public SpecificationController()
    {
    }

    public String add(ModelMap model)
    {
        model.addAttribute("types", net.shopxx.entity.Specification.Type.values());
        return "/admin/specification/add";
    }

    public String save(Specification specification, RedirectAttributes redirectAttributes)
    {
        for(Iterator iterator = specification.getSpecificationValues().iterator(); iterator.hasNext();)
        {
            SpecificationValue specificationvalue = (SpecificationValue)iterator.next();
            if(specificationvalue == null || specificationvalue.getName() == null)
            {
                iterator.remove();
            } else
            {
                if(specification.getType() == net.shopxx.entity.Specification.Type.text)
                    specificationvalue.setImage(null);
                specificationvalue.setSpecification(specification);
            }
        }

        if(!IIIllIlI(specification, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            specification.setProducts(null);
            IIIlllIl.save(specification);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("types", net.shopxx.entity.Specification.Type.values());
        model.addAttribute("specification", IIIlllIl.find(id));
        return "/admin/specification/edit";
    }

    public String update(Specification specification, RedirectAttributes redirectAttributes)
    {
        for(Iterator iterator = specification.getSpecificationValues().iterator(); iterator.hasNext();)
        {
            SpecificationValue specificationvalue = (SpecificationValue)iterator.next();
            if(specificationvalue == null || specificationvalue.getName() == null)
            {
                iterator.remove();
            } else
            {
                if(specification.getType() == net.shopxx.entity.Specification.Type.text)
                    specificationvalue.setImage(null);
                specificationvalue.setSpecification(specification);
            }
        }

        if(!IIIllIlI(specification, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.update(specification, new String[] {
                "products"
            });
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/specification/list";
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
                Specification specification = (Specification)IIIlllIl.find(long1);
                if(specification != null && specification.getProducts() != null && !specification.getProducts().isEmpty())
                    return Message.error("admin.specification.deleteExistProductNotAllowed", new Object[] {
                        specification.getName()
                    });
            }

            IIIlllIl.delete(ids);
        }
        return IIIlllII;
    }

    private SpecificationService IIIlllIl;
}
