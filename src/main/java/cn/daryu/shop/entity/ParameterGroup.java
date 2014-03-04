// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package net.shopxx.entity:
//            OrderEntity, ProductCategory

public class ParameterGroup extends OrderEntity
{

    public ParameterGroup()
    {
        IIIlllII = new ArrayList();
    }

    public String getName()
    {
        return IIIllIlI;
    }

    public void setName(String name)
    {
        IIIllIlI = name;
    }

    public ProductCategory getProductCategory()
    {
        return IIIllIll;
    }

    public void setProductCategory(ProductCategory productCategory)
    {
        IIIllIll = productCategory;
    }

    public List getParameters()
    {
        return IIIlllII;
    }

    public void setParameters(List parameters)
    {
        IIIlllII = parameters;
    }

    private static final long serialVersionUID = 0x2aa222ac35083c5L;
    private String IIIllIlI;
    private ProductCategory IIIllIll;
    private List IIIlllII;
}
