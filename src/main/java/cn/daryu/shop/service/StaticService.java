// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.util.Map;
import net.shopxx.entity.Article;
import net.shopxx.entity.Product;

public interface StaticService
{

    public abstract int build(String s, String s1, Map map);

    public abstract int build(String s, String s1);

    public abstract int build(Article article);

    public abstract int build(Product product);

    public abstract int buildIndex();

    public abstract int buildSitemap();

    public abstract int buildOther();

    public abstract int buildAll();

    public abstract int delete(String s);

    public abstract int delete(Article article);

    public abstract int delete(Product product);

    public abstract int deleteIndex();

    public abstract int deleteOther();
}
