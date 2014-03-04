// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;
import net.shopxx.dao.NavigationDao;
import net.shopxx.entity.Navigation;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class NavigationDaoImpl extends BaseDaoImpl
    implements NavigationDao
{

    public NavigationDaoImpl()
    {
    }

    public List findList(net.shopxx.entity.Navigation.Position position)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Navigation);
        Root root = criteriaquery.from(net/shopxx/entity/Navigation);
        criteriaquery.select(root);
        if(position != null)
            criteriaquery.where(criteriabuilder.equal(root.get("position"), position));
        criteriaquery.orderBy(new Order[] {
            criteriabuilder.asc(root.get("order"))
        });
        return IIIllIlI.createQuery(criteriaquery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }
}
