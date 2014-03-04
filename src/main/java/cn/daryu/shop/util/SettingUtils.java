// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.shopxx.CommonAttributes;
import net.shopxx.Setting;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.io.IOUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.core.io.ClassPathResource;

public final class SettingUtils
{

    private SettingUtils()
    {
    }

    public static Setting get()
    {
        Ehcache ehcache = IIIllIlI.getEhcache("setting");
        Element element = ehcache.get(Setting.CACHE_KEY);
        Setting setting;
        if(element != null)
        {
            setting = (Setting)element.getObjectValue();
        } else
        {
            setting = new Setting();
            try
            {
                java.io.File file = (new ClassPathResource("/shopxx.xml")).getFile();
                Document document = (new SAXReader()).read(file);
                List list = document.selectNodes("/shopxx/setting");
                for(Iterator iterator = list.iterator(); iterator.hasNext();)
                {
                    org.dom4j.Element element1 = (org.dom4j.Element)iterator.next();
                    String s = element1.attributeValue("name");
                    String s1 = element1.attributeValue("value");
                    try
                    {
                        IIIllIll.setProperty(setting, s, s1);
                    }
                    catch(IllegalAccessException illegalaccessexception)
                    {
                        illegalaccessexception.printStackTrace();
                    }
                    catch(InvocationTargetException invocationtargetexception)
                    {
                        invocationtargetexception.printStackTrace();
                    }
                }

            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
            ehcache.put(new Element(Setting.CACHE_KEY, setting));
        }
        return setting;
    }

    public static void set(Setting setting)
    {
        java.io.File file;
        Document document;
        FileOutputStream fileoutputstream;
        XMLWriter xmlwriter;
        file = (new ClassPathResource("/shopxx.xml")).getFile();
        document = (new SAXReader()).read(file);
        List list = document.selectNodes("/shopxx/setting");
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            org.dom4j.Element element = (org.dom4j.Element)iterator.next();
            try
            {
                String s = element.attributeValue("name");
                String s1 = IIIllIll.getProperty(setting, s);
                Attribute attribute = element.attribute("value");
                attribute.setValue(s1);
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                illegalaccessexception.printStackTrace();
            }
            catch(InvocationTargetException invocationtargetexception)
            {
                invocationtargetexception.printStackTrace();
            }
            catch(NoSuchMethodException nosuchmethodexception)
            {
                nosuchmethodexception.printStackTrace();
            }
        }

        fileoutputstream = null;
        xmlwriter = null;
        try
        {
            OutputFormat outputformat = OutputFormat.createPrettyPrint();
            outputformat.setEncoding("UTF-8");
            outputformat.setIndent(true);
            outputformat.setIndent("\t");
            outputformat.setNewlines(true);
            fileoutputstream = new FileOutputStream(file);
            xmlwriter = new XMLWriter(fileoutputstream, outputformat);
            xmlwriter.write(document);
            break MISSING_BLOCK_LABEL_263;
        }
        catch(Exception exception1)
        {
            exception1.printStackTrace();
        }
        if(xmlwriter != null)
            try
            {
                xmlwriter.close();
            }
            catch(IOException ioexception) { }
        IOUtils.closeQuietly(fileoutputstream);
        break MISSING_BLOCK_LABEL_283;
        Exception exception2;
        exception2;
        if(xmlwriter != null)
            try
            {
                xmlwriter.close();
            }
            catch(IOException ioexception1) { }
        IOUtils.closeQuietly(fileoutputstream);
        throw exception2;
        if(xmlwriter != null)
            try
            {
                xmlwriter.close();
            }
            catch(IOException ioexception2) { }
        IOUtils.closeQuietly(fileoutputstream);
        Ehcache ehcache = IIIllIlI.getEhcache("setting");
        ehcache.put(new Element(Setting.CACHE_KEY, setting));
        break MISSING_BLOCK_LABEL_319;
        Exception exception;
        exception;
        exception.printStackTrace();
    }

    private static final CacheManager IIIllIlI = CacheManager.create();
    private static final BeanUtilsBean IIIllIll;

    static 
    {
        _cls1 _lcls1 = new _cls1();
        DateConverter dateconverter = new DateConverter();
        dateconverter.setPatterns(CommonAttributes.DATE_PATTERNS);
        _lcls1.register(dateconverter, java/util/Date);
        IIIllIll = new BeanUtilsBean(_lcls1);
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
