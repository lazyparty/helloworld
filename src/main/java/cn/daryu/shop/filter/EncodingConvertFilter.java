// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.filter;

import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class EncodingConvertFilter extends OncePerRequestFilter
{

    public EncodingConvertFilter()
    {
        IIIllIlI = "ISO-8859-1";
        IIIllIll = "UTF-8";
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    {
        if(request.getMethod().equalsIgnoreCase("GET"))
        {
            for(Iterator iterator = request.getParameterMap().values().iterator(); iterator.hasNext();)
            {
                String as[] = (String[])iterator.next();
                for(int i = 0; i < as.length; i++)
                    try
                    {
                        as[i] = new String(as[i].getBytes(IIIllIlI), IIIllIll);
                    }
                    catch(UnsupportedEncodingException unsupportedencodingexception)
                    {
                        unsupportedencodingexception.printStackTrace();
                    }

            }

        }
        filterChain.doFilter(request, response);
    }

    public String getFromEncoding()
    {
        return IIIllIlI;
    }

    public void setFromEncoding(String fromEncoding)
    {
        IIIllIlI = fromEncoding;
    }

    public String getToEncoding()
    {
        return IIIllIll;
    }

    public void setToEncoding(String toEncoding)
    {
        IIIllIll = toEncoding;
    }

    private String IIIllIlI;
    private String IIIllIll;
}
