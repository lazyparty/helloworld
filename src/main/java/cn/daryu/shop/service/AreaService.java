// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.util.List;

// Referenced classes of package net.shopxx.service:
//            BaseService

public interface AreaService
    extends BaseService
{

    public abstract List findRoots();

    public abstract List findRoots(Integer integer);
}
