// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import java.util.List;
import net.shopxx.dao.AreaDao;
import net.shopxx.entity.Area;
import net.shopxx.service.AreaService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class AreaServiceImpl extends BaseServiceImpl
    implements AreaService
{

    public AreaServiceImpl()
    {
    }

    public void setBaseDao(AreaDao areaDao)
    {
        super.setBaseDao(areaDao);
    }

    public List findRoots()
    {
        return IIIllIlI.findRoots(null);
    }

    public List findRoots(Integer count)
    {
        return IIIllIlI.findRoots(count);
    }

    public void save(Area area)
    {
        super.save(area);
    }

    public Area update(Area area)
    {
        return (Area)super.update(area);
    }

    public transient Area update(Area area, String ignoreProperties[])
    {
        return (Area)super.update(area, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(Area area)
    {
        super.delete(area);
    }

    public volatile void save(Object obj)
    {
        save((Area)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((Area)obj);
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
        return update((Area)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((Area)obj);
    }

    private AreaDao IIIllIlI;
}
