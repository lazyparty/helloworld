// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import java.util.*;
import net.shopxx.dao.GoodsDao;
import net.shopxx.dao.ProductDao;
import net.shopxx.entity.Goods;
import net.shopxx.entity.Product;
import net.shopxx.service.GoodsService;
import net.shopxx.service.StaticService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class GoodsServiceImpl extends BaseServiceImpl
    implements GoodsService
{

    public GoodsServiceImpl()
    {
    }

    public void setBaseDao(GoodsDao goodsDao)
    {
        super.setBaseDao(goodsDao);
    }

    public void save(Goods goods)
    {
        Assert.notNull(goods);
        super.save(goods);
        IIIllIlI.flush();
        if(goods.getProducts() != null)
        {
            Product product;
            for(Iterator iterator = goods.getProducts().iterator(); iterator.hasNext(); IIIlllII.build(product))
                product = (Product)iterator.next();

        }
    }

    public Goods update(Goods goods)
    {
        Assert.notNull(goods);
        HashSet hashset = new HashSet();
        CollectionUtils.select(goods.getProducts(), new _cls1(), hashset);
        List list = IIIllIll.findList(goods, hashset);
        Product product;
        for(Iterator iterator = list.iterator(); iterator.hasNext(); IIIlllII.delete(product))
            product = (Product)iterator.next();

        Goods goods1 = (Goods)super.update(goods);
        IIIllIlI.flush();
        if(goods1.getProducts() != null)
        {
            Product product1;
            for(Iterator iterator1 = goods1.getProducts().iterator(); iterator1.hasNext(); IIIlllII.build(product1))
                product1 = (Product)iterator1.next();

        }
        return goods1;
    }

    public transient Goods update(Goods goods, String ignoreProperties[])
    {
        return (Goods)super.update(goods, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(Goods goods)
    {
        if(goods != null && goods.getProducts() != null)
        {
            Product product;
            for(Iterator iterator = goods.getProducts().iterator(); iterator.hasNext(); IIIlllII.delete(product))
                product = (Product)iterator.next();

        }
        super.delete(goods);
    }

    public volatile void save(Object obj)
    {
        save((Goods)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((Goods)obj);
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
        return update((Goods)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((Goods)obj);
    }

    private GoodsDao IIIllIlI;
    private ProductDao IIIllIll;
    private StaticService IIIlllII;

    private class _cls1
        implements Predicate
    {

        public boolean evaluate(Object object)
        {
            Product product = (Product)object;
            return product != null && product.getId() != null;
        }

        final GoodsServiceImpl IIIllIlI;

        _cls1()
        {
            IIIllIlI = GoodsServiceImpl.this;
            super();
        }
    }

}
