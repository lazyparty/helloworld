// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.entity.Admin;
import net.shopxx.entity.Member;

// Referenced classes of package net.shopxx.service:
//            BaseService

public interface MemberService
    extends BaseService
{

    public abstract boolean usernameExists(String s);

    public abstract boolean usernameDisabled(String s);

    public abstract boolean emailExists(String s);

    public abstract boolean emailUnique(String s, String s1);

    public abstract void save(Member member, Admin admin);

    public abstract void update(Member member, Integer integer, BigDecimal bigdecimal, String s, Admin admin);

    public abstract Member findByUsername(String s);

    public abstract List findListByEmail(String s);

    public abstract Page findPurchasePage(Date date, Date date1, Pageable pageable);

    public abstract boolean isAuthenticated();

    public abstract Member getCurrent();

    public abstract String getCurrentUsername();
}
