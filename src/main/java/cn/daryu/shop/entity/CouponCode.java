// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.Date;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Order, Coupon, Member

public class CouponCode extends BaseEntity
{

    public CouponCode()
    {
    }

    public String getCode()
    {
        return IIIllIlI;
    }

    public void setCode(String code)
    {
        IIIllIlI = code;
    }

    public Boolean getIsUsed()
    {
        return IIIllIll;
    }

    public void setIsUsed(Boolean isUsed)
    {
        IIIllIll = isUsed;
    }

    public Date getUsedDate()
    {
        return IIIlllII;
    }

    public void setUsedDate(Date usedDate)
    {
        IIIlllII = usedDate;
    }

    public Coupon getCoupon()
    {
        return IIIlllIl;
    }

    public void setCoupon(Coupon coupon)
    {
        IIIlllIl = coupon;
    }

    public Member getMember()
    {
        return IIIllllI;
    }

    public void setMember(Member member)
    {
        IIIllllI = member;
    }

    public Order getOrder()
    {
        return IIIlllll;
    }

    public void setOrder(Order order)
    {
        IIIlllll = order;
    }

    public void preRemove()
    {
        if(getOrder() != null)
            getOrder().setCouponCode(null);
    }

    private static final long serialVersionUID = 0xe6d760ab0cb787e1L;
    private String IIIllIlI;
    private Boolean IIIllIll;
    private Date IIIlllII;
    private Coupon IIIlllIl;
    private Member IIIllllI;
    private Order IIIlllll;
}
