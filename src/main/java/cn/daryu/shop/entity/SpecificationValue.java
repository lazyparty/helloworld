// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.HashSet;
import java.util.Set;

// Referenced classes of package net.shopxx.entity:
//            OrderEntity, Specification

public class SpecificationValue extends OrderEntity
{

    public SpecificationValue()
    {
        IIIlllIl = new HashSet();
    }

    public String getName()
    {
        return IIIllIlI;
    }

    public void setName(String name)
    {
        IIIllIlI = name;
    }

    public String getImage()
    {
        return IIIllIll;
    }

    public void setImage(String image)
    {
        IIIllIll = image;
    }

    public Specification getSpecification()
    {
        return IIIlllII;
    }

    public void setSpecification(Specification specification)
    {
        IIIlllII = specification;
    }

    public Set getProducts()
    {
        return IIIlllIl;
    }

    public void setProducts(Set products)
    {
        IIIlllIl = products;
    }

    private static final long serialVersionUID = 0x884ec3bd8f807ca0L;
    private String IIIllIlI;
    private String IIIllIll;
    private Specification IIIlllII;
    private Set IIIlllIl;
}
