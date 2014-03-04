// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import java.util.List;
import net.shopxx.dao.BrandDao;
import net.shopxx.entity.Brand;
import net.shopxx.service.BrandService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class BrandServiceImpl extends BaseServiceImpl
    implements BrandService
{

    public BrandServiceImpl()
    {
    }

    public void setBaseDao(BrandDao brandDao)
    {
        super.setBaseDao(brandDao);
    }

    public List findList(Integer count, List filters, List orders, String cacheRegion)
    {
        return IIIllIlI.findList(null, count, filters, orders);
    }

    public void save(Brand brand)
    {
        super.save(brand);
    }

    public Brand update(Brand brand)
    {
        return (Brand)super.update(brand);
    }

    public transient Brand update(Brand brand, String ignoreProperties[])
    {
        return (Brand)super.update(brand, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(Brand brand)
    {
        super.delete(brand);
    }

    public volatile void save(Object obj)
    {
        save((Brand)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((Brand)obj);
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
        return update((Brand)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((Brand)obj);
    }

    private BrandDao IIIllIlI;
}
