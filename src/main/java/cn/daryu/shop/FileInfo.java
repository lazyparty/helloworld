// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx;

import java.util.Date;

public class FileInfo
{

    public FileInfo()
    {
    }

    public String getName()
    {
        return IIIllIlI;
    }

    public void setName(String name)
    {
        IIIllIlI = name;
    }

    public String getUrl()
    {
        return IIIllIll;
    }

    public void setUrl(String url)
    {
        IIIllIll = url;
    }

    public Boolean getIsDirectory()
    {
        return IIIlllII;
    }

    public void setIsDirectory(Boolean isDirectory)
    {
        IIIlllII = isDirectory;
    }

    public Long getSize()
    {
        return IIIlllIl;
    }

    public void setSize(Long size)
    {
        IIIlllIl = size;
    }

    public Date getLastModified()
    {
        return IIIllllI;
    }

    public void setLastModified(Date lastModified)
    {
        IIIllllI = lastModified;
    }

    private String IIIllIlI;
    private String IIIllIll;
    private Boolean IIIlllII;
    private Long IIIlllIl;
    private Date IIIllllI;
}
