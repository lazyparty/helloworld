// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.dao.ReturnsDao;
import net.shopxx.service.ReturnsService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class ReturnsServiceImpl extends BaseServiceImpl
    implements ReturnsService
{

    public ReturnsServiceImpl()
    {
    }

    public void setBaseDao(ReturnsDao returnsDao)
    {
        super.setBaseDao(returnsDao);
    }
}
