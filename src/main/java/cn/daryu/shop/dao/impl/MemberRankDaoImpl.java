// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import net.shopxx.dao.MemberRankDao;
import net.shopxx.entity.MemberRank;
import net.shopxx.entity.Product;
import org.springframework.util.Assert;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class MemberRankDaoImpl extends BaseDaoImpl
    implements MemberRankDao
{

    public MemberRankDaoImpl()
    {
    }

    public boolean nameExists(String name)
    {
        if(name == null)
            return false;
        String s = "select count(*) from MemberRank memberRank where lower(memberRank.name) = lower(:name)";
        Long long1 = (Long)IIIllIlI.createQuery(s, java/lang/Long).setFlushMode(FlushModeType.COMMIT).setParameter("name", name).getSingleResult();
        return long1.longValue() > 0L;
    }

    public boolean amountExists(BigDecimal amount)
    {
        if(amount == null)
            return false;
        String s = "select count(*) from MemberRank memberRank where memberRank.amount = :amount";
        Long long1 = (Long)IIIllIlI.createQuery(s, java/lang/Long).setFlushMode(FlushModeType.COMMIT).setParameter("amount", amount).getSingleResult();
        return long1.longValue() > 0L;
    }

    public MemberRank findDefault()
    {
        try
        {
            String s = "select memberRank from MemberRank memberRank where memberRank.isDefault = true";
            return (MemberRank)IIIllIlI.createQuery(s, net/shopxx/entity/MemberRank).setFlushMode(FlushModeType.COMMIT).getSingleResult();
        }
        catch(NoResultException noresultexception)
        {
            return null;
        }
    }

    public MemberRank findByAmount(BigDecimal amount)
    {
        if(amount == null)
        {
            return null;
        } else
        {
            String s = "select memberRank from MemberRank memberRank where memberRank.isSpecial = false and memberRank.amount <= :amount order by memberRank.amount desc";
            return (MemberRank)IIIllIlI.createQuery(s, net/shopxx/entity/MemberRank).setFlushMode(FlushModeType.COMMIT).setParameter("amount", amount).setMaxResults(1).getSingleResult();
        }
    }

    public void persist(MemberRank memberRank)
    {
        Assert.notNull(memberRank);
        if(memberRank.getIsDefault().booleanValue())
        {
            String s = "update MemberRank memberRank set memberRank.isDefault = false where memberRank.isDefault = true";
            IIIllIlI.createQuery(s).setFlushMode(FlushModeType.COMMIT).executeUpdate();
        }
        super.persist(memberRank);
    }

    public MemberRank merge(MemberRank memberRank)
    {
        Assert.notNull(memberRank);
        if(memberRank.getIsDefault().booleanValue())
        {
            String s = "update MemberRank memberRank set memberRank.isDefault = false where memberRank.isDefault = true and memberRank != :memberRank";
            IIIllIlI.createQuery(s).setFlushMode(FlushModeType.COMMIT).setParameter("memberRank", memberRank).executeUpdate();
        }
        return (MemberRank)super.merge(memberRank);
    }

    public void remove(MemberRank memberRank)
    {
        if(memberRank != null && !memberRank.getIsDefault().booleanValue())
        {
            String s = "select product from Product product join product.memberPrice memberPrice where index(memberPrice) = :memberRank";
            List list = IIIllIlI.createQuery(s, net/shopxx/entity/Product).setFlushMode(FlushModeType.COMMIT).setParameter("memberRank", memberRank).getResultList();
            for(int i = 0; i < list.size(); i++)
            {
                Product product = (Product)list.get(i);
                product.getMemberPrice().remove(memberRank);
                if(i % 20 == 0)
                {
                    super.flush();
                    super.clear();
                }
            }

            super.remove((MemberRank)super.merge(memberRank));
        }
    }

    public volatile Object merge(Object obj)
    {
        return merge((MemberRank)obj);
    }

    public volatile void persist(Object obj)
    {
        persist((MemberRank)obj);
    }

    public volatile void remove(Object obj)
    {
        remove((MemberRank)obj);
    }
}
