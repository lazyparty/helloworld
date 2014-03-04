// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.dao.ShippingMethodDao;
import net.shopxx.service.ShippingMethodService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class ShippingMethodServiceImpl extends BaseServiceImpl
    implements ShippingMethodService
{

    public ShippingMethodServiceImpl()
    {
    }

    public void setBaseDao(ShippingMethodDao shippingMethodDao)
    {
        super.setBaseDao(shippingMethodDao);
    }
}
