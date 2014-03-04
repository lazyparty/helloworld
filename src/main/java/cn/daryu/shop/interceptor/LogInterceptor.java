// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.interceptor;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.shopxx.LogConfig;
import net.shopxx.entity.Log;
import net.shopxx.service.*;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogInterceptor extends HandlerInterceptorAdapter
{

    public LogInterceptor()
    {
        IIIlllII = IIIllIlI;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
    {
        List list = IIIlllIl.getAll();
        if(list != null)
        {
            String s = request.getServletPath();
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                LogConfig logconfig = (LogConfig)iterator.next();
                if(IIIllIll.match(logconfig.getUrlPattern(), s))
                {
                    String s1 = IIIlllll.getCurrentUsername();
                    String s2 = logconfig.getOperation();
                    String s3 = s1;
                    String s4 = (String)request.getAttribute(Log.LOG_CONTENT_ATTRIBUTE_NAME);
                    String s5 = request.getRemoteAddr();
                    request.removeAttribute(Log.LOG_CONTENT_ATTRIBUTE_NAME);
                    StringBuffer stringbuffer = new StringBuffer();
                    Map map = request.getParameterMap();
                    if(map != null)
                    {
                        for(Iterator iterator1 = map.entrySet().iterator(); iterator1.hasNext();)
                        {
                            java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
                            String s6 = (String)entry.getKey();
                            if(!ArrayUtils.contains(IIIlllII, s6))
                            {
                                String as[] = (String[])entry.getValue();
                                if(as != null)
                                {
                                    String as1[];
                                    int j = (as1 = as).length;
                                    for(int i = 0; i < j; i++)
                                    {
                                        String s7 = as1[i];
                                        stringbuffer.append((new StringBuilder(String.valueOf(s6))).append(" = ").append(s7).append("\n").toString());
                                    }

                                }
                            }
                        }

                    }
                    Log log = new Log();
                    log.setOperation(s2);
                    log.setOperator(s3);
                    log.setContent(s4);
                    log.setParameter(stringbuffer.toString());
                    log.setIp(s5);
                    IIIllllI.save(log);
                    break;
                }
            }

        }
    }

    public String[] getIgnoreParameters()
    {
        return IIIlllII;
    }

    public void setIgnoreParameters(String ignoreParameters[])
    {
        IIIlllII = ignoreParameters;
    }

    private static final String IIIllIlI[] = {
        "password", "rePassword", "currentPassword"
    };
    private static AntPathMatcher IIIllIll = new AntPathMatcher();
    private String IIIlllII[];
    private LogConfigService IIIlllIl;
    private LogService IIIllllI;
    private AdminService IIIlllll;

}
