// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.LockModeType;
import net.shopxx.*;

public interface BaseDao
{

    public abstract Object find(Serializable serializable);

    public abstract List findList(Integer integer, Integer integer1, List list, List list1);

    public abstract Page findPage(Pageable pageable);

    public transient abstract long count(Filter afilter[]);

    public abstract void persist(Object obj);

    public abstract Object merge(Object obj);

    public abstract void remove(Object obj);

    public abstract void refresh(Object obj);

    public abstract Serializable getIdentifier(Object obj);

    public abstract boolean isManaged(Object obj);

    public abstract void detach(Object obj);

    public abstract void lock(Object obj, LockModeType lockmodetype);

    public abstract void clear();

    public abstract void flush();
}
