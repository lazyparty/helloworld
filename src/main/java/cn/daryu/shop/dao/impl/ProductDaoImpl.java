// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.Setting;
import net.shopxx.dao.GoodsDao;
import net.shopxx.dao.ProductDao;
import net.shopxx.dao.SnDao;
import net.shopxx.entity.Attribute;
import net.shopxx.entity.Brand;
import net.shopxx.entity.Goods;
import net.shopxx.entity.Member;
import net.shopxx.entity.Product;
import net.shopxx.entity.ProductCategory;
import net.shopxx.entity.Promotion;
import net.shopxx.entity.SpecificationValue;
import net.shopxx.util.SettingUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class ProductDaoImpl extends BaseDaoImpl
    implements ProductDao
{

    public ProductDaoImpl()
    {
    }

    public boolean snExists(String sn)
    {
        if(sn == null)
            return false;
        String s = "select count(*) from Product product where lower(product.sn) = lower(:sn)";
        Long long1 = (Long)IIIllIlI.createQuery(s, java/lang/Long).setFlushMode(FlushModeType.COMMIT).setParameter("sn", sn).getSingleResult();
        return long1.longValue() > 0L;
    }

    public Product findBySn(String sn)
    {
        if(sn == null)
            return null;
        String s = "select product from Product product where lower(product.sn) = lower(:sn)";
        try
        {
            return (Product)IIIllIlI.createQuery(s, net/shopxx/entity/Product).setFlushMode(FlushModeType.COMMIT).setParameter("sn", sn).getSingleResult();
        }
        catch(NoResultException noresultexception)
        {
            return null;
        }
    }

    public List search(String keyword, Boolean isGift, Integer count)
    {
        if(StringUtils.isEmpty(keyword))
            return Collections.emptyList();
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Product);
        Root root = criteriaquery.from(net/shopxx/entity/Product);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        if(IIIllIll.matcher(keyword).matches())
            predicate = criteriabuilder.and(predicate, criteriabuilder.or(new Predicate[] {
                criteriabuilder.equal(root.get("id"), Long.valueOf(keyword)), criteriabuilder.like(root.get("sn"), (new StringBuilder("%")).append(keyword).append("%").toString()), criteriabuilder.like(root.get("fullName"), (new StringBuilder("%")).append(keyword).append("%").toString())
            }));
        else
            predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.like(root.get("sn"), (new StringBuilder("%")).append(keyword).append("%").toString()), criteriabuilder.like(root.get("fullName"), (new StringBuilder("%")).append(keyword).append("%").toString())));
        if(isGift != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isGift"), isGift));
        criteriaquery.where(predicate);
        criteriaquery.orderBy(new Order[] {
            criteriabuilder.desc(root.get("isTop"))
        });
        return super.IIIllIlI(criteriaquery, null, count, null, null);
    }

    public List findList(ProductCategory productCategory, Brand brand, Promotion promotion, List tags, Map attributeValue, BigDecimal startPrice, BigDecimal endPrice, 
            Boolean isMarketable, Boolean isList, Boolean isTop, Boolean isGift, Boolean isOutOfStock, Boolean isStockAlert, net.shopxx.entity.Product.OrderType orderType, 
            Integer count, List filters, List orders)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Product);
        Root root = criteriaquery.from(net/shopxx/entity/Product);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        if(productCategory != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.equal(root.get("productCategory"), productCategory), criteriabuilder.like(root.get("productCategory").get("treePath"), (new StringBuilder("%,")).append(productCategory.getId()).append(",").append("%").toString())));
        if(brand != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("brand"), brand));
        if(promotion != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.or(new Predicate[] {
                criteriabuilder.equal(root.join("promotions", JoinType.LEFT), promotion), criteriabuilder.equal(root.join("productCategory", JoinType.LEFT).join("promotions", JoinType.LEFT), promotion), criteriabuilder.equal(root.join("brand", JoinType.LEFT).join("promotions", JoinType.LEFT), promotion)
            }));
        if(attributeValue != null)
        {
            for(Iterator iterator = attributeValue.entrySet().iterator(); iterator.hasNext();)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                String s = (new StringBuilder("attributeValue")).append(((Attribute)entry.getKey()).getPropertyIndex()).toString();
                predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get(s), entry.getValue()));
            }

        }
        if(startPrice != null && endPrice != null && startPrice.compareTo(endPrice) > 0)
        {
            BigDecimal bigdecimal = startPrice;
            startPrice = endPrice;
            endPrice = bigdecimal;
        }
        if(startPrice != null && startPrice.compareTo(new BigDecimal(0)) >= 0)
            predicate = criteriabuilder.and(predicate, criteriabuilder.ge(root.get("price"), startPrice));
        if(endPrice != null && endPrice.compareTo(new BigDecimal(0)) >= 0)
            predicate = criteriabuilder.and(predicate, criteriabuilder.le(root.get("price"), endPrice));
        if(tags != null && !tags.isEmpty())
        {
            predicate = criteriabuilder.and(predicate, root.join("tags").in(tags));
            criteriaquery.distinct(true);
        }
        if(isMarketable != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isMarketable"), isMarketable));
        if(isList != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isList"), isList));
        if(isTop != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isTop"), isTop));
        if(isGift != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isGift"), isGift));
        Path path = root.get("stock");
        Path path1 = root.get("allocatedStock");
        if(isOutOfStock != null)
            if(isOutOfStock.booleanValue())
                predicate = criteriabuilder.and(new Predicate[] {
                    predicate, criteriabuilder.isNotNull(path), criteriabuilder.lessThanOrEqualTo(path, path1)
                });
            else
                predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.isNull(path), criteriabuilder.greaterThan(path, path1)));
        if(isStockAlert != null)
        {
            Setting setting = SettingUtils.get();
            if(isStockAlert.booleanValue())
                predicate = criteriabuilder.and(new Predicate[] {
                    predicate, criteriabuilder.isNotNull(path), criteriabuilder.lessThanOrEqualTo(path, criteriabuilder.sum(path1, setting.getStockAlertCount()))
                });
            else
                predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.isNull(path), criteriabuilder.greaterThan(path, criteriabuilder.sum(path1, setting.getStockAlertCount()))));
        }
        criteriaquery.where(predicate);
        if(orderType == net.shopxx.entity.Product.OrderType.priceAsc)
        {
            orders.add(net.shopxx.Order.asc("price"));
            orders.add(net.shopxx.Order.desc("createDate"));
        } else
        if(orderType == net.shopxx.entity.Product.OrderType.priceDesc)
        {
            orders.add(net.shopxx.Order.desc("price"));
            orders.add(net.shopxx.Order.desc("createDate"));
        } else
        if(orderType == net.shopxx.entity.Product.OrderType.salesDesc)
        {
            orders.add(net.shopxx.Order.desc("sales"));
            orders.add(net.shopxx.Order.desc("createDate"));
        } else
        if(orderType == net.shopxx.entity.Product.OrderType.scoreDesc)
        {
            orders.add(net.shopxx.Order.desc("score"));
            orders.add(net.shopxx.Order.desc("createDate"));
        } else
        if(orderType == net.shopxx.entity.Product.OrderType.dateDesc)
        {
            orders.add(net.shopxx.Order.desc("createDate"));
        } else
        {
            orders.add(net.shopxx.Order.desc("isTop"));
            orders.add(net.shopxx.Order.desc("modifyDate"));
        }
        return super.IIIllIlI(criteriaquery, null, count, filters, orders);
    }

    public List findList(ProductCategory productCategory, Date beginDate, Date endDate, Integer first, Integer count)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Product);
        Root root = criteriaquery.from(net/shopxx/entity/Product);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isMarketable"), Boolean.valueOf(true)));
        if(productCategory != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.equal(root.get("productCategory"), productCategory), criteriabuilder.like(root.get("productCategory").get("treePath"), (new StringBuilder("%,")).append(productCategory.getId()).append(",").append("%").toString())));
        if(beginDate != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.greaterThanOrEqualTo(root.get("createDate"), beginDate));
        if(endDate != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.lessThanOrEqualTo(root.get("createDate"), endDate));
        criteriaquery.where(predicate);
        criteriaquery.orderBy(new Order[] {
            criteriabuilder.desc(root.get("isTop"))
        });
        return super.IIIllIlI(criteriaquery, first, count, null, null);
    }

    public List findList(Goods goods, Set excludes)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Product);
        Root root = criteriaquery.from(net/shopxx/entity/Product);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        if(goods != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("goods"), goods));
        if(excludes != null && !excludes.isEmpty())
            predicate = criteriabuilder.and(predicate, criteriabuilder.not(root.in(excludes)));
        criteriaquery.where(predicate);
        return IIIllIlI.createQuery(criteriaquery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

    public Page findPage(ProductCategory productCategory, Brand brand, Promotion promotion, List tags, Map attributeValue, BigDecimal startPrice, BigDecimal endPrice, 
            Boolean isMarketable, Boolean isList, Boolean isTop, Boolean isGift, Boolean isOutOfStock, Boolean isStockAlert, net.shopxx.entity.Product.OrderType orderType, 
            Pageable pageable)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Product);
        Root root = criteriaquery.from(net/shopxx/entity/Product);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        if(productCategory != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.equal(root.get("productCategory"), productCategory), criteriabuilder.like(root.get("productCategory").get("treePath"), (new StringBuilder("%,")).append(productCategory.getId()).append(",").append("%").toString())));
        if(brand != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("brand"), brand));
        if(promotion != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.or(new Predicate[] {
                criteriabuilder.equal(root.join("promotions", JoinType.LEFT), promotion), criteriabuilder.equal(root.join("productCategory", JoinType.LEFT).join("promotions", JoinType.LEFT), promotion), criteriabuilder.equal(root.join("brand", JoinType.LEFT).join("promotions", JoinType.LEFT), promotion)
            }));
        if(tags != null && !tags.isEmpty())
        {
            predicate = criteriabuilder.and(predicate, root.join("tags").in(tags));
            criteriaquery.distinct(true);
        }
        if(attributeValue != null)
        {
            for(Iterator iterator = attributeValue.entrySet().iterator(); iterator.hasNext();)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                String s = (new StringBuilder("attributeValue")).append(((Attribute)entry.getKey()).getPropertyIndex()).toString();
                predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get(s), entry.getValue()));
            }

        }
        if(startPrice != null && endPrice != null && startPrice.compareTo(endPrice) > 0)
        {
            BigDecimal bigdecimal = startPrice;
            startPrice = endPrice;
            endPrice = bigdecimal;
        }
        if(startPrice != null && startPrice.compareTo(new BigDecimal(0)) >= 0)
            predicate = criteriabuilder.and(predicate, criteriabuilder.ge(root.get("price"), startPrice));
        if(endPrice != null && endPrice.compareTo(new BigDecimal(0)) >= 0)
            predicate = criteriabuilder.and(predicate, criteriabuilder.le(root.get("price"), endPrice));
        if(isMarketable != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isMarketable"), isMarketable));
        if(isList != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isList"), isList));
        if(isTop != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isTop"), isTop));
        if(isGift != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isGift"), isGift));
        Path path = root.get("stock");
        Path path1 = root.get("allocatedStock");
        if(isOutOfStock != null)
            if(isOutOfStock.booleanValue())
                predicate = criteriabuilder.and(new Predicate[] {
                    predicate, criteriabuilder.isNotNull(path), criteriabuilder.lessThanOrEqualTo(path, path1)
                });
            else
                predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.isNull(path), criteriabuilder.greaterThan(path, path1)));
        if(isStockAlert != null)
        {
            Setting setting = SettingUtils.get();
            if(isStockAlert.booleanValue())
                predicate = criteriabuilder.and(new Predicate[] {
                    predicate, criteriabuilder.isNotNull(path), criteriabuilder.lessThanOrEqualTo(path, criteriabuilder.sum(path1, setting.getStockAlertCount()))
                });
            else
                predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.isNull(path), criteriabuilder.greaterThan(path, criteriabuilder.sum(path1, setting.getStockAlertCount()))));
        }
        criteriaquery.where(predicate);
        List list = pageable.getOrders();
        if(orderType == net.shopxx.entity.Product.OrderType.priceAsc)
        {
            list.add(net.shopxx.Order.asc("price"));
            list.add(net.shopxx.Order.desc("createDate"));
        } else
        if(orderType == net.shopxx.entity.Product.OrderType.priceDesc)
        {
            list.add(net.shopxx.Order.desc("price"));
            list.add(net.shopxx.Order.desc("createDate"));
        } else
        if(orderType == net.shopxx.entity.Product.OrderType.salesDesc)
        {
            list.add(net.shopxx.Order.desc("sales"));
            list.add(net.shopxx.Order.desc("createDate"));
        } else
        if(orderType == net.shopxx.entity.Product.OrderType.scoreDesc)
        {
            list.add(net.shopxx.Order.desc("score"));
            list.add(net.shopxx.Order.desc("createDate"));
        } else
        if(orderType == net.shopxx.entity.Product.OrderType.dateDesc)
        {
            list.add(net.shopxx.Order.desc("createDate"));
        } else
        {
            list.add(net.shopxx.Order.desc("isTop"));
            list.add(net.shopxx.Order.desc("modifyDate"));
        }
        return super.IIIllIlI(criteriaquery, pageable);
    }

    public Page findPage(Member member, Pageable pageable)
    {
        if(member == null)
        {
            return new Page(Collections.emptyList(), 0L, pageable);
        } else
        {
            CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
            CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Product);
            Root root = criteriaquery.from(net/shopxx/entity/Product);
            criteriaquery.select(root);
            criteriaquery.where(criteriabuilder.equal(root.join("favoriteMembers"), member));
            return super.IIIllIlI(criteriaquery, pageable);
        }
    }

    public Page findSalesPage(Date beginDate, Date endDate, Pageable pageable)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(java/lang/Object);
        Root root = criteriaquery.from(net/shopxx/entity/Product);
        Join join = root.join("orderItems");
        Join join1 = join.join("order");
        criteriaquery.multiselect(new Selection[] {
            root, criteriabuilder.sum(join.get("quantity")), criteriabuilder.sum(criteriabuilder.prod(join.get("quantity"), join.get("price")))
        });
        Predicate predicate = criteriabuilder.conjunction();
        if(beginDate != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.greaterThanOrEqualTo(join.get("createDate"), beginDate));
        if(endDate != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.lessThanOrEqualTo(join.get("createDate"), endDate));
        predicate = criteriabuilder.and(predicate, criteriabuilder.equal(join1.get("orderStatus"), net.shopxx.entity.Order.OrderStatus.completed));
        predicate = criteriabuilder.and(predicate, criteriabuilder.equal(join1.get("paymentStatus"), net.shopxx.entity.Order.PaymentStatus.paid));
        criteriaquery.where(predicate);
        criteriaquery.groupBy(new Expression[] {
            root.get("id")
        });
        CriteriaQuery criteriaquery1 = criteriabuilder.createQuery(java/lang/Long);
        Root root1 = criteriaquery1.from(net/shopxx/entity/Product);
        Join join2 = root1.join("orderItems");
        Join join3 = join2.join("order");
        Predicate predicate1 = criteriabuilder.conjunction();
        if(beginDate != null)
            predicate1 = criteriabuilder.and(predicate1, criteriabuilder.greaterThanOrEqualTo(join2.get("createDate"), beginDate));
        if(endDate != null)
            predicate1 = criteriabuilder.and(predicate1, criteriabuilder.lessThanOrEqualTo(join2.get("createDate"), endDate));
        predicate1 = criteriabuilder.and(predicate1, criteriabuilder.equal(join3.get("orderStatus"), net.shopxx.entity.Order.OrderStatus.completed));
        criteriaquery1.select(criteriabuilder.countDistinct(root1));
        criteriaquery1.where(predicate1);
        Long long1 = (Long)IIIllIlI.createQuery(criteriaquery1).setFlushMode(FlushModeType.COMMIT).getSingleResult();
        int i = (int)Math.ceil((double)long1.longValue() / (double)pageable.getPageSize());
        if(i < pageable.getPageNumber())
            pageable.setPageNumber(i);
        criteriaquery.orderBy(new Order[] {
            criteriabuilder.desc(criteriabuilder.sum(criteriabuilder.prod(join.get("quantity"), join.get("price"))))
        });
        TypedQuery typedquery = IIIllIlI.createQuery(criteriaquery).setFlushMode(FlushModeType.COMMIT);
        typedquery.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        typedquery.setMaxResults(pageable.getPageSize());
        return new Page(typedquery.getResultList(), long1.longValue(), pageable);
    }

    public Long count(Member favoriteMember, Boolean isMarketable, Boolean isList, Boolean isTop, Boolean isGift, Boolean isOutOfStock, Boolean isStockAlert)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Product);
        Root root = criteriaquery.from(net/shopxx/entity/Product);
        criteriaquery.select(root);
        Predicate predicate = criteriabuilder.conjunction();
        if(favoriteMember != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.join("favoriteMembers"), favoriteMember));
        if(isMarketable != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isMarketable"), isMarketable));
        if(isList != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isList"), isList));
        if(isTop != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isTop"), isTop));
        if(isGift != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isGift"), isGift));
        Path path = root.get("stock");
        Path path1 = root.get("allocatedStock");
        if(isOutOfStock != null)
            if(isOutOfStock.booleanValue())
                predicate = criteriabuilder.and(new Predicate[] {
                    predicate, criteriabuilder.isNotNull(path), criteriabuilder.lessThanOrEqualTo(path, path1)
                });
            else
                predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.isNull(path), criteriabuilder.greaterThan(path, path1)));
        if(isStockAlert != null)
        {
            Setting setting = SettingUtils.get();
            if(isStockAlert.booleanValue())
                predicate = criteriabuilder.and(new Predicate[] {
                    predicate, criteriabuilder.isNotNull(path), criteriabuilder.lessThanOrEqualTo(path, criteriabuilder.sum(path1, setting.getStockAlertCount()))
                });
            else
                predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.isNull(path), criteriabuilder.greaterThan(path, criteriabuilder.sum(path1, setting.getStockAlertCount()))));
        }
        criteriaquery.where(predicate);
        return super.IIIllIlI(criteriaquery, null);
    }

    public boolean isPurchased(Member member, Product product)
    {
        if(member == null || product == null)
            return false;
        String s = "select count(*) from OrderItem orderItem where orderItem.product = :product and orderItem.order.member = :member and orderItem.order.orderStatus = :orderStatus";
        Long long1 = (Long)IIIllIlI.createQuery(s, java/lang/Long).setFlushMode(FlushModeType.COMMIT).setParameter("product", product).setParameter("member", member).setParameter("orderStatus", net.shopxx.entity.Order.OrderStatus.completed).getSingleResult();
        return long1.longValue() > 0L;
    }

    public void persist(Product product)
    {
        Assert.notNull(product);
        IIIllIlI(product);
        super.persist(product);
    }

    public Product merge(Product product)
    {
        Assert.notNull(product);
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
        IIIllIlI(product);
        return (Product)super.merge(product);
    }

    public void remove(Product product)
    {
        if(product != null)
        {
            Goods goods = product.getGoods();
            if(goods != null && goods.getProducts() != null)
            {
                goods.getProducts().remove(product);
                if(goods.getProducts().isEmpty())
                    IIIlllII.remove(goods);
            }
        }
        super.remove(product);
    }

    private void IIIllIlI(Product product)
    {
        if(product == null)
            return;
        if(StringUtils.isEmpty(product.getSn()))
        {
            String s;
            do
                s = IIIlllIl.generate(net.shopxx.entity.Sn.Type.product);
            while(snExists(s));
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
        return merge((Product)obj);
    }

    public volatile void persist(Object obj)
    {
        persist((Product)obj);
    }

    public volatile void remove(Object obj)
    {
        remove((Product)obj);
    }

    private static final Pattern IIIllIll = Pattern.compile("\\d*");
    private GoodsDao IIIlllII;
    private SnDao IIIlllIl;


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

        final ProductDaoImpl IIIllIlI;

        _cls1()
        {
            IIIllIlI = ProductDaoImpl.this;
            super();
        }
    }

}
