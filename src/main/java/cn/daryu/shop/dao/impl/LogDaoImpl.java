// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import javax.persistence.*;
import net.shopxx.dao.LogDao;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class LogDaoImpl extends BaseDaoImpl
    implements LogDao
{

    public LogDaoImpl()
    {
    }

    public void removeAll()
    {
        String s = "delete from Log log";
        IIIllIlI.createQuery(s).setFlushMode(FlushModeType.COMMIT).executeUpdate();
    }
}
