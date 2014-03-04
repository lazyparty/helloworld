// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import net.shopxx.dao.RoleDao;
import net.shopxx.entity.Role;
import net.shopxx.service.RoleService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class RoleServiceImpl extends BaseServiceImpl
    implements RoleService
{

    public RoleServiceImpl()
    {
    }

    public void setBaseDao(RoleDao roleDao)
    {
        super.setBaseDao(roleDao);
    }

    public void save(Role role)
    {
        super.save(role);
    }

    public Role update(Role role)
    {
        return (Role)super.update(role);
    }

    public transient Role update(Role role, String ignoreProperties[])
    {
        return (Role)super.update(role, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(Role role)
    {
        super.delete(role);
    }

    public volatile void save(Object obj)
    {
        save((Role)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((Role)obj);
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
        return update((Role)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((Role)obj);
    }
}
