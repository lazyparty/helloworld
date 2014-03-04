// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import net.shopxx.dao.PromotionDao;
import net.shopxx.entity.Promotion;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class PromotionDaoImpl extends BaseDaoImpl
    implements PromotionDao
{

    public PromotionDaoImpl()
    {
    }

    public List findList(Boolean hasBegun, Boolean hasEnded, Integer count, List filters, List orders)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Promotion);
        Root root = criteriaquery.from(net/shopxx/entity/Promotion);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        if(hasBegun != null)
            if(hasBegun.booleanValue())
                predicate = criteriabuilder.and(predicate, criteriabuilder.or(root.get("beginDate").isNull(), criteriabuilder.lessThanOrEqualTo(root.get("beginDate"), new Date())));
            else
                predicate = criteriabuilder.and(new Predicate[] {
                    predicate, root.get("beginDate").isNotNull(), criteriabuilder.greaterThan(root.get("beginDate"), new Date())
                });
        if(hasEnded != null)
            if(hasEnded.booleanValue())
                predicate = criteriabuilder.and(new Predicate[] {
                    predicate, root.get("endDate").isNotNull(), criteriabuilder.lessThan(root.get("endDate"), new Date())
                });
            else
                predicate = criteriabuilder.and(predicate, criteriabuilder.or(root.get("endDate").isNull(), criteriabuilder.greaterThanOrEqualTo(root.get("endDate"), new Date())));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, null, count, filters, orders);
    }
}
