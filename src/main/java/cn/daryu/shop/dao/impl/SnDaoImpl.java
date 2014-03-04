// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import javax.persistence.*;
import net.shopxx.dao.SnDao;
import net.shopxx.entity.Sn;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class SnDaoImpl
    implements SnDao, InitializingBean
{

    public SnDaoImpl()
    {
    }

    public void afterPropertiesSet()
    {
        IIIllIlI = new HiloOptimizer(net.shopxx.entity.Sn.Type.product, IIlIIIIl, IIlIIIlI);
        IIIllIll = new HiloOptimizer(net.shopxx.entity.Sn.Type.order, IIlIIIll, IIlIIlII);
        IIIlllII = new HiloOptimizer(net.shopxx.entity.Sn.Type.payment, IIlIIlIl, IIlIIllI);
        IIIlllIl = new HiloOptimizer(net.shopxx.entity.Sn.Type.refunds, IIlIIlll, IIlIlIII);
        IIIllllI = new HiloOptimizer(net.shopxx.entity.Sn.Type.shipping, IIlIlIIl, IIlIlIlI);
        IIIlllll = new HiloOptimizer(net.shopxx.entity.Sn.Type.returns, IIlIlIll, IIlIllII);
    }

    public String generate(net.shopxx.entity.Sn.Type type)
    {
        Assert.notNull(type);
        if(type == net.shopxx.entity.Sn.Type.product)
            return IIIllIlI.generate();
        if(type == net.shopxx.entity.Sn.Type.order)
            return IIIllIll.generate();
        if(type == net.shopxx.entity.Sn.Type.payment)
            return IIIlllII.generate();
        if(type == net.shopxx.entity.Sn.Type.refunds)
            return IIIlllIl.generate();
        if(type == net.shopxx.entity.Sn.Type.shipping)
            return IIIllllI.generate();
        if(type == net.shopxx.entity.Sn.Type.returns)
            return IIIlllll.generate();
        else
            return null;
    }

    private long IIIllIlI(net.shopxx.entity.Sn.Type type)
    {
        String s = "select sn from Sn sn where sn.type = :type";
        Sn sn = (Sn)IIlIIIII.createQuery(s, net/shopxx/entity/Sn).setFlushMode(FlushModeType.COMMIT).setParameter("type", type).setLockMode(LockModeType.PESSIMISTIC_WRITE).getSingleResult();
        long l = sn.getLastValue().longValue();
        sn.setLastValue(Long.valueOf(l + 1L));
        IIlIIIII.merge(sn);
        return l;
    }

    static long IIIllIlI(SnDaoImpl sndaoimpl, net.shopxx.entity.Sn.Type type)
    {
        return sndaoimpl.IIIllIlI(type);
    }

    private HiloOptimizer IIIllIlI;
    private HiloOptimizer IIIllIll;
    private HiloOptimizer IIIlllII;
    private HiloOptimizer IIIlllIl;
    private HiloOptimizer IIIllllI;
    private HiloOptimizer IIIlllll;
    private EntityManager IIlIIIII;
    private String IIlIIIIl;
    private int IIlIIIlI;
    private String IIlIIIll;
    private int IIlIIlII;
    private String IIlIIlIl;
    private int IIlIIllI;
    private String IIlIIlll;
    private int IIlIlIII;
    private String IIlIlIIl;
    private int IIlIlIlI;
    private String IIlIlIll;
    private int IIlIllII;

    private class HiloOptimizer
    {

        public synchronized String generate()
        {
            if(IIIllllI > IIIlllIl)
            {
                IIlIIIII = SnDaoImpl.IIIllIlI(IIIllIlI, IIIllIll);
                IIIllllI = IIlIIIII != 0L ? 0 : 1;
                IIIlllll = IIlIIIII * (long)(IIIlllIl + 1);
            }
            return (new StringBuilder(String.valueOf(FreemarkerUtils.process(IIIlllII, null)))).append(IIIlllll + (long)(IIIllllI++)).toString();
        }

        private net.shopxx.entity.Sn.Type IIIllIll;
        private String IIIlllII;
        private int IIIlllIl;
        private int IIIllllI;
        private long IIIlllll;
        private long IIlIIIII;
        final SnDaoImpl IIIllIlI;

        public HiloOptimizer(net.shopxx.entity.Sn.Type type, String prefix, int maxLo)
        {
            IIIllIlI = SnDaoImpl.this;
            super();
            IIIllIll = type;
            IIIlllII = prefix == null ? "" : prefix.replace("{", "${");
            IIIlllIl = maxLo;
            IIIllllI = maxLo + 1;
        }
    }

}
