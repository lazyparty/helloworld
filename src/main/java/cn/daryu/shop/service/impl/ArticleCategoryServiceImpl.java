// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import java.util.List;
import net.shopxx.dao.ArticleCategoryDao;
import net.shopxx.entity.ArticleCategory;
import net.shopxx.service.ArticleCategoryService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class ArticleCategoryServiceImpl extends BaseServiceImpl
    implements ArticleCategoryService
{

    public ArticleCategoryServiceImpl()
    {
    }

    public void setBaseDao(ArticleCategoryDao articleCategoryDao)
    {
        super.setBaseDao(articleCategoryDao);
    }

    public List findRoots()
    {
        return IIIllIlI.findRoots(null);
    }

    public List findRoots(Integer count)
    {
        return IIIllIlI.findRoots(count);
    }

    public List findRoots(Integer count, String cacheRegion)
    {
        return IIIllIlI.findRoots(count);
    }

    public List findParents(ArticleCategory articleCategory)
    {
        return IIIllIlI.findParents(articleCategory, null);
    }

    public List findParents(ArticleCategory articleCategory, Integer count)
    {
        return IIIllIlI.findParents(articleCategory, count);
    }

    public List findParents(ArticleCategory articleCategory, Integer count, String cacheRegion)
    {
        return IIIllIlI.findParents(articleCategory, count);
    }

    public List findTree()
    {
        return IIIllIlI.findChildren(null, null);
    }

    public List findChildren(ArticleCategory articleCategory)
    {
        return IIIllIlI.findChildren(articleCategory, null);
    }

    public List findChildren(ArticleCategory articleCategory, Integer count)
    {
        return IIIllIlI.findChildren(articleCategory, count);
    }

    public List findChildren(ArticleCategory articleCategory, Integer count, String cacheRegion)
    {
        return IIIllIlI.findChildren(articleCategory, count);
    }

    public void save(ArticleCategory articleCategory)
    {
        super.save(articleCategory);
    }

    public ArticleCategory update(ArticleCategory articleCategory)
    {
        return (ArticleCategory)super.update(articleCategory);
    }

    public transient ArticleCategory update(ArticleCategory articleCategory, String ignoreProperties[])
    {
        return (ArticleCategory)super.update(articleCategory, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(ArticleCategory articleCategory)
    {
        super.delete(articleCategory);
    }

    public volatile void save(Object obj)
    {
        save((ArticleCategory)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((ArticleCategory)obj);
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
        return update((ArticleCategory)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((ArticleCategory)obj);
    }

    private ArticleCategoryDao IIIllIlI;
}
