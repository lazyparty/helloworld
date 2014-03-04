// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;


// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Product, Member

public class Review extends BaseEntity
{

    public Review()
    {
    }

    public Integer getScore()
    {
        return IIIlllII;
    }

    public void setScore(Integer score)
    {
        IIIlllII = score;
    }

    public String getContent()
    {
        return IIIlllIl;
    }

    public void setContent(String content)
    {
        IIIlllIl = content;
    }

    public Boolean getIsShow()
    {
        return IIIllllI;
    }

    public void setIsShow(Boolean isShow)
    {
        IIIllllI = isShow;
    }

    public String getIp()
    {
        return IIIlllll;
    }

    public void setIp(String ip)
    {
        IIIlllll = ip;
    }

    public Member getMember()
    {
        return IIlIIIII;
    }

    public void setMember(Member member)
    {
        IIlIIIII = member;
    }

    public Product getProduct()
    {
        return IIlIIIIl;
    }

    public void setProduct(Product product)
    {
        IIlIIIIl = product;
    }

    public String getPath()
    {
        if(getProduct() != null && getProduct().getId() != null)
            return (new StringBuilder("/review/content/")).append(getProduct().getId()).append(".jhtml").toString();
        else
            return null;
    }

    private static final long serialVersionUID = 0x7a1151d440a24c24L;
    private static final String IIIllIlI = "/review/content";
    private static final String IIIllIll = ".jhtml";
    private Integer IIIlllII;
    private String IIIlllIl;
    private Boolean IIIllllI;
    private String IIIlllll;
    private Member IIlIIIII;
    private Product IIlIIIIl;
}
