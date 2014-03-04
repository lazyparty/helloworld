// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.dao.SnDao;
import net.shopxx.service.SnService;

public class SnServiceImpl
    implements SnService
{

    public SnServiceImpl()
    {
    }

    public String generate(net.shopxx.entity.Sn.Type type)
    {
        return IIIllIlI.generate(type);
    }

    private SnDao IIIllIlI;
}
