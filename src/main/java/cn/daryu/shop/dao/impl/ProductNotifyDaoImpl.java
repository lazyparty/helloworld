// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import javax.persistence.*;
import javax.persistence.criteria.*;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.ProductNotifyDao;
import net.shopxx.entity.*;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class ProductNotifyDaoImpl extends BaseDaoImpl
    implements ProductNotifyDao
{

    public ProductNotifyDaoImpl()
    {
    }

    public boolean exists(Product product, String email)
    {
        String s = "select count(*) from ProductNotify productNotify where productNotify.product = :product and lower(productNotify.email) = lower(:email) and productNotify.hasSent = false";
        Long long1 = (Long)IIIllIlI.createQuery(s, java/lang/Long).setFlushMode(FlushModeType.COMMIT).setParameter("product", product).setParameter("email", email).getSingleResult();
        return long1.longValue() > 0L;
    }

    public Page findPage(Member member, Boolean isMarketable, Boolean isOutOfStock, Boolean hasSent, Pageable pageable)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/ProductNotify);
        Root root = criteriaquery.from(net/shopxx/entity/ProductNotify);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        if(member != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("member"), member));
        if(isMarketable != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("product").get("isMarketable"), isMarketable));
        if(isOutOfStock != null)
        {
            Path path = root.get("product").get("stock");
            Path path1 = root.get("product").get("allocatedStock");
            if(isOutOfStock.booleanValue())
                predicate = criteriabuilder.and(new Predicate[] {
                    predicate, criteriabuilder.isNotNull(path), criteriabuilder.lessThanOrEqualTo(path, path1)
                });
            else
                predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.isNull(path), criteriabuilder.greaterThan(path, path1)));
        }
        if(hasSent != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("hasSent"), hasSent));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, pageable);
    }

    public Long count(Member member, Boolean isMarketable, Boolean isOutOfStock, Boolean hasSent)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/ProductNotify);
        Root root = criteriaquery.from(net/shopxx/entity/ProductNotify);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        if(member != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("member"), member));
        if(isMarketable != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("product").get("isMarketable"), isMarketable));
        if(isOutOfStock != null)
        {
            Path path = root.get("product").get("stock");
            Path path1 = root.get("product").get("allocatedStock");
            if(isOutOfStock.booleanValue())
                predicate = criteriabuilder.and(new Predicate[] {
                    predicate, criteriabuilder.isNotNull(path), criteriabuilder.lessThanOrEqualTo(path, path1)
                });
            else
                predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.isNull(path), criteriabuilder.greaterThan(path, path1)));
        }
        if(hasSent != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("hasSent"), hasSent));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, null);
    }
}
