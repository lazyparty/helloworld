// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import net.shopxx.dao.SeoDao;
import net.shopxx.entity.Seo;
import net.shopxx.service.SeoService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class SeoServiceImpl extends BaseServiceImpl
    implements SeoService
{

    public SeoServiceImpl()
    {
    }

    public void setBaseDao(SeoDao seoDao)
    {
        super.setBaseDao(seoDao);
    }

    public Seo find(net.shopxx.entity.Seo.Type type)
    {
        return IIIllIlI.find(type);
    }

    public Seo find(net.shopxx.entity.Seo.Type type, String cacheRegion)
    {
        return IIIllIlI.find(type);
    }

    public void save(Seo seo)
    {
        super.save(seo);
    }

    public Seo update(Seo seo)
    {
        return (Seo)super.update(seo);
    }

    public transient Seo update(Seo seo, String ignoreProperties[])
    {
        return (Seo)super.update(seo, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(Seo seo)
    {
        super.delete(seo);
    }

    public volatile void save(Object obj)
    {
        save((Seo)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((Seo)obj);
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
        return update((Seo)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((Seo)obj);
    }

    private SeoDao IIIllIlI;
}
