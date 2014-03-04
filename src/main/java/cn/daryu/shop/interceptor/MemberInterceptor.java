// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.interceptor;

import java.net.URLEncoder;
import javax.servlet.http.*;
import net.shopxx.Principal;
import net.shopxx.entity.Member;
import net.shopxx.service.MemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MemberInterceptor extends HandlerInterceptorAdapter
{

    public MemberInterceptor()
    {
        IIIllllI = "/login.jhtml";
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    {
        HttpSession httpsession = request.getSession();
        Principal principal = (Principal)httpsession.getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
        if(principal != null)
            return true;
        String s = request.getHeader("X-Requested-With");
        if(s != null && s.equalsIgnoreCase("XMLHttpRequest"))
        {
            response.addHeader("loginStatus", "accessDenied");
            response.sendError(403);
            return false;
        }
        if(request.getMethod().equalsIgnoreCase("GET"))
        {
            String s1 = request.getQueryString() == null ? request.getRequestURI() : (new StringBuilder(String.valueOf(request.getRequestURI()))).append("?").append(request.getQueryString()).toString();
            response.sendRedirect((new StringBuilder(String.valueOf(request.getContextPath()))).append(IIIllllI).append("?").append("redirectUrl").append("=").append(URLEncoder.encode(s1, IIIlllll)).toString());
        } else
        {
            response.sendRedirect((new StringBuilder(String.valueOf(request.getContextPath()))).append(IIIllllI).toString());
        }
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
    {
        if(modelAndView != null)
        {
            String s = modelAndView.getViewName();
            if(!StringUtils.startsWith(s, "redirect:"))
                modelAndView.addObject("member", IIlIIIII.getCurrent());
        }
    }

    public String getLoginUrl()
    {
        return IIIllllI;
    }

    public void setLoginUrl(String loginUrl)
    {
        IIIllllI = loginUrl;
    }

    private static final String IIIllIlI = "redirect:";
    private static final String IIIllIll = "redirectUrl";
    private static final String IIIlllII = "member";
    private static final String IIIlllIl = "/login.jhtml";
    private String IIIllllI;
    private String IIIlllll;
    private MemberService IIlIIIII;
}
