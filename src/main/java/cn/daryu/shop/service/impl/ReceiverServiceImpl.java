// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.ReceiverDao;
import net.shopxx.entity.Member;
import net.shopxx.entity.Receiver;
import net.shopxx.service.ReceiverService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class ReceiverServiceImpl extends BaseServiceImpl
    implements ReceiverService
{

    public ReceiverServiceImpl()
    {
    }

    public void setBaseDao(ReceiverDao receiverDao)
    {
        super.setBaseDao(receiverDao);
    }

    public Receiver findDefault(Member member)
    {
        return IIIllIlI.findDefault(member);
    }

    public Page findPage(Member member, Pageable pageable)
    {
        return IIIllIlI.findPage(member, pageable);
    }

    private ReceiverDao IIIllIlI;
}
