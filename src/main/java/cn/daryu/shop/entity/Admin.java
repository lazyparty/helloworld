// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.*;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Order

public class Admin extends BaseEntity
{

    public Admin()
    {
        IIlIIlIl = new HashSet();
        IIlIIllI = new HashSet();
    }

    public String getUsername()
    {
        return IIIllIlI;
    }

    public void setUsername(String username)
    {
        IIIllIlI = username;
    }

    public String getPassword()
    {
        return IIIllIll;
    }

    public void setPassword(String password)
    {
        IIIllIll = password;
    }

    public String getEmail()
    {
        return IIIlllII;
    }

    public void setEmail(String email)
    {
        IIIlllII = email;
    }

    public String getName()
    {
        return IIIlllIl;
    }

    public void setName(String name)
    {
        IIIlllIl = name;
    }

    public String getDepartment()
    {
        return IIIllllI;
    }

    public void setDepartment(String department)
    {
        IIIllllI = department;
    }

    public Boolean getIsEnabled()
    {
        return IIIlllll;
    }

    public void setIsEnabled(Boolean isEnabled)
    {
        IIIlllll = isEnabled;
    }

    public Boolean getIsLocked()
    {
        return IIlIIIII;
    }

    public void setIsLocked(Boolean isLocked)
    {
        IIlIIIII = isLocked;
    }

    public Integer getLoginFailureCount()
    {
        return IIlIIIIl;
    }

    public void setLoginFailureCount(Integer loginFailureCount)
    {
        IIlIIIIl = loginFailureCount;
    }

    public Date getLockedDate()
    {
        return IIlIIIlI;
    }

    public void setLockedDate(Date lockedDate)
    {
        IIlIIIlI = lockedDate;
    }

    public Date getLoginDate()
    {
        return IIlIIIll;
    }

    public void setLoginDate(Date loginDate)
    {
        IIlIIIll = loginDate;
    }

    public String getLoginIp()
    {
        return IIlIIlII;
    }

    public void setLoginIp(String loginIp)
    {
        IIlIIlII = loginIp;
    }

    public Set getRoles()
    {
        return IIlIIlIl;
    }

    public void setRoles(Set roles)
    {
        IIlIIlIl = roles;
    }

    public Set getOrders()
    {
        return IIlIIllI;
    }

    public void setOrders(Set orders)
    {
        IIlIIllI = orders;
    }

    public void preRemove()
    {
        Set set = getOrders();
        if(set != null)
        {
            Order order;
            for(Iterator iterator = set.iterator(); iterator.hasNext(); order.setOperator(null))
            {
                order = (Order)iterator.next();
                order.setLockExpire(null);
            }

        }
    }

    private static final long serialVersionUID = 0x97a56a9304af3f36L;
    private String IIIllIlI;
    private String IIIllIll;
    private String IIIlllII;
    private String IIIlllIl;
    private String IIIllllI;
    private Boolean IIIlllll;
    private Boolean IIlIIIII;
    private Integer IIlIIIIl;
    private Date IIlIIIlI;
    private Date IIlIIIll;
    private String IIlIIlII;
    private Set IIlIIlIl;
    private Set IIlIIllI;
}
