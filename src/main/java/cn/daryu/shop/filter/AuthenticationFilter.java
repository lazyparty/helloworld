// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.filter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.shopxx.AuthenticationToken;
import net.shopxx.service.RSAService;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class AuthenticationFilter extends FormAuthenticationFilter
{

    public AuthenticationFilter()
    {
        IIIlllIl = "enPassword";
        IIIllllI = "captchaId";
        IIIlllll = "captcha";
    }

    protected org.apache.shiro.authc.AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse)
    {
        String s = getUsername(servletRequest);
        String s1 = getPassword(servletRequest);
        String s2 = IIIllIlI(servletRequest);
        String s3 = IIIllIll(servletRequest);
        boolean flag = isRememberMe(servletRequest);
        String s4 = getHost(servletRequest);
        return new AuthenticationToken(s, s1, s2, s3, flag, s4);
    }

    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse)
    {
        HttpServletRequest httpservletrequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpservletresponse = (HttpServletResponse)servletResponse;
        String s = httpservletrequest.getHeader("X-Requested-With");
        if(s != null && s.equalsIgnoreCase("XMLHttpRequest"))
        {
            httpservletresponse.addHeader("loginStatus", "accessDenied");
            httpservletresponse.sendError(403);
            return false;
        } else
        {
            return super.onAccessDenied(httpservletrequest, httpservletresponse);
        }
    }

    protected boolean onLoginSuccess(org.apache.shiro.authc.AuthenticationToken token, Subject subject, ServletRequest servletRequest, ServletResponse servletResponse)
    {
        Session session = subject.getSession();
        HashMap hashmap = new HashMap();
        Collection collection = session.getAttributeKeys();
        Object obj;
        for(Iterator iterator = collection.iterator(); iterator.hasNext(); hashmap.put(obj, session.getAttribute(obj)))
            obj = iterator.next();

        session.stop();
        session = subject.getSession();
        java.util.Map.Entry entry;
        for(Iterator iterator1 = hashmap.entrySet().iterator(); iterator1.hasNext(); session.setAttribute(entry.getKey(), entry.getValue()))
            entry = (java.util.Map.Entry)iterator1.next();

        return super.onLoginSuccess(token, subject, servletRequest, servletResponse);
    }

    protected String getPassword(ServletRequest servletRequest)
    {
        HttpServletRequest httpservletrequest = (HttpServletRequest)servletRequest;
        String s = IIlIIIII.decryptParameter(IIIlllIl, httpservletrequest);
        IIlIIIII.removePrivateKey(httpservletrequest);
        return s;
    }

    protected String IIIllIlI(ServletRequest servletrequest)
    {
        String s = WebUtils.getCleanParam(servletrequest, IIIllllI);
        if(s == null)
            s = ((HttpServletRequest)servletrequest).getSession().getId();
        return s;
    }

    protected String IIIllIll(ServletRequest servletrequest)
    {
        return WebUtils.getCleanParam(servletrequest, IIIlllll);
    }

    public String getEnPasswordParam()
    {
        return IIIlllIl;
    }

    public void setEnPasswordParam(String enPasswordParam)
    {
        IIIlllIl = enPasswordParam;
    }

    public String getCaptchaIdParam()
    {
        return IIIllllI;
    }

    public void setCaptchaIdParam(String captchaIdParam)
    {
        IIIllllI = captchaIdParam;
    }

    public String getCaptchaParam()
    {
        return IIIlllll;
    }

    public void setCaptchaParam(String captchaParam)
    {
        IIIlllll = captchaParam;
    }

    private static final String IIIllIlI = "enPassword";
    private static final String IIIllIll = "captchaId";
    private static final String IIIlllII = "captcha";
    private String IIIlllIl;
    private String IIIllllI;
    private String IIIlllll;
    private RSAService IIlIIIII;
}
