// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.io.Serializable;
import java.util.Date;

public class SafeKey
    implements Serializable
{

    public SafeKey()
    {
    }

    public String getValue()
    {
        return IIIllIlI;
    }

    public void setValue(String value)
    {
        IIIllIlI = value;
    }

    public Date getExpire()
    {
        return IIIllIll;
    }

    public void setExpire(Date expire)
    {
        IIIllIll = expire;
    }

    public boolean hasExpired()
    {
        return getExpire() != null && (new Date()).after(getExpire());
    }

    private static final long serialVersionUID = 0x89881cac13e5eae4L;
    private String IIIllIlI;
    private Date IIIllIll;
}
