// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx;

import java.io.Serializable;

public class Principal
    implements Serializable
{

    public Principal(Long id, String username)
    {
        IIIllIlI = id;
        IIIllIll = username;
    }

    public Long getId()
    {
        return IIIllIlI;
    }

    public void setId(Long id)
    {
        IIIllIlI = id;
    }

    public String getUsername()
    {
        return IIIllIll;
    }

    public void setUsername(String username)
    {
        IIIllIll = username;
    }

    public String toString()
    {
        return IIIllIll;
    }

    private static final long serialVersionUID = 0x5079c475846df0c7L;
    private Long IIIllIlI;
    private String IIIllIll;
}
