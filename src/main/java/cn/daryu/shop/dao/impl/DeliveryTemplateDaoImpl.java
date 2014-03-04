// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import javax.persistence.*;
import net.shopxx.dao.DeliveryTemplateDao;
import net.shopxx.entity.DeliveryTemplate;
import org.springframework.util.Assert;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class DeliveryTemplateDaoImpl extends BaseDaoImpl
    implements DeliveryTemplateDao
{

    public DeliveryTemplateDaoImpl()
    {
    }

    public DeliveryTemplate findDefault()
    {
        try
        {
            String s = "select deliveryTemplate from DeliveryTemplate deliveryTemplate where deliveryTemplate.isDefault = true";
            return (DeliveryTemplate)IIIllIlI.createQuery(s, net/shopxx/entity/DeliveryTemplate).setFlushMode(FlushModeType.COMMIT).getSingleResult();
        }
        catch(NoResultException noresultexception)
        {
            return null;
        }
    }

    public void persist(DeliveryTemplate deliveryTemplate)
    {
        Assert.notNull(deliveryTemplate);
        if(deliveryTemplate.getIsDefault().booleanValue())
        {
            String s = "update DeliveryTemplate deliveryTemplate set deliveryTemplate.isDefault = false where deliveryTemplate.isDefault = true";
            IIIllIlI.createQuery(s).setFlushMode(FlushModeType.COMMIT).executeUpdate();
        }
        super.persist(deliveryTemplate);
    }

    public DeliveryTemplate merge(DeliveryTemplate deliveryTemplate)
    {
        Assert.notNull(deliveryTemplate);
        if(deliveryTemplate.getIsDefault().booleanValue())
        {
            String s = "update DeliveryTemplate deliveryTemplate set deliveryTemplate.isDefault = false where deliveryTemplate.isDefault = true and deliveryTemplate != :deliveryTemplate";
            IIIllIlI.createQuery(s).setFlushMode(FlushModeType.COMMIT).setParameter("deliveryTemplate", deliveryTemplate).executeUpdate();
        }
        return (DeliveryTemplate)super.merge(deliveryTemplate);
    }

    public volatile Object merge(Object obj)
    {
        return merge((DeliveryTemplate)obj);
    }

    public volatile void persist(Object obj)
    {
        persist((DeliveryTemplate)obj);
    }
}
