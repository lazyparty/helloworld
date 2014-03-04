// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;
import net.shopxx.dao.FriendLinkDao;
import net.shopxx.entity.FriendLink;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class FriendLinkDaoImpl extends BaseDaoImpl
    implements FriendLinkDao
{

    public FriendLinkDaoImpl()
    {
    }

    public List findList(net.shopxx.entity.FriendLink.Type type)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/FriendLink);
        Root root = criteriaquery.from(net/shopxx/entity/FriendLink);
        criteriaquery.select(root);
        if(type != null)
            criteriaquery.where(criteriabuilder.equal(root.get("type"), type));
        criteriaquery.orderBy(new Order[] {
            criteriabuilder.asc(root.get("order"))
        });
        return IIIllIlI.createQuery(criteriaquery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }
}
