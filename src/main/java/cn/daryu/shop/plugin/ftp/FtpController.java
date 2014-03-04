// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.plugin.ftp;

import net.shopxx.controller.admin.BaseController;
import net.shopxx.entity.PluginConfig;
import net.shopxx.service.PluginConfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.plugin.ftp:
//            FtpPlugin

public class FtpController extends BaseController
{

    public FtpController()
    {
    }

    public String install(RedirectAttributes redirectAttributes)
    {
        if(!IIIlllIl.getIsInstalled())
        {
            PluginConfig pluginconfig = new PluginConfig();
            pluginconfig.setPluginId(IIIlllIl.getId());
            pluginconfig.setIsEnabled(Boolean.valueOf(false));
            IIIllllI.save(pluginconfig);
        }
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:/admin/storage_plugin/list.jhtml";
    }

    public String uninstall(RedirectAttributes redirectAttributes)
    {
        if(IIIlllIl.getIsInstalled())
        {
            PluginConfig pluginconfig = IIIlllIl.getPluginConfig();
            IIIllllI.delete(pluginconfig);
        }
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:/admin/storage_plugin/list.jhtml";
    }

    public String setting(ModelMap model)
    {
        PluginConfig pluginconfig = IIIlllIl.getPluginConfig();
        model.addAttribute("pluginConfig", pluginconfig);
        return "/net/shopxx/plugin/ftp/setting";
    }

    public String update(String host, Integer port, String username, String password, String urlPrefix, Boolean isEnabled, Integer order, 
            RedirectAttributes redirectAttributes)
    {
        PluginConfig pluginconfig = IIIlllIl.getPluginConfig();
        pluginconfig.setAttribute("host", host);
        pluginconfig.setAttribute("port", port.toString());
        pluginconfig.setAttribute("username", username);
        pluginconfig.setAttribute("password", password);
        pluginconfig.setAttribute("urlPrefix", StringUtils.removeEnd(urlPrefix, "/"));
        pluginconfig.setIsEnabled(isEnabled);
        pluginconfig.setOrder(order);
        IIIllllI.update(pluginconfig);
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:/admin/storage_plugin/list.jhtml";
    }

    private FtpPlugin IIIlllIl;
    private PluginConfigService IIIllllI;
}
