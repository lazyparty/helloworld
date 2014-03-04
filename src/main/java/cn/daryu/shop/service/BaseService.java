// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.io.Serializable;
import java.util.List;
import net.shopxx.*;

public interface BaseService
{

    public abstract Object find(Serializable serializable);

    public abstract List findAll();

    public transient abstract List findList(Serializable aserializable[]);

    public abstract List findList(Integer integer, List list, List list1);

    public abstract List findList(Integer integer, Integer integer1, List list, List list1);

    public abstract Page findPage(Pageable pageable);

    public abstract long count();

    public transient abstract long count(Filter afilter[]);

    public abstract boolean exists(Serializable serializable);

    public transient abstract boolean exists(Filter afilter[]);

    public abstract void save(Object obj);

    public abstract Object update(Object obj);

    public transient abstract Object update(Object obj, String as[]);

    public abstract void delete(Serializable serializable);

    public transient abstract void delete(Serializable aserializable[]);

    public abstract void delete(Object obj);
}
