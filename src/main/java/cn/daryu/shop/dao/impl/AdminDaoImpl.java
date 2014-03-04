// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import javax.persistence.*;
import net.shopxx.dao.AdminDao;
import net.shopxx.entity.Admin;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class AdminDaoImpl extends BaseDaoImpl
    implements AdminDao
{

    public AdminDaoImpl()
    {
    }

    public boolean usernameExists(String username)
    {
        if(username == null)
            return false;
        String s = "select count(*) from Admin admin where lower(admin.username) = lower(:username)";
        Long long1 = (Long)IIIllIlI.createQuery(s, java/lang/Long).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
        return long1.longValue() > 0L;
    }

    public Admin findByUsername(String username)
    {
        if(username == null)
            return null;
        try
        {
            String s = "select admin from Admin admin where lower(admin.username) = lower(:username)";
            return (Admin)IIIllIlI.createQuery(s, net/shopxx/entity/Admin).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
        }
        catch(NoResultException noresultexception)
        {
            return null;
        }
    }
}
