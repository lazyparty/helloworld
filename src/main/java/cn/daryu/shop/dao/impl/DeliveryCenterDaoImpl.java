// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import javax.persistence.*;
import net.shopxx.dao.DeliveryCenterDao;
import net.shopxx.entity.DeliveryCenter;
import org.springframework.util.Assert;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class DeliveryCenterDaoImpl extends BaseDaoImpl
    implements DeliveryCenterDao
{

    public DeliveryCenterDaoImpl()
    {
    }

    public DeliveryCenter findDefault()
    {
        try
        {
            String s = "select deliveryCenter from DeliveryCenter deliveryCenter where deliveryCenter.isDefault = true";
            return (DeliveryCenter)IIIllIlI.createQuery(s, net/shopxx/entity/DeliveryCenter).setFlushMode(FlushModeType.COMMIT).getSingleResult();
        }
        catch(NoResultException noresultexception)
        {
            return null;
        }
    }

    public void persist(DeliveryCenter deliveryCenter)
    {
        Assert.notNull(deliveryCenter);
        if(deliveryCenter.getIsDefault().booleanValue())
        {
            String s = "update DeliveryCenter deliveryCenter set deliveryCenter.isDefault = false where deliveryCenter.isDefault = true";
            IIIllIlI.createQuery(s).setFlushMode(FlushModeType.COMMIT).executeUpdate();
        }
        super.persist(deliveryCenter);
    }

    public DeliveryCenter merge(DeliveryCenter deliveryCenter)
    {
        Assert.notNull(deliveryCenter);
        if(deliveryCenter.getIsDefault().booleanValue())
        {
            String s = "update DeliveryCenter deliveryCenter set deliveryCenter.isDefault = false where deliveryCenter.isDefault = true and deliveryCenter != :deliveryCenter";
            IIIllIlI.createQuery(s).setFlushMode(FlushModeType.COMMIT).setParameter("deliveryCenter", deliveryCenter).executeUpdate();
        }
        return (DeliveryCenter)super.merge(deliveryCenter);
    }

    public volatile Object merge(Object obj)
    {
        return merge((DeliveryCenter)obj);
    }

    public volatile void persist(Object obj)
    {
        persist((DeliveryCenter)obj);
    }
}
