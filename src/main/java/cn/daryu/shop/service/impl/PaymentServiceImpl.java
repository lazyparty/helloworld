// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.dao.PaymentDao;
import net.shopxx.entity.Payment;
import net.shopxx.service.PaymentService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class PaymentServiceImpl extends BaseServiceImpl
    implements PaymentService
{

    public PaymentServiceImpl()
    {
    }

    public void setBaseDao(PaymentDao paymentDao)
    {
        super.setBaseDao(paymentDao);
    }

    public Payment findBySn(String sn)
    {
        return IIIllIlI.findBySn(sn);
    }

    private PaymentDao IIIllIlI;
}
