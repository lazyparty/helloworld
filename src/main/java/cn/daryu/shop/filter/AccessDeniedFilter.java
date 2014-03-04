// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

public class AccessDeniedFilter
    implements Filter
{

    public AccessDeniedFilter()
    {
    }

    public void init(FilterConfig filterconfig)
    {
    }

    public void destroy()
    {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
    {
        HttpServletResponse httpservletresponse = (HttpServletResponse)servletResponse;
        httpservletresponse.sendError(403, "Access denied!");
    }

    private static final String IIIllIlI = "Access denied!";
}
