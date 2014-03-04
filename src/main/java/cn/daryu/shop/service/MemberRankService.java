// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.math.BigDecimal;
import net.shopxx.entity.MemberRank;

// Referenced classes of package net.shopxx.service:
//            BaseService

public interface MemberRankService
    extends BaseService
{

    public abstract boolean nameExists(String s);

    public abstract boolean nameUnique(String s, String s1);

    public abstract boolean amountExists(BigDecimal bigdecimal);

    public abstract boolean amountUnique(BigDecimal bigdecimal, BigDecimal bigdecimal1);

    public abstract MemberRank findDefault();

    public abstract MemberRank findByAmount(BigDecimal bigdecimal);
}
