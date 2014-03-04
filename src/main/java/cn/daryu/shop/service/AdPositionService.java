// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import net.shopxx.entity.AdPosition;

// Referenced classes of package net.shopxx.service:
//            BaseService

public interface AdPositionService
    extends BaseService
{

    public abstract AdPosition find(Long long1, String s);
}
