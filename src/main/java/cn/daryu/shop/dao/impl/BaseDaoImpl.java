// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.Attribute;
import net.shopxx.Filter;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.BaseDao;
import net.shopxx.entity.OrderEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

public abstract class BaseDaoImpl
    implements BaseDao
{

    public BaseDaoImpl()
    {
        java.lang.reflect.Type type = getClass().getGenericSuperclass();
        java.lang.reflect.Type atype[] = ((ParameterizedType)type).getActualTypeArguments();
        IIIllIll = (Class)atype[0];
    }

    public Object find(Serializable id)
    {
        if(id != null)
            return IIIllIlI.find(IIIllIll, id);
        else
            return null;
    }

    public List findList(Integer first, Integer count, List filters, List orders)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(IIIllIll);
        criteriaquery.select(criteriaquery.from(IIIllIll));
        return IIIllIlI(criteriaquery, first, count, filters, orders);
    }

    public Page findPage(Pageable pageable)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(IIIllIll);
        criteriaquery.select(criteriaquery.from(IIIllIll));
        return IIIllIlI(criteriaquery, pageable);
    }

    public transient long count(Filter filters[])
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(IIIllIll);
        criteriaquery.select(criteriaquery.from(IIIllIll));
        return IIIllIlI(criteriaquery, filters == null ? null : Arrays.asList(filters)).longValue();
    }

    public void persist(Object entity)
    {
        Assert.notNull(entity);
        IIIllIlI.persist(entity);
    }

    public Object merge(Object entity)
    {
        Assert.notNull(entity);
        return IIIllIlI.merge(entity);
    }

    public void remove(Object entity)
    {
        if(entity != null)
            IIIllIlI.remove(entity);
    }

    public void refresh(Object entity)
    {
        Assert.notNull(entity);
        IIIllIlI.refresh(entity);
    }

    public Serializable getIdentifier(Object entity)
    {
        Assert.notNull(entity);
        return (Serializable)IIIllIlI.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
    }

    public boolean isManaged(Object entity)
    {
        return IIIllIlI.contains(entity);
    }

    public void detach(Object entity)
    {
        IIIllIlI.detach(entity);
    }

    public void lock(Object entity, LockModeType lockModeType)
    {
        if(entity != null && lockModeType != null)
            IIIllIlI.lock(entity, lockModeType);
    }

    public void clear()
    {
        IIIllIlI.clear();
    }

    public void flush()
    {
        IIIllIlI.flush();
    }

    protected List IIIllIlI(CriteriaQuery criteriaquery, Integer integer, Integer integer1, List list, List list1)
    {
        Assert.notNull(criteriaquery);
        Assert.notNull(criteriaquery.getSelection());
        Assert.notEmpty(criteriaquery.getRoots());
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        Root root = IIIllIlI(criteriaquery);
        IIIllIll(criteriaquery, list);
        IIIlllII(criteriaquery, list1);
        if(criteriaquery.getOrderList().isEmpty())
            if(net/shopxx/entity/OrderEntity.isAssignableFrom(IIIllIll))
                criteriaquery.orderBy(new Order[] {
                    criteriabuilder.asc(root.get("order"))
                });
            else
                criteriaquery.orderBy(new Order[] {
                    criteriabuilder.desc(root.get("createDate"))
                });
        TypedQuery typedquery = IIIllIlI.createQuery(criteriaquery).setFlushMode(FlushModeType.COMMIT);
        if(integer != null)
            typedquery.setFirstResult(integer.intValue());
        if(integer1 != null)
            typedquery.setMaxResults(integer1.intValue());
        return typedquery.getResultList();
    }

    protected Page IIIllIlI(CriteriaQuery criteriaquery, Pageable pageable)
    {
        Assert.notNull(criteriaquery);
        Assert.notNull(criteriaquery.getSelection());
        Assert.notEmpty(criteriaquery.getRoots());
        if(pageable == null)
            pageable = new Pageable();
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        Root root = IIIllIlI(criteriaquery);
        IIIllIll(criteriaquery, pageable);
        IIIlllII(criteriaquery, pageable);
        if(criteriaquery.getOrderList().isEmpty())
            if(net/shopxx/entity/OrderEntity.isAssignableFrom(IIIllIll))
                criteriaquery.orderBy(new Order[] {
                    criteriabuilder.asc(root.get("order"))
                });
            else
                criteriaquery.orderBy(new Order[] {
                    criteriabuilder.desc(root.get("createDate"))
                });
        long l = IIIllIlI(criteriaquery, ((List) (null))).longValue();
        int i = (int)Math.ceil((double)l / (double)pageable.getPageSize());
        if(i < pageable.getPageNumber())
            pageable.setPageNumber(i);
        TypedQuery typedquery = IIIllIlI.createQuery(criteriaquery).setFlushMode(FlushModeType.COMMIT);
        typedquery.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        typedquery.setMaxResults(pageable.getPageSize());
        return new Page(typedquery.getResultList(), l, pageable);
    }

    protected Long IIIllIlI(CriteriaQuery criteriaquery, List list)
    {
        Assert.notNull(criteriaquery);
        Assert.notNull(criteriaquery.getSelection());
        Assert.notEmpty(criteriaquery.getRoots());
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        IIIllIll(criteriaquery, list);
        CriteriaQuery criteriaquery1 = criteriabuilder.createQuery(java/lang/Long);
        Root root;
        Root root2;
        for(Iterator iterator = criteriaquery.getRoots().iterator(); iterator.hasNext(); IIIllIlI(((From) (root)), ((From) (root2))))
        {
            root = (Root)iterator.next();
            root2 = criteriaquery1.from(root.getJavaType());
            root2.alias(IIIllIlI(((Selection) (root))));
        }

        Root root1 = IIIllIlI(criteriaquery1, criteriaquery.getResultType());
        criteriaquery1.select(criteriabuilder.count(root1));
        if(criteriaquery.getGroupList() != null)
            criteriaquery1.groupBy(criteriaquery.getGroupList());
        if(criteriaquery.getGroupRestriction() != null)
            criteriaquery1.having(criteriaquery.getGroupRestriction());
        if(criteriaquery.getRestriction() != null)
            criteriaquery1.where(criteriaquery.getRestriction());
        return (Long)IIIllIlI.createQuery(criteriaquery1).setFlushMode(FlushModeType.COMMIT).getSingleResult();
    }

    private synchronized String IIIllIlI(Selection selection)
    {
        if(selection != null)
        {
            String s = selection.getAlias();
            if(s == null)
            {
                if(IIIlllII >= 1000L)
                    IIIlllII = 0L;
                s = (new StringBuilder("shopxxGeneratedAlias")).append(IIIlllII++).toString();
                selection.alias(s);
            }
            return s;
        } else
        {
            return null;
        }
    }

    private Root IIIllIlI(CriteriaQuery criteriaquery)
    {
        if(criteriaquery != null)
            return IIIllIlI(criteriaquery, criteriaquery.getResultType());
        else
            return null;
    }

    private Root IIIllIlI(CriteriaQuery criteriaquery, Class class1)
    {
        if(criteriaquery != null && criteriaquery.getRoots() != null && class1 != null)
        {
            for(Iterator iterator = criteriaquery.getRoots().iterator(); iterator.hasNext();)
            {
                Root root = (Root)iterator.next();
                if(class1.equals(root.getJavaType()))
                    return (Root)root.as(class1);
            }

        }
        return null;
    }

    private void IIIllIlI(From from, From from1)
    {
        Join join;
        Join join1;
        for(Iterator iterator = from.getJoins().iterator(); iterator.hasNext(); IIIllIlI(((From) (join)), ((From) (join1))))
        {
            join = (Join)iterator.next();
            join1 = from1.join(join.getAttribute().getName(), join.getJoinType());
            join1.alias(IIIllIlI(((Selection) (join))));
        }

        Fetch fetch;
        Fetch fetch1;
        for(Iterator iterator1 = from.getFetches().iterator(); iterator1.hasNext(); IIIllIlI(fetch, fetch1))
        {
            fetch = (Fetch)iterator1.next();
            fetch1 = from1.fetch(fetch.getAttribute().getName());
        }

    }

    private void IIIllIlI(Fetch fetch, Fetch fetch1)
    {
        Fetch fetch2;
        Fetch fetch3;
        for(Iterator iterator = fetch.getFetches().iterator(); iterator.hasNext(); IIIllIlI(fetch2, fetch3))
        {
            fetch2 = (Fetch)iterator.next();
            fetch3 = fetch1.fetch(fetch2.getAttribute().getName());
        }

    }

    private void IIIllIll(CriteriaQuery criteriaquery, List list)
    {
        if(criteriaquery == null || list == null || list.isEmpty())
            return;
        Root root = IIIllIlI(criteriaquery);
        if(root == null)
            return;
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        javax.persistence.criteria.Predicate predicate = criteriaquery.getRestriction() == null ? criteriabuilder.conjunction() : criteriaquery.getRestriction();
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            Filter filter = (Filter)iterator.next();
            if(filter != null && !StringUtils.isEmpty(filter.getProperty()))
                if(filter.getOperator() == net.shopxx.Filter.Operator.eq && filter.getValue() != null)
                {
                    if(filter.getIgnoreCase() != null && filter.getIgnoreCase().booleanValue() && (filter.getValue() instanceof String))
                        predicate = criteriabuilder.and(predicate, criteriabuilder.equal(criteriabuilder.lower(root.get(filter.getProperty())), ((String)filter.getValue()).toLowerCase()));
                    else
                        predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get(filter.getProperty()), filter.getValue()));
                } else
                if(filter.getOperator() == net.shopxx.Filter.Operator.ne && filter.getValue() != null)
                {
                    if(filter.getIgnoreCase() != null && filter.getIgnoreCase().booleanValue() && (filter.getValue() instanceof String))
                        predicate = criteriabuilder.and(predicate, criteriabuilder.notEqual(criteriabuilder.lower(root.get(filter.getProperty())), ((String)filter.getValue()).toLowerCase()));
                    else
                        predicate = criteriabuilder.and(predicate, criteriabuilder.notEqual(root.get(filter.getProperty()), filter.getValue()));
                } else
                if(filter.getOperator() == net.shopxx.Filter.Operator.gt && filter.getValue() != null)
                    predicate = criteriabuilder.and(predicate, criteriabuilder.gt(root.get(filter.getProperty()), (Number)filter.getValue()));
                else
                if(filter.getOperator() == net.shopxx.Filter.Operator.lt && filter.getValue() != null)
                    predicate = criteriabuilder.and(predicate, criteriabuilder.lt(root.get(filter.getProperty()), (Number)filter.getValue()));
                else
                if(filter.getOperator() == net.shopxx.Filter.Operator.ge && filter.getValue() != null)
                    predicate = criteriabuilder.and(predicate, criteriabuilder.ge(root.get(filter.getProperty()), (Number)filter.getValue()));
                else
                if(filter.getOperator() == net.shopxx.Filter.Operator.le && filter.getValue() != null)
                    predicate = criteriabuilder.and(predicate, criteriabuilder.le(root.get(filter.getProperty()), (Number)filter.getValue()));
                else
                if(filter.getOperator() == net.shopxx.Filter.Operator.like && filter.getValue() != null && (filter.getValue() instanceof String))
                    predicate = criteriabuilder.and(predicate, criteriabuilder.like(root.get(filter.getProperty()), (String)filter.getValue()));
                else
                if(filter.getOperator() == net.shopxx.Filter.Operator.in && filter.getValue() != null)
                    predicate = criteriabuilder.and(predicate, root.get(filter.getProperty()).in(new Object[] {
                        filter.getValue()
                    }));
                else
                if(filter.getOperator() == net.shopxx.Filter.Operator.isNull)
                    predicate = criteriabuilder.and(predicate, root.get(filter.getProperty()).isNull());
                else
                if(filter.getOperator() == net.shopxx.Filter.Operator.isNotNull)
                    predicate = criteriabuilder.and(predicate, root.get(filter.getProperty()).isNotNull());
        }

        criteriaquery.where(predicate);
    }

    private void IIIllIll(CriteriaQuery criteriaquery, Pageable pageable)
    {
        if(criteriaquery == null || pageable == null)
            return;
        Root root = IIIllIlI(criteriaquery);
        if(root == null)
            return;
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        javax.persistence.criteria.Predicate predicate = criteriaquery.getRestriction() == null ? criteriabuilder.conjunction() : criteriaquery.getRestriction();
        if(StringUtils.isNotEmpty(pageable.getSearchProperty()) && StringUtils.isNotEmpty(pageable.getSearchValue()))
            predicate = criteriabuilder.and(predicate, criteriabuilder.like(root.get(pageable.getSearchProperty()), (new StringBuilder("%")).append(pageable.getSearchValue()).append("%").toString()));
        if(pageable.getFilters() != null)
        {
            for(Iterator iterator = pageable.getFilters().iterator(); iterator.hasNext();)
            {
                Filter filter = (Filter)iterator.next();
                if(filter != null && !StringUtils.isEmpty(filter.getProperty()))
                    if(filter.getOperator() == net.shopxx.Filter.Operator.eq && filter.getValue() != null)
                    {
                        if(filter.getIgnoreCase() != null && filter.getIgnoreCase().booleanValue() && (filter.getValue() instanceof String))
                            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(criteriabuilder.lower(root.get(filter.getProperty())), ((String)filter.getValue()).toLowerCase()));
                        else
                            predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get(filter.getProperty()), filter.getValue()));
                    } else
                    if(filter.getOperator() == net.shopxx.Filter.Operator.ne && filter.getValue() != null)
                    {
                        if(filter.getIgnoreCase() != null && filter.getIgnoreCase().booleanValue() && (filter.getValue() instanceof String))
                            predicate = criteriabuilder.and(predicate, criteriabuilder.notEqual(criteriabuilder.lower(root.get(filter.getProperty())), ((String)filter.getValue()).toLowerCase()));
                        else
                            predicate = criteriabuilder.and(predicate, criteriabuilder.notEqual(root.get(filter.getProperty()), filter.getValue()));
                    } else
                    if(filter.getOperator() == net.shopxx.Filter.Operator.gt && filter.getValue() != null)
                        predicate = criteriabuilder.and(predicate, criteriabuilder.gt(root.get(filter.getProperty()), (Number)filter.getValue()));
                    else
                    if(filter.getOperator() == net.shopxx.Filter.Operator.lt && filter.getValue() != null)
                        predicate = criteriabuilder.and(predicate, criteriabuilder.lt(root.get(filter.getProperty()), (Number)filter.getValue()));
                    else
                    if(filter.getOperator() == net.shopxx.Filter.Operator.ge && filter.getValue() != null)
                        predicate = criteriabuilder.and(predicate, criteriabuilder.ge(root.get(filter.getProperty()), (Number)filter.getValue()));
                    else
                    if(filter.getOperator() == net.shopxx.Filter.Operator.le && filter.getValue() != null)
                        predicate = criteriabuilder.and(predicate, criteriabuilder.le(root.get(filter.getProperty()), (Number)filter.getValue()));
                    else
                    if(filter.getOperator() == net.shopxx.Filter.Operator.like && filter.getValue() != null && (filter.getValue() instanceof String))
                        predicate = criteriabuilder.and(predicate, criteriabuilder.like(root.get(filter.getProperty()), (String)filter.getValue()));
                    else
                    if(filter.getOperator() == net.shopxx.Filter.Operator.in && filter.getValue() != null)
                        predicate = criteriabuilder.and(predicate, root.get(filter.getProperty()).in(new Object[] {
                            filter.getValue()
                        }));
                    else
                    if(filter.getOperator() == net.shopxx.Filter.Operator.isNull)
                        predicate = criteriabuilder.and(predicate, root.get(filter.getProperty()).isNull());
                    else
                    if(filter.getOperator() == net.shopxx.Filter.Operator.isNotNull)
                        predicate = criteriabuilder.and(predicate, root.get(filter.getProperty()).isNotNull());
            }

        }
        criteriaquery.where(predicate);
    }

    private void IIIlllII(CriteriaQuery criteriaquery, List list)
    {
        if(criteriaquery == null || list == null || list.isEmpty())
            return;
        Root root = IIIllIlI(criteriaquery);
        if(root == null)
            return;
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        ArrayList arraylist = new ArrayList();
        if(!criteriaquery.getOrderList().isEmpty())
            arraylist.addAll(criteriaquery.getOrderList());
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            net.shopxx.Order order = (net.shopxx.Order)iterator.next();
            if(order.getDirection() == net.shopxx.Order.Direction.asc)
                arraylist.add(criteriabuilder.asc(root.get(order.getProperty())));
            else
            if(order.getDirection() == net.shopxx.Order.Direction.desc)
                arraylist.add(criteriabuilder.desc(root.get(order.getProperty())));
        }

        criteriaquery.orderBy(arraylist);
    }

    private void IIIlllII(CriteriaQuery criteriaquery, Pageable pageable)
    {
        if(criteriaquery == null || pageable == null)
            return;
        Root root = IIIllIlI(criteriaquery);
        if(root == null)
            return;
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        ArrayList arraylist = new ArrayList();
        if(!criteriaquery.getOrderList().isEmpty())
            arraylist.addAll(criteriaquery.getOrderList());
        if(StringUtils.isNotEmpty(pageable.getOrderProperty()) && pageable.getOrderDirection() != null)
            if(pageable.getOrderDirection() == net.shopxx.Order.Direction.asc)
                arraylist.add(criteriabuilder.asc(root.get(pageable.getOrderProperty())));
            else
            if(pageable.getOrderDirection() == net.shopxx.Order.Direction.desc)
                arraylist.add(criteriabuilder.desc(root.get(pageable.getOrderProperty())));
        if(pageable.getOrders() != null)
        {
            for(Iterator iterator = pageable.getOrders().iterator(); iterator.hasNext();)
            {
                net.shopxx.Order order = (net.shopxx.Order)iterator.next();
                if(order.getDirection() == net.shopxx.Order.Direction.asc)
                    arraylist.add(criteriabuilder.asc(root.get(order.getProperty())));
                else
                if(order.getDirection() == net.shopxx.Order.Direction.desc)
                    arraylist.add(criteriabuilder.desc(root.get(order.getProperty())));
            }

        }
        criteriaquery.orderBy(arraylist);
    }

    private Class IIIllIll;
    private static volatile long IIIlllII = 0L;
    protected EntityManager IIIllIlI;

}
