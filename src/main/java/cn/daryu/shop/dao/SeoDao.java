// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao;

import net.shopxx.entity.Seo;

// Referenced classes of package net.shopxx.dao:
//            BaseDao

public interface SeoDao
    extends BaseDao
{

    public abstract Seo find(net.shopxx.entity.Seo.Type type);
}
