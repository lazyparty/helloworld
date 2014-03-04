// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.*;
import javax.persistence.*;
import net.shopxx.dao.*;
import net.shopxx.entity.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class GoodsDaoImpl extends BaseDaoImpl
    implements GoodsDao
{

    public GoodsDaoImpl()
    {
    }

    public void persist(Goods goods)
    {
        Assert.notNull(goods);
        if(goods.getProducts() != null)
        {
            Product product;
            for(Iterator iterator = goods.getProducts().iterator(); iterator.hasNext(); IIIllIlI(product))
                product = (Product)iterator.next();

        }
        super.persist(goods);
    }

    public Goods merge(Goods goods)
    {
        Assert.notNull(goods);
        if(goods.getProducts() != null)
        {
            Product product;
            for(Iterator iterator = goods.getProducts().iterator(); iterator.hasNext(); IIIllIlI(product))
            {
                product = (Product)iterator.next();
                if(product.getId() != null)
                {
                    if(!product.getIsGift().booleanValue())
                    {
                        String s = "delete from GiftItem giftItem where giftItem.gift = :product";
                        IIIllIlI.createQuery(s).setFlushMode(FlushModeType.COMMIT).setParameter("product", product).executeUpdate();
                    }
                    if(!product.getIsMarketable().booleanValue() || product.getIsGift().booleanValue())
                    {
                        String s1 = "delete from CartItem cartItem where cartItem.product = :product";
                        IIIllIlI.createQuery(s1).setFlushMode(FlushModeType.COMMIT).setParameter("product", product).executeUpdate();
                    }
                }
            }

        }
        return (Goods)super.merge(goods);
    }

    private void IIIllIlI(Product product)
    {
        if(product == null)
            return;
        if(StringUtils.isEmpty(product.getSn()))
        {
            String s;
            do
                s = IIIlllII.generate(net.shopxx.entity.Sn.Type.product);
            while(IIIllIll.snExists(s));
            product.setSn(s);
        }
        StringBuffer stringbuffer = new StringBuffer(product.getName());
        if(product.getSpecificationValues() != null && !product.getSpecificationValues().isEmpty())
        {
            ArrayList arraylist = new ArrayList(product.getSpecificationValues());
            Collections.sort(arraylist, new _cls1());
            stringbuffer.append("[");
            int i = 0;
            for(Iterator iterator = arraylist.iterator(); iterator.hasNext();)
            {
                if(i != 0)
                    stringbuffer.append(" ");
                stringbuffer.append(((SpecificationValue)iterator.next()).getName());
                i++;
            }

            stringbuffer.append("]");
        }
        product.setFullName(stringbuffer.toString());
    }

    public volatile Object merge(Object obj)
    {
        return merge((Goods)obj);
    }

    public volatile void persist(Object obj)
    {
        persist((Goods)obj);
    }

    private ProductDao IIIllIll;
    private SnDao IIIlllII;

    private class _cls1
        implements Comparator
    {

        public int compare(SpecificationValue a1, SpecificationValue a2)
        {
            return (new CompareToBuilder()).append(a1.getSpecification(), a2.getSpecification()).toComparison();
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((SpecificationValue)obj, (SpecificationValue)obj1);
        }

        final GoodsDaoImpl IIIllIlI;

        _cls1()
        {
            IIIllIlI = GoodsDaoImpl.this;
            super();
        }
    }

}
