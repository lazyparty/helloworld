// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.CouponDao;
import net.shopxx.service.CouponService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class CouponServiceImpl extends BaseServiceImpl
    implements CouponService
{

    public CouponServiceImpl()
    {
    }

    public void setBaseDao(CouponDao couponDao)
    {
        super.setBaseDao(couponDao);
    }

    public Page findPage(Boolean isEnabled, Boolean isExchange, Boolean hasExpired, Pageable pageable)
    {
        return IIIllIlI.findPage(isEnabled, isExchange, hasExpired, pageable);
    }

    private CouponDao IIIllIlI;
}
