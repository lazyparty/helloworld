// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import java.util.List;
import net.shopxx.dao.NavigationDao;
import net.shopxx.entity.Navigation;
import net.shopxx.service.NavigationService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class NavigationServiceImpl extends BaseServiceImpl
    implements NavigationService
{

    public NavigationServiceImpl()
    {
    }

    public void setBaseDao(NavigationDao navigationDao)
    {
        super.setBaseDao(navigationDao);
    }

    public List findList(net.shopxx.entity.Navigation.Position position)
    {
        return IIIllIlI.findList(position);
    }

    public List findList(Integer count, List filters, List orders, String cacheRegion)
    {
        return IIIllIlI.findList(null, count, filters, orders);
    }

    public void save(Navigation navigation)
    {
        super.save(navigation);
    }

    public Navigation update(Navigation navigation)
    {
        return (Navigation)super.update(navigation);
    }

    public transient Navigation update(Navigation navigation, String ignoreProperties[])
    {
        return (Navigation)super.update(navigation, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(Navigation navigation)
    {
        super.delete(navigation);
    }

    public volatile void save(Object obj)
    {
        save((Navigation)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((Navigation)obj);
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
        return update((Navigation)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((Navigation)obj);
    }

    private NavigationDao IIIllIlI;
}
