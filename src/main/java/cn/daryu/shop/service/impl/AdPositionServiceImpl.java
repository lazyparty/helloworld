// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import net.shopxx.dao.AdPositionDao;
import net.shopxx.entity.AdPosition;
import net.shopxx.service.AdPositionService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class AdPositionServiceImpl extends BaseServiceImpl
    implements AdPositionService
{

    public AdPositionServiceImpl()
    {
    }

    public void setBaseDao(AdPositionDao adPositionDao)
    {
        super.setBaseDao(adPositionDao);
    }

    public AdPosition find(Long id, String cacheRegion)
    {
        return (AdPosition)IIIllIlI.find(id);
    }

    public void save(AdPosition adPosition)
    {
        super.save(adPosition);
    }

    public AdPosition update(AdPosition adPosition)
    {
        return (AdPosition)super.update(adPosition);
    }

    public transient AdPosition update(AdPosition adPosition, String ignoreProperties[])
    {
        return (AdPosition)super.update(adPosition, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(AdPosition adPosition)
    {
        super.delete(adPosition);
    }

    public volatile void save(Object obj)
    {
        save((AdPosition)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((AdPosition)obj);
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
        return update((AdPosition)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((AdPosition)obj);
    }

    private AdPositionDao IIIllIlI;
}
