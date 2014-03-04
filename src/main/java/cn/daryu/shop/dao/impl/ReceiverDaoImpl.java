// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import javax.persistence.*;
import javax.persistence.criteria.*;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.ReceiverDao;
import net.shopxx.entity.Member;
import net.shopxx.entity.Receiver;
import org.springframework.util.Assert;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class ReceiverDaoImpl extends BaseDaoImpl
    implements ReceiverDao
{

    public ReceiverDaoImpl()
    {
    }

    public Receiver findDefault(Member member)
    {
        if(member == null)
            return null;
        try
        {
            String s = "select receiver from Receiver receiver where receiver.member = :member and receiver.isDefault = true";
            return (Receiver)IIIllIlI.createQuery(s, net/shopxx/entity/Receiver).setFlushMode(FlushModeType.COMMIT).setParameter("member", member).getSingleResult();
        }
        catch(NoResultException noresultexception) { }
        try
        {
            String s1 = "select receiver from Receiver receiver where receiver.member = :member order by receiver.modifyDate desc";
            return (Receiver)IIIllIlI.createQuery(s1, net/shopxx/entity/Receiver).setFlushMode(FlushModeType.COMMIT).setParameter("member", member).setMaxResults(1).getSingleResult();
        }
        catch(NoResultException noresultexception1)
        {
            return null;
        }
    }

    public Page findPage(Member member, Pageable pageable)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Receiver);
        Root root = criteriaquery.from(net/shopxx/entity/Receiver);
        criteriaquery.select(root);
        if(member != null)
            criteriaquery.where(criteriabuilder.equal(root.get("member"), member));
        return super.IIIllIlI(criteriaquery, pageable);
    }

    public void persist(Receiver receiver)
    {
        Assert.notNull(receiver);
        Assert.notNull(receiver.getMember());
        if(receiver.getIsDefault().booleanValue())
        {
            String s = "update Receiver receiver set receiver.isDefault = false where receiver.member = :member and receiver.isDefault = true";
            IIIllIlI.createQuery(s).setFlushMode(FlushModeType.COMMIT).setParameter("member", receiver.getMember()).executeUpdate();
        }
        super.persist(receiver);
    }

    public Receiver merge(Receiver receiver)
    {
        Assert.notNull(receiver);
        Assert.notNull(receiver.getMember());
        if(receiver.getIsDefault().booleanValue())
        {
            String s = "update Receiver receiver set receiver.isDefault = false where receiver.member = :member and receiver.isDefault = true and receiver != :receiver";
            IIIllIlI.createQuery(s).setFlushMode(FlushModeType.COMMIT).setParameter("member", receiver.getMember()).setParameter("receiver", receiver).executeUpdate();
        }
        return (Receiver)super.merge(receiver);
    }

    public volatile Object merge(Object obj)
    {
        return merge((Receiver)obj);
    }

    public volatile void persist(Object obj)
    {
        persist((Receiver)obj);
    }
}
