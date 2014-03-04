// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.plugin.file;

import net.shopxx.controller.admin.BaseController;
import net.shopxx.entity.PluginConfig;
import net.shopxx.service.PluginConfigService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.plugin.file:
//            FilePlugin

public class FileController extends BaseController
{

    public FileController()
    {
    }

    public String setting(ModelMap model)
    {
        PluginConfig pluginconfig = IIIlllIl.getPluginConfig();
        model.addAttribute("pluginConfig", pluginconfig);
        return "/net/shopxx/plugin/file/setting";
    }

    public String update(Integer order, RedirectAttributes redirectAttributes)
    {
        PluginConfig pluginconfig = IIIlllIl.getPluginConfig();
        pluginconfig.setIsEnabled(Boolean.valueOf(true));
        pluginconfig.setOrder(order);
        IIIllllI.update(pluginconfig);
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:/admin/storage_plugin/list.jhtml";
    }

    private FilePlugin IIIlllIl;
    private PluginConfigService IIIllllI;
}
