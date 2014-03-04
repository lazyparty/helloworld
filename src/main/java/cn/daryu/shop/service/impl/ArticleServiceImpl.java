// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import java.util.*;
import net.sf.ehcache.*;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.ArticleDao;
import net.shopxx.entity.Article;
import net.shopxx.entity.ArticleCategory;
import net.shopxx.service.ArticleService;
import net.shopxx.service.StaticService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.util.Assert;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class ArticleServiceImpl extends BaseServiceImpl
    implements ArticleService, DisposableBean
{

    public ArticleServiceImpl()
    {
        IIIllIlI = System.currentTimeMillis();
    }

    public void setBaseDao(ArticleDao articleDao)
    {
        super.setBaseDao(articleDao);
    }

    public List findList(ArticleCategory articleCategory, List tags, Integer count, List filters, List orders)
    {
        return IIIlllII.findList(articleCategory, tags, count, filters, orders);
    }

    public List findList(ArticleCategory articleCategory, List tags, Integer count, List filters, List orders, String cacheRegion)
    {
        return IIIlllII.findList(articleCategory, tags, count, filters, orders);
    }

    public List findList(ArticleCategory articleCategory, Date beginDate, Date endDate, Integer first, Integer count)
    {
        return IIIlllII.findList(articleCategory, beginDate, endDate, first, count);
    }

    public Page findPage(ArticleCategory articleCategory, List tags, Pageable pageable)
    {
        return IIIlllII.findPage(articleCategory, tags, pageable);
    }

    public long viewHits(Long id)
    {
        Ehcache ehcache = IIIllIll.getEhcache("articleHits");
        Element element = ehcache.get(id);
        Long long1;
        if(element != null)
        {
            long1 = (Long)element.getObjectValue();
        } else
        {
            Article article = (Article)IIIlllII.find(id);
            if(article == null)
                return 0L;
            long1 = article.getHits();
        }
        long1 = Long.valueOf(long1.longValue() + 1L);
        ehcache.put(new Element(id, long1));
        long l = System.currentTimeMillis();
        if(l > IIIllIlI + 0x927c0L)
        {
            IIIllIlI = l;
            IIIllIlI();
            ehcache.removeAll();
        }
        return long1.longValue();
    }

    public void destroy()
    {
        IIIllIlI();
    }

    private void IIIllIlI()
    {
        Ehcache ehcache = IIIllIll.getEhcache("articleHits");
        List list = ehcache.getKeys();
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            Long long1 = (Long)iterator.next();
            Article article = (Article)IIIlllII.find(long1);
            if(article != null)
            {
                Element element = ehcache.get(long1);
                long l = ((Long)element.getObjectValue()).longValue();
                article.setHits(Long.valueOf(l));
                IIIlllII.merge(article);
            }
        }

    }

    public void save(Article article)
    {
        Assert.notNull(article);
        super.save(article);
        IIIlllII.flush();
        IIIlllIl.build(article);
    }

    public Article update(Article article)
    {
        Assert.notNull(article);
        Article article1 = (Article)super.update(article);
        IIIlllII.flush();
        IIIlllIl.build(article1);
        return article1;
    }

    public transient Article update(Article article, String ignoreProperties[])
    {
        return (Article)super.update(article, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(Article article)
    {
        if(article != null)
            IIIlllIl.delete(article);
        super.delete(article);
    }

    public volatile void save(Object obj)
    {
        save((Article)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((Article)obj);
    }

    public volatile transient void delete(Serializable aserializable[])
    {
        delete((Long[])aserializable);
    }

    public volatile void delete(Serializable serializable)
    {
        delete((Long)serializable);
    }

    public volatile transient Object update(Object obj, String as[])
    {
        return update((Article)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((Article)obj);
    }

    private long IIIllIlI;
    private CacheManager IIIllIll;
    private ArticleDao IIIlllII;
    private StaticService IIIlllIl;
}
