// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.dao.SpecificationValueDao;
import net.shopxx.service.SpecificationValueService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class SpecificationValueServiceImpl extends BaseServiceImpl
    implements SpecificationValueService
{

    public SpecificationValueServiceImpl()
    {
    }

    public void setBaseDao(SpecificationValueDao specificationValueDao)
    {
        super.setBaseDao(specificationValueDao);
    }
}
