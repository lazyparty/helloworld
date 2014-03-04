// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.util;

import freemarker.core.Environment;
import freemarker.template.*;
import freemarker.template.utility.DeepUnwrap;
import java.io.*;
import java.util.*;
import net.shopxx.CommonAttributes;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

// Referenced classes of package net.shopxx.util:
//            SpringUtils

public final class FreemarkerUtils
{

    private FreemarkerUtils()
    {
    }

    public static String process(String template, Map model)
    {
        Configuration configuration = null;
        org.springframework.context.ApplicationContext applicationcontext = SpringUtils.getApplicationContext();
        if(applicationcontext != null)
        {
            FreeMarkerConfigurer freemarkerconfigurer = (FreeMarkerConfigurer)SpringUtils.getBean("freeMarkerConfigurer", org/springframework/web/servlet/view/freemarker/FreeMarkerConfigurer);
            if(freemarkerconfigurer != null)
                configuration = freemarkerconfigurer.getConfiguration();
        }
        return process(template, model, configuration);
    }

    public static String process(String template, Map model, Configuration configuration)
    {
        if(template == null)
            return null;
        if(configuration == null)
            configuration = new Configuration();
        StringWriter stringwriter = new StringWriter();
        try
        {
            (new Template("template", new StringReader(template), configuration)).process(model, stringwriter);
        }
        catch(TemplateException templateexception)
        {
            templateexception.printStackTrace();
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        return stringwriter.toString();
    }

    public static Object getParameter(String name, Class type, Map params)
    {
        Assert.hasText(name);
        Assert.notNull(type);
        Assert.notNull(params);
        TemplateModel templatemodel = (TemplateModel)params.get(name);
        if(templatemodel == null)
        {
            return null;
        } else
        {
            Object obj = DeepUnwrap.unwrap(templatemodel);
            return IIIllIlI.convert(obj, type);
        }
    }

    public static TemplateModel getVariable(String name, Environment env)
    {
        Assert.hasText(name);
        Assert.notNull(env);
        return env.getVariable(name);
    }

    public static void setVariable(String name, Object value, Environment env)
    {
        Assert.hasText(name);
        Assert.notNull(env);
        if(value instanceof TemplateModel)
            env.setVariable(name, (TemplateModel)value);
        else
            env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
    }

    public static void setVariables(Map variables, Environment env)
    {
        Assert.notNull(variables);
        Assert.notNull(env);
        for(Iterator iterator = variables.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            String s = (String)entry.getKey();
            Object obj = entry.getValue();
            if(obj instanceof TemplateModel)
                env.setVariable(s, (TemplateModel)obj);
            else
                env.setVariable(s, ObjectWrapper.BEANS_WRAPPER.wrap(obj));
        }

    }

    private static final ConvertUtilsBean IIIllIlI;

    static 
    {
        IIIllIlI = new _cls1();
        DateConverter dateconverter = new DateConverter();
        dateconverter.setPatterns(CommonAttributes.DATE_PATTERNS);
        IIIllIlI.register(dateconverter, java/util/Date);
    }

    private class _cls1 extends ConvertUtilsBean
    {

        public String convert(Object value)
        {
            if(value != null)
            {
                Class class1 = value.getClass();
                if(class1.isEnum() && super.lookup(class1) == null)
                    super.register(new EnumConverter(class1), class1);
                else
                if(class1.isArray() && class1.getComponentType().isEnum())
                {
                    if(super.lookup(class1) == null)
                    {
                        ArrayConverter arrayconverter = new ArrayConverter(class1, new EnumConverter(class1.getComponentType()), 0);
                        arrayconverter.setOnlyFirstToString(false);
                        super.register(arrayconverter, class1);
                    }
                    Converter converter = super.lookup(class1);
                    return (String)converter.convert(java/lang/String, value);
                }
            }
            return super.convert(value);
        }

        public Object convert(String value, Class clazz)
        {
            if(clazz.isEnum() && super.lookup(clazz) == null)
                super.register(new EnumConverter(clazz), clazz);
            return super.convert(value, clazz);
        }

        public Object convert(String values[], Class clazz)
        {
            if(clazz.isArray() && clazz.getComponentType().isEnum() && super.lookup(clazz.getComponentType()) == null)
                super.register(new EnumConverter(clazz.getComponentType()), clazz.getComponentType());
            return super.convert(values, clazz);
        }

        public Object convert(Object value, Class targetType)
        {
            if(super.lookup(targetType) == null)
                if(targetType.isEnum())
                    super.register(new EnumConverter(targetType), targetType);
                else
                if(targetType.isArray() && targetType.getComponentType().isEnum())
                {
                    ArrayConverter arrayconverter = new ArrayConverter(targetType, new EnumConverter(targetType.getComponentType()), 0);
                    arrayconverter.setOnlyFirstToString(false);
                    super.register(arrayconverter, targetType);
                }
            return super.convert(value, targetType);
        }

        _cls1()
        {
        }
    }

}
