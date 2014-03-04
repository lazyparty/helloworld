// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.math.BigDecimal;
import net.shopxx.dao.MemberRankDao;
import net.shopxx.entity.MemberRank;
import net.shopxx.service.MemberRankService;
import org.apache.commons.lang.StringUtils;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class MemberRankServiceImpl extends BaseServiceImpl
    implements MemberRankService
{

    public MemberRankServiceImpl()
    {
    }

    public void setBaseDao(MemberRankDao memberRankDao)
    {
        super.setBaseDao(memberRankDao);
    }

    public boolean nameExists(String name)
    {
        return IIIllIlI.nameExists(name);
    }

    public boolean nameUnique(String previousName, String currentName)
    {
        if(StringUtils.equalsIgnoreCase(previousName, currentName))
            return true;
        return !IIIllIlI.nameExists(currentName);
    }

    public boolean amountExists(BigDecimal amount)
    {
        return IIIllIlI.amountExists(amount);
    }

    public boolean amountUnique(BigDecimal previousAmount, BigDecimal currentAmount)
    {
        if(previousAmount != null && previousAmount.compareTo(currentAmount) == 0)
            return true;
        return !IIIllIlI.amountExists(currentAmount);
    }

    public MemberRank findDefault()
    {
        return IIIllIlI.findDefault();
    }

    public MemberRank findByAmount(BigDecimal amount)
    {
        return IIIllIlI.findByAmount(amount);
    }

    private MemberRankDao IIIllIlI;
}
