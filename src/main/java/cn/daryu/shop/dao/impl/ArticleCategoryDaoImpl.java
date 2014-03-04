// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.*;
import javax.persistence.*;
import net.shopxx.dao.ArticleCategoryDao;
import net.shopxx.entity.ArticleCategory;
import org.springframework.util.Assert;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class ArticleCategoryDaoImpl extends BaseDaoImpl
    implements ArticleCategoryDao
{

    public ArticleCategoryDaoImpl()
    {
    }

    public List findRoots(Integer count)
    {
        String s = "select articleCategory from ArticleCategory articleCategory where articleCategory.parent is null order by articleCategory.order asc";
        TypedQuery typedquery = IIIllIlI.createQuery(s, net/shopxx/entity/ArticleCategory).setFlushMode(FlushModeType.COMMIT);
        if(count != null)
            typedquery.setMaxResults(count.intValue());
        return typedquery.getResultList();
    }

    public List findParents(ArticleCategory articleCategory, Integer count)
    {
        if(articleCategory == null || articleCategory.getParent() == null)
            return Collections.emptyList();
        String s = "select articleCategory from ArticleCategory articleCategory where articleCategory.id in (:ids) order by articleCategory.grade asc";
        TypedQuery typedquery = IIIllIlI.createQuery(s, net/shopxx/entity/ArticleCategory).setFlushMode(FlushModeType.COMMIT).setParameter("ids", articleCategory.getTreePaths());
        if(count != null)
            typedquery.setMaxResults(count.intValue());
        return typedquery.getResultList();
    }

    public List findChildren(ArticleCategory articleCategory, Integer count)
    {
        TypedQuery typedquery;
        if(articleCategory != null)
        {
            String s = "select articleCategory from ArticleCategory articleCategory where articleCategory.treePath like :treePath order by articleCategory.order asc";
            typedquery = IIIllIlI.createQuery(s, net/shopxx/entity/ArticleCategory).setFlushMode(FlushModeType.COMMIT).setParameter("treePath", (new StringBuilder("%,")).append(articleCategory.getId()).append(",").append("%").toString());
        } else
        {
            String s1 = "select articleCategory from ArticleCategory articleCategory order by articleCategory.order asc";
            typedquery = IIIllIlI.createQuery(s1, net/shopxx/entity/ArticleCategory).setFlushMode(FlushModeType.COMMIT);
        }
        if(count != null)
            typedquery.setMaxResults(count.intValue());
        return IIIllIlI(typedquery.getResultList(), articleCategory);
    }

    public void persist(ArticleCategory articleCategory)
    {
        Assert.notNull(articleCategory);
        IIIllIlI(articleCategory);
        super.persist(articleCategory);
    }

    public ArticleCategory merge(ArticleCategory articleCategory)
    {
        Assert.notNull(articleCategory);
        IIIllIlI(articleCategory);
        ArticleCategory articlecategory;
        for(Iterator iterator = findChildren(articleCategory, null).iterator(); iterator.hasNext(); IIIllIlI(articlecategory))
            articlecategory = (ArticleCategory)iterator.next();

        return (ArticleCategory)super.merge(articleCategory);
    }

    private List IIIllIlI(List list, ArticleCategory articlecategory)
    {
        ArrayList arraylist = new ArrayList();
        if(list != null)
        {
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                ArticleCategory articlecategory1 = (ArticleCategory)iterator.next();
                if(articlecategory1.getParent() == articlecategory)
                {
                    arraylist.add(articlecategory1);
                    arraylist.addAll(IIIllIlI(list, articlecategory1));
                }
            }

        }
        return arraylist;
    }

    private void IIIllIlI(ArticleCategory articlecategory)
    {
        if(articlecategory == null)
            return;
        ArticleCategory articlecategory1 = articlecategory.getParent();
        if(articlecategory1 != null)
            articlecategory.setTreePath((new StringBuilder(String.valueOf(articlecategory1.getTreePath()))).append(articlecategory1.getId()).append(",").toString());
        else
            articlecategory.setTreePath(",");
        articlecategory.setGrade(Integer.valueOf(articlecategory.getTreePaths().size()));
    }

    public volatile Object merge(Object obj)
    {
        return merge((ArticleCategory)obj);
    }

    public volatile void persist(Object obj)
    {
        persist((ArticleCategory)obj);
    }
}
