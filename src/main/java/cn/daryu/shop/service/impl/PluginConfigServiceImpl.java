// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import net.shopxx.dao.PluginConfigDao;
import net.shopxx.entity.PluginConfig;
import net.shopxx.service.PluginConfigService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class PluginConfigServiceImpl extends BaseServiceImpl
    implements PluginConfigService
{

    public PluginConfigServiceImpl()
    {
    }

    public void setBaseDao(PluginConfigDao pluginConfigDao)
    {
        super.setBaseDao(pluginConfigDao);
    }

    public boolean pluginIdExists(String pluginId)
    {
        return IIIllIlI.pluginIdExists(pluginId);
    }

    public PluginConfig findByPluginId(String pluginId)
    {
        return IIIllIlI.findByPluginId(pluginId);
    }

    private PluginConfigDao IIIllIlI;
}
