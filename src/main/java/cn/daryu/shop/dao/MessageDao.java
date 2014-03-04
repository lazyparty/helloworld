// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao;

import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.entity.Member;

// Referenced classes of package net.shopxx.dao:
//            BaseDao

public interface MessageDao
    extends BaseDao
{

    public abstract Page findPage(Member member, Pageable pageable);

    public abstract Page findDraftPage(Member member, Pageable pageable);

    public abstract Long count(Member member, Boolean boolean1);

    public abstract void remove(Long long1, Member member);
}
