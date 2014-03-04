// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.*;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.MemberDao;
import net.shopxx.entity.Member;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class MemberDaoImpl extends BaseDaoImpl
    implements MemberDao
{

    public MemberDaoImpl()
    {
    }

    public boolean usernameExists(String username)
    {
        if(username == null)
            return false;
        String s = "select count(*) from Member members where lower(members.username) = lower(:username)";
        Long long1 = (Long)IIIllIlI.createQuery(s, java/lang/Long).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
        return long1.longValue() > 0L;
    }

    public boolean emailExists(String email)
    {
        if(email == null)
            return false;
        String s = "select count(*) from Member members where lower(members.email) = lower(:email)";
        Long long1 = (Long)IIIllIlI.createQuery(s, java/lang/Long).setFlushMode(FlushModeType.COMMIT).setParameter("email", email).getSingleResult();
        return long1.longValue() > 0L;
    }

    public Member findByUsername(String username)
    {
        if(username == null)
            return null;
        try
        {
            String s = "select members from Member members where lower(members.username) = lower(:username)";
            return (Member)IIIllIlI.createQuery(s, net/shopxx/entity/Member).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
        }
        catch(NoResultException noresultexception)
        {
            return null;
        }
    }

    public List findListByEmail(String email)
    {
        if(email == null)
        {
            return Collections.emptyList();
        } else
        {
            String s = "select members from Member members where lower(members.email) = lower(:email)";
            return IIIllIlI.createQuery(s, net/shopxx/entity/Member).setFlushMode(FlushModeType.COMMIT).setParameter("email", email).getResultList();
        }
    }

    public Page findPurchasePage(Date beginDate, Date endDate, Pageable pageable)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(java/lang/Object);
        Root root = criteriaquery.from(net/shopxx/entity/Member);
        Join join = root.join("orders");
        criteriaquery.multiselect(new Selection[] {
            root, criteriabuilder.sum(join.get("amountPaid"))
        });
        javax.persistence.criteria.Predicate predicate = criteriabuilder.conjunction();
        if(beginDate != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.greaterThanOrEqualTo(join.get("createDate"), beginDate));
        if(endDate != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.lessThanOrEqualTo(join.get("createDate"), endDate));
        predicate = criteriabuilder.and(predicate, criteriabuilder.equal(join.get("orderStatus"), net.shopxx.entity.Order.OrderStatus.completed));
        predicate = criteriabuilder.and(predicate, criteriabuilder.equal(join.get("paymentStatus"), net.shopxx.entity.Order.PaymentStatus.paid));
        criteriaquery.where(predicate);
        criteriaquery.groupBy(new Expression[] {
            root.get("id")
        });
        CriteriaQuery criteriaquery1 = criteriabuilder.createQuery(java/lang/Long);
        Root root1 = criteriaquery1.from(net/shopxx/entity/Member);
        Join join1 = root1.join("orders");
        criteriaquery1.select(criteriabuilder.countDistinct(root1));
        javax.persistence.criteria.Predicate predicate1 = criteriabuilder.conjunction();
        if(beginDate != null)
            predicate1 = criteriabuilder.and(predicate1, criteriabuilder.greaterThanOrEqualTo(join1.get("createDate"), beginDate));
        if(endDate != null)
            predicate1 = criteriabuilder.and(predicate1, criteriabuilder.lessThanOrEqualTo(join1.get("createDate"), endDate));
        predicate1 = criteriabuilder.and(predicate1, criteriabuilder.equal(join1.get("orderStatus"), net.shopxx.entity.Order.OrderStatus.completed));
        criteriaquery1.where(predicate1);
        Long long1 = (Long)IIIllIlI.createQuery(criteriaquery1).setFlushMode(FlushModeType.COMMIT).getSingleResult();
        int i = (int)Math.ceil((double)long1.longValue() / (double)pageable.getPageSize());
        if(i < pageable.getPageNumber())
            pageable.setPageNumber(i);
        criteriaquery.orderBy(new Order[] {
            criteriabuilder.desc(criteriabuilder.sum(join.get("amountPaid")))
        });
        TypedQuery typedquery = IIIllIlI.createQuery(criteriaquery).setFlushMode(FlushModeType.COMMIT);
        typedquery.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        typedquery.setMaxResults(pageable.getPageSize());
        return new Page(typedquery.getResultList(), long1.longValue(), pageable);
    }
}
