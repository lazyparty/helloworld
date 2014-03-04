// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.MessageDao;
import net.shopxx.entity.Member;
import net.shopxx.service.MessageService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class MessageServiceImpl extends BaseServiceImpl
    implements MessageService
{

    public MessageServiceImpl()
    {
    }

    public void setBaseDao(MessageDao messageDao)
    {
        super.setBaseDao(messageDao);
    }

    public Page findPage(Member member, Pageable pageable)
    {
        return IIIllIlI.findPage(member, pageable);
    }

    public Page findDraftPage(Member sender, Pageable pageable)
    {
        return IIIllIlI.findDraftPage(sender, pageable);
    }

    public Long count(Member member, Boolean read)
    {
        return IIIllIlI.count(member, read);
    }

    public void delete(Long id, Member member)
    {
        IIIllIlI.remove(id, member);
    }

    private MessageDao IIIllIlI;
}
