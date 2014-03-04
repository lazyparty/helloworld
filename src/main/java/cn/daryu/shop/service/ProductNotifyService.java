// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.entity.Member;
import net.shopxx.entity.Product;

// Referenced classes of package net.shopxx.service:
//            BaseService

public interface ProductNotifyService
    extends BaseService
{

    public abstract boolean exists(Product product, String s);

    public abstract Page findPage(Member member, Boolean boolean1, Boolean boolean2, Boolean boolean3, Pageable pageable);

    public abstract Long count(Member member, Boolean boolean1, Boolean boolean2, Boolean boolean3);

    public abstract int send(Long along[]);
}
