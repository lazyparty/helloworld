// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.List;
import javax.persistence.*;
import net.shopxx.dao.AreaDao;
import net.shopxx.entity.Area;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class AreaDaoImpl extends BaseDaoImpl
    implements AreaDao
{

    public AreaDaoImpl()
    {
    }

    public List findRoots(Integer count)
    {
        String s = "select area from Area area where area.parent is null order by area.order asc";
        TypedQuery typedquery = IIIllIlI.createQuery(s, net/shopxx/entity/Area).setFlushMode(FlushModeType.COMMIT);
        if(count != null)
            typedquery.setMaxResults(count.intValue());
        return typedquery.getResultList();
    }
}
