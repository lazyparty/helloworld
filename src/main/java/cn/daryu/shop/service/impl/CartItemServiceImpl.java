// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.dao.CartItemDao;
import net.shopxx.service.CartItemService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class CartItemServiceImpl extends BaseServiceImpl
    implements CartItemService
{

    public CartItemServiceImpl()
    {
    }

    public void setBaseDao(CartItemDao cartItemDao)
    {
        super.setBaseDao(cartItemDao);
    }
}
