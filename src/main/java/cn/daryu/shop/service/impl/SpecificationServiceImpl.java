// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.dao.SpecificationDao;
import net.shopxx.service.SpecificationService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class SpecificationServiceImpl extends BaseServiceImpl
    implements SpecificationService
{

    public SpecificationServiceImpl()
    {
    }

    public void setBaseDao(SpecificationDao specificationDao)
    {
        super.setBaseDao(specificationDao);
    }
}
