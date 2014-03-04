// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;


// Referenced classes of package net.shopxx.entity:
//            BaseEntity

public class Seo extends BaseEntity
{

    public Seo()
    {
    }

    public Type getType()
    {
        return IIIllIlI;
    }

    public void setType(Type type)
    {
        IIIllIlI = type;
    }

    public String getTitle()
    {
        return IIIllIll;
    }

    public void setTitle(String title)
    {
        IIIllIll = title;
    }

    public String getKeywords()
    {
        return IIIlllII;
    }

    public void setKeywords(String keywords)
    {
        if(keywords != null)
            keywords = keywords.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
        IIIlllII = keywords;
    }

    public String getDescription()
    {
        return IIIlllIl;
    }

    public void setDescription(String description)
    {
        IIIlllIl = description;
    }

    private static final long serialVersionUID = 0xcf60824d1ced8e70L;
    private Type IIIllIlI;
    private String IIIllIll;
    private String IIIlllII;
    private String IIIlllIl;
}
