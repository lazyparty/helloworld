// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import java.util.List;
import net.shopxx.dao.FriendLinkDao;
import net.shopxx.entity.FriendLink;
import net.shopxx.service.FriendLinkService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class FriendLinkServiceImpl extends BaseServiceImpl
    implements FriendLinkService
{

    public FriendLinkServiceImpl()
    {
    }

    public void setBaseDao(FriendLinkDao friendLinkDao)
    {
        super.setBaseDao(friendLinkDao);
    }

    public List findList(net.shopxx.entity.FriendLink.Type type)
    {
        return friendLinkDao.findList(type);
    }

    public List findList(Integer count, List filters, List orders, String cacheRegion)
    {
        return friendLinkDao.findList(null, count, filters, orders);
    }

    public void save(FriendLink friendLink)
    {
        super.save(friendLink);
    }

    public FriendLink update(FriendLink friendLink)
    {
        return (FriendLink)super.update(friendLink);
    }

    public transient FriendLink update(FriendLink friendLink, String ignoreProperties[])
    {
        return (FriendLink)super.update(friendLink, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(FriendLink friendLink)
    {
        super.delete(friendLink);
    }

    public volatile void save(Object obj)
    {
        save((FriendLink)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((FriendLink)obj);
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
        return update((FriendLink)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((FriendLink)obj);
    }

    public FriendLinkDao friendLinkDao;
}
