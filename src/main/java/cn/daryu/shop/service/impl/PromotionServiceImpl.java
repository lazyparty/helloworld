// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import java.util.List;
import net.shopxx.dao.PromotionDao;
import net.shopxx.entity.Promotion;
import net.shopxx.service.PromotionService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class PromotionServiceImpl extends BaseServiceImpl
    implements PromotionService
{

    public PromotionServiceImpl()
    {
    }

    public void setBaseDao(PromotionDao promotionDao)
    {
        super.setBaseDao(promotionDao);
    }

    public List findList(Boolean hasBegun, Boolean hasEnded, Integer count, List filters, List orders)
    {
        return IIIllIlI.findList(hasBegun, hasEnded, count, filters, orders);
    }

    public List findList(Boolean hasBegun, Boolean hasEnded, Integer count, List filters, List orders, String cacheRegion)
    {
        return IIIllIlI.findList(hasBegun, hasEnded, count, filters, orders);
    }

    public void save(Promotion promotion)
    {
        super.save(promotion);
    }

    public Promotion update(Promotion promotion)
    {
        return (Promotion)super.update(promotion);
    }

    public transient Promotion update(Promotion promotion, String ignoreProperties[])
    {
        return (Promotion)super.update(promotion, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(Promotion promotion)
    {
        super.delete(promotion);
    }

    public volatile void save(Object obj)
    {
        save((Promotion)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((Promotion)obj);
    }

    public volatile transient void delete(Serializable aserializable[])
    {
        delete((Long[])aserializable);
    }

    public volatile void delete(Serializable serializable)
    {
        delete((Long)serializable);
    }

    public volatile transient Object update(Object obj, String as[])
    {
        return update((Promotion)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((Promotion)obj);
    }

    private PromotionDao IIIllIlI;
}
