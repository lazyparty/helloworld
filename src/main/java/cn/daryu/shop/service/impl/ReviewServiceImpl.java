// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import java.util.List;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.ProductDao;
import net.shopxx.dao.ReviewDao;
import net.shopxx.entity.*;
import net.shopxx.service.ReviewService;
import net.shopxx.service.StaticService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class ReviewServiceImpl extends BaseServiceImpl
    implements ReviewService
{

    public ReviewServiceImpl()
    {
    }

    public void setBaseDao(ReviewDao reviewDao)
    {
        super.setBaseDao(reviewDao);
    }

    public List findList(Member member, Product product, net.shopxx.entity.Review.Type type, Boolean isShow, Integer count, List filters, List orders)
    {
        return IIIllIlI.findList(member, product, type, isShow, count, filters, orders);
    }

    public List findList(Member member, Product product, net.shopxx.entity.Review.Type type, Boolean isShow, Integer count, List filters, List orders, 
            String cacheRegion)
    {
        return IIIllIlI.findList(member, product, type, isShow, count, filters, orders);
    }

    public Page findPage(Member member, Product product, net.shopxx.entity.Review.Type type, Boolean isShow, Pageable pageable)
    {
        return IIIllIlI.findPage(member, product, type, isShow, pageable);
    }

    public Long count(Member member, Product product, net.shopxx.entity.Review.Type type, Boolean isShow)
    {
        return IIIllIlI.count(member, product, type, isShow);
    }

    public boolean isReviewed(Member member, Product product)
    {
        return IIIllIlI.isReviewed(member, product);
    }

    public void save(Review review)
    {
        super.save(review);
        Product product = review.getProduct();
        if(product != null)
        {
            IIIllIlI.flush();
            long l = IIIllIlI.calculateTotalScore(product);
            long l1 = IIIllIlI.calculateScoreCount(product);
            product.setTotalScore(Long.valueOf(l));
            product.setScoreCount(Long.valueOf(l1));
            IIIllIll.merge(product);
            IIIllIlI.flush();
            IIIlllII.build(product);
        }
    }

    public Review update(Review review)
    {
        Review review1 = (Review)super.update(review);
        Product product = review1.getProduct();
        if(product != null)
        {
            IIIllIlI.flush();
            long l = IIIllIlI.calculateTotalScore(product);
            long l1 = IIIllIlI.calculateScoreCount(product);
            product.setTotalScore(Long.valueOf(l));
            product.setScoreCount(Long.valueOf(l1));
            IIIllIll.merge(product);
            IIIllIlI.flush();
            IIIlllII.build(product);
        }
        return review1;
    }

    public transient Review update(Review review, String ignoreProperties[])
    {
        return (Review)super.update(review, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(Review review)
    {
        if(review != null)
        {
            super.delete(review);
            Product product = review.getProduct();
            if(product != null)
            {
                IIIllIlI.flush();
                long l = IIIllIlI.calculateTotalScore(product);
                long l1 = IIIllIlI.calculateScoreCount(product);
                product.setTotalScore(Long.valueOf(l));
                product.setScoreCount(Long.valueOf(l1));
                IIIllIll.merge(product);
                IIIllIlI.flush();
                IIIlllII.build(product);
            }
        }
    }

    public volatile void save(Object obj)
    {
        save((Review)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((Review)obj);
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
        return update((Review)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((Review)obj);
    }

    private ReviewDao IIIllIlI;
    private ProductDao IIIllIll;
    private StaticService IIIlllII;
}
