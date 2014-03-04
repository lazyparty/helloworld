// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package cn.daryu.shop.entity;

import java.util.Date;

// Referenced classes of package net.shopxx.entity:
//            OrderEntity, AdPosition

public class Ad extends OrderEntity
{

    public Ad()
    {
    }

    public String getTitle()
    {
        return IIIllIlI;
    }

    public void setTitle(String title)
    {
        IIIllIlI = title;
    }

    public Type getType()
    {
        return IIIllIll;
    }

    public void setType(Type type)
    {
        IIIllIll = type;
    }

    public String getContent()
    {
        return IIIlllII;
    }

    public void setContent(String content)
    {
        IIIlllII = content;
    }

    public String getPath()
    {
        return IIIlllIl;
    }

    public void setPath(String path)
    {
        IIIlllIl = path;
    }

    public Date getBeginDate()
    {
        return IIIllllI;
    }

    public void setBeginDate(Date beginDate)
    {
        IIIllllI = beginDate;
    }

    public Date getEndDate()
    {
        return IIIlllll;
    }

    public void setEndDate(Date endDate)
    {
        IIIlllll = endDate;
    }

    public String getUrl()
    {
        return IIlIIIII;
    }

    public void setUrl(String url)
    {
        IIlIIIII = url;
    }

    public AdPosition getAdPosition()
    {
        return IIlIIIIl;
    }

    public void setAdPosition(AdPosition adPosition)
    {
        IIlIIIIl = adPosition;
    }

    public boolean hasBegun()
    {
        return getBeginDate() == null || (new Date()).after(getBeginDate());
    }

    public boolean hasEnded()
    {
        return getEndDate() != null && (new Date()).after(getEndDate());
    }

    private static final long serialVersionUID = 0xedd9f664d862b132L;
    private String IIIllIlI;
    private Type IIIllIll;
    private String IIIlllII;
    private String IIIlllIl;
    private Date IIIllllI;
    private Date IIIlllll;
    private String IIlIIIII;
    private AdPosition IIlIIIIl;
}
