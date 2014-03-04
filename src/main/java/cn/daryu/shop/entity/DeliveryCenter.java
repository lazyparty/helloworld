// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;


// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Area

public class DeliveryCenter extends BaseEntity
{

    public DeliveryCenter()
    {
    }

    public String getName()
    {
        return IIIllIlI;
    }

    public void setName(String name)
    {
        IIIllIlI = name;
    }

    public String getContact()
    {
        return IIIllIll;
    }

    public void setContact(String contact)
    {
        IIIllIll = contact;
    }

    public String getAreaName()
    {
        return IIIlllII;
    }

    public void setAreaName(String areaName)
    {
        IIIlllII = areaName;
    }

    public String getAddress()
    {
        return IIIlllIl;
    }

    public void setAddress(String address)
    {
        IIIlllIl = address;
    }

    public String getZipCode()
    {
        return IIIllllI;
    }

    public void setZipCode(String zipCode)
    {
        IIIllllI = zipCode;
    }

    public String getPhone()
    {
        return IIIlllll;
    }

    public void setPhone(String phone)
    {
        IIIlllll = phone;
    }

    public String getMobile()
    {
        return IIlIIIII;
    }

    public void setMobile(String mobile)
    {
        IIlIIIII = mobile;
    }

    public String getMemo()
    {
        return IIlIIIIl;
    }

    public void setMemo(String memo)
    {
        IIlIIIIl = memo;
    }

    public Boolean getIsDefault()
    {
        return IIlIIIlI;
    }

    public void setIsDefault(Boolean isDefault)
    {
        IIlIIIlI = isDefault;
    }

    public Area getArea()
    {
        return IIlIIIll;
    }

    public void setArea(Area area)
    {
        IIlIIIll = area;
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

    private static final long serialVersionUID = 0x2e32f855be4ffae3L;
    private String IIIllIlI;
    private String IIIllIll;
    private String IIIlllII;
    private String IIIlllIl;
    private String IIIllllI;
    private String IIIlllll;
    private String IIlIIIII;
    private String IIlIIIIl;
    private Boolean IIlIIIlI;
    private Area IIlIIIll;
}
