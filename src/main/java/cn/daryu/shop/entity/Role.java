// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.*;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity

public class Role extends BaseEntity
{

    public Role()
    {
        IIIlllIl = new ArrayList();
        IIIllllI = new HashSet();
    }

    public String getName()
    {
        return IIIllIlI;
    }

    public void setName(String name)
    {
        IIIllIlI = name;
    }

    public Boolean getIsSystem()
    {
        return IIIllIll;
    }

    public void setIsSystem(Boolean isSystem)
    {
        IIIllIll = isSystem;
    }

    public String getDescription()
    {
        return IIIlllII;
    }

    public void setDescription(String description)
    {
        IIIlllII = description;
    }

    public List getAuthorities()
    {
        return IIIlllIl;
    }

    public void setAuthorities(List authorities)
    {
        IIIlllIl = authorities;
    }

    public Set getAdmins()
    {
        return IIIllllI;
    }

    public void setAdmins(Set admins)
    {
        IIIllllI = admins;
    }

    private static final long serialVersionUID = 0xa4362ab8c84f6c44L;
    private String IIIllIlI;
    private Boolean IIIllIll;
    private String IIIlllII;
    private List IIIlllIl;
    private Set IIIllllI;
}
