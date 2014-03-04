// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.dao.LogDao;
import net.shopxx.service.LogService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class LogServiceImpl extends BaseServiceImpl
    implements LogService
{

    public LogServiceImpl()
    {
    }

    public void setBaseDao(LogDao logDao)
    {
        super.setBaseDao(logDao);
    }

    public void clear()
    {
        IIIllIlI.removeAll();
    }

    private LogDao IIIllIlI;
}
