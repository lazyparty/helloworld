// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;


// Referenced classes of package net.shopxx.entity:
//            BaseEntity

public class Sn extends BaseEntity
{

    public Sn()
    {
    }

    public Type getType()
    {
        return IIIllIlI;
    }

    public void setType(Type type)
    {
        IIIllIlI = type;
    }

    public Long getLastValue()
    {
        return IIIllIll;
    }

    public void setLastValue(Long lastValue)
    {
        IIIllIll = lastValue;
    }

    private static final long serialVersionUID = 0xdfa80d55d6e222ccL;
    private Type IIIllIlI;
    private Long IIIllIll;
}
