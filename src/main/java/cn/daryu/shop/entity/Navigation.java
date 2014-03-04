// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;


// Referenced classes of package net.shopxx.entity:
//            OrderEntity

public class Navigation extends OrderEntity
{

    public Navigation()
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

    public Position getPosition()
    {
        return IIIllIll;
    }

    public void setPosition(Position position)
    {
        IIIllIll = position;
    }

    public String getUrl()
    {
        return IIIlllII;
    }

    public void setUrl(String url)
    {
        IIIlllII = url;
    }

    public Boolean getIsBlankTarget()
    {
        return IIIlllIl;
    }

    public void setIsBlankTarget(Boolean isBlankTarget)
    {
        IIIlllIl = isBlankTarget;
    }

    private static final long serialVersionUID = 0x960856e00eaf3fb5L;
    private String IIIllIlI;
    private Position IIIllIll;
    private String IIIlllII;
    private Boolean IIIlllIl;
}
