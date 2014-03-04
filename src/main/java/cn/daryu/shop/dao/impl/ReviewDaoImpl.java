// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.ReviewDao;
import net.shopxx.entity.*;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class ReviewDaoImpl extends BaseDaoImpl
    implements ReviewDao
{

    public ReviewDaoImpl()
    {
    }

    public List findList(Member member, Product product, net.shopxx.entity.Review.Type type, Boolean isShow, Integer count, List filters, List orders)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Review);
        Root root = criteriaquery.from(net/shopxx/entity/Review);
        criteriaquery.select(root);
        javax.persistence.criteria.Predicate predicate = criteriabuilder.conjunction();
        if(member != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("member"), member));
        if(product != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("product"), product));
        if(type == net.shopxx.entity.Review.Type.positive)
            predicate = criteriabuilder.and(predicate, criteriabuilder.ge(root.get("score"), Integer.valueOf(4)));
        else
        if(type == net.shopxx.entity.Review.Type.moderate)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("score"), Integer.valueOf(3)));
        else
        if(type == net.shopxx.entity.Review.Type.negative)
            predicate = criteriabuilder.and(predicate, criteriabuilder.le(root.get("score"), Integer.valueOf(2)));
        if(isShow != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isShow"), isShow));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, null, count, filters, orders);
    }

    public Page findPage(Member member, Product product, net.shopxx.entity.Review.Type type, Boolean isShow, Pageable pageable)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Review);
        Root root = criteriaquery.from(net/shopxx/entity/Review);
        criteriaquery.select(root);
        javax.persistence.criteria.Predicate predicate = criteriabuilder.conjunction();
        if(member != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("member"), member));
        if(product != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("product"), product));
        if(type == net.shopxx.entity.Review.Type.positive)
            predicate = criteriabuilder.and(predicate, criteriabuilder.ge(root.get("score"), Integer.valueOf(4)));
        else
        if(type == net.shopxx.entity.Review.Type.moderate)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("score"), Integer.valueOf(3)));
        else
        if(type == net.shopxx.entity.Review.Type.negative)
            predicate = criteriabuilder.and(predicate, criteriabuilder.le(root.get("score"), Integer.valueOf(2)));
        if(isShow != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isShow"), isShow));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, pageable);
    }

    public Long count(Member member, Product product, net.shopxx.entity.Review.Type type, Boolean isShow)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Review);
        Root root = criteriaquery.from(net/shopxx/entity/Review);
        criteriaquery.select(root);
        javax.persistence.criteria.Predicate predicate = criteriabuilder.conjunction();
        if(member != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("member"), member));
        if(product != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("product"), product));
        if(type == net.shopxx.entity.Review.Type.positive)
            predicate = criteriabuilder.and(predicate, criteriabuilder.ge(root.get("score"), Integer.valueOf(4)));
        else
        if(type == net.shopxx.entity.Review.Type.moderate)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("score"), Integer.valueOf(3)));
        else
        if(type == net.shopxx.entity.Review.Type.negative)
            predicate = criteriabuilder.and(predicate, criteriabuilder.le(root.get("score"), Integer.valueOf(2)));
        if(isShow != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isShow"), isShow));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, null);
    }

    public boolean isReviewed(Member member, Product product)
    {
        if(member == null || product == null)
            return false;
        String s = "select count(*) from Review review where review.member = :member and review.product = :product";
        Long long1 = (Long)IIIllIlI.createQuery(s, java/lang/Long).setFlushMode(FlushModeType.COMMIT).setParameter("member", member).setParameter("product", product).getSingleResult();
        return long1.longValue() > 0L;
    }

    public long calculateTotalScore(Product product)
    {
        if(product == null)
        {
            return 0L;
        } else
        {
            String s = "select sum(review.score) from Review review where review.product = :product and review.isShow = :isShow";
            Long long1 = (Long)IIIllIlI.createQuery(s, java/lang/Long).setFlushMode(FlushModeType.COMMIT).setParameter("product", product).setParameter("isShow", Boolean.valueOf(true)).getSingleResult();
            return long1 == null ? 0L : long1.longValue();
        }
    }

    public long calculateScoreCount(Product product)
    {
        if(product == null)
        {
            return 0L;
        } else
        {
            String s = "select count(*) from Review review where review.product = :product and review.isShow = :isShow";
            return ((Long)IIIllIlI.createQuery(s, java/lang/Long).setFlushMode(FlushModeType.COMMIT).setParameter("product", product).setParameter("isShow", Boolean.valueOf(true)).getSingleResult()).longValue();
        }
    }
}
