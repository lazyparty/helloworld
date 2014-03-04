// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.plugin.alipayDirect;

import java.math.BigDecimal;
import net.shopxx.controller.admin.BaseController;
import net.shopxx.entity.PluginConfig;
import net.shopxx.service.PluginConfigService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.plugin.alipayDirect:
//            AlipayDirectPlugin

public class AlipayDirectController extends BaseController
{

    public AlipayDirectController()
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
        return "redirect:/admin/payment_plugin/list.jhtml";
    }

    public String uninstall(RedirectAttributes redirectAttributes)
    {
        if(IIIlllIl.getIsInstalled())
        {
            PluginConfig pluginconfig = IIIlllIl.getPluginConfig();
            IIIllllI.delete(pluginconfig);
        }
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:/admin/payment_plugin/list.jhtml";
    }

    public String setting(ModelMap model)
    {
        PluginConfig pluginconfig = IIIlllIl.getPluginConfig();
        model.addAttribute("feeTypes", net.shopxx.plugin.PaymentPlugin.FeeType.values());
        model.addAttribute("pluginConfig", pluginconfig);
        return "/net/shopxx/plugin/alipayDirect/setting";
    }

    public String update(String paymentName, String partner, String key, net.shopxx.plugin.PaymentPlugin.FeeType feeType, BigDecimal fee, String logo, String description, 
            Boolean isEnabled, Integer order, RedirectAttributes redirectAttributes)
    {
        PluginConfig pluginconfig = IIIlllIl.getPluginConfig();
        pluginconfig.setAttribute("paymentName", paymentName);
        pluginconfig.setAttribute("partner", partner);
        pluginconfig.setAttribute("key", key);
        pluginconfig.setAttribute("feeType", feeType.toString());
        pluginconfig.setAttribute("fee", fee.toString());
        pluginconfig.setAttribute("logo", logo);
        pluginconfig.setAttribute("description", description);
        pluginconfig.setIsEnabled(isEnabled);
        pluginconfig.setOrder(order);
        IIIllllI.update(pluginconfig);
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:/admin/payment_plugin/list.jhtml";
    }

    private AlipayDirectPlugin IIIlllIl;
    private PluginConfigService IIIllllI;
}
