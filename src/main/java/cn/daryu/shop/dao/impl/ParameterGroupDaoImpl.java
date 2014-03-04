// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.*;
import javax.persistence.*;
import net.shopxx.dao.ParameterDao;
import net.shopxx.dao.ParameterGroupDao;
import net.shopxx.entity.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class ParameterGroupDaoImpl extends BaseDaoImpl
    implements ParameterGroupDao
{

    public ParameterGroupDaoImpl()
    {
    }

    public ParameterGroup merge(ParameterGroup parameterGroup)
    {
        Assert.notNull(parameterGroup);
        HashSet hashset = new HashSet();
        CollectionUtils.select(parameterGroup.getParameters(), new _cls1(), hashset);
        List list = IIIllIll.findList(parameterGroup, hashset);
        for(int i = 0; i < list.size(); i++)
        {
            Parameter parameter = (Parameter)list.get(i);
            String s = "select product from Product product join product.parameterValue parameterValue where index(parameterValue) = :parameter";
            List list1 = IIIllIlI.createQuery(s, net/shopxx/entity/Product).setFlushMode(FlushModeType.COMMIT).setParameter("parameter", parameter).getResultList();
            for(Iterator iterator = list1.iterator(); iterator.hasNext();)
            {
                Product product = (Product)iterator.next();
                product.getParameterValue().remove(parameter);
                if(i % 20 == 0)
                {
                    super.flush();
                    super.clear();
                }
            }

        }

        return (ParameterGroup)super.merge(parameterGroup);
    }

    public void remove(ParameterGroup parameterGroup)
    {
        if(parameterGroup != null)
        {
            for(int i = 0; i < parameterGroup.getParameters().size(); i++)
            {
                Parameter parameter = (Parameter)parameterGroup.getParameters().get(i);
                String s = "select product from Product product join product.parameterValue parameterValue where index(parameterValue) = :parameter";
                List list = IIIllIlI.createQuery(s, net/shopxx/entity/Product).setFlushMode(FlushModeType.COMMIT).setParameter("parameter", parameter).getResultList();
                for(Iterator iterator = list.iterator(); iterator.hasNext();)
                {
                    Product product = (Product)iterator.next();
                    product.getParameterValue().remove(parameter);
                    if(i % 20 == 0)
                    {
                        super.flush();
                        super.clear();
                    }
                }

            }

            super.remove((ParameterGroup)super.merge(parameterGroup));
        }
    }

    public volatile Object merge(Object obj)
    {
        return merge((ParameterGroup)obj);
    }

    public volatile void remove(Object obj)
    {
        remove((ParameterGroup)obj);
    }

    private ParameterDao IIIllIll;

    private class _cls1
        implements Predicate
    {

        public boolean evaluate(Object object)
        {
            Parameter parameter = (Parameter)object;
            return parameter != null && parameter.getId() != null;
        }

        final ParameterGroupDaoImpl IIIllIlI;

        _cls1()
        {
            IIIllIlI = ParameterGroupDaoImpl.this;
            super();
        }
    }

}
