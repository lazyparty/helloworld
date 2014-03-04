// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import net.shopxx.entity.Seo;

// Referenced classes of package net.shopxx.service:
//            BaseService

public interface SeoService
    extends BaseService
{

    public abstract Seo find(net.shopxx.entity.Seo.Type type);

    public abstract Seo find(net.shopxx.entity.Seo.Type type, String s);
}
