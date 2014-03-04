// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.HashSet;
import java.util.Set;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Member

public class Message extends BaseEntity
{

    public Message()
    {
        IIlIIlIl = new HashSet();
    }

    public String getTitle()
    {
        return IIIllIlI;
    }

    public void setTitle(String title)
    {
        IIIllIlI = title;
    }

    public String getContent()
    {
        return IIIllIll;
    }

    public void setContent(String content)
    {
        IIIllIll = content;
    }

    public String getIp()
    {
        return IIIlllII;
    }

    public void setIp(String ip)
    {
        IIIlllII = ip;
    }

    public Boolean getIsDraft()
    {
        return IIIlllIl;
    }

    public void setIsDraft(Boolean isDraft)
    {
        IIIlllIl = isDraft;
    }

    public Boolean getSenderRead()
    {
        return IIIllllI;
    }

    public void setSenderRead(Boolean senderRead)
    {
        IIIllllI = senderRead;
    }

    public Boolean getReceiverRead()
    {
        return IIIlllll;
    }

    public void setReceiverRead(Boolean receiverRead)
    {
        IIIlllll = receiverRead;
    }

    public Boolean getSenderDelete()
    {
        return IIlIIIII;
    }

    public void setSenderDelete(Boolean senderDelete)
    {
        IIlIIIII = senderDelete;
    }

    public Boolean getReceiverDelete()
    {
        return IIlIIIIl;
    }

    public void setReceiverDelete(Boolean receiverDelete)
    {
        IIlIIIIl = receiverDelete;
    }

    public Member getSender()
    {
        return IIlIIIlI;
    }

    public void setSender(Member sender)
    {
        IIlIIIlI = sender;
    }

    public Member getReceiver()
    {
        return IIlIIIll;
    }

    public void setReceiver(Member receiver)
    {
        IIlIIIll = receiver;
    }

    public Message getForMessage()
    {
        return IIlIIlII;
    }

    public void setForMessage(Message forMessage)
    {
        IIlIIlII = forMessage;
    }

    public Set getReplyMessages()
    {
        return IIlIIlIl;
    }

    public void setReplyMessages(Set replyMessages)
    {
        IIlIIlIl = replyMessages;
    }

    private static final long serialVersionUID = 0xba1eddbb5130f25eL;
    private String IIIllIlI;
    private String IIIllIll;
    private String IIIlllII;
    private Boolean IIIlllIl;
    private Boolean IIIllllI;
    private Boolean IIIlllll;
    private Boolean IIlIIIII;
    private Boolean IIlIIIIl;
    private Member IIlIIIlI;
    private Member IIlIIIll;
    private Message IIlIIlII;
    private Set IIlIIlIl;
}
