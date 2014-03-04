// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package net.shopxx.entity:
//            OrderEntity, ProductCategory

public class Attribute extends OrderEntity
{

    public Attribute()
    {
        IIIlllIl = new ArrayList();
    }

    public String getName()
    {
        return IIIllIlI;
    }

    public void setName(String name)
    {
        IIIllIlI = name;
    }

    public Integer getPropertyIndex()
    {
        return IIIllIll;
    }

    public void setPropertyIndex(Integer propertyIndex)
    {
        IIIllIll = propertyIndex;
    }

    public ProductCategory getProductCategory()
    {
        return IIIlllII;
    }

    public void setProductCategory(ProductCategory productCategory)
    {
        IIIlllII = productCategory;
    }

    public List getOptions()
    {
        return IIIlllIl;
    }

    public void setOptions(List options)
    {
        IIIlllIl = options;
    }

    private static final long serialVersionUID = 0x21f84fcb1491efafL;
    private String IIIllIlI;
    private Integer IIIllIll;
    private ProductCategory IIIlllII;
    private List IIIlllIl;
}
