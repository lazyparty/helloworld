// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.dao.PaymentMethodDao;
import net.shopxx.service.PaymentMethodService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class PaymentMethodServiceImpl extends BaseServiceImpl
    implements PaymentMethodService
{

    public PaymentMethodServiceImpl()
    {
    }

    public void setBaseDao(PaymentMethodDao paymentMethodDao)
    {
        super.setBaseDao(paymentMethodDao);
    }
}
