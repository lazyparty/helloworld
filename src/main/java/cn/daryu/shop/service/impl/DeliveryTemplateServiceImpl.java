// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.dao.DeliveryTemplateDao;
import net.shopxx.entity.DeliveryTemplate;
import net.shopxx.service.DeliveryTemplateService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class DeliveryTemplateServiceImpl extends BaseServiceImpl
    implements DeliveryTemplateService
{

    public DeliveryTemplateServiceImpl()
    {
    }

    public void setBaseDao(DeliveryTemplateDao deliveryTemplateDao)
    {
        super.setBaseDao(deliveryTemplateDao);
    }

    public DeliveryTemplate findDefault()
    {
        return IIIllIlI.findDefault();
    }

    private DeliveryTemplateDao IIIllIlI;
}
