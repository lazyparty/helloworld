// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao;

import java.math.BigDecimal;
import net.shopxx.entity.MemberRank;

// Referenced classes of package net.shopxx.dao:
//            BaseDao

public interface MemberRankDao
    extends BaseDao
{

    public abstract boolean nameExists(String s);

    public abstract boolean amountExists(BigDecimal bigdecimal);

    public abstract MemberRank findDefault();

    public abstract MemberRank findByAmount(BigDecimal bigdecimal);
}
