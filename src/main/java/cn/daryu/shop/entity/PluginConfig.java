// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package net.shopxx.entity:
//            OrderEntity

public class PluginConfig extends OrderEntity
{

    public PluginConfig()
    {
        IIIlllII = new HashMap();
    }

    public String getPluginId()
    {
        return IIIllIlI;
    }

    public void setPluginId(String pluginId)
    {
        IIIllIlI = pluginId;
    }

    public Boolean getIsEnabled()
    {
        return IIIllIll;
    }

    public void setIsEnabled(Boolean isEnabled)
    {
        IIIllIll = isEnabled;
    }

    public Map getAttributes()
    {
        return IIIlllII;
    }

    public void setAttributes(Map attributes)
    {
        IIIlllII = attributes;
    }

    public String getAttribute(String name)
    {
        if(getAttributes() != null && name != null)
            return (String)getAttributes().get(name);
        else
            return null;
    }

    public void setAttribute(String name, String value)
    {
        if(getAttributes() != null && name != null)
            getAttributes().put(name, value);
    }

    private static final long serialVersionUID = 0xc387856d6c8f9d5aL;
    private String IIIllIlI;
    private Boolean IIIllIll;
    private Map IIIlllII;
}
