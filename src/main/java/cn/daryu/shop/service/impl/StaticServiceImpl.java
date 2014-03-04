// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import net.shopxx.Filter;
import net.shopxx.dao.ArticleDao;
import net.shopxx.dao.BrandDao;
import net.shopxx.dao.ProductDao;
import net.shopxx.dao.PromotionDao;
import net.shopxx.entity.Article;
import net.shopxx.entity.Product;
import net.shopxx.service.StaticService;
import net.shopxx.service.TemplateService;
import net.shopxx.util.FreemarkerUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.util.Assert;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

public class StaticServiceImpl
    implements StaticService, ServletContextAware
{

    public StaticServiceImpl()
    {
    }

    public void setServletContext(ServletContext servletContext)
    {
        IIIllIll = servletContext;
    }

    public int build(String templatePath, String staticPath, Map model)
    {
        FileOutputStream fileoutputstream;
        OutputStreamWriter outputstreamwriter;
        BufferedWriter bufferedwriter;
        Assert.hasText(templatePath);
        Assert.hasText(staticPath);
        fileoutputstream = null;
        outputstreamwriter = null;
        bufferedwriter = null;
        Template template = IIIlllII.getConfiguration().getTemplate(templatePath);
        File file = new File(IIIllIll.getRealPath(staticPath));
        File file1 = file.getParentFile();
        if(!file1.exists())
            file1.mkdirs();
        fileoutputstream = new FileOutputStream(file);
        outputstreamwriter = new OutputStreamWriter(fileoutputstream, "UTF-8");
        bufferedwriter = new BufferedWriter(outputstreamwriter);
        template.process(model, bufferedwriter);
        bufferedwriter.flush();
        IOUtils.closeQuietly(bufferedwriter);
        IOUtils.closeQuietly(outputstreamwriter);
        IOUtils.closeQuietly(fileoutputstream);
        return 1;
        Exception exception;
        exception;
        exception.printStackTrace();
        IOUtils.closeQuietly(bufferedwriter);
        IOUtils.closeQuietly(outputstreamwriter);
        IOUtils.closeQuietly(fileoutputstream);
        break MISSING_BLOCK_LABEL_180;
        Exception exception1;
        exception1;
        IOUtils.closeQuietly(bufferedwriter);
        IOUtils.closeQuietly(outputstreamwriter);
        IOUtils.closeQuietly(fileoutputstream);
        throw exception1;
        return 0;
    }

    public int build(String templatePath, String staticPath)
    {
        return build(templatePath, staticPath, null);
    }

    public int build(Article article)
    {
        Assert.notNull(article);
        delete(article);
        net.shopxx.Template template = IIIlllIl.get("articleContent");
        int i = 0;
        if(article.getIsPublication().booleanValue())
        {
            HashMap hashmap = new HashMap();
            hashmap.put("article", article);
            for(int j = 1; j <= article.getTotalPages(); j++)
            {
                article.setPageNumber(Integer.valueOf(j));
                i += build(template.getTemplatePath(), article.getPath(), ((Map) (hashmap)));
            }

            article.setPageNumber(null);
        }
        return i;
    }

    public int build(Product product)
    {
        Assert.notNull(product);
        delete(product);
        net.shopxx.Template template = IIIlllIl.get("productContent");
        int i = 0;
        if(product.getIsMarketable().booleanValue())
        {
            HashMap hashmap = new HashMap();
            hashmap.put("product", product);
            i += build(template.getTemplatePath(), product.getPath(), ((Map) (hashmap)));
        }
        return i;
    }

    public int buildIndex()
    {
        net.shopxx.Template template = IIIlllIl.get("index");
        return build(template.getTemplatePath(), template.getStaticPath());
    }

    public int buildSitemap()
    {
        int i;
        net.shopxx.Template template;
        net.shopxx.Template template1;
        HashMap hashmap;
        ArrayList arraylist;
        int j;
        int k;
        int l;
        int i1;
        i = 0;
        template = IIIlllIl.get("sitemapIndex");
        template1 = IIIlllIl.get("sitemap");
        hashmap = new HashMap();
        arraylist = new ArrayList();
        j = 0;
        k = 0;
        l = 0;
        i1 = IIIllIlI.intValue();
        do
        {
            hashmap.put("index", Integer.valueOf(k));
            String s = template1.getTemplatePath();
            String s1 = FreemarkerUtils.process(template1.getStaticPath(), hashmap);
            if(j == 0)
            {
                List list = IIIllllI.findList(Integer.valueOf(l), Integer.valueOf(i1), null, null);
                hashmap.put("articles", list);
                if(list.size() < i1)
                {
                    j++;
                    l = 0;
                    i1 -= list.size();
                } else
                {
                    i += build(s, s1, hashmap);
                    IIIllllI.clear();
                    IIIllllI.flush();
                    arraylist.add(s1);
                    hashmap.clear();
                    k++;
                    l += list.size();
                    i1 = IIIllIlI.intValue();
                }
                continue;
            }
            if(j == 1)
            {
                List list1 = IIIlllll.findList(Integer.valueOf(l), Integer.valueOf(i1), null, null);
                hashmap.put("products", list1);
                if(list1.size() < i1)
                {
                    j++;
                    l = 0;
                    i1 -= list1.size();
                } else
                {
                    i += build(s, s1, hashmap);
                    IIIlllll.clear();
                    IIIlllll.flush();
                    arraylist.add(s1);
                    hashmap.clear();
                    k++;
                    l += list1.size();
                    i1 = IIIllIlI.intValue();
                }
                continue;
            }
            if(j == 2)
            {
                List list2 = IIlIIIII.findList(Integer.valueOf(l), Integer.valueOf(i1), null, null);
                hashmap.put("brands", list2);
                if(list2.size() < i1)
                {
                    j++;
                    l = 0;
                    i1 -= list2.size();
                } else
                {
                    i += build(s, s1, hashmap);
                    IIlIIIII.clear();
                    IIlIIIII.flush();
                    arraylist.add(s1);
                    hashmap.clear();
                    k++;
                    l += list2.size();
                    i1 = IIIllIlI.intValue();
                }
                continue;
            }
            if(j != 3)
                continue;
            List list3 = IIlIIIIl.findList(Integer.valueOf(l), Integer.valueOf(i1), null, null);
            hashmap.put("promotions", list3);
            i += build(s, s1, hashmap);
            IIlIIIIl.clear();
            IIlIIIIl.flush();
            arraylist.add(s1);
            if(list3.size() < i1)
            {
                hashmap.put("staticPaths", arraylist);
                i += build(template.getTemplatePath(), template.getStaticPath(), hashmap);
                break;
            }
            try
            {
                hashmap.clear();
                k++;
                l += list3.size();
                i1 = IIIllIlI.intValue();
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        } while(true);
        return i;
    }

    public int buildOther()
    {
        int i = 0;
        net.shopxx.Template template = IIIlllIl.get("shopCommonJs");
        net.shopxx.Template template1 = IIIlllIl.get("adminCommonJs");
        i += build(template.getTemplatePath(), template.getStaticPath());
        i += build(template1.getTemplatePath(), template1.getStaticPath());
        return i;
    }

    public int buildAll()
    {
        int i = 0;
        for(int j = 0; (long)j < IIIllllI.count(new Filter[0]); j += 20)
        {
            List list = IIIllllI.findList(Integer.valueOf(j), Integer.valueOf(20), null, null);
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                Article article = (Article)iterator.next();
                i += build(article);
            }

            IIIllllI.clear();
        }

        for(int k = 0; (long)k < IIIlllll.count(new Filter[0]); k += 20)
        {
            List list1 = IIIlllll.findList(Integer.valueOf(k), Integer.valueOf(20), null, null);
            for(Iterator iterator1 = list1.iterator(); iterator1.hasNext();)
            {
                Product product = (Product)iterator1.next();
                i += build(product);
            }

            IIIlllll.clear();
        }

        buildIndex();
        buildSitemap();
        buildOther();
        return i;
    }

    public int delete(String staticPath)
    {
        Assert.hasText(staticPath);
        File file = new File(IIIllIll.getRealPath(staticPath));
        if(file.exists())
        {
            file.delete();
            return 1;
        } else
        {
            return 0;
        }
    }

    public int delete(Article article)
    {
        Assert.notNull(article);
        int i = 0;
        for(int j = 1; j <= article.getTotalPages() + 1000; j++)
        {
            article.setPageNumber(Integer.valueOf(j));
            int k = delete(article.getPath());
            if(k < 1)
                break;
            i += k;
        }

        article.setPageNumber(null);
        return i;
    }

    public int delete(Product product)
    {
        Assert.notNull(product);
        return delete(product.getPath());
    }

    public int deleteIndex()
    {
        net.shopxx.Template template = IIIlllIl.get("index");
        return delete(template.getStaticPath());
    }

    public int deleteOther()
    {
        int i = 0;
        net.shopxx.Template template = IIIlllIl.get("shopCommonJs");
        net.shopxx.Template template1 = IIIlllIl.get("adminCommonJs");
        i += delete(template.getStaticPath());
        i += delete(template1.getStaticPath());
        return i;
    }

    private static final Integer IIIllIlI = Integer.valueOf(40000);
    private ServletContext IIIllIll;
    private FreeMarkerConfigurer IIIlllII;
    private TemplateService IIIlllIl;
    private ArticleDao IIIllllI;
    private ProductDao IIIlllll;
    private BrandDao IIlIIIII;
    private PromotionDao IIlIIIIl;

}
