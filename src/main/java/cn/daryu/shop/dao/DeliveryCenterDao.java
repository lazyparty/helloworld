// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao;

import net.shopxx.entity.DeliveryCenter;

// Referenced classes of package net.shopxx.dao:
//            BaseDao

public interface DeliveryCenterDao
    extends BaseDao
{

    public abstract DeliveryCenter findDefault();
}
