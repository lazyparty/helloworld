// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao;

import java.util.List;
import net.shopxx.entity.ProductCategory;

// Referenced classes of package net.shopxx.dao:
//            BaseDao

public interface ProductCategoryDao
    extends BaseDao
{

    public abstract List findRoots(Integer integer);

    public abstract List findParents(ProductCategory productcategory, Integer integer);

    public abstract List findChildren(ProductCategory productcategory, Integer integer);
}
