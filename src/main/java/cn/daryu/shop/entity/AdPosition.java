// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.HashSet;
import java.util.Set;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity

public class AdPosition extends BaseEntity
{

    public AdPosition()
    {
        IIIlllll = new HashSet();
    }

    public String getName()
    {
        return IIIllIlI;
    }

    public void setName(String name)
    {
        IIIllIlI = name;
    }

    public Integer getWidth()
    {
        return IIIllIll;
    }

    public void setWidth(Integer width)
    {
        IIIllIll = width;
    }

    public Integer getHeight()
    {
        return IIIlllII;
    }

    public void setHeight(Integer height)
    {
        IIIlllII = height;
    }

    public String getDescription()
    {
        return IIIlllIl;
    }

    public void setDescription(String description)
    {
        IIIlllIl = description;
    }

    public String getTemplate()
    {
        return IIIllllI;
    }

    public void setTemplate(String template)
    {
        IIIllllI = template;
    }

    public Set getAds()
    {
        return IIIlllll;
    }

    public void setAds(Set ads)
    {
        IIIlllll = ads;
    }

    private static final long serialVersionUID = 0x930fbc0bb42992e6L;
    private String IIIllIlI;
    private Integer IIIllIll;
    private Integer IIIlllII;
    private String IIIlllIl;
    private String IIIllllI;
    private Set IIIlllll;
}
