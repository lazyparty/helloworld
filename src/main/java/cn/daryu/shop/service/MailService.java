// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.util.Map;
import net.shopxx.entity.ProductNotify;
import net.shopxx.entity.SafeKey;

public interface MailService
{

    public abstract void send(String s, String s1, Integer integer, String s2, String s3, String s4, String s5, 
            String s6, Map map, boolean flag);

    public abstract void send(String s, String s1, String s2, Map map, boolean flag);

    public abstract void send(String s, String s1, String s2, Map map);

    public abstract void send(String s, String s1, String s2);

    public abstract void sendTestMail(String s, String s1, Integer integer, String s2, String s3, String s4);

    public abstract void sendFindPasswordMail(String s, String s1, SafeKey safekey);

    public abstract void sendProductNotifyMail(ProductNotify productnotify);
}
