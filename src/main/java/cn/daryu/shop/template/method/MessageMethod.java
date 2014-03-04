// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.template.method;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import java.util.List;
import net.shopxx.util.SpringUtils;
import org.apache.commons.lang.StringUtils;

public class MessageMethod
    implements TemplateMethodModel
{

    public MessageMethod()
    {
    }

    public Object exec(List arguments)
    {
        if(arguments != null && !arguments.isEmpty() && arguments.get(0) != null && StringUtils.isNotEmpty(arguments.get(0).toString()))
        {
            String s = null;
            String s1 = arguments.get(0).toString();
            if(arguments.size() > 1)
            {
                Object aobj[] = arguments.subList(1, arguments.size()).toArray();
                s = SpringUtils.getMessage(s1, aobj);
            } else
            {
                s = SpringUtils.getMessage(s1, new Object[0]);
            }
            return new SimpleScalar(s);
        } else
        {
            return null;
        }
    }
}
