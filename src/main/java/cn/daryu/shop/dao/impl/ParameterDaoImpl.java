// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.criteria.*;
import net.shopxx.dao.ParameterDao;
import net.shopxx.entity.Parameter;
import net.shopxx.entity.ParameterGroup;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class ParameterDaoImpl extends BaseDaoImpl
    implements ParameterDao
{

    public ParameterDaoImpl()
    {
    }

    public List findList(ParameterGroup parameterGroup, Set excludes)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Parameter);
        Root root = criteriaquery.from(net/shopxx/entity/Parameter);
        criteriaquery.select(root);
        javax.persistence.criteria.Predicate predicate = criteriabuilder.conjunction();
        if(parameterGroup != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("parameterGroup"), parameterGroup));
        if(excludes != null && !excludes.isEmpty())
            predicate = criteriabuilder.and(predicate, criteriabuilder.not(root.in(excludes)));
        criteriaquery.where(predicate);
        return IIIllIlI.createQuery(criteriaquery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }
}
