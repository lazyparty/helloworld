// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.dao.RefundsDao;
import net.shopxx.service.RefundsService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class RefundsServiceImpl extends BaseServiceImpl
    implements RefundsService
{

    public RefundsServiceImpl()
    {
    }

    public void setBaseDao(RefundsDao refundsDao)
    {
        super.setBaseDao(refundsDao);
    }
}
