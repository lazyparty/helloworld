// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import javax.persistence.*;
import net.shopxx.dao.ShippingDao;
import net.shopxx.entity.Shipping;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class ShippingDaoImpl extends BaseDaoImpl
    implements ShippingDao
{

    public ShippingDaoImpl()
    {
    }

    public Shipping findBySn(String sn)
    {
        if(sn == null)
            return null;
        String s = "select shipping from Shipping shipping where lower(shipping.sn) = lower(:sn)";
        try
        {
            return (Shipping)IIIllIlI.createQuery(s, net/shopxx/entity/Shipping).setFlushMode(FlushModeType.COMMIT).setParameter("sn", sn).getSingleResult();
        }
        catch(NoResultException noresultexception)
        {
            return null;
        }
    }
}
