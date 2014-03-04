// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.plugin.file;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletContext;
import net.shopxx.FileInfo;
import net.shopxx.Setting;
import net.shopxx.plugin.StoragePlugin;
import net.shopxx.util.SettingUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.web.context.ServletContextAware;

public class FilePlugin extends StoragePlugin
    implements ServletContextAware
{

    public FilePlugin()
    {
    }

    public void setServletContext(ServletContext servletContext)
    {
        IIIllIlI = servletContext;
    }

    public String getName()
    {
        return "\u672C\u5730\u6587\u4EF6\u5B58\u50A8";
    }

    public String getVersion()
    {
        return "1.0";
    }

    public String getAuthor()
    {
        return "SHOP++";
    }

    public String getSiteUrl()
    {
        return "http://www.shopxx.net";
    }

    public String getInstallUrl()
    {
        return null;
    }

    public String getUninstallUrl()
    {
        return null;
    }

    public String getSettingUrl()
    {
        return "file/setting.jhtml";
    }

    public void upload(String path, File file, String contentType)
    {
        File file1 = new File(IIIllIlI.getRealPath(path));
        try
        {
            FileUtils.moveFile(file, file1);
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public String getUrl(String path)
    {
        Setting setting = SettingUtils.get();
        return (new StringBuilder(String.valueOf(setting.getSiteUrl()))).append(path).toString();
    }

    public List browser(String path)
    {
        Setting setting = SettingUtils.get();
        ArrayList arraylist = new ArrayList();
        File file = new File(IIIllIlI.getRealPath(path));
        if(file.exists() && file.isDirectory())
        {
            File afile[];
            int j = (afile = file.listFiles()).length;
            for(int i = 0; i < j; i++)
            {
                File file1 = afile[i];
                FileInfo fileinfo = new FileInfo();
                fileinfo.setName(file1.getName());
                fileinfo.setUrl((new StringBuilder(String.valueOf(setting.getSiteUrl()))).append(path).append(file1.getName()).toString());
                fileinfo.setIsDirectory(Boolean.valueOf(file1.isDirectory()));
                fileinfo.setSize(Long.valueOf(file1.length()));
                fileinfo.setLastModified(new Date(file1.lastModified()));
                arraylist.add(fileinfo);
            }

        }
        return arraylist;
    }

    private ServletContext IIIllIlI;
}
