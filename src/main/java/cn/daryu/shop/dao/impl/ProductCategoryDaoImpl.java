// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.*;
import javax.persistence.*;
import net.shopxx.dao.ProductCategoryDao;
import net.shopxx.entity.ProductCategory;
import org.springframework.util.Assert;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class ProductCategoryDaoImpl extends BaseDaoImpl
    implements ProductCategoryDao
{

    public ProductCategoryDaoImpl()
    {
    }

    public List findRoots(Integer count)
    {
        String s = "select productCategory from ProductCategory productCategory where productCategory.parent is null order by productCategory.order asc";
        TypedQuery typedquery = IIIllIlI.createQuery(s, net/shopxx/entity/ProductCategory).setFlushMode(FlushModeType.COMMIT);
        if(count != null)
            typedquery.setMaxResults(count.intValue());
        return typedquery.getResultList();
    }

    public List findParents(ProductCategory productCategory, Integer count)
    {
        if(productCategory == null || productCategory.getParent() == null)
            return Collections.emptyList();
        String s = "select productCategory from ProductCategory productCategory where productCategory.id in (:ids) order by productCategory.grade asc";
        TypedQuery typedquery = IIIllIlI.createQuery(s, net/shopxx/entity/ProductCategory).setFlushMode(FlushModeType.COMMIT).setParameter("ids", productCategory.getTreePaths());
        if(count != null)
            typedquery.setMaxResults(count.intValue());
        return typedquery.getResultList();
    }

    public List findChildren(ProductCategory productCategory, Integer count)
    {
        TypedQuery typedquery;
        if(productCategory != null)
        {
            String s = "select productCategory from ProductCategory productCategory where productCategory.treePath like :treePath order by productCategory.order asc";
            typedquery = IIIllIlI.createQuery(s, net/shopxx/entity/ProductCategory).setFlushMode(FlushModeType.COMMIT).setParameter("treePath", (new StringBuilder("%,")).append(productCategory.getId()).append(",").append("%").toString());
        } else
        {
            String s1 = "select productCategory from ProductCategory productCategory order by productCategory.order asc";
            typedquery = IIIllIlI.createQuery(s1, net/shopxx/entity/ProductCategory).setFlushMode(FlushModeType.COMMIT);
        }
        if(count != null)
            typedquery.setMaxResults(count.intValue());
        return IIIllIlI(typedquery.getResultList(), productCategory);
    }

    public void persist(ProductCategory productCategory)
    {
        Assert.notNull(productCategory);
        IIIllIlI(productCategory);
        super.persist(productCategory);
    }

    public ProductCategory merge(ProductCategory productCategory)
    {
        Assert.notNull(productCategory);
        IIIllIlI(productCategory);
        ProductCategory productcategory;
        for(Iterator iterator = findChildren(productCategory, null).iterator(); iterator.hasNext(); IIIllIlI(productcategory))
            productcategory = (ProductCategory)iterator.next();

        return (ProductCategory)super.merge(productCategory);
    }

    public void remove(ProductCategory productCategory)
    {
        if(productCategory != null)
        {
            StringBuffer stringbuffer = new StringBuffer("update Product product set ");
            for(int i = 0; i < 20; i++)
            {
                String s = (new StringBuilder("attributeValue")).append(i).toString();
                if(i == 0)
                    stringbuffer.append((new StringBuilder("product.")).append(s).append(" = null").toString());
                else
                    stringbuffer.append((new StringBuilder(", product.")).append(s).append(" = null").toString());
            }

            stringbuffer.append(" where product.productCategory = :productCategory");
            IIIllIlI.createQuery(stringbuffer.toString()).setFlushMode(FlushModeType.COMMIT).setParameter("productCategory", productCategory).executeUpdate();
            super.remove(productCategory);
        }
    }

    private List IIIllIlI(List list, ProductCategory productcategory)
    {
        ArrayList arraylist = new ArrayList();
        if(list != null)
        {
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                ProductCategory productcategory1 = (ProductCategory)iterator.next();
                if(productcategory1.getParent() == productcategory)
                {
                    arraylist.add(productcategory1);
                    arraylist.addAll(IIIllIlI(list, productcategory1));
                }
            }

        }
        return arraylist;
    }

    private void IIIllIlI(ProductCategory productcategory)
    {
        if(productcategory == null)
            return;
        ProductCategory productcategory1 = productcategory.getParent();
        if(productcategory1 != null)
            productcategory.setTreePath((new StringBuilder(String.valueOf(productcategory1.getTreePath()))).append(productcategory1.getId()).append(",").toString());
        else
            productcategory.setTreePath(",");
        productcategory.setGrade(Integer.valueOf(productcategory.getTreePaths().size()));
    }

    public volatile Object merge(Object obj)
    {
        return merge((ProductCategory)obj);
    }

    public volatile void persist(Object obj)
    {
        persist((ProductCategory)obj);
    }

    public volatile void remove(Object obj)
    {
        remove((ProductCategory)obj);
    }
}
