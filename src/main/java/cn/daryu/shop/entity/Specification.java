// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.*;

// Referenced classes of package net.shopxx.entity:
//            OrderEntity

public class Specification extends OrderEntity
{

    public Specification()
    {
        IIIlllIl = new ArrayList();
        IIIllllI = new HashSet();
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

    public String getMemo()
    {
        return IIIlllII;
    }

    public void setMemo(String memo)
    {
        IIIlllII = memo;
    }

    public List getSpecificationValues()
    {
        return IIIlllIl;
    }

    public void setSpecificationValues(List specificationValues)
    {
        IIIlllIl = specificationValues;
    }

    public Set getProducts()
    {
        return IIIllllI;
    }

    public void setProducts(Set products)
    {
        IIIllllI = products;
    }

    private static final long serialVersionUID = 0xa7ebb9b753e180c2L;
    private String IIIllIlI;
    private Type IIIllIll;
    private String IIIlllII;
    private List IIIlllIl;
    private Set IIIllllI;
}
