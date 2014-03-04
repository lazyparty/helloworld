// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import net.shopxx.entity.Payment;

// Referenced classes of package net.shopxx.service:
//            BaseService

public interface PaymentService
    extends BaseService
{

    public abstract Payment findBySn(String s);
}
