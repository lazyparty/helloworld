// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao;

import java.util.Date;
import java.util.List;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.entity.Member;

// Referenced classes of package net.shopxx.dao:
//            BaseDao

public interface MemberDao
    extends BaseDao
{

    public abstract boolean usernameExists(String s);

    public abstract boolean emailExists(String s);

    public abstract Member findByUsername(String s);

    public abstract List findListByEmail(String s);

    public abstract Page findPurchasePage(Date date, Date date1, Pageable pageable);
}
