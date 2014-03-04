// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.Collections;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.DepositDao;
import net.shopxx.entity.Deposit;
import net.shopxx.entity.Member;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class DepositDaoImpl extends BaseDaoImpl
    implements DepositDao
{

    public DepositDaoImpl()
    {
    }

    public Page findPage(Member member, Pageable pageable)
    {
        if(member == null)
        {
            return new Page(Collections.emptyList(), 0L, pageable);
        } else
        {
            CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
            CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Deposit);
            Root root = criteriaquery.from(net/shopxx/entity/Deposit);
            criteriaquery.select(root);
            criteriaquery.where(criteriabuilder.equal(root.get("member"), member));
            return super.IIIllIlI(criteriaquery, pageable);
        }
    }
}
