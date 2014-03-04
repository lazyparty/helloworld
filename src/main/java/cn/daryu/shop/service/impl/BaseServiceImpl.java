// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import net.shopxx.*;
import net.shopxx.dao.BaseDao;
import net.shopxx.service.BaseService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

public class BaseServiceImpl
    implements BaseService
{

    public BaseServiceImpl()
    {
    }

    public void setBaseDao(BaseDao baseDao)
    {
        IIIllIll = baseDao;
    }

    public Object find(Serializable id)
    {
        return IIIllIll.find(id);
    }

    public List findAll()
    {
        return findList(null, null, null, null);
    }

    public transient List findList(Serializable ids[])
    {
        ArrayList arraylist = new ArrayList();
        if(ids != null)
        {
            Serializable aserializable[];
            int j = (aserializable = ids).length;
            for(int i = 0; i < j; i++)
            {
                Serializable serializable = aserializable[i];
                Object obj = find(serializable);
                if(obj != null)
                    arraylist.add(obj);
            }

        }
        return arraylist;
    }

    public List findList(Integer count, List filters, List orders)
    {
        return findList(null, count, filters, orders);
    }

    public List findList(Integer first, Integer count, List filters, List orders)
    {
        return IIIllIll.findList(first, count, filters, orders);
    }

    public Page findPage(Pageable pageable)
    {
        return IIIllIll.findPage(pageable);
    }

    public long count()
    {
        return count(new Filter[0]);
    }

    public transient long count(Filter filters[])
    {
        return IIIllIll.count(filters);
    }

    public boolean exists(Serializable id)
    {
        return IIIllIll.find(id) != null;
    }

    public transient boolean exists(Filter filters[])
    {
        return IIIllIll.count(filters) > 0L;
    }

    public void save(Object entity)
    {
        IIIllIll.persist(entity);
    }

    public Object update(Object entity)
    {
        return IIIllIll.merge(entity);
    }

    public transient Object update(Object entity, String ignoreProperties[])
    {
        Assert.notNull(entity);
        if(IIIllIll.isManaged(entity))
            throw new IllegalArgumentException("Entity must not be managed");
        Object obj = IIIllIll.find(IIIllIll.getIdentifier(entity));
        if(obj != null)
        {
            IIIllIlI(entity, obj, (String[])ArrayUtils.addAll(ignoreProperties, IIIllIlI));
            return update(obj);
        } else
        {
            return update(entity);
        }
    }

    public void delete(Serializable id)
    {
        delete(IIIllIll.find(id));
    }

    public transient void delete(Serializable ids[])
    {
        if(ids != null)
        {
            Serializable aserializable[];
            int j = (aserializable = ids).length;
            for(int i = 0; i < j; i++)
            {
                Serializable serializable = aserializable[i];
                delete(IIIllIll.find(serializable));
            }

        }
    }

    public void delete(Object entity)
    {
        IIIllIll.remove(entity);
    }

    private void IIIllIlI(Object obj, Object obj1, String as[])
    {
        Assert.notNull(obj, "Source must not be null");
        Assert.notNull(obj1, "Target must not be null");
        PropertyDescriptor apropertydescriptor[] = BeanUtils.getPropertyDescriptors(obj1.getClass());
        List list = as == null ? null : Arrays.asList(as);
        PropertyDescriptor apropertydescriptor1[];
        int j = (apropertydescriptor1 = apropertydescriptor).length;
        for(int i = 0; i < j; i++)
        {
            PropertyDescriptor propertydescriptor = apropertydescriptor1[i];
            if(propertydescriptor.getWriteMethod() != null && (as == null || !list.contains(propertydescriptor.getName())))
            {
                PropertyDescriptor propertydescriptor1 = BeanUtils.getPropertyDescriptor(obj.getClass(), propertydescriptor.getName());
                if(propertydescriptor1 != null && propertydescriptor1.getReadMethod() != null)
                    try
                    {
                        Method method = propertydescriptor1.getReadMethod();
                        if(!Modifier.isPublic(method.getDeclaringClass().getModifiers()))
                            method.setAccessible(true);
                        Object obj2 = method.invoke(obj, new Object[0]);
                        Object obj3 = method.invoke(obj1, new Object[0]);
                        if(obj2 != null && obj3 != null && (obj3 instanceof Collection))
                        {
                            Collection collection = (Collection)obj3;
                            collection.clear();
                            collection.addAll((Collection)obj2);
                        } else
                        {
                            Method method1 = propertydescriptor.getWriteMethod();
                            if(!Modifier.isPublic(method1.getDeclaringClass().getModifiers()))
                                method1.setAccessible(true);
                            method1.invoke(obj1, new Object[] {
                                obj2
                            });
                        }
                    }
                    catch(Throwable throwable)
                    {
                        throw new FatalBeanException("Could not copy properties from source to target", throwable);
                    }
            }
        }

    }

    private static final String IIIllIlI[] = {
        "id", "createDate", "modifyDate"
    };
    private BaseDao IIIllIll;

}
