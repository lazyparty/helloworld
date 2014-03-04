// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.listener;

import java.util.Date;
import net.shopxx.entity.BaseEntity;

public class EntityListener
{

    public EntityListener()
    {
    }

    public void prePersist(BaseEntity entity)
    {
        entity.setCreateDate(new Date());
        entity.setModifyDate(new Date());
    }

    public void preUpdate(BaseEntity entity)
    {
        entity.setModifyDate(new Date());
    }
}
