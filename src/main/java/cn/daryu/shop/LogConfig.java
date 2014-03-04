// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx;

import java.io.Serializable;

public class LogConfig
    implements Serializable
{

    public LogConfig()
    {
    }

    public String getOperation()
    {
        return IIIllIlI;
    }

    public void setOperation(String operation)
    {
        IIIllIlI = operation;
    }

    public String getUrlPattern()
    {
        return IIIllIll;
    }

    public void setUrlPattern(String urlPattern)
    {
        IIIllIll = urlPattern;
    }

    private static final long serialVersionUID = 0xf09c940795f5802eL;
    private String IIIllIlI;
    private String IIIllIll;
}
