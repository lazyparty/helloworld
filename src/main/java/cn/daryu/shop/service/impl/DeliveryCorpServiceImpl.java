// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.dao.DeliveryCorpDao;
import net.shopxx.service.DeliveryCorpService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class DeliveryCorpServiceImpl extends BaseServiceImpl
    implements DeliveryCorpService
{

    public DeliveryCorpServiceImpl()
    {
    }

    public void setBaseDao(DeliveryCorpDao deliveryCorpDao)
    {
        super.setBaseDao(deliveryCorpDao);
    }
}
