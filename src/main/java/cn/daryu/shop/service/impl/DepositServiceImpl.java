// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.DepositDao;
import net.shopxx.entity.Member;
import net.shopxx.service.DepositService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class DepositServiceImpl extends BaseServiceImpl
    implements DepositService
{

    public DepositServiceImpl()
    {
    }

    public void setBaseDao(DepositDao depositDao)
    {
        super.setBaseDao(depositDao);
    }

    public Page findPage(Member member, Pageable pageable)
    {
        return IIIllIlI.findPage(member, pageable);
    }

    private DepositDao IIIllIlI;
}
