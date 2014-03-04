// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import java.util.*;
import net.shopxx.Principal;
import net.shopxx.dao.AdminDao;
import net.shopxx.entity.Admin;
import net.shopxx.entity.Role;
import net.shopxx.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class AdminServiceImpl extends BaseServiceImpl
    implements AdminService
{

    public AdminServiceImpl()
    {
    }

    public void setBaseDao(AdminDao adminDao)
    {
        super.setBaseDao(adminDao);
    }

    public boolean usernameExists(String username)
    {
        return IIIllIlI.usernameExists(username);
    }

    public Admin findByUsername(String username)
    {
        return IIIllIlI.findByUsername(username);
    }

    public List findAuthorities(Long id)
    {
        ArrayList arraylist = new ArrayList();
        Admin admin = (Admin)IIIllIlI.find(id);
        if(admin != null)
        {
            Role role;
            for(Iterator iterator = admin.getRoles().iterator(); iterator.hasNext(); arraylist.addAll(role.getAuthorities()))
                role = (Role)iterator.next();

        }
        return arraylist;
    }

    public boolean isAuthenticated()
    {
        Subject subject = SecurityUtils.getSubject();
        if(subject != null)
            return subject.isAuthenticated();
        else
            return false;
    }

    public Admin getCurrent()
    {
        Subject subject = SecurityUtils.getSubject();
        if(subject != null)
        {
            Principal principal = (Principal)subject.getPrincipal();
            if(principal != null)
                return (Admin)IIIllIlI.find(principal.getId());
        }
        return null;
    }

    public String getCurrentUsername()
    {
        Subject subject = SecurityUtils.getSubject();
        if(subject != null)
        {
            Principal principal = (Principal)subject.getPrincipal();
            if(principal != null)
                return principal.getUsername();
        }
        return null;
    }

    public void save(Admin admin)
    {
        super.save(admin);
    }

    public Admin update(Admin admin)
    {
        return (Admin)super.update(admin);
    }

    public transient Admin update(Admin admin, String ignoreProperties[])
    {
        return (Admin)super.update(admin, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(Admin admin)
    {
        super.delete(admin);
    }

    public volatile void save(Object obj)
    {
        save((Admin)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((Admin)obj);
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
        return update((Admin)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((Admin)obj);
    }

    private AdminDao IIIllIlI;
}
