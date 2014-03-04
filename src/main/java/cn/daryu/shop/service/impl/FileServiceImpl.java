// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.File;
import java.util.*;
import javax.servlet.ServletContext;
import net.shopxx.Setting;
import net.shopxx.plugin.StoragePlugin;
import net.shopxx.service.FileService;
import net.shopxx.service.PluginService;
import net.shopxx.util.FreemarkerUtils;
import net.shopxx.util.SettingUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

public class FileServiceImpl
    implements FileService, ServletContextAware
{

    public FileServiceImpl()
    {
    }

    public void setServletContext(ServletContext servletContext)
    {
        IIIllIlI = servletContext;
    }

    private void IIIllIlI(StoragePlugin storageplugin, String s, File file, String s1)
    {
        IIIllIll.execute(new _cls1(file, storageplugin, s, s1));
    }

    public boolean isValid(net.shopxx.FileInfo.FileType fileType, MultipartFile multipartFile)
    {
        if(multipartFile == null)
            return false;
        Setting setting = SettingUtils.get();
        if(setting.getUploadMaxSize() != null && setting.getUploadMaxSize().intValue() != 0 && multipartFile.getSize() > (long)setting.getUploadMaxSize().intValue() * 1024L * 1024L)
            return false;
        String as[];
        if(fileType == net.shopxx.FileInfo.FileType.flash)
            as = setting.getUploadFlashExtensions();
        else
        if(fileType == net.shopxx.FileInfo.FileType.media)
            as = setting.getUploadMediaExtensions();
        else
        if(fileType == net.shopxx.FileInfo.FileType.file)
            as = setting.getUploadFileExtensions();
        else
            as = setting.getUploadImageExtensions();
        if(ArrayUtils.isNotEmpty(as))
            return FilenameUtils.isExtension(multipartFile.getOriginalFilename(), as);
        else
            return false;
    }

    public String upload(net.shopxx.FileInfo.FileType fileType, MultipartFile multipartFile, boolean async)
    {
        String s;
        if(multipartFile == null)
            return null;
        Setting setting = SettingUtils.get();
        if(fileType == net.shopxx.FileInfo.FileType.flash)
            s = setting.getFlashUploadPath();
        else
        if(fileType == net.shopxx.FileInfo.FileType.media)
            s = setting.getMediaUploadPath();
        else
        if(fileType == net.shopxx.FileInfo.FileType.file)
            s = setting.getFileUploadPath();
        else
            s = setting.getImageUploadPath();
        String s2;
        StoragePlugin storageplugin;
        File file;
        HashMap hashmap = new HashMap();
        hashmap.put("uuid", UUID.randomUUID().toString());
        String s1 = FreemarkerUtils.process(s, hashmap);
        s2 = (new StringBuilder(String.valueOf(s1))).append(UUID.randomUUID()).append(".").append(FilenameUtils.getExtension(multipartFile.getOriginalFilename())).toString();
        Iterator iterator = IIIlllII.getStoragePlugins(true).iterator();
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_326;
        storageplugin = (StoragePlugin)iterator.next();
        file = new File((new StringBuilder(String.valueOf(System.getProperty("java.io.tmpdir")))).append("/upload_").append(UUID.randomUUID()).append(".tmp").toString());
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        multipartFile.transferTo(file);
        if(async)
        {
            IIIllIlI(storageplugin, s2, file, multipartFile.getContentType());
            break MISSING_BLOCK_LABEL_311;
        }
        storageplugin.upload(s2, file, multipartFile.getContentType());
        break MISSING_BLOCK_LABEL_305;
        Exception exception1;
        exception1;
        FileUtils.deleteQuietly(file);
        throw exception1;
        FileUtils.deleteQuietly(file);
        return storageplugin.getUrl(s2);
        Exception exception;
        exception;
        exception.printStackTrace();
        return null;
    }

    public String upload(net.shopxx.FileInfo.FileType fileType, MultipartFile multipartFile)
    {
        return upload(fileType, multipartFile, false);
    }

    public String uploadLocal(net.shopxx.FileInfo.FileType fileType, MultipartFile multipartFile)
    {
        if(multipartFile == null)
            return null;
        Setting setting = SettingUtils.get();
        String s;
        if(fileType == net.shopxx.FileInfo.FileType.flash)
            s = setting.getFlashUploadPath();
        else
        if(fileType == net.shopxx.FileInfo.FileType.media)
            s = setting.getMediaUploadPath();
        else
        if(fileType == net.shopxx.FileInfo.FileType.file)
            s = setting.getFileUploadPath();
        else
            s = setting.getImageUploadPath();
        try
        {
            HashMap hashmap = new HashMap();
            hashmap.put("uuid", UUID.randomUUID().toString());
            String s1 = FreemarkerUtils.process(s, hashmap);
            String s2 = (new StringBuilder(String.valueOf(s1))).append(UUID.randomUUID()).append(".").append(FilenameUtils.getExtension(multipartFile.getOriginalFilename())).toString();
            File file = new File(IIIllIlI.getRealPath(s2));
            if(!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            multipartFile.transferTo(file);
            return s2;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    public List browser(String path, net.shopxx.FileInfo.FileType fileType, net.shopxx.FileInfo.OrderType orderType)
    {
        if(path != null)
        {
            if(!path.startsWith("/"))
                path = (new StringBuilder("/")).append(path).toString();
            if(!path.endsWith("/"))
                path = (new StringBuilder(String.valueOf(path))).append("/").toString();
        } else
        {
            path = "/";
        }
        Setting setting = SettingUtils.get();
        String s;
        if(fileType == net.shopxx.FileInfo.FileType.flash)
            s = setting.getFlashUploadPath();
        else
        if(fileType == net.shopxx.FileInfo.FileType.media)
            s = setting.getMediaUploadPath();
        else
        if(fileType == net.shopxx.FileInfo.FileType.file)
            s = setting.getFileUploadPath();
        else
            s = setting.getImageUploadPath();
        String s1 = StringUtils.substringBefore(s, "${");
        s1 = (new StringBuilder(String.valueOf(StringUtils.substringBeforeLast(s1, "/")))).append(path).toString();
        Object obj = new ArrayList();
        if(s1.indexOf("..") >= 0)
            return ((List) (obj));
        Iterator iterator = IIIlllII.getStoragePlugins(true).iterator();
        if(iterator.hasNext())
        {
            StoragePlugin storageplugin = (StoragePlugin)iterator.next();
            obj = storageplugin.browser(s1);
        }
        if(orderType == net.shopxx.FileInfo.OrderType.size)
            Collections.sort(((List) (obj)), new SizeComparator(null));
        else
        if(orderType == net.shopxx.FileInfo.OrderType.type)
            Collections.sort(((List) (obj)), new TypeComparator(null));
        else
            Collections.sort(((List) (obj)), new NameComparator(null));
        return ((List) (obj));
    }

    private ServletContext IIIllIlI;
    private TaskExecutor IIIllIll;
    private PluginService IIIlllII;

    private class _cls1
        implements Runnable
    {

        public void run()
        {
            IIIlllII.upload(IIIlllIl, IIIllIll, IIIllllI);
            break MISSING_BLOCK_LABEL_33;
            Exception exception;
            exception;
            FileUtils.deleteQuietly(IIIllIll);
            throw exception;
            FileUtils.deleteQuietly(IIIllIll);
            return;
        }

        final FileServiceImpl IIIllIlI;
        private final File IIIllIll;
        private final StoragePlugin IIIlllII;
        private final String IIIlllIl;
        private final String IIIllllI;

        _cls1(File file, StoragePlugin storageplugin, String s, String s1)
        {
            IIIllIlI = FileServiceImpl.this;
            IIIllIll = file;
            IIIlllII = storageplugin;
            IIIlllIl = s;
            IIIllllI = s1;
            super();
        }
    }


    private class SizeComparator
        implements Comparator
    {

        public int compare(FileInfo fileInfos1, FileInfo fileInfos2)
        {
            return (new CompareToBuilder()).append(!fileInfos1.getIsDirectory().booleanValue(), !fileInfos2.getIsDirectory().booleanValue()).append(fileInfos1.getSize(), fileInfos2.getSize()).toComparison();
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((FileInfo)obj, (FileInfo)obj1);
        }

        final FileServiceImpl IIIllIlI;

        private SizeComparator()
        {
            IIIllIlI = FileServiceImpl.this;
            super();
        }

        SizeComparator(SizeComparator sizecomparator)
        {
            this();
        }
    }


    private class TypeComparator
        implements Comparator
    {

        public int compare(FileInfo fileInfos1, FileInfo fileInfos2)
        {
            return (new CompareToBuilder()).append(!fileInfos1.getIsDirectory().booleanValue(), !fileInfos2.getIsDirectory().booleanValue()).append(FilenameUtils.getExtension(fileInfos1.getName()), FilenameUtils.getExtension(fileInfos2.getName())).toComparison();
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((FileInfo)obj, (FileInfo)obj1);
        }

        final FileServiceImpl IIIllIlI;

        private TypeComparator()
        {
            IIIllIlI = FileServiceImpl.this;
            super();
        }

        TypeComparator(TypeComparator typecomparator)
        {
            this();
        }
    }


    private class NameComparator
        implements Comparator
    {

        public int compare(FileInfo fileInfos1, FileInfo fileInfos2)
        {
            return (new CompareToBuilder()).append(!fileInfos1.getIsDirectory().booleanValue(), !fileInfos2.getIsDirectory().booleanValue()).append(fileInfos1.getName(), fileInfos2.getName()).toComparison();
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((FileInfo)obj, (FileInfo)obj1);
        }

        final FileServiceImpl IIIllIlI;

        private NameComparator()
        {
            IIIllIlI = FileServiceImpl.this;
            super();
        }

        NameComparator(NameComparator namecomparator)
        {
            this();
        }
    }

}
