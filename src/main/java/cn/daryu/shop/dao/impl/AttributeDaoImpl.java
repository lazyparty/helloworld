// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.List;
import javax.persistence.*;
import net.shopxx.dao.AttributeDao;
import net.shopxx.entity.Attribute;
import org.springframework.util.Assert;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class AttributeDaoImpl extends BaseDaoImpl
    implements AttributeDao
{

    public AttributeDaoImpl()
    {
    }

    public void persist(Attribute attribute)
    {
        Assert.notNull(attribute);
        String s = "select attribute.propertyIndex from Attribute attribute where attribute.productCategory = :productCategory";
        List list = IIIllIlI.createQuery(s, java/lang/Integer).setFlushMode(FlushModeType.COMMIT).setParameter("productCategory", attribute.getProductCategory()).getResultList();
        for(int i = 0; i < 20; i++)
        {
            if(list.contains(Integer.valueOf(i)))
                continue;
            attribute.setPropertyIndex(Integer.valueOf(i));
            super.persist(attribute);
            break;
        }

    }

    public void remove(Attribute attribute)
    {
        if(attribute != null)
        {
            String s = (new StringBuilder("attributeValue")).append(attribute.getPropertyIndex()).toString();
            String s1 = (new StringBuilder("update Product product set product.")).append(s).append(" = null where product.productCategory = :productCategory").toString();
            IIIllIlI.createQuery(s1).setFlushMode(FlushModeType.COMMIT).setParameter("productCategory", attribute.getProductCategory()).executeUpdate();
            super.remove(attribute);
        }
    }

    public volatile void persist(Object obj)
    {
        persist((Attribute)obj);
    }

    public volatile void remove(Object obj)
    {
        remove((Attribute)obj);
    }
}
