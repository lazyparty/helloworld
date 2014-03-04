// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import net.shopxx.Setting;
import net.shopxx.service.CacheService;
import net.shopxx.util.SettingUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class StatisticsController extends BaseController
{

    public StatisticsController()
    {
    }

    public String view()
    {
        return "/admin/statistics/view";
    }

    public String setting()
    {
        return "/admin/statistics/setting";
    }

    public String setting(Boolean isEnabled, RedirectAttributes redirectAttributes)
    {
        Setting setting1 = SettingUtils.get();
        if(isEnabled.booleanValue() && (StringUtils.isEmpty(setting1.getCnzzSiteId()) || StringUtils.isEmpty(setting1.getCnzzPassword())))
            try
            {
                String s = (new StringBuilder("http://intf.cnzz.com/user/companion/shopxx.php?domain=")).append(setting1.getSiteUrl()).append("&key=").append(DigestUtils.md5Hex((new StringBuilder(String.valueOf(setting1.getSiteUrl()))).append("Lfg4uP0H").toString())).toString();
                URLConnection urlconnection = (new URL(s)).openConnection();
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
                String s1;
                for(s1 = null; (s1 = bufferedreader.readLine()) != null;)
                    if(s1.contains("@"))
                        break;

                if(s1 != null)
                {
                    setting1.setCnzzSiteId(StringUtils.substringBefore(s1, "@"));
                    setting1.setCnzzPassword(StringUtils.substringAfter(s1, "@"));
                }
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
            }
        setting1.setIsCnzzEnabled(isEnabled);
        SettingUtils.set(setting1);
        IIIlllIl.clear();
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:setting.jhtml";
    }

    private CacheService IIIlllIl;
}
