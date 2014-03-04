// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.dao.DeliveryCenterDao;
import net.shopxx.entity.DeliveryCenter;
import net.shopxx.service.DeliveryCenterService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class DeliveryCenterServiceImpl extends BaseServiceImpl
    implements DeliveryCenterService
{

    public DeliveryCenterServiceImpl()
    {
    }

    public void setBaseDao(DeliveryCenterDao DeliveryCenterDao)
    {
        super.setBaseDao(DeliveryCenterDao);
    }

    public DeliveryCenter findDefault()
    {
        return IIIllIlI.findDefault();
    }

    private DeliveryCenterDao IIIllIlI;
}
