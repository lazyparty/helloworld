// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao;

import net.shopxx.entity.Admin;

// Referenced classes of package net.shopxx.dao:
//            BaseDao

public interface AdminDao
    extends BaseDao
{

    public abstract boolean usernameExists(String s);

    public abstract Admin findByUsername(String s);
}
