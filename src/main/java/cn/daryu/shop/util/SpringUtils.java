// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.util;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.web.servlet.LocaleResolver;

public final class SpringUtils
    implements DisposableBean, ApplicationContextAware
{

    private SpringUtils()
    {
    }

    public void setApplicationContext(ApplicationContext applicationContext)
    {
        IIIllIlI = applicationContext;
    }

    public void destroy()
    {
        IIIllIlI = null;
    }

    public static ApplicationContext getApplicationContext()
    {
        return IIIllIlI;
    }

    public static Object getBean(String name)
    {
        Assert.hasText(name);
        return IIIllIlI.getBean(name);
    }

    public static Object getBean(String name, Class type)
    {
        Assert.hasText(name);
        Assert.notNull(type);
        return IIIllIlI.getBean(name, type);
    }

    public static transient String getMessage(String code, Object args[])
    {
        LocaleResolver localeresolver = (LocaleResolver)getBean("localeResolver", org/springframework/web/servlet/LocaleResolver);
        java.util.Locale locale = localeresolver.resolveLocale(null);
        return IIIllIlI.getMessage(code, args, locale);
    }

    private static ApplicationContext IIIllIlI;
}
