// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.plugin;

import java.io.File;
import java.util.List;
import net.shopxx.entity.PluginConfig;
import net.shopxx.service.PluginConfigService;
import org.apache.commons.lang.builder.*;
import org.springframework.stereotype.Component;

public abstract class StoragePlugin
    implements Comparable
{

    public StoragePlugin()
    {
    }

    public final String getId()
    {
        return ((Component)getClass().getAnnotation(org/springframework/stereotype/Component)).value();
    }

    public abstract String getName();

    public abstract String getVersion();

    public abstract String getAuthor();

    public abstract String getSiteUrl();

    public abstract String getInstallUrl();

    public abstract String getUninstallUrl();

    public abstract String getSettingUrl();

    public boolean getIsInstalled()
    {
        return IIIllIlI.pluginIdExists(getId());
    }

    public PluginConfig getPluginConfig()
    {
        return IIIllIlI.findByPluginId(getId());
    }

    public boolean getIsEnabled()
    {
        PluginConfig pluginconfig = getPluginConfig();
        return pluginconfig == null ? false : pluginconfig.getIsEnabled().booleanValue();
    }

    public String getAttribute(String name)
    {
        PluginConfig pluginconfig = getPluginConfig();
        return pluginconfig == null ? null : pluginconfig.getAttribute(name);
    }

    public Integer getOrder()
    {
        PluginConfig pluginconfig = getPluginConfig();
        return pluginconfig == null ? null : pluginconfig.getOrder();
    }

    public abstract void upload(String s, File file, String s1);

    public abstract String getUrl(String s);

    public abstract List browser(String s);

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        if(this == obj)
        {
            return true;
        } else
        {
            StoragePlugin storageplugin = (StoragePlugin)obj;
            return (new EqualsBuilder()).append(getId(), storageplugin.getId()).isEquals();
        }
    }

    public int hashCode()
    {
        return (new HashCodeBuilder(17, 37)).append(getId()).toHashCode();
    }

    public int compareTo(StoragePlugin storagePlugin)
    {
        return (new CompareToBuilder()).append(getOrder(), storagePlugin.getOrder()).append(getId(), storagePlugin.getId()).toComparison();
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((StoragePlugin)obj);
    }

    private PluginConfigService IIIllIlI;
}
