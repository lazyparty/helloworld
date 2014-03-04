// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletContext;
import net.shopxx.Template;
import net.shopxx.service.TemplateService;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ServletContextAware;

public class TemplateServiceImpl
    implements TemplateService, ServletContextAware
{

    public TemplateServiceImpl()
    {
    }

    public void setServletContext(ServletContext servletContext)
    {
        IIIllIlI = servletContext;
    }

    public List getAll()
    {
        try
        {
            File file = (new ClassPathResource("/shopxx.xml")).getFile();
            Document document = (new SAXReader()).read(file);
            ArrayList arraylist = new ArrayList();
            List list = document.selectNodes("/shopxx/template");
            Template template;
            for(Iterator iterator = list.iterator(); iterator.hasNext(); arraylist.add(template))
            {
                Element element = (Element)iterator.next();
                template = IIIllIlI(element);
            }

            return arraylist;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    public List getList(net.shopxx.Template.Type type)
    {
        if(type != null)
        {
            try
            {
                File file = (new ClassPathResource("/shopxx.xml")).getFile();
                Document document = (new SAXReader()).read(file);
                ArrayList arraylist = new ArrayList();
                List list = document.selectNodes((new StringBuilder("/shopxx/template[@type='")).append(type).append("']").toString());
                Template template;
                for(Iterator iterator = list.iterator(); iterator.hasNext(); arraylist.add(template))
                {
                    Element element = (Element)iterator.next();
                    template = IIIllIlI(element);
                }

                return arraylist;
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
            return null;
        } else
        {
            return getAll();
        }
    }

    public Template get(String id)
    {
        try
        {
            File file = (new ClassPathResource("/shopxx.xml")).getFile();
            Document document = (new SAXReader()).read(file);
            Element element = (Element)document.selectSingleNode((new StringBuilder("/shopxx/template[@id='")).append(id).append("']").toString());
            Template template = IIIllIlI(element);
            return template;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    public String read(String id)
    {
        Template template = get(id);
        return read(template);
    }

    public String read(Template template)
    {
        String s = IIIllIlI.getRealPath((new StringBuilder(String.valueOf(IIIllIll[0]))).append(template.getTemplatePath()).toString());
        File file = new File(s);
        String s1 = null;
        try
        {
            s1 = FileUtils.readFileToString(file, "UTF-8");
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        return s1;
    }

    public void write(String id, String content)
    {
        Template template = get(id);
        write(template, content);
    }

    public void write(Template template, String content)
    {
        String s = IIIllIlI.getRealPath((new StringBuilder(String.valueOf(IIIllIll[0]))).append(template.getTemplatePath()).toString());
        File file = new File(s);
        try
        {
            FileUtils.writeStringToFile(file, content, "UTF-8");
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    private Template IIIllIlI(Element element)
    {
        String s = element.attributeValue("id");
        String s1 = element.attributeValue("type");
        String s2 = element.attributeValue("name");
        String s3 = element.attributeValue("templatePath");
        String s4 = element.attributeValue("staticPath");
        String s5 = element.attributeValue("description");
        Template template = new Template();
        template.setId(s);
        template.setType(net.shopxx.Template.Type.valueOf(s1));
        template.setName(s2);
        template.setTemplatePath(s3);
        template.setStaticPath(s4);
        template.setDescription(s5);
        return template;
    }

    private ServletContext IIIllIlI;
    private String IIIllIll[];
}
