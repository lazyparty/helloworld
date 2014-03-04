// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.template.directive;

import freemarker.core.Environment;
import freemarker.template.*;
import java.beans.PropertyDescriptor;
import java.util.*;
import net.shopxx.Filter;
import net.shopxx.Order;
import net.shopxx.util.FreemarkerUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

public abstract class BaseDirective
    implements TemplateDirectiveModel
{

    public BaseDirective()
    {
    }

    protected boolean IIIllIlI(Environment environment, Map map)
    {
        Boolean boolean1 = (Boolean)FreemarkerUtils.getParameter("useCache", java/lang/Boolean, map);
        return boolean1 == null ? true : boolean1.booleanValue();
    }

    protected String IIIllIll(Environment environment, Map map)
    {
        String s = (String)FreemarkerUtils.getParameter("cacheRegion", java/lang/String, map);
        return s == null ? environment.getTemplate().getName() : s;
    }

    protected Long IIIllIlI(Map map)
    {
        return (Long)FreemarkerUtils.getParameter("id", java/lang/Long, map);
    }

    protected Integer IIIllIll(Map map)
    {
        return (Integer)FreemarkerUtils.getParameter("count", java/lang/Integer, map);
    }

    protected transient List IIIllIlI(Map map, Class class1, String as[])
    {
        ArrayList arraylist = new ArrayList();
        PropertyDescriptor apropertydescriptor[] = PropertyUtils.getPropertyDescriptors(class1);
        PropertyDescriptor apropertydescriptor1[];
        int j = (apropertydescriptor1 = apropertydescriptor).length;
        for(int i = 0; i < j; i++)
        {
            PropertyDescriptor propertydescriptor = apropertydescriptor1[i];
            String s = propertydescriptor.getName();
            Class class2 = propertydescriptor.getPropertyType();
            if(!ArrayUtils.contains(as, s) && map.containsKey(s))
            {
                Object obj = FreemarkerUtils.getParameter(s, class2, map);
                arraylist.add(Filter.eq(s, obj));
            }
        }

        return arraylist;
    }

    protected transient List IIIllIlI(Map map, String as[])
    {
        String s = StringUtils.trim((String)FreemarkerUtils.getParameter("orderBy", java/lang/String, map));
        ArrayList arraylist = new ArrayList();
        if(StringUtils.isNotEmpty(s))
        {
            String as1[] = s.split("\\s*,\\s*");
            String as2[];
            int j = (as2 = as1).length;
            for(int i = 0; i < j; i++)
            {
                String s1 = as2[i];
                if(!StringUtils.isNotEmpty(s1))
                    continue;
                String s2 = null;
                net.shopxx.Order.Direction direction = null;
                String as3[] = s1.split("\\s+");
                if(as3.length == 1)
                {
                    s2 = as3[0];
                } else
                {
                    if(as3.length < 2)
                        continue;
                    s2 = as3[0];
                    try
                    {
                        direction = net.shopxx.Order.Direction.valueOf(as3[1]);
                    }
                    catch(IllegalArgumentException illegalargumentexception)
                    {
                        continue;
                    }
                }
                if(!ArrayUtils.contains(as, s2))
                    arraylist.add(new Order(s2, direction));
            }

        }
        return arraylist;
    }

    protected void IIIllIlI(String s, Object obj, Environment environment, TemplateDirectiveBody templatedirectivebody)
    {
        freemarker.template.TemplateModel templatemodel = FreemarkerUtils.getVariable(s, environment);
        FreemarkerUtils.setVariable(s, obj, environment);
        templatedirectivebody.render(environment.getOut());
        FreemarkerUtils.setVariable(s, templatemodel, environment);
    }

    protected void IIIllIlI(Map map, Environment environment, TemplateDirectiveBody templatedirectivebody)
    {
        HashMap hashmap = new HashMap();
        String s;
        freemarker.template.TemplateModel templatemodel;
        for(Iterator iterator = map.keySet().iterator(); iterator.hasNext(); hashmap.put(s, templatemodel))
        {
            s = (String)iterator.next();
            templatemodel = FreemarkerUtils.getVariable(s, environment);
        }

        FreemarkerUtils.setVariables(map, environment);
        templatedirectivebody.render(environment.getOut());
        FreemarkerUtils.setVariables(hashmap, environment);
    }

    private static final String IIIllIlI = "useCache";
    private static final String IIIllIll = "cacheRegion";
    private static final String IIIlllII = "id";
    private static final String IIIlllIl = "count";
    private static final String IIIllllI = "orderBy";
    private static final String IIIlllll = "\\s*,\\s*";
    private static final String IIlIIIII = "\\s+";
}
