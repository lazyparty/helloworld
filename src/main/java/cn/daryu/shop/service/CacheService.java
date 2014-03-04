// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;


public interface CacheService
{

    public abstract String getDiskStorePath();

    public abstract int getCacheSize();

    public abstract void clear();
}
