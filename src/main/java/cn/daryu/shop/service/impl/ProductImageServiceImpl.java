// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.File;
import java.util.*;
import javax.servlet.ServletContext;
import net.shopxx.Setting;
import net.shopxx.entity.ProductImage;
import net.shopxx.plugin.StoragePlugin;
import net.shopxx.service.ProductImageService;
import net.shopxx.util.FreemarkerUtils;
import net.shopxx.util.SettingUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

public class ProductImageServiceImpl
    implements ProductImageService, ServletContextAware
{

    public ProductImageServiceImpl()
    {
    }

    public void setServletContext(ServletContext servletContext)
    {
        IIIlllII = servletContext;
    }

    private void IIIllIlI(String s, String s1, String s2, String s3, File file, String s4)
    {
        try
        {
            IIIlllIl.execute(new _cls1(file, s, s4, s1, s2, s3));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void build(ProductImage productImage)
    {
        MultipartFile multipartfile = productImage.getFile();
        if(multipartfile != null && !multipartfile.isEmpty())
            try
            {
                Setting setting = SettingUtils.get();
                HashMap hashmap = new HashMap();
                hashmap.put("uuid", UUID.randomUUID().toString());
                String s = FreemarkerUtils.process(setting.getImageUploadPath(), hashmap);
                String s1 = UUID.randomUUID().toString();
                String s2 = (new StringBuilder(String.valueOf(s))).append(s1).append("-source.").append(FilenameUtils.getExtension(multipartfile.getOriginalFilename())).toString();
                String s3 = (new StringBuilder(String.valueOf(s))).append(s1).append("-large.").append("jpg").toString();
                String s4 = (new StringBuilder(String.valueOf(s))).append(s1).append("-medium.").append("jpg").toString();
                String s5 = (new StringBuilder(String.valueOf(s))).append(s1).append("-thumbnail.").append("jpg").toString();
                Collections.sort(IIIllllI);
                for(Iterator iterator = IIIllllI.iterator(); iterator.hasNext();)
                {
                    StoragePlugin storageplugin = (StoragePlugin)iterator.next();
                    if(storageplugin.getIsEnabled())
                    {
                        File file = new File((new StringBuilder(String.valueOf(System.getProperty("java.io.tmpdir")))).append("/upload_").append(UUID.randomUUID()).append(".tmp").toString());
                        if(!file.getParentFile().exists())
                            file.getParentFile().mkdirs();
                        multipartfile.transferTo(file);
                        IIIllIlI(s2, s3, s4, s5, file, multipartfile.getContentType());
                        productImage.setSource(storageplugin.getUrl(s2));
                        productImage.setLarge(storageplugin.getUrl(s3));
                        productImage.setMedium(storageplugin.getUrl(s4));
                        productImage.setThumbnail(storageplugin.getUrl(s5));
                    }
                }

            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
    }

    static List IIIllIlI(ProductImageServiceImpl productimageserviceimpl)
    {
        return productimageserviceimpl.IIIllllI;
    }

    static ServletContext IIIllIll(ProductImageServiceImpl productimageserviceimpl)
    {
        return productimageserviceimpl.IIIlllII;
    }

    private static final String IIIllIlI = "jpg";
    private static final String IIIllIll = "image/jpeg";
    private ServletContext IIIlllII;
    private TaskExecutor IIIlllIl;
    private List IIIllllI;

    private class _cls1
        implements Runnable
    {

        public void run()
        {
            Iterator iterator;
            Collections.sort(ProductImageServiceImpl.IIIllIlI(IIIllIlI));
            iterator = ProductImageServiceImpl.IIIllIlI(IIIllIlI).iterator();
              goto _L1
_L4:
            StoragePlugin storageplugin = (StoragePlugin)iterator.next();
            if(!storageplugin.getIsEnabled()) goto _L1; else goto _L2
_L2:
            Setting setting;
            File file;
            File file1;
            File file2;
            File file3;
            setting = SettingUtils.get();
            String s = System.getProperty("java.io.tmpdir");
            file = new File(ProductImageServiceImpl.IIIllIll(IIIllIlI).getRealPath(setting.getWatermarkImage()));
            file1 = new File((new StringBuilder(String.valueOf(s))).append("/upload_").append(UUID.randomUUID()).append(".").append("jpg").toString());
            file2 = new File((new StringBuilder(String.valueOf(s))).append("/upload_").append(UUID.randomUUID()).append(".").append("jpg").toString());
            file3 = new File((new StringBuilder(String.valueOf(s))).append("/upload_").append(UUID.randomUUID()).append(".").append("jpg").toString());
            ImageUtils.zoom(IIIllIll, file1, setting.getLargeProductImageWidth().intValue(), setting.getLargeProductImageHeight().intValue());
            ImageUtils.addWatermark(file1, file1, file, setting.getWatermarkPosition(), setting.getWatermarkAlpha().intValue());
            ImageUtils.zoom(IIIllIll, file2, setting.getMediumProductImageWidth().intValue(), setting.getMediumProductImageHeight().intValue());
            ImageUtils.addWatermark(file2, file2, file, setting.getWatermarkPosition(), setting.getWatermarkAlpha().intValue());
            ImageUtils.zoom(IIIllIll, file3, setting.getThumbnailProductImageWidth().intValue(), setting.getThumbnailProductImageHeight().intValue());
            storageplugin.upload(IIIlllII, IIIllIll, IIIlllIl);
            storageplugin.upload(IIIllllI, file1, "image/jpeg");
            storageplugin.upload(IIIlllll, file2, "image/jpeg");
            storageplugin.upload(IIlIIIII, file3, "image/jpeg");
            break MISSING_BLOCK_LABEL_409;
            Exception exception;
            exception;
            FileUtils.deleteQuietly(IIIllIll);
            FileUtils.deleteQuietly(file1);
            FileUtils.deleteQuietly(file2);
            FileUtils.deleteQuietly(file3);
            throw exception;
            FileUtils.deleteQuietly(IIIllIll);
            FileUtils.deleteQuietly(file1);
            FileUtils.deleteQuietly(file2);
            FileUtils.deleteQuietly(file3);
            break; /* Loop/switch isn't completed */
_L1:
            if(iterator.hasNext()) goto _L4; else goto _L3
_L3:
        }

        final ProductImageServiceImpl IIIllIlI;
        private final File IIIllIll;
        private final String IIIlllII;
        private final String IIIlllIl;
        private final String IIIllllI;
        private final String IIIlllll;
        private final String IIlIIIII;

        _cls1(File file, String s, String s1, String s2, String s3, String s4)
        {
            IIIllIlI = ProductImageServiceImpl.this;
            IIIllIll = file;
            IIIlllII = s;
            IIIlllIl = s1;
            IIIllllI = s2;
            IIIlllll = s3;
            IIlIIIII = s4;
            super();
        }
    }

}
