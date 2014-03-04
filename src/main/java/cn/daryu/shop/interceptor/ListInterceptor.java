// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.shopxx.util.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ListInterceptor extends HandlerInterceptorAdapter
{

    public ListInterceptor()
    {
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
    {
        if(modelAndView != null && modelAndView.isReference())
        {
            String s = modelAndView.getViewName();
            if(StringUtils.startsWith(s, "redirect:"))
            {
                String s1 = CookieUtils.getCookie(request, "listQuery");
                if(StringUtils.isNotEmpty(s1))
                {
                    if(StringUtils.startsWith(s1, "?"))
                        s1 = s1.substring(1);
                    if(StringUtils.contains(s, "?"))
                        modelAndView.setViewName((new StringBuilder(String.valueOf(s))).append("&").append(s1).toString());
                    else
                        modelAndView.setViewName((new StringBuilder(String.valueOf(s))).append("?").append(s1).toString());
                    CookieUtils.removeCookie(request, response, "listQuery");
                }
            }
        }
    }

    private static final String IIIllIlI = "redirect:";
    private static final String IIIllIll = "listQuery";
}
