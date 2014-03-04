// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.util.List;
import net.shopxx.plugin.PaymentPlugin;
import net.shopxx.plugin.StoragePlugin;

public interface PluginService
{

    public abstract List getPaymentPlugins();

    public abstract List getStoragePlugins();

    public abstract List getPaymentPlugins(boolean flag);

    public abstract List getStoragePlugins(boolean flag);

    public abstract PaymentPlugin getPaymentPlugin(String s);

    public abstract StoragePlugin getStoragePlugin(String s);
}
