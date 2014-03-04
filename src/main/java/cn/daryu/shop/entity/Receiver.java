// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;


// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Area, Member

public class Receiver extends BaseEntity
{

    public Receiver()
    {
    }

    public String getConsignee()
    {
        return IIIllIlI;
    }

    public void setConsignee(String consignee)
    {
        IIIllIlI = consignee;
    }

    public String getAreaName()
    {
        return IIIllIll;
    }

    public void setAreaName(String areaName)
    {
        IIIllIll = areaName;
    }

    public String getAddress()
    {
        return IIIlllII;
    }

    public void setAddress(String address)
    {
        IIIlllII = address;
    }

    public String getZipCode()
    {
        return IIIlllIl;
    }

    public void setZipCode(String zipCode)
    {
        IIIlllIl = zipCode;
    }

    public String getPhone()
    {
        return IIIllllI;
    }

    public void setPhone(String phone)
    {
        IIIllllI = phone;
    }

    public Boolean getIsDefault()
    {
        return IIIlllll;
    }

    public void setIsDefault(Boolean isDefault)
    {
        IIIlllll = isDefault;
    }

    public Area getArea()
    {
        return IIlIIIII;
    }

    public void setArea(Area area)
    {
        IIlIIIII = area;
    }

    public Member getMember()
    {
        return IIlIIIIl;
    }

    public void setMember(Member member)
    {
        IIlIIIIl = member;
    }

    public void prePersist()
    {
        if(getArea() != null)
            setAreaName(getArea().getFullName());
    }

    public void preUpdate()
    {
        if(getArea() != null)
            setAreaName(getArea().getFullName());
    }

    private static final long serialVersionUID = 0x251a8aea240da8b8L;
    public static final Integer MAX_RECEIVER_COUNT = Integer.valueOf(8);
    private String IIIllIlI;
    private String IIIllIll;
    private String IIIlllII;
    private String IIIlllIl;
    private String IIIllllI;
    private Boolean IIIlllll;
    private Area IIlIIIII;
    private Member IIlIIIIl;

}
