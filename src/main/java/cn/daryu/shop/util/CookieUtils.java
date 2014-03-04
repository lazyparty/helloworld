// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.http.*;
import net.shopxx.Setting;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

// Referenced classes of package net.shopxx.util:
//            SettingUtils

public final class CookieUtils
{

    private CookieUtils()
    {
    }

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge, String path, String domain, Boolean secure)
    {
        Assert.notNull(request);
        Assert.notNull(response);
        Assert.hasText(name);
        try
        {
            name = URLEncoder.encode(name, "UTF-8");
            value = URLEncoder.encode(value, "UTF-8");
            Cookie cookie = new Cookie(name, value);
            if(maxAge != null)
                cookie.setMaxAge(maxAge.intValue());
            if(StringUtils.isNotEmpty(path))
                cookie.setPath(path);
            if(StringUtils.isNotEmpty(domain))
                cookie.setDomain(domain);
            if(secure != null)
                cookie.setSecure(secure.booleanValue());
            response.addCookie(cookie);
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            unsupportedencodingexception.printStackTrace();
        }
    }

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge)
    {
        Setting setting = SettingUtils.get();
        addCookie(request, response, name, value, maxAge, setting.getCookiePath(), setting.getCookieDomain(), null);
    }

    public static void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value)
    {
        Setting setting = SettingUtils.get();
        addCookie(request, response, name, value, null, setting.getCookiePath(), setting.getCookieDomain(), null);
    }

    public static String getCookie(HttpServletRequest request, String name)
    {
        Cookie acookie[];
        Assert.notNull(request);
        Assert.hasText(name);
        acookie = request.getCookies();
        if(acookie == null)
            break MISSING_BLOCK_LABEL_84;
        int i;
        int j;
        Cookie acookie1[];
        name = URLEncoder.encode(name, "UTF-8");
        j = (acookie1 = acookie).length;
        i = 0;
          goto _L1
_L3:
        Cookie cookie = acookie1[i];
        if(name.equals(cookie.getName()))
            return URLDecoder.decode(cookie.getValue(), "UTF-8");
        i++;
_L1:
        if(i < j) goto _L3; else goto _L2
_L2:
        break MISSING_BLOCK_LABEL_84;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        unsupportedencodingexception.printStackTrace();
        return null;
    }

    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name, String path, String domain)
    {
        Assert.notNull(request);
        Assert.notNull(response);
        Assert.hasText(name);
        try
        {
            name = URLEncoder.encode(name, "UTF-8");
            Cookie cookie = new Cookie(name, null);
            cookie.setMaxAge(0);
            if(StringUtils.isNotEmpty(path))
                cookie.setPath(path);
            if(StringUtils.isNotEmpty(domain))
                cookie.setDomain(domain);
            response.addCookie(cookie);
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            unsupportedencodingexception.printStackTrace();
        }
    }

    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name)
    {
        Setting setting = SettingUtils.get();
        removeCookie(request, response, name, setting.getCookiePath(), setting.getCookieDomain());
    }
}
