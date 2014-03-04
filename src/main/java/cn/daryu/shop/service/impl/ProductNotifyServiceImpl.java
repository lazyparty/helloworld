// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.util.Iterator;
import java.util.List;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.ProductNotifyDao;
import net.shopxx.entity.*;
import net.shopxx.service.MailService;
import net.shopxx.service.ProductNotifyService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class ProductNotifyServiceImpl extends BaseServiceImpl
    implements ProductNotifyService
{

    public ProductNotifyServiceImpl()
    {
    }

    public void setBaseDao(ProductNotifyDao ProductNotifyDao)
    {
        super.setBaseDao(ProductNotifyDao);
    }

    public boolean exists(Product product, String email)
    {
        return IIIllIlI.exists(product, email);
    }

    public Page findPage(Member member, Boolean isMarketable, Boolean isOutOfStock, Boolean hasSent, Pageable pageable)
    {
        return IIIllIlI.findPage(member, isMarketable, isOutOfStock, hasSent, pageable);
    }

    public Long count(Member member, Boolean isMarketable, Boolean isOutOfStock, Boolean hasSent)
    {
        return IIIllIlI.count(member, isMarketable, isOutOfStock, hasSent);
    }

    public int send(Long ids[])
    {
        List list = findList(ids);
        ProductNotify productnotify;
        for(Iterator iterator = list.iterator(); iterator.hasNext(); IIIllIlI.merge(productnotify))
        {
            productnotify = (ProductNotify)iterator.next();
            IIIllIll.sendProductNotifyMail(productnotify);
            productnotify.setHasSent(Boolean.valueOf(true));
        }

        return list.size();
    }

    ProductNotifyDao IIIllIlI;
    MailService IIIllIll;
}
