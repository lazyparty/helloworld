// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Tag;
import net.shopxx.service.TagService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class TagController extends BaseController
{

    public TagController()
    {
    }

    public String add(ModelMap model)
    {
        model.addAttribute("types", net.shopxx.entity.Tag.Type.values());
        return "/admin/tag/add";
    }

    public String save(Tag tag, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(tag, new Class[] {
    net/shopxx/entity/BaseEntity$Save
}))
        {
            return "/admin/common/error";
        } else
        {
            tag.setArticles(null);
            tag.setProducts(null);
            IIIlllIl.save(tag);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("types", net.shopxx.entity.Tag.Type.values());
        model.addAttribute("tag", IIIlllIl.find(id));
        return "/admin/tag/edit";
    }

    public String update(Tag tag, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(tag, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.update(tag, new String[] {
                "type", "articles", "products"
            });
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/tag/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private TagService IIIlllIl;
}
