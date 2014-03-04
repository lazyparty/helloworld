// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import javax.persistence.*;
import net.shopxx.dao.PluginConfigDao;
import net.shopxx.entity.PluginConfig;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class PluginConfigDaoImpl extends BaseDaoImpl
    implements PluginConfigDao
{

    public PluginConfigDaoImpl()
    {
    }

    public boolean pluginIdExists(String pluginId)
    {
        if(pluginId == null)
            return false;
        String s = "select count(*) from PluginConfig pluginConfig where pluginConfig.pluginId = :pluginId";
        Long long1 = (Long)IIIllIlI.createQuery(s, java/lang/Long).setFlushMode(FlushModeType.COMMIT).setParameter("pluginId", pluginId).getSingleResult();
        return long1.longValue() > 0L;
    }

    public PluginConfig findByPluginId(String pluginId)
    {
        if(pluginId == null)
            return null;
        try
        {
            String s = "select pluginConfig from PluginConfig pluginConfig where pluginConfig.pluginId = :pluginId";
            return (PluginConfig)IIIllIlI.createQuery(s, net/shopxx/entity/PluginConfig).setFlushMode(FlushModeType.COMMIT).setParameter("pluginId", pluginId).getSingleResult();
        }
        catch(NoResultException noresultexception)
        {
            return null;
        }
    }
}
