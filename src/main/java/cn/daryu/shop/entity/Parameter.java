// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;


// Referenced classes of package net.shopxx.entity:
//            OrderEntity, ParameterGroup

public class Parameter extends OrderEntity
{

    public Parameter()
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

    public ParameterGroup getParameterGroup()
    {
        return IIIllIll;
    }

    public void setParameterGroup(ParameterGroup parameterGroup)
    {
        IIIllIll = parameterGroup;
    }

    private static final long serialVersionUID = 0xaf0b00bb9a377206L;
    private String IIIllIlI;
    private ParameterGroup IIIllIll;
}
