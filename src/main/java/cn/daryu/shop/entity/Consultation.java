// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.HashSet;
import java.util.Set;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Product, Member

public class Consultation extends BaseEntity
{

    public Consultation()
    {
        IIlIIIlI = new HashSet();
    }

    public String getContent()
    {
        return IIIlllII;
    }

    public void setContent(String content)
    {
        IIIlllII = content;
    }

    public Boolean getIsShow()
    {
        return IIIlllIl;
    }

    public void setIsShow(Boolean isShow)
    {
        IIIlllIl = isShow;
    }

    public String getIp()
    {
        return IIIllllI;
    }

    public void setIp(String ip)
    {
        IIIllllI = ip;
    }

    public Member getMember()
    {
        return IIIlllll;
    }

    public void setMember(Member member)
    {
        IIIlllll = member;
    }

    public Product getProduct()
    {
        return IIlIIIII;
    }

    public void setProduct(Product product)
    {
        IIlIIIII = product;
    }

    public Consultation getForConsultation()
    {
        return IIlIIIIl;
    }

    public void setForConsultation(Consultation forConsultation)
    {
        IIlIIIIl = forConsultation;
    }

    public Set getReplyConsultations()
    {
        return IIlIIIlI;
    }

    public void setReplyConsultations(Set replyConsultations)
    {
        IIlIIIlI = replyConsultations;
    }

    public String getPath()
    {
        if(getProduct() != null && getProduct().getId() != null)
            return (new StringBuilder("/consultation/content/")).append(getProduct().getId()).append(".jhtml").toString();
        else
            return null;
    }

    private static final long serialVersionUID = 0xc92da6eb34773767L;
    private static final String IIIllIlI = "/consultation/content";
    private static final String IIIllIll = ".jhtml";
    private String IIIlllII;
    private Boolean IIIlllIl;
    private String IIIllllI;
    private Member IIIlllll;
    private Product IIlIIIII;
    private Consultation IIlIIIIl;
    private Set IIlIIIlI;
}
