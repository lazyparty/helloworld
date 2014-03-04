// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import javax.persistence.*;
import net.shopxx.dao.SeoDao;
import net.shopxx.entity.Seo;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class SeoDaoImpl extends BaseDaoImpl
    implements SeoDao
{

    public SeoDaoImpl()
    {
    }

    public Seo find(net.shopxx.entity.Seo.Type type)
    {
        if(type == null)
            return null;
        try
        {
            String s = "select seo from Seo seo where seo.type = :type";
            return (Seo)IIIllIlI.createQuery(s, net/shopxx/entity/Seo).setFlushMode(FlushModeType.COMMIT).setParameter("type", type).getSingleResult();
        }
        catch(NoResultException noresultexception)
        {
            return null;
        }
    }
}
