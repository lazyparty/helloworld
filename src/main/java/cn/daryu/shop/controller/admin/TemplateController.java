// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import freemarker.template.Configuration;
import net.shopxx.service.TemplateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class TemplateController extends BaseController
{

    public TemplateController()
    {
    }

    public String edit(String id, ModelMap model)
    {
        if(StringUtils.isEmpty(id))
        {
            return "/admin/common/error";
        } else
        {
            model.addAttribute("template", IIIllllI.get(id));
            model.addAttribute("content", IIIllllI.read(id));
            return "/admin/template/edit";
        }
    }

    public String update(String id, String content, RedirectAttributes redirectAttributes)
    {
        if(StringUtils.isEmpty(id) || content == null)
        {
            return "/admin/common/error";
        } else
        {
            IIIllllI.write(id, content);
            IIIlllIl.getConfiguration().clearTemplateCache();
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(net.shopxx.Template.Type type, ModelMap model)
    {
        model.addAttribute("type", type);
        model.addAttribute("types", net.shopxx.Template.Type.values());
        model.addAttribute("templates", IIIllllI.getList(type));
        return "/admin/template/list";
    }

    private FreeMarkerConfigurer IIIlllIl;
    private TemplateService IIIllllI;
}
