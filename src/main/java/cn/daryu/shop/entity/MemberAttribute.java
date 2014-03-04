// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package net.shopxx.entity:
//            OrderEntity

public class MemberAttribute extends OrderEntity
{

    public MemberAttribute()
    {
        IIIlllll = new ArrayList();
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

    public Boolean getIsEnabled()
    {
        return IIIlllII;
    }

    public void setIsEnabled(Boolean isEnabled)
    {
        IIIlllII = isEnabled;
    }

    public Boolean getIsRequired()
    {
        return IIIlllIl;
    }

    public void setIsRequired(Boolean isRequired)
    {
        IIIlllIl = isRequired;
    }

    public Integer getPropertyIndex()
    {
        return IIIllllI;
    }

    public void setPropertyIndex(Integer propertyIndex)
    {
        IIIllllI = propertyIndex;
    }

    public List getOptions()
    {
        return IIIlllll;
    }

    public void setOptions(List options)
    {
        IIIlllll = options;
    }

    private static final long serialVersionUID = 0x3ea3e708cebedb98L;
    private String IIIllIlI;
    private Type IIIllIll;
    private Boolean IIIlllII;
    private Boolean IIIlllIl;
    private Integer IIIllllI;
    private List IIIlllll;
}
