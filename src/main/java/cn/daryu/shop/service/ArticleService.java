// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.util.Date;
import java.util.List;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.entity.ArticleCategory;

// Referenced classes of package net.shopxx.service:
//            BaseService

public interface ArticleService
    extends BaseService
{

    public abstract List findList(ArticleCategory articlecategory, List list, Integer integer, List list1, List list2);

    public abstract List findList(ArticleCategory articlecategory, List list, Integer integer, List list1, List list2, String s);

    public abstract List findList(ArticleCategory articlecategory, Date date, Date date1, Integer integer, Integer integer1);

    public abstract Page findPage(ArticleCategory articlecategory, List list, Pageable pageable);

    public abstract long viewHits(Long long1);
}
