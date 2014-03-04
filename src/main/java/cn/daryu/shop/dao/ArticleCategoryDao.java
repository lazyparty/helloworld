// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao;

import java.util.List;
import net.shopxx.entity.ArticleCategory;

// Referenced classes of package net.shopxx.dao:
//            BaseDao

public interface ArticleCategoryDao
    extends BaseDao
{

    public abstract List findRoots(Integer integer);

    public abstract List findParents(ArticleCategory articlecategory, Integer integer);

    public abstract List findChildren(ArticleCategory articlecategory, Integer integer);
}
