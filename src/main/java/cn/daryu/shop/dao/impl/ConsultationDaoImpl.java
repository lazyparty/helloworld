// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.ConsultationDao;
import net.shopxx.entity.*;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class ConsultationDaoImpl extends BaseDaoImpl
    implements ConsultationDao
{

    public ConsultationDaoImpl()
    {
    }

    public List findList(Member member, Product product, Boolean isShow, Integer count, List filters, List orders)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Consultation);
        Root root = criteriaquery.from(net/shopxx/entity/Consultation);
        criteriaquery.select(root);
        javax.persistence.criteria.Predicate predicate = criteriabuilder.conjunction();
        predicate = criteriabuilder.and(predicate, criteriabuilder.isNull(root.get("forConsultation")));
        if(member != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("member"), member));
        if(product != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("product"), product));
        if(isShow != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isShow"), isShow));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, null, count, filters, orders);
    }

    public Page findPage(Member member, Product product, Boolean isShow, Pageable pageable)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Consultation);
        Root root = criteriaquery.from(net/shopxx/entity/Consultation);
        criteriaquery.select(root);
        javax.persistence.criteria.Predicate predicate = criteriabuilder.conjunction();
        predicate = criteriabuilder.and(predicate, criteriabuilder.isNull(root.get("forConsultation")));
        if(member != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("member"), member));
        if(product != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("product"), product));
        if(isShow != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isShow"), isShow));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, pageable);
    }

    public Long count(Member member, Product product, Boolean isShow)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Consultation);
        Root root = criteriaquery.from(net/shopxx/entity/Consultation);
        criteriaquery.select(root);
        javax.persistence.criteria.Predicate predicate = criteriabuilder.conjunction();
        predicate = criteriabuilder.and(predicate, criteriabuilder.isNull(root.get("forConsultation")));
        if(member != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("member"), member));
        if(product != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("product"), product));
        if(isShow != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isShow"), isShow));
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, null);
    }
}
