// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.shopxx.service.CacheService;
import net.shopxx.util.SettingUtils;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

public class CacheServiceImpl
    implements CacheService
{

    public CacheServiceImpl()
    {
    }

    public String getDiskStorePath()
    {
        return IIIllIlI.getConfiguration().getDiskStoreConfiguration().getPath();
    }

    public int getCacheSize()
    {
        int i = 0;
        String as[] = IIIllIlI.getCacheNames();
        if(as != null)
        {
            String as1[];
            int k = (as1 = as).length;
            for(int j = 0; j < k; j++)
            {
                String s = as1[j];
                Ehcache ehcache = IIIllIlI.getEhcache(s);
                if(ehcache != null)
                    i += ehcache.getSize();
            }

        }
        return i;
    }

    public void clear()
    {
        IIIllIll.clearCache();
        try
        {
            IIIlllII.getConfiguration().setSharedVariable("setting", SettingUtils.get());
        }
        catch(TemplateModelException templatemodelexception)
        {
            templatemodelexception.printStackTrace();
        }
        IIIlllII.getConfiguration().clearTemplateCache();
    }

    private CacheManager IIIllIlI;
    private ReloadableResourceBundleMessageSource IIIllIll;
    private FreeMarkerConfigurer IIIlllII;
}
