// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.util.List;
import net.shopxx.entity.ArticleCategory;

// Referenced classes of package net.shopxx.service:
//            BaseService

public interface ArticleCategoryService
    extends BaseService
{

    public abstract List findRoots();

    public abstract List findRoots(Integer integer);

    public abstract List findRoots(Integer integer, String s);

    public abstract List findParents(ArticleCategory articlecategory);

    public abstract List findParents(ArticleCategory articlecategory, Integer integer);

    public abstract List findParents(ArticleCategory articlecategory, Integer integer, String s);

    public abstract List findTree();

    public abstract List findChildren(ArticleCategory articlecategory);

    public abstract List findChildren(ArticleCategory articlecategory, Integer integer);

    public abstract List findChildren(ArticleCategory articlecategory, Integer integer, String s);
}
