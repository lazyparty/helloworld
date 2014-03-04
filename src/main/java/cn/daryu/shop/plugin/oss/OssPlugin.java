// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.plugin.oss;

import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import net.shopxx.FileInfo;
import net.shopxx.entity.PluginConfig;
import net.shopxx.plugin.StoragePlugin;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class OssPlugin extends StoragePlugin
{

    public OssPlugin()
    {
    }

    public String getName()
    {
        return "\u963F\u91CC\u4E91\u5B58\u50A8";
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
        return "oss/install.jhtml";
    }

    public String getUninstallUrl()
    {
        return "oss/uninstall.jhtml";
    }

    public String getSettingUrl()
    {
        return "oss/setting.jhtml";
    }

    public void upload(String path, File file, String contentType)
    {
        String s;
        String s1;
        String s2;
        FileInputStream fileinputstream;
        PluginConfig pluginconfig = getPluginConfig();
        if(pluginconfig == null)
            break MISSING_BLOCK_LABEL_139;
        s = pluginconfig.getAttribute("accessId");
        s1 = pluginconfig.getAttribute("accessKey");
        s2 = pluginconfig.getAttribute("bucketName");
        fileinputstream = null;
        try
        {
            fileinputstream = new FileInputStream(file);
            OSSClient ossclient = new OSSClient(s, s1);
            ObjectMetadata objectmetadata = new ObjectMetadata();
            objectmetadata.setContentType(contentType);
            objectmetadata.setContentLength(file.length());
            ossclient.putObject(s2, StringUtils.removeStart(path, "/"), fileinputstream, objectmetadata);
            break MISSING_BLOCK_LABEL_134;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        IOUtils.closeQuietly(fileinputstream);
        break MISSING_BLOCK_LABEL_139;
        Exception exception1;
        exception1;
        IOUtils.closeQuietly(fileinputstream);
        throw exception1;
        IOUtils.closeQuietly(fileinputstream);
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
        ArrayList arraylist = new ArrayList();
        PluginConfig pluginconfig = getPluginConfig();
        if(pluginconfig != null)
        {
            String s = pluginconfig.getAttribute("accessId");
            String s1 = pluginconfig.getAttribute("accessKey");
            String s2 = pluginconfig.getAttribute("bucketName");
            String s3 = pluginconfig.getAttribute("urlPrefix");
            try
            {
                OSSClient ossclient = new OSSClient(s, s1);
                ListObjectsRequest listobjectsrequest = new ListObjectsRequest(s2);
                listobjectsrequest.setPrefix(StringUtils.removeStart(path, "/"));
                listobjectsrequest.setDelimiter("/");
                ObjectListing objectlisting = ossclient.listObjects(listobjectsrequest);
                FileInfo fileinfo;
                for(Iterator iterator = objectlisting.getCommonPrefixes().iterator(); iterator.hasNext(); arraylist.add(fileinfo))
                {
                    String s4 = (String)iterator.next();
                    fileinfo = new FileInfo();
                    fileinfo.setName(StringUtils.substringAfterLast(StringUtils.removeEnd(s4, "/"), "/"));
                    fileinfo.setUrl((new StringBuilder(String.valueOf(s3))).append("/").append(s4).toString());
                    fileinfo.setIsDirectory(Boolean.valueOf(true));
                    fileinfo.setSize(Long.valueOf(0L));
                }

                for(Iterator iterator1 = objectlisting.getObjectSummaries().iterator(); iterator1.hasNext();)
                {
                    OSSObjectSummary ossobjectsummary = (OSSObjectSummary)iterator1.next();
                    if(!ossobjectsummary.getKey().endsWith("/"))
                    {
                        FileInfo fileinfo1 = new FileInfo();
                        fileinfo1.setName(StringUtils.substringAfterLast(ossobjectsummary.getKey(), "/"));
                        fileinfo1.setUrl((new StringBuilder(String.valueOf(s3))).append("/").append(ossobjectsummary.getKey()).toString());
                        fileinfo1.setIsDirectory(Boolean.valueOf(false));
                        fileinfo1.setSize(Long.valueOf(ossobjectsummary.getSize()));
                        fileinfo1.setLastModified(ossobjectsummary.getLastModified());
                        arraylist.add(fileinfo1);
                    }
                }

            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }
        return arraylist;
    }
}
