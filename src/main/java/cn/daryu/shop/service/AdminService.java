// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.util.List;
import net.shopxx.entity.Admin;

// Referenced classes of package net.shopxx.service:
//            BaseService

public interface AdminService
    extends BaseService
{

    public abstract boolean usernameExists(String s);

    public abstract Admin findByUsername(String s);

    public abstract List findAuthorities(Long long1);

    public abstract boolean isAuthenticated();

    public abstract Admin getCurrent();

    public abstract String getCurrentUsername();
}
