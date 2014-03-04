// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;


// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Member, Product

public class ProductNotify extends BaseEntity
{

    public ProductNotify()
    {
    }

    public String getEmail()
    {
        return IIIllIlI;
    }

    public void setEmail(String email)
    {
        IIIllIlI = email;
    }

    public Boolean getHasSent()
    {
        return IIIllIll;
    }

    public void setHasSent(Boolean hasSent)
    {
        IIIllIll = hasSent;
    }

    public Member getMember()
    {
        return IIIlllII;
    }

    public void setMember(Member member)
    {
        IIIlllII = member;
    }

    public Product getProduct()
    {
        return IIIlllIl;
    }

    public void setProduct(Product product)
    {
        IIIlllIl = product;
    }

    private static final long serialVersionUID = 0x2c4f795572654c8dL;
    private String IIIllIlI;
    private Boolean IIIllIll;
    private Member IIIlllII;
    private Product IIIlllIl;
}
