// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao;

import java.util.List;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.entity.*;

// Referenced classes of package net.shopxx.dao:
//            BaseDao

public interface CouponCodeDao
    extends BaseDao
{

    public abstract boolean codeExists(String s);

    public abstract CouponCode findByCode(String s);

    public abstract CouponCode build(Coupon coupon, Member member);

    public abstract List build(Coupon coupon, Member member, Integer integer);

    public abstract Page findPage(Member member, Pageable pageable);

    public abstract Long count(Coupon coupon, Member member, Boolean boolean1, Boolean boolean2, Boolean boolean3);
}
