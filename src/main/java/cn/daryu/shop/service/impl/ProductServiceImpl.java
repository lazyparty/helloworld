// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import javax.persistence.LockModeType;
import net.sf.ehcache.*;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.ProductDao;
import net.shopxx.entity.*;
import net.shopxx.service.ProductService;
import net.shopxx.service.StaticService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.util.Assert;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class ProductServiceImpl extends BaseServiceImpl
    implements ProductService, DisposableBean
{

    public ProductServiceImpl()
    {
        IIIllIlI = System.currentTimeMillis();
    }

    public void setBaseDao(ProductDao productDao)
    {
        super.setBaseDao(productDao);
    }

    public boolean snExists(String sn)
    {
        return IIIlllII.snExists(sn);
    }

    public Product findBySn(String sn)
    {
        return IIIlllII.findBySn(sn);
    }

    public boolean snUnique(String previousSn, String currentSn)
    {
        if(StringUtils.equalsIgnoreCase(previousSn, currentSn))
            return true;
        return !IIIlllII.snExists(currentSn);
    }

    public List search(String keyword, Boolean isGift, Integer count)
    {
        return IIIlllII.search(keyword, isGift, count);
    }

    public List findList(ProductCategory productCategory, Brand brand, Promotion promotion, List tags, Map attributeValue, BigDecimal startPrice, BigDecimal endPrice, 
            Boolean isMarketable, Boolean isList, Boolean isTop, Boolean isGift, Boolean isOutOfStock, Boolean isStockAlert, net.shopxx.entity.Product.OrderType orderType, 
            Integer count, List filters, List orders)
    {
        return IIIlllII.findList(productCategory, brand, promotion, tags, attributeValue, startPrice, endPrice, isMarketable, isList, isTop, isGift, isOutOfStock, isStockAlert, orderType, count, filters, orders);
    }

    public List findList(ProductCategory productCategory, Brand brand, Promotion promotion, List tags, Map attributeValue, BigDecimal startPrice, BigDecimal endPrice, 
            Boolean isMarketable, Boolean isList, Boolean isTop, Boolean isGift, Boolean isOutOfStock, Boolean isStockAlert, net.shopxx.entity.Product.OrderType orderType, 
            Integer count, List filters, List orders, String cacheRegion)
    {
        return IIIlllII.findList(productCategory, brand, promotion, tags, attributeValue, startPrice, endPrice, isMarketable, isList, isTop, isGift, isOutOfStock, isStockAlert, orderType, count, filters, orders);
    }

    public List findList(ProductCategory productCategory, Date beginDate, Date endDate, Integer first, Integer count)
    {
        return IIIlllII.findList(productCategory, beginDate, endDate, first, count);
    }

    public Page findPage(ProductCategory productCategory, Brand brand, Promotion promotion, List tags, Map attributeValue, BigDecimal startPrice, BigDecimal endPrice, 
            Boolean isMarketable, Boolean isList, Boolean isTop, Boolean isGift, Boolean isOutOfStock, Boolean isStockAlert, net.shopxx.entity.Product.OrderType orderType, 
            Pageable pageable)
    {
        return IIIlllII.findPage(productCategory, brand, promotion, tags, attributeValue, startPrice, endPrice, isMarketable, isList, isTop, isGift, isOutOfStock, isStockAlert, orderType, pageable);
    }

    public Page findPage(Member member, Pageable pageable)
    {
        return IIIlllII.findPage(member, pageable);
    }

    public Page findSalesPage(Date beginDate, Date endDate, Pageable pageable)
    {
        return IIIlllII.findSalesPage(beginDate, endDate, pageable);
    }

    public Long count(Member favoriteMember, Boolean isMarketable, Boolean isList, Boolean isTop, Boolean isGift, Boolean isOutOfStock, Boolean isStockAlert)
    {
        return IIIlllII.count(favoriteMember, isMarketable, isList, isTop, isGift, isOutOfStock, isStockAlert);
    }

    public boolean isPurchased(Member member, Product product)
    {
        return IIIlllII.isPurchased(member, product);
    }

    public long viewHits(Long id)
    {
        Ehcache ehcache = IIIllIll.getEhcache("productHits");
        Element element = ehcache.get(id);
        Long long1;
        if(element != null)
        {
            long1 = (Long)element.getObjectValue();
        } else
        {
            Product product = (Product)IIIlllII.find(id);
            if(product == null)
                return 0L;
            long1 = product.getHits();
        }
        long1 = Long.valueOf(long1.longValue() + 1L);
        ehcache.put(new Element(id, long1));
        long l = System.currentTimeMillis();
        if(l > IIIllIlI + 0x927c0L)
        {
            IIIllIlI = l;
            IIIllIlI();
            ehcache.removeAll();
        }
        return long1.longValue();
    }

    public void destroy()
    {
        IIIllIlI();
    }

    private void IIIllIlI()
    {
        Ehcache ehcache = IIIllIll.getEhcache("productHits");
        List list = ehcache.getKeys();
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            Long long1 = (Long)iterator.next();
            Product product = (Product)IIIlllII.find(long1);
            if(product != null)
            {
                IIIlllII.lock(product, LockModeType.PESSIMISTIC_WRITE);
                Element element = ehcache.get(long1);
                long l = ((Long)element.getObjectValue()).longValue();
                long l1 = l - product.getHits().longValue();
                Calendar calendar = Calendar.getInstance();
                Calendar calendar1 = DateUtils.toCalendar(product.getWeekHitsDate());
                Calendar calendar2 = DateUtils.toCalendar(product.getMonthHitsDate());
                if(calendar.get(1) != calendar1.get(1) || calendar.get(3) > calendar1.get(3))
                    product.setWeekHits(Long.valueOf(l1));
                else
                    product.setWeekHits(Long.valueOf(product.getWeekHits().longValue() + l1));
                if(calendar.get(1) != calendar2.get(1) || calendar.get(2) > calendar2.get(2))
                    product.setMonthHits(Long.valueOf(l1));
                else
                    product.setMonthHits(Long.valueOf(product.getMonthHits().longValue() + l1));
                product.setHits(Long.valueOf(l));
                product.setWeekHitsDate(new Date());
                product.setMonthHitsDate(new Date());
                IIIlllII.merge(product);
            }
        }

    }

    public void save(Product product)
    {
        Assert.notNull(product);
        super.save(product);
        IIIlllII.flush();
        IIIlllIl.build(product);
    }

    public Product update(Product product)
    {
        Assert.notNull(product);
        Product product1 = (Product)super.update(product);
        IIIlllII.flush();
        IIIlllIl.build(product1);
        return product1;
    }

    public transient Product update(Product product, String ignoreProperties[])
    {
        return (Product)super.update(product, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(Product product)
    {
        if(product != null)
            IIIlllIl.delete(product);
        super.delete(product);
    }

    public volatile void save(Object obj)
    {
        save((Product)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((Product)obj);
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
        return update((Product)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((Product)obj);
    }

    private long IIIllIlI;
    private CacheManager IIIllIll;
    private ProductDao IIIlllII;
    private StaticService IIIlllIl;
}
