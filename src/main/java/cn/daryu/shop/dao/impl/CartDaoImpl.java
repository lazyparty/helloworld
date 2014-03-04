// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.Date;
import javax.persistence.*;
import net.shopxx.dao.CartDao;
import org.apache.commons.lang.time.DateUtils;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class CartDaoImpl extends BaseDaoImpl
    implements CartDao
{

    public CartDaoImpl()
    {
    }

    public void evictExpired()
    {
        String s = "delete from Cart cart where cart.modifyDate <= :expire";
        IIIllIlI.createQuery(s).setFlushMode(FlushModeType.COMMIT).setParameter("expire", DateUtils.addSeconds(new Date(), 0xfff6c580)).executeUpdate();
    }
}
