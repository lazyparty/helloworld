// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.*;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Product

public class Goods extends BaseEntity
{

    public Goods()
    {
        IIIllIlI = new HashSet();
    }

    public Set getProducts()
    {
        return IIIllIlI;
    }

    public void setProducts(Set products)
    {
        IIIllIlI = products;
    }

    public Set getSpecificationValues()
    {
        HashSet hashset = new HashSet();
        if(getProducts() != null)
        {
            Product product;
            for(Iterator iterator = getProducts().iterator(); iterator.hasNext(); hashset.addAll(product.getSpecificationValues()))
                product = (Product)iterator.next();

        }
        return hashset;
    }

    private static final long serialVersionUID = 0x9f2ca037756b8e5dL;
    private Set IIIllIlI;
}
