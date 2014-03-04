// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import java.util.List;
import net.shopxx.dao.ProductCategoryDao;
import net.shopxx.entity.ProductCategory;
import net.shopxx.service.ProductCategoryService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class ProductCategoryServiceImpl extends BaseServiceImpl
    implements ProductCategoryService
{

    public ProductCategoryServiceImpl()
    {
    }

    public void setBaseDao(ProductCategoryDao productCategoryDao)
    {
        super.setBaseDao(productCategoryDao);
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

    public List findParents(ProductCategory productCategory)
    {
        return IIIllIlI.findParents(productCategory, null);
    }

    public List findParents(ProductCategory productCategory, Integer count)
    {
        return IIIllIlI.findParents(productCategory, count);
    }

    public List findParents(ProductCategory productCategory, Integer count, String cacheRegion)
    {
        return IIIllIlI.findParents(productCategory, count);
    }

    public List findTree()
    {
        return IIIllIlI.findChildren(null, null);
    }

    public List findChildren(ProductCategory productCategory)
    {
        return IIIllIlI.findChildren(productCategory, null);
    }

    public List findChildren(ProductCategory productCategory, Integer count)
    {
        return IIIllIlI.findChildren(productCategory, count);
    }

    public List findChildren(ProductCategory productCategory, Integer count, String cacheRegion)
    {
        return IIIllIlI.findChildren(productCategory, count);
    }

    public void save(ProductCategory productCategory)
    {
        super.save(productCategory);
    }

    public ProductCategory update(ProductCategory productCategory)
    {
        return (ProductCategory)super.update(productCategory);
    }

    public transient ProductCategory update(ProductCategory productCategory, String ignoreProperties[])
    {
        return (ProductCategory)super.update(productCategory, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(ProductCategory productCategory)
    {
        super.delete(productCategory);
    }

    public volatile void save(Object obj)
    {
        save((ProductCategory)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((ProductCategory)obj);
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
        return update((ProductCategory)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((ProductCategory)obj);
    }

    private ProductCategoryDao IIIllIlI;
}
