// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.*;

// Referenced classes of package net.shopxx.entity:
//            OrderEntity, Article, Product

public class Tag extends OrderEntity
{

    public Tag()
    {
        IIIllllI = new HashSet();
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

    public Type getType()
    {
        return IIIllIll;
    }

    public void setType(Type type)
    {
        IIIllIll = type;
    }

    public String getIcon()
    {
        return IIIlllII;
    }

    public void setIcon(String icon)
    {
        IIIlllII = icon;
    }

    public String getMemo()
    {
        return IIIlllIl;
    }

    public void setMemo(String memo)
    {
        IIIlllIl = memo;
    }

    public Set getArticles()
    {
        return IIIllllI;
    }

    public void setArticles(Set articles)
    {
        IIIllllI = articles;
    }

    public Set getProducts()
    {
        return IIIlllll;
    }

    public void setProducts(Set products)
    {
        IIIlllll = products;
    }

    public void preRemove()
    {
        Set set = getArticles();
        if(set != null)
        {
            Article article;
            for(Iterator iterator = set.iterator(); iterator.hasNext(); article.getTags().remove(this))
                article = (Article)iterator.next();

        }
        Set set1 = getProducts();
        if(set1 != null)
        {
            Product product;
            for(Iterator iterator1 = set1.iterator(); iterator1.hasNext(); product.getTags().remove(this))
                product = (Product)iterator1.next();

        }
    }

    private static final long serialVersionUID = 0xda0b3175d1c3a39bL;
    private String IIIllIlI;
    private Type IIIllIll;
    private String IIIlllII;
    private String IIIlllIl;
    private Set IIIllllI;
    private Set IIIlllll;
}
