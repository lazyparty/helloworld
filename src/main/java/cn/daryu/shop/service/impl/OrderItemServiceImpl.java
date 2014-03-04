// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.dao.OrderItemDao;
import net.shopxx.service.OrderItemService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class OrderItemServiceImpl extends BaseServiceImpl
    implements OrderItemService
{

    public OrderItemServiceImpl()
    {
    }

    public void setBaseDao(OrderItemDao orderItemDao)
    {
        super.setBaseDao(orderItemDao);
    }
}
