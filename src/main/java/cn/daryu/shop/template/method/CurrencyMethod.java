// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.template.method;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import java.math.BigDecimal;
import java.util.List;
import net.shopxx.Setting;
import net.shopxx.util.SettingUtils;
import org.apache.commons.lang.StringUtils;

public class CurrencyMethod
    implements TemplateMethodModel
{

    public CurrencyMethod()
    {
    }

    public Object exec(List arguments)
    {
        if(arguments != null && !arguments.isEmpty() && arguments.get(0) != null && StringUtils.isNotEmpty(arguments.get(0).toString()))
        {
            boolean flag = false;
            boolean flag1 = false;
            if(arguments.size() == 2)
            {
                if(arguments.get(1) != null)
                    flag = Boolean.valueOf(arguments.get(1).toString()).booleanValue();
            } else
            if(arguments.size() > 2)
            {
                if(arguments.get(1) != null)
                    flag = Boolean.valueOf(arguments.get(1).toString()).booleanValue();
                if(arguments.get(2) != null)
                    flag1 = Boolean.valueOf(arguments.get(2).toString()).booleanValue();
            }
            Setting setting = SettingUtils.get();
            BigDecimal bigdecimal = new BigDecimal(arguments.get(0).toString());
            String s = setting.setScale(bigdecimal).toString();
            if(flag)
                s = (new StringBuilder(String.valueOf(setting.getCurrencySign()))).append(s).toString();
            if(flag1)
                s = (new StringBuilder(String.valueOf(s))).append(setting.getCurrencyUnit()).toString();
            return new SimpleScalar(s);
        } else
        {
            return null;
        }
    }
}
