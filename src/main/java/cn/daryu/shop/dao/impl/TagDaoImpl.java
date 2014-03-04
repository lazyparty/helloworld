// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;
import net.shopxx.dao.TagDao;
import net.shopxx.entity.Tag;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class TagDaoImpl extends BaseDaoImpl
    implements TagDao
{

    public TagDaoImpl()
    {
    }

    public List findList(net.shopxx.entity.Tag.Type type)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Tag);
        Root root = criteriaquery.from(net/shopxx/entity/Tag);
        criteriaquery.select(root);
        if(type != null)
            criteriaquery.where(criteriabuilder.equal(root.get("type"), type));
        criteriaquery.orderBy(new Order[] {
            criteriabuilder.asc(root.get("order"))
        });
        return IIIllIlI.createQuery(criteriaquery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }
}
