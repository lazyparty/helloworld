// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.*;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.CouponCodeDao;
import net.shopxx.entity.*;
import org.springframework.util.Assert;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class CouponCodeDaoImpl extends BaseDaoImpl
    implements CouponCodeDao
{

    public CouponCodeDaoImpl()
    {
    }

    public boolean codeExists(String code)
    {
        if(code == null)
            return false;
        String s = "select count(*) from CouponCode couponCode where lower(couponCode.code) = lower(:code)";
        Long long1 = (Long)IIIllIlI.createQuery(s, java/lang/Long).setFlushMode(FlushModeType.COMMIT).setParameter("code", code).getSingleResult();
        return long1.longValue() > 0L;
    }

    public CouponCode findByCode(String code)
    {
        if(code == null)
            return null;
        try
        {
            String s = "select couponCode from CouponCode couponCode where lower(couponCode.code) = lower(:code)";
            return (CouponCode)IIIllIlI.createQuery(s, net/shopxx/entity/CouponCode).setFlushMode(FlushModeType.COMMIT).setParameter("code", code).getSingleResult();
        }
        catch(NoResultException noresultexception)
        {
            return null;
        }
    }

    public CouponCode build(Coupon coupon, Member member)
    {
        Assert.notNull(coupon);
        CouponCode couponcode = new CouponCode();
        String s = UUID.randomUUID().toString().toUpperCase();
        couponcode.setCode((new StringBuilder(String.valueOf(coupon.getPrefix()))).append(s.substring(0, 8)).append(s.substring(9, 13)).append(s.substring(14, 18)).append(s.substring(19, 23)).append(s.substring(24)).toString());
        couponcode.setIsUsed(Boolean.valueOf(false));
        couponcode.setCoupon(coupon);
        couponcode.setMember(member);
        super.persist(couponcode);
        return couponcode;
    }

    public List build(Coupon coupon, Member member, Integer count)
    {
        Assert.notNull(coupon);
        Assert.notNull(count);
        ArrayList arraylist = new ArrayList();
        for(int i = 0; i < count.intValue(); i++)
        {
            CouponCode couponcode = build(coupon, member);
            arraylist.add(couponcode);
            if(i % 20 == 0)
            {
                super.flush();
                super.clear();
            }
        }

        return arraylist;
    }

    public Page findPage(Member member, Pageable pageable)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/CouponCode);
        Root root = criteriaquery.from(net/shopxx/entity/CouponCode);
        criteriaquery.select(root);
        if(member != null)
            criteriaquery.where(criteriabuilder.equal(root.get("member"), member));
        return super.IIIllIlI(criteriaquery, pageable);
    }

    public Long count(Coupon coupon, Member member, Boolean hasBegun, Boolean hasExpired, Boolean isUsed)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/CouponCode);
        Root root = criteriaquery.from(net/shopxx/entity/CouponCode);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        Path path = root.get("coupon");
        if(coupon != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(path, coupon));
        if(member != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("member"), member));
        if(hasBegun != null)
            if(hasBegun.booleanValue())
                predicate = criteriabuilder.and(predicate, criteriabuilder.or(path.get("beginDate").isNull(), criteriabuilder.lessThanOrEqualTo(path.get("beginDate"), new Date())));
            else
                predicate = criteriabuilder.and(new Predicate[] {
                    predicate, path.get("beginDate").isNotNull(), criteriabuilder.greaterThan(path.get("beginDate"), new Date())
                });
        if(hasExpired != null)
            if(hasExpired.booleanValue())
                predicate = criteriabuilder.and(new Predicate[] {
                    predicate, path.get("endDate").isNotNull(), criteriabuilder.lessThan(path.get("endDate"), new Date())
                });
            else
                predicate = criteriabuilder.and(predicate, criteriabuilder.or(path.get("endDate").isNull(), criteriabuilder.greaterThanOrEqualTo(path.get("endDate"), new Date())));
        if(isUsed != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isUsed"), isUsed));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, null);
    }
}
