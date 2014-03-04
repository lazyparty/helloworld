// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import javax.persistence.*;
import net.shopxx.dao.PaymentDao;
import net.shopxx.entity.Payment;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class PaymentDaoImpl extends BaseDaoImpl
    implements PaymentDao
{

    public PaymentDaoImpl()
    {
    }

    public Payment findBySn(String sn)
    {
        if(sn == null)
            return null;
        String s = "select payment from Payment payment where lower(payment.sn) = lower(:sn)";
        try
        {
            return (Payment)IIIllIlI.createQuery(s, net/shopxx/entity/Payment).setFlushMode(FlushModeType.COMMIT).setParameter("sn", sn).getSingleResult();
        }
        catch(NoResultException noresultexception)
        {
            return null;
        }
    }
}
