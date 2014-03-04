// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.filter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.shopxx.Setting;
import net.shopxx.util.SettingUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

public class SiteStatusFilter extends OncePerRequestFilter
{

    public SiteStatusFilter()
    {
        IIIlllIl = IIIllIlI;
        IIIllllI = "/common/site_close.jhtml";
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    {
        Setting setting = SettingUtils.get();
        if(setting.getIsSiteEnabled().booleanValue())
        {
            filterChain.doFilter(request, response);
        } else
        {
            String s = request.getServletPath();
            if(s.equals(IIIllllI))
            {
                filterChain.doFilter(request, response);
                return;
            }
            if(IIIlllIl != null)
            {
                String as[];
                int j = (as = IIIlllIl).length;
                for(int i = 0; i < j; i++)
                {
                    String s1 = as[i];
                    if(IIIlllII.match(s1, s))
                    {
                        filterChain.doFilter(request, response);
                        return;
                    }
                }

            }
            response.sendRedirect((new StringBuilder(String.valueOf(request.getContextPath()))).append(IIIllllI).toString());
        }
    }

    public String[] getIgnoreUrlPatterns()
    {
        return IIIlllIl;
    }

    public void setIgnoreUrlPatterns(String ignoreUrlPatterns[])
    {
        IIIlllIl = ignoreUrlPatterns;
    }

    public String getRedirectUrl()
    {
        return IIIllllI;
    }

    public void setRedirectUrl(String redirectUrl)
    {
        IIIllllI = redirectUrl;
    }

    private static final String IIIllIlI[] = {
        "/admin/**"
    };
    private static final String IIIllIll = "/common/site_close.jhtml";
    private static AntPathMatcher IIIlllII = new AntPathMatcher();
    private String IIIlllIl[];
    private String IIIllllI;

}
