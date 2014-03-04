// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.dao.OrderLogDao;
import net.shopxx.service.OrderLogService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class OrderLogServiceImpl extends BaseServiceImpl
    implements OrderLogService
{

    public OrderLogServiceImpl()
    {
    }

    public void setBaseDao(OrderLogDao orderLogDao)
    {
        super.setBaseDao(orderLogDao);
    }
}
