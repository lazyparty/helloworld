// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import net.shopxx.entity.Cart;
import net.shopxx.entity.Member;

// Referenced classes of package net.shopxx.service:
//            BaseService

public interface CartService
    extends BaseService
{

    public abstract Cart getCurrent();

    public abstract void merge(Member member, Cart cart);

    public abstract void evictExpired();
}
