// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.util.List;

// Referenced classes of package net.shopxx.service:
//            BaseService

public interface FriendLinkService
    extends BaseService
{

    public abstract List findList(net.shopxx.entity.FriendLink.Type type);

    public abstract List findList(Integer integer, List list, List list1, String s);
}
