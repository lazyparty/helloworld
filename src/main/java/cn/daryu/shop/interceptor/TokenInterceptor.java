// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.interceptor;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.shopxx.util.CookieUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TokenInterceptor extends HandlerInterceptorAdapter
{

    public TokenInterceptor()
    {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    {
        String s = CookieUtils.getCookie(request, "token");
        if(request.getMethod().equalsIgnoreCase("POST"))
        {
            String s1 = request.getHeader("X-Requested-With");
            if(s1 != null && s1.equalsIgnoreCase("XMLHttpRequest"))
            {
                if(s != null && s.equals(request.getHeader("token")))
                    return true;
                response.addHeader("tokenStatus", "accessDenied");
            } else
            if(s != null && s.equals(request.getParameter("token")))
                return true;
            if(s == null)
            {
                s = UUID.randomUUID().toString();
                CookieUtils.addCookie(request, response, "token", s);
            }
            response.sendError(403, "Bad or missing token!");
            return false;
        }
        if(s == null)
        {
            s = UUID.randomUUID().toString();
            CookieUtils.addCookie(request, response, "token", s);
        }
        request.setAttribute("token", s);
        return true;
    }

    private static final String IIIllIlI = "token";
    private static final String IIIllIll = "token";
    private static final String IIIlllII = "token";
    private static final String IIIlllIl = "Bad or missing token!";
}
