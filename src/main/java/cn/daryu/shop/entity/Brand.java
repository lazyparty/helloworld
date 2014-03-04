// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.*;

// Referenced classes of package net.shopxx.entity:
//            OrderEntity, Product, ProductCategory, Promotion

public class Brand extends OrderEntity
{

    public Brand()
    {
        IIlIIIIl = new HashSet();
        IIlIIIlI = new HashSet();
        IIlIIIll = new HashSet();
    }

    public String getName()
    {
        return IIIlllII;
    }

    public void setName(String name)
    {
        IIIlllII = name;
    }

    public Type getType()
    {
        return IIIlllIl;
    }

    public void setType(Type type)
    {
        IIIlllIl = type;
    }

    public String getLogo()
    {
        return IIIllllI;
    }

    public void setLogo(String logo)
    {
        IIIllllI = logo;
    }

    public String getUrl()
    {
        return IIIlllll;
    }

    public void setUrl(String url)
    {
        IIIlllll = url;
    }

    public String getIntroduction()
    {
        return IIlIIIII;
    }

    public void setIntroduction(String introduction)
    {
        IIlIIIII = introduction;
    }

    public Set getProducts()
    {
        return IIlIIIIl;
    }

    public void setProducts(Set products)
    {
        IIlIIIIl = products;
    }

    public Set getProductCategories()
    {
        return IIlIIIlI;
    }

    public void setProductCategories(Set productCategories)
    {
        IIlIIIlI = productCategories;
    }

    public Set getPromotions()
    {
        return IIlIIIll;
    }

    public void setPromotions(Set promotions)
    {
        IIlIIIll = promotions;
    }

    public String getPath()
    {
        if(getId() != null)
            return (new StringBuilder("/brand/content/")).append(getId()).append(".jhtml").toString();
        else
            return null;
    }

    public void preRemove()
    {
        Set set = getProducts();
        if(set != null)
        {
            Product product;
            for(Iterator iterator = set.iterator(); iterator.hasNext(); product.setBrand(null))
                product = (Product)iterator.next();

        }
        Set set1 = getProductCategories();
        if(set1 != null)
        {
            ProductCategory productcategory;
            for(Iterator iterator1 = set1.iterator(); iterator1.hasNext(); productcategory.getBrands().remove(this))
                productcategory = (ProductCategory)iterator1.next();

        }
        Set set2 = getPromotions();
        if(set2 != null)
        {
            Promotion promotion;
            for(Iterator iterator2 = set2.iterator(); iterator2.hasNext(); promotion.getBrands().remove(this))
                promotion = (Promotion)iterator2.next();

        }
    }

    private static final long serialVersionUID = 0xab365fb3af489b91L;
    private static final String IIIllIlI = "/brand/content";
    private static final String IIIllIll = ".jhtml";
    private String IIIlllII;
    private Type IIIlllIl;
    private String IIIllllI;
    private String IIIlllll;
    private String IIlIIIII;
    private Set IIlIIIIl;
    private Set IIlIIIlI;
    private Set IIlIIIll;
}
