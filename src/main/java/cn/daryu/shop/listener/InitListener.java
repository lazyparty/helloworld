// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.listener;

import java.io.File;
import javax.servlet.ServletContext;
import net.shopxx.service.*;
import org.springframework.context.*;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.ServletContextAware;

public class InitListener
    implements ApplicationListener, ServletContextAware
{

    public InitListener()
    {
    }

    public void setServletContext(ServletContext servletContext)
    {
        IIIllIll = servletContext;
    }

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        if(IIIllIll != null && contextRefreshedEvent.getApplicationContext().getParent() == null)
        {
            File file = new File(IIIllIll.getRealPath("/install_init.conf"));
            if(file.exists())
            {
                IIIlllIl.clear();
                IIIlllII.buildAll();
                IIIllllI.purge();
                IIIllllI.index();
                file.delete();
            } else
            {
                IIIlllII.buildIndex();
                IIIlllII.buildOther();
            }
        }
    }

    public volatile void onApplicationEvent(ApplicationEvent applicationevent)
    {
        onApplicationEvent((ContextRefreshedEvent)applicationevent);
    }

    private static final String IIIllIlI = "/install_init.conf";
    private ServletContext IIIllIll;
    private StaticService IIIlllII;
    private CacheService IIIlllIl;
    private SearchService IIIllllI;
}
