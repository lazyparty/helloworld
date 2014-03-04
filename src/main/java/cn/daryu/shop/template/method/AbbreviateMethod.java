// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.template.method;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

public class AbbreviateMethod
    implements TemplateMethodModel
{

    public AbbreviateMethod()
    {
    }

    public Object exec(List arguments)
    {
        if(arguments != null && !arguments.isEmpty() && arguments.get(0) != null && StringUtils.isNotEmpty(arguments.get(0).toString()))
        {
            Integer integer = null;
            String s = null;
            if(arguments.size() == 2)
            {
                if(arguments.get(1) != null)
                    integer = Integer.valueOf(arguments.get(1).toString());
            } else
            if(arguments.size() > 2)
            {
                if(arguments.get(1) != null)
                    integer = Integer.valueOf(arguments.get(1).toString());
                if(arguments.get(2) != null)
                    s = arguments.get(2).toString();
            }
            return new SimpleScalar(IIIllIlI(arguments.get(0).toString(), integer, s));
        } else
        {
            return null;
        }
    }

    private String IIIllIlI(String s, Integer integer, String s1)
    {
        if(integer != null)
        {
            int i = 0;
            int j = 0;
            for(; i < s.length(); i++)
            {
                j = IIIllIlI.matcher(String.valueOf(s.charAt(i))).find() ? j + 2 : j + 1;
                if(j >= integer.intValue())
                    break;
            }

            if(i < s.length())
            {
                if(s1 != null)
                    return (new StringBuilder(String.valueOf(s.substring(0, i + 1)))).append(s1).toString();
                else
                    return s.substring(0, i + 1);
            } else
            {
                return s;
            }
        }
        if(s1 != null)
            return (new StringBuilder(String.valueOf(s))).append(s1).toString();
        else
            return s;
    }

    private static final Pattern IIIllIlI = Pattern.compile("[\\u4e00-\\u9fa5\\ufe30-\\uffa0]+$");

}
