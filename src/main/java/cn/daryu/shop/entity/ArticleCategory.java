// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.*;
import org.apache.commons.lang.StringUtils;

// Referenced classes of package net.shopxx.entity:
//            OrderEntity

public class ArticleCategory extends OrderEntity
{

    public ArticleCategory()
    {
        IIlIIIll = new HashSet();
        IIlIIlII = new HashSet();
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

    public ArticleCategory getParent()
    {
        return IIlIIIlI;
    }

    public void setParent(ArticleCategory parent)
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

    public Set getArticles()
    {
        return IIlIIlII;
    }

    public void setArticles(Set articles)
    {
        IIlIIlII = articles;
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
            return (new StringBuilder("/article/list/")).append(getId()).append(".jhtml").toString();
        else
            return null;
    }

    private static final long serialVersionUID = 0xb8c5281a3d90d86aL;
    public static final String TREE_PATH_SEPARATOR = ",";
    private static final String IIIllIlI = "/article/list";
    private static final String IIIllIll = ".jhtml";
    private String IIIlllII;
    private String IIIlllIl;
    private String IIIllllI;
    private String IIIlllll;
    private String IIlIIIII;
    private Integer IIlIIIIl;
    private ArticleCategory IIlIIIlI;
    private Set IIlIIIll;
    private Set IIlIIlII;
}
