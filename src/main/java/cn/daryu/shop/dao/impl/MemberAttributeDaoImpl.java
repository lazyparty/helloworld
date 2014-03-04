// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.List;
import javax.persistence.*;
import net.shopxx.dao.MemberAttributeDao;
import net.shopxx.entity.MemberAttribute;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class MemberAttributeDaoImpl extends BaseDaoImpl
    implements MemberAttributeDao
{

    public MemberAttributeDaoImpl()
    {
    }

    public Integer findUnusedPropertyIndex()
    {
        for(int i = 0; i < 10; i++)
        {
            String s = "select count(*) from MemberAttribute memberAttribute where memberAttribute.propertyIndex = :propertyIndex";
            Long long1 = (Long)IIIllIlI.createQuery(s, java/lang/Long).setFlushMode(FlushModeType.COMMIT).setParameter("propertyIndex", Integer.valueOf(i)).getSingleResult();
            if(long1.longValue() == 0L)
                return Integer.valueOf(i);
        }

        return null;
    }

    public List findList()
    {
        String s = "select memberAttribute from MemberAttribute memberAttribute where memberAttribute.isEnabled = true order by memberAttribute.order asc";
        return IIIllIlI.createQuery(s, net/shopxx/entity/MemberAttribute).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

    public void remove(MemberAttribute memberAttribute)
    {
        if(memberAttribute != null && (memberAttribute.getType() == net.shopxx.entity.MemberAttribute.Type.text || memberAttribute.getType() == net.shopxx.entity.MemberAttribute.Type.select || memberAttribute.getType() == net.shopxx.entity.MemberAttribute.Type.checkbox))
        {
            String s = (new StringBuilder("attributeValue")).append(memberAttribute.getPropertyIndex()).toString();
            String s1 = (new StringBuilder("update Member members set members.")).append(s).append(" = null").toString();
            IIIllIlI.createQuery(s1).setFlushMode(FlushModeType.COMMIT).executeUpdate();
            super.remove(memberAttribute);
        }
    }

    public volatile void remove(Object obj)
    {
        remove((MemberAttribute)obj);
    }
}
