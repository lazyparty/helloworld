// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx;

import java.io.Serializable;

public class Template
    implements Serializable
{

    public Template()
    {
    }

    public String getId()
    {
        return IIIllIlI;
    }

    public void setId(String id)
    {
        IIIllIlI = id;
    }

    public Type getType()
    {
        return IIIllIll;
    }

    public void setType(Type type)
    {
        IIIllIll = type;
    }

    public String getName()
    {
        return IIIlllII;
    }

    public void setName(String name)
    {
        IIIlllII = name;
    }

    public String getTemplatePath()
    {
        return IIIlllIl;
    }

    public void setTemplatePath(String templatePath)
    {
        IIIlllIl = templatePath;
    }

    public String getStaticPath()
    {
        return IIIllllI;
    }

    public void setStaticPath(String staticPath)
    {
        IIIllllI = staticPath;
    }

    public String getDescription()
    {
        return IIIlllll;
    }

    public void setDescription(String description)
    {
        IIIlllll = description;
    }

    private static final long serialVersionUID = 0xf8d15362512db821L;
    private String IIIllIlI;
    private Type IIIllIll;
    private String IIIlllII;
    private String IIIlllIl;
    private String IIIllllI;
    private String IIIlllll;
}
