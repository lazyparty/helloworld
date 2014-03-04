// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.math.BigDecimal;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.entity.Article;
import net.shopxx.entity.Product;

public interface SearchService
{

    public abstract void index();

    public abstract void index(Class class1);

    public abstract void index(Article article);

    public abstract void index(Product product);

    public abstract void purge();

    public abstract void purge(Class class1);

    public abstract void purge(Article article);

    public abstract void purge(Product product);

    public abstract Page search(String s, Pageable pageable);

    public abstract Page search(String s, BigDecimal bigdecimal, BigDecimal bigdecimal1, net.shopxx.entity.Product.OrderType ordertype, Pageable pageable);
}
