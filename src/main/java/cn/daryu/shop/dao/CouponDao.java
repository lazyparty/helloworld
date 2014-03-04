// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao;

import net.shopxx.Page;
import net.shopxx.Pageable;

// Referenced classes of package net.shopxx.dao:
//            BaseDao

public interface CouponDao
    extends BaseDao
{

    public abstract Page findPage(Boolean boolean1, Boolean boolean2, Boolean boolean3, Pageable pageable);
}
