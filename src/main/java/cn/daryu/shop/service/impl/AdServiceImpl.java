// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import net.shopxx.dao.AdDao;
import net.shopxx.entity.Ad;
import net.shopxx.service.AdService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class AdServiceImpl extends BaseServiceImpl
    implements AdService
{

    public AdServiceImpl()
    {
    }

    public void setBaseDao(AdDao adDao)
    {
        super.setBaseDao(adDao);
    }

    public void save(Ad ad)
    {
        super.save(ad);
    }

    public Ad update(Ad ad)
    {
        return (Ad)super.update(ad);
    }

    public transient Ad update(Ad ad, String ignoreProperties[])
    {
        return (Ad)super.update(ad, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(Ad ad)
    {
        super.delete(ad);
    }

    public volatile void save(Object obj)
    {
        save((Ad)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((Ad)obj);
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
        return update((Ad)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((Ad)obj);
    }
}
