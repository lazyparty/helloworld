// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.util.*;
import net.shopxx.LogConfig;
import net.shopxx.service.LogConfigService;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

public class LogConfigServiceImpl
    implements LogConfigService
{

    public LogConfigServiceImpl()
    {
    }

    public List getAll()
    {
        try
        {
            java.io.File file = (new ClassPathResource("/shopxx.xml")).getFile();
            Document document = (new SAXReader()).read(file);
            List list = document.selectNodes("/shopxx/logConfig");
            ArrayList arraylist = new ArrayList();
            LogConfig logconfig;
            for(Iterator iterator = list.iterator(); iterator.hasNext(); arraylist.add(logconfig))
            {
                Element element = (Element)iterator.next();
                String s = element.attributeValue("operation");
                String s1 = element.attributeValue("urlPattern");
                logconfig = new LogConfig();
                logconfig.setOperation(s);
                logconfig.setUrlPattern(s1);
            }

            return arraylist;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }
}
