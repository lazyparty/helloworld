// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.util.List;
import net.shopxx.entity.ProductCategory;

// Referenced classes of package net.shopxx.service:
//            BaseService

public interface ProductCategoryService
    extends BaseService
{

    public abstract List findRoots();

    public abstract List findRoots(Integer integer);

    public abstract List findRoots(Integer integer, String s);

    public abstract List findParents(ProductCategory productcategory);

    public abstract List findParents(ProductCategory productcategory, Integer integer);

    public abstract List findParents(ProductCategory productcategory, Integer integer, String s);

    public abstract List findTree();

    public abstract List findChildren(ProductCategory productcategory);

    public abstract List findChildren(ProductCategory productcategory, Integer integer);

    public abstract List findChildren(ProductCategory productcategory, Integer integer, String s);
}
