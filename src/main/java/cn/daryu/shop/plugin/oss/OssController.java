// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.plugin.oss;

import java.math.BigDecimal;
import net.shopxx.Message;
import net.shopxx.controller.admin.BaseController;
import net.shopxx.entity.PluginConfig;
import net.shopxx.service.PluginConfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.plugin.oss:
//            OssPlugin

public class OssController extends BaseController
{

    public OssController()
    {
    }

    public String install(RedirectAttributes redirectAttributes)
    {
        String s = System.getProperty("java.specification.version");
        if(StringUtils.isNotEmpty(s))
        {
            BigDecimal bigdecimal = new BigDecimal(s);
            if(bigdecimal.compareTo(new BigDecimal("1.6")) < 0)
            {
                IIIllIlI(redirectAttributes, Message.error("admin.plugin.oss.unsupportedJavaVersion", new Object[0]));
                return "redirect:/admin/storage_plugin/list.jhtml";
            }
        }
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
        return "/net/shopxx/plugin/oss/setting";
    }

    public String update(String accessId, String accessKey, String bucketName, String urlPrefix, Boolean isEnabled, Integer order, RedirectAttributes redirectAttributes)
    {
        PluginConfig pluginconfig = IIIlllIl.getPluginConfig();
        pluginconfig.setAttribute("accessId", accessId);
        pluginconfig.setAttribute("accessKey", accessKey);
        pluginconfig.setAttribute("bucketName", bucketName);
        pluginconfig.setAttribute("urlPrefix", StringUtils.removeEnd(urlPrefix, "/"));
        pluginconfig.setIsEnabled(isEnabled);
        pluginconfig.setOrder(order);
        IIIllllI.update(pluginconfig);
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:/admin/storage_plugin/list.jhtml";
    }

    private OssPlugin IIIlllIl;
    private PluginConfigService IIIllllI;
}
