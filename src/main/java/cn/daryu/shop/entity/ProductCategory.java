// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.*;
import org.apache.commons.lang.StringUtils;

// Referenced classes of package net.shopxx.entity:
//            OrderEntity, Promotion

public class ProductCategory extends OrderEntity
{

    public ProductCategory()
    {
        IIlIIIll = new HashSet();
        IIlIIlII = new HashSet();
        IIlIIlIl = new HashSet();
        IIlIIllI = new HashSet();
        IIlIIlll = new HashSet();
        IIlIlIII = new HashSet();
    }

    public String getName()
    {
        return IIIlllII;
    }

    public void setName(String name)
    {
        IIIlllII = name;
    }

    public String getSeoTitle()
    {
        return IIIlllIl;
    }

    public void setSeoTitle(String seoTitle)
    {
        IIIlllIl = seoTitle;
    }

    public String getSeoKeywords()
    {
        return IIIllllI;
    }

    public void setSeoKeywords(String seoKeywords)
    {
        IIIllllI = seoKeywords;
    }

    public String getSeoDescription()
    {
        return IIIlllll;
    }

    public void setSeoDescription(String seoDescription)
    {
        IIIlllll = seoDescription;
    }

    public String getTreePath()
    {
        return IIlIIIII;
    }

    public void setTreePath(String treePath)
    {
        IIlIIIII = treePath;
    }

    public Integer getGrade()
    {
        return IIlIIIIl;
    }

    public void setGrade(Integer grade)
    {
        IIlIIIIl = grade;
    }

    public ProductCategory getParent()
    {
        return IIlIIIlI;
    }

    public void setParent(ProductCategory parent)
    {
        IIlIIIlI = parent;
    }

    public Set getChildren()
    {
        return IIlIIIll;
    }

    public void setChildren(Set children)
    {
        IIlIIIll = children;
    }

    public Set getProducts()
    {
        return IIlIIlII;
    }

    public void setProducts(Set products)
    {
        IIlIIlII = products;
    }

    public Set getBrands()
    {
        return IIlIIlIl;
    }

    public void setBrands(Set brands)
    {
        IIlIIlIl = brands;
    }

    public Set getParameterGroups()
    {
        return IIlIIllI;
    }

    public void setParameterGroups(Set parameterGroups)
    {
        IIlIIllI = parameterGroups;
    }

    public Set getAttributes()
    {
        return IIlIIlll;
    }

    public void setAttributes(Set attributes)
    {
        IIlIIlll = attributes;
    }

    public Set getPromotions()
    {
        return IIlIlIII;
    }

    public void setPromotions(Set promotions)
    {
        IIlIlIII = promotions;
    }

    public List getTreePaths()
    {
        ArrayList arraylist = new ArrayList();
        String as[] = StringUtils.split(getTreePath(), ",");
        if(as != null)
        {
            String as1[];
            int j = (as1 = as).length;
            for(int i = 0; i < j; i++)
            {
                String s = as1[i];
                arraylist.add(Long.valueOf(s));
            }

        }
        return arraylist;
    }

    public String getPath()
    {
        if(getId() != null)
            return (new StringBuilder("/product/list/")).append(getId()).append(".jhtml").toString();
        else
            return null;
    }

    public void preRemove()
    {
        Set set = getPromotions();
        if(set != null)
        {
            Promotion promotion;
            for(Iterator iterator = set.iterator(); iterator.hasNext(); promotion.getProductCategories().remove(this))
                promotion = (Promotion)iterator.next();

        }
    }

    private static final long serialVersionUID = 0x46b6edbfffd1fafdL;
    public static final String TREE_PATH_SEPARATOR = ",";
    private static final String IIIllIlI = "/product/list";
    private static final String IIIllIll = ".jhtml";
    private String IIIlllII;
    private String IIIlllIl;
    private String IIIllllI;
    private String IIIlllll;
    private String IIlIIIII;
    private Integer IIlIIIIl;
    private ProductCategory IIlIIIlI;
    private Set IIlIIIll;
    private Set IIlIIlII;
    private Set IIlIIlIl;
    private Set IIlIIllI;
    private Set IIlIIlll;
    private Set IIlIlIII;
}
