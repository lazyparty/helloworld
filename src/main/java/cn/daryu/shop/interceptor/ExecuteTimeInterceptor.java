// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ExecuteTimeInterceptor extends HandlerInterceptorAdapter
{

    public ExecuteTimeInterceptor()
    {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    {
        Long long1 = (Long)request.getAttribute(IIIllIll);
        if(long1 == null)
        {
            Long long2 = Long.valueOf(System.currentTimeMillis());
            request.setAttribute(IIIllIll, long2);
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
    {
        Long long1 = (Long)request.getAttribute(EXECUTE_TIME_ATTRIBUTE_NAME);
        if(long1 == null)
        {
            Long long2 = (Long)request.getAttribute(IIIllIll);
            Long long3 = Long.valueOf(System.currentTimeMillis());
            long1 = Long.valueOf(long3.longValue() - long2.longValue());
            request.setAttribute(IIIllIll, long2);
        }
        if(modelAndView != null)
        {
            String s = modelAndView.getViewName();
            if(!StringUtils.startsWith(s, "redirect:"))
                modelAndView.addObject(EXECUTE_TIME_ATTRIBUTE_NAME, long1);
        }
        if(IIIllIlI.isDebugEnabled())
            IIIllIlI.debug((new StringBuilder("[")).append(handler).append("] executeTime: ").append(long1).append("ms").toString());
    }

    private static final Logger IIIllIlI = LoggerFactory.getLogger(net/shopxx/interceptor/ExecuteTimeInterceptor);
    private static final String IIIllIll = (new StringBuilder(String.valueOf(net/shopxx/interceptor/ExecuteTimeInterceptor.getName()))).append(".START_TIME").toString();
    public static final String EXECUTE_TIME_ATTRIBUTE_NAME = (new StringBuilder(String.valueOf(net/shopxx/interceptor/ExecuteTimeInterceptor.getName()))).append(".EXECUTE_TIME").toString();
    private static final String IIIlllII = "redirect:";

}
