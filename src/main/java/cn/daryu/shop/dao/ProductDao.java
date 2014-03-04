// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao;

import java.math.BigDecimal;
import java.util.*;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.entity.*;

// Referenced classes of package net.shopxx.dao:
//            BaseDao

public interface ProductDao
    extends BaseDao
{

    public abstract boolean snExists(String s);

    public abstract Product findBySn(String s);

    public abstract List search(String s, Boolean boolean1, Integer integer);

    public abstract List findList(ProductCategory productcategory, Brand brand, Promotion promotion, List list, Map map, BigDecimal bigdecimal, BigDecimal bigdecimal1, 
            Boolean boolean1, Boolean boolean2, Boolean boolean3, Boolean boolean4, Boolean boolean5, Boolean boolean6, net.shopxx.entity.Product.OrderType ordertype, 
            Integer integer, List list1, List list2);

    public abstract List findList(ProductCategory productcategory, Date date, Date date1, Integer integer, Integer integer1);

    public abstract List findList(Goods goods, Set set);

    public abstract Page findPage(ProductCategory productcategory, Brand brand, Promotion promotion, List list, Map map, BigDecimal bigdecimal, BigDecimal bigdecimal1, 
            Boolean boolean1, Boolean boolean2, Boolean boolean3, Boolean boolean4, Boolean boolean5, Boolean boolean6, net.shopxx.entity.Product.OrderType ordertype, 
            Pageable pageable);

    public abstract Page findPage(Member member, Pageable pageable);

    public abstract Page findSalesPage(Date date, Date date1, Pageable pageable);

    public abstract Long count(Member member, Boolean boolean1, Boolean boolean2, Boolean boolean3, Boolean boolean4, Boolean boolean5, Boolean boolean6);

    public abstract boolean isPurchased(Member member, Product product);
}
