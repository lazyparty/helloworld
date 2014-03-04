// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;


// Referenced classes of package net.shopxx.entity:
//            OrderEntity

public class FriendLink extends OrderEntity
{

    public FriendLink()
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

    public Type getType()
    {
        return IIIllIll;
    }

    public void setType(Type type)
    {
        IIIllIll = type;
    }

    public String getLogo()
    {
        return IIIlllII;
    }

    public void setLogo(String logo)
    {
        IIIlllII = logo;
    }

    public String getUrl()
    {
        return IIIlllIl;
    }

    public void setUrl(String url)
    {
        IIIlllIl = url;
    }

    private static final long serialVersionUID = 0x29e7ece844786cfcL;
    private String IIIllIlI;
    private Type IIIllIll;
    private String IIIlllII;
    private String IIIlllIl;
}
