// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.util.*;
import net.shopxx.plugin.PaymentPlugin;
import net.shopxx.plugin.StoragePlugin;
import net.shopxx.service.PluginService;
import org.apache.commons.collections.CollectionUtils;

public class PluginServiceImpl
    implements PluginService
{

    public PluginServiceImpl()
    {
        IIIllIlI = new ArrayList();
        IIIllIll = new ArrayList();
        IIIlllII = new HashMap();
        IIIlllIl = new HashMap();
    }

    public List getPaymentPlugins()
    {
        Collections.sort(IIIllIlI);
        return IIIllIlI;
    }

    public List getStoragePlugins()
    {
        Collections.sort(IIIllIll);
        return IIIllIll;
    }

    public List getPaymentPlugins(boolean isEnabled)
    {
        ArrayList arraylist = new ArrayList();
        CollectionUtils.select(IIIllIlI, new _cls1(isEnabled), arraylist);
        Collections.sort(arraylist);
        return arraylist;
    }

    public List getStoragePlugins(boolean isEnabled)
    {
        ArrayList arraylist = new ArrayList();
        CollectionUtils.select(IIIllIll, new _cls2(isEnabled), arraylist);
        Collections.sort(arraylist);
        return arraylist;
    }

    public PaymentPlugin getPaymentPlugin(String id)
    {
        return (PaymentPlugin)IIIlllII.get(id);
    }

    public StoragePlugin getStoragePlugin(String id)
    {
        return (StoragePlugin)IIIlllIl.get(id);
    }

    private List IIIllIlI;
    private List IIIllIll;
    private Map IIIlllII;
    private Map IIIlllIl;

    private class _cls1
        implements Predicate
    {

        public boolean evaluate(Object object)
        {
            PaymentPlugin paymentplugin = (PaymentPlugin)object;
            return paymentplugin.getIsEnabled() == IIIllIll;
        }

        final PluginServiceImpl IIIllIlI;
        private final boolean IIIllIll;

        _cls1(boolean flag)
        {
            IIIllIlI = PluginServiceImpl.this;
            IIIllIll = flag;
            super();
        }
    }


    private class _cls2
        implements Predicate
    {

        public boolean evaluate(Object object)
        {
            StoragePlugin storageplugin = (StoragePlugin)object;
            return storageplugin.getIsEnabled() == IIIllIll;
        }

        final PluginServiceImpl IIIllIlI;
        private final boolean IIIllIll;

        _cls2(boolean flag)
        {
            IIIllIlI = PluginServiceImpl.this;
            IIIllIll = flag;
            super();
        }
    }

}
