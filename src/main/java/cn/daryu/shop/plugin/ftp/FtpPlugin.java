// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.plugin.ftp;

import java.io.*;
import java.util.*;
import net.shopxx.FileInfo;
import net.shopxx.entity.PluginConfig;
import net.shopxx.plugin.StoragePlugin;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.*;

public class FtpPlugin extends StoragePlugin
{

    public FtpPlugin()
    {
    }

    public String getName()
    {
        return "FTP\u5B58\u50A8";
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
        return "ftp/install.jhtml";
    }

    public String getUninstallUrl()
    {
        return "ftp/uninstall.jhtml";
    }

    public String getSettingUrl()
    {
        return "ftp/setting.jhtml";
    }

    public void upload(String path, File file, String contentType)
    {
        String s;
        Integer integer;
        String s1;
        String s2;
        FTPClient ftpclient;
        FileInputStream fileinputstream;
        PluginConfig pluginconfig = getPluginConfig();
        if(pluginconfig == null)
            break MISSING_BLOCK_LABEL_359;
        s = pluginconfig.getAttribute("host");
        integer = Integer.valueOf(pluginconfig.getAttribute("port"));
        s1 = pluginconfig.getAttribute("username");
        s2 = pluginconfig.getAttribute("password");
        ftpclient = new FTPClient();
        fileinputstream = null;
        try
        {
            fileinputstream = new FileInputStream(file);
            ftpclient.connect(s, integer.intValue());
            ftpclient.login(s1, s2);
            ftpclient.setFileTransferMode(10);
            ftpclient.setFileType(2);
            ftpclient.enterLocalPassiveMode();
            if(FTPReply.isPositiveCompletion(ftpclient.getReplyCode()))
            {
                String s3 = StringUtils.substringBeforeLast(path, "/");
                String s4 = StringUtils.substringAfterLast(path, "/");
                if(!ftpclient.changeWorkingDirectory(s3))
                {
                    String as[] = StringUtils.split(s3, "/");
                    String s5 = "/";
                    ftpclient.changeWorkingDirectory(s5);
                    String as1[];
                    int j = (as1 = as).length;
                    for(int i = 0; i < j; i++)
                    {
                        String s6 = as1[i];
                        s5 = (new StringBuilder(String.valueOf(s5))).append(s6).append("/").toString();
                        if(!ftpclient.changeWorkingDirectory(s5))
                        {
                            ftpclient.makeDirectory(s6);
                            ftpclient.changeWorkingDirectory(s5);
                        }
                    }

                }
                ftpclient.storeFile(s4, fileinputstream);
                ftpclient.logout();
            }
            break MISSING_BLOCK_LABEL_336;
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        IOUtils.closeQuietly(fileinputstream);
        if(ftpclient.isConnected())
            try
            {
                ftpclient.disconnect();
            }
            catch(IOException ioexception1) { }
        break MISSING_BLOCK_LABEL_359;
        Exception exception;
        exception;
        IOUtils.closeQuietly(fileinputstream);
        if(ftpclient.isConnected())
            try
            {
                ftpclient.disconnect();
            }
            catch(IOException ioexception2) { }
        throw exception;
        IOUtils.closeQuietly(fileinputstream);
        if(ftpclient.isConnected())
            try
            {
                ftpclient.disconnect();
            }
            catch(IOException ioexception3) { }
    }

    public String getUrl(String path)
    {
        PluginConfig pluginconfig = getPluginConfig();
        if(pluginconfig != null)
        {
            String s = pluginconfig.getAttribute("urlPrefix");
            return (new StringBuilder(String.valueOf(s))).append(path).toString();
        } else
        {
            return null;
        }
    }

    public List browser(String path)
    {
        ArrayList arraylist;
        String s;
        Integer integer;
        String s1;
        String s2;
        String s3;
        FTPClient ftpclient;
        arraylist = new ArrayList();
        PluginConfig pluginconfig = getPluginConfig();
        if(pluginconfig == null)
            break MISSING_BLOCK_LABEL_336;
        s = pluginconfig.getAttribute("host");
        integer = Integer.valueOf(pluginconfig.getAttribute("port"));
        s1 = pluginconfig.getAttribute("username");
        s2 = pluginconfig.getAttribute("password");
        s3 = pluginconfig.getAttribute("urlPrefix");
        ftpclient = new FTPClient();
        try
        {
            ftpclient.connect(s, integer.intValue());
            ftpclient.login(s1, s2);
            ftpclient.setFileTransferMode(10);
            ftpclient.setFileType(2);
            ftpclient.enterLocalPassiveMode();
            if(FTPReply.isPositiveCompletion(ftpclient.getReplyCode()) && ftpclient.changeWorkingDirectory(path))
            {
                FTPFile aftpfile[];
                int j = (aftpfile = ftpclient.listFiles()).length;
                for(int i = 0; i < j; i++)
                {
                    FTPFile ftpfile = aftpfile[i];
                    FileInfo fileinfo = new FileInfo();
                    fileinfo.setName(ftpfile.getName());
                    fileinfo.setUrl((new StringBuilder(String.valueOf(s3))).append(path).append(ftpfile.getName()).toString());
                    fileinfo.setIsDirectory(Boolean.valueOf(ftpfile.isDirectory()));
                    fileinfo.setSize(Long.valueOf(ftpfile.getSize()));
                    fileinfo.setLastModified(ftpfile.getTimestamp().getTime());
                    arraylist.add(fileinfo);
                }

            }
            break MISSING_BLOCK_LABEL_318;
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        if(ftpclient.isConnected())
            try
            {
                ftpclient.disconnect();
            }
            catch(IOException ioexception1) { }
        break MISSING_BLOCK_LABEL_336;
        Exception exception;
        exception;
        if(ftpclient.isConnected())
            try
            {
                ftpclient.disconnect();
            }
            catch(IOException ioexception2) { }
        throw exception;
        if(ftpclient.isConnected())
            try
            {
                ftpclient.disconnect();
            }
            catch(IOException ioexception3) { }
        return arraylist;
    }
}
