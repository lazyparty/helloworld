// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.CouponDao;
import net.shopxx.entity.Coupon;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class CouponDaoImpl extends BaseDaoImpl
    implements CouponDao
{

    public CouponDaoImpl()
    {
    }

    public Page findPage(Boolean isEnabled, Boolean isExchange, Boolean hasExpired, Pageable pageable)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Coupon);
        Root root = criteriaquery.from(net/shopxx/entity/Coupon);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        if(isEnabled != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isEnabled"), isEnabled));
        if(isExchange != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isExchange"), isExchange));
        if(hasExpired != null)
            if(hasExpired.booleanValue())
                predicate = criteriabuilder.and(new Predicate[] {
                    predicate, root.get("endDate").isNotNull(), criteriabuilder.lessThan(root.get("endDate"), new Date())
                });
            else
                predicate = criteriabuilder.and(predicate, criteriabuilder.or(root.get("endDate").isNull(), criteriabuilder.greaterThanOrEqualTo(root.get("endDate"), new Date())));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, pageable);
    }
}
