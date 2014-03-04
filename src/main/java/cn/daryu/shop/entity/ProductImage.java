// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.io.Serializable;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.springframework.web.multipart.MultipartFile;

public class ProductImage
    implements Serializable, Comparable
{

    public ProductImage()
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

    public String getSource()
    {
        return IIIllIll;
    }

    public void setSource(String source)
    {
        IIIllIll = source;
    }

    public String getLarge()
    {
        return IIIlllII;
    }

    public void setLarge(String large)
    {
        IIIlllII = large;
    }

    public String getMedium()
    {
        return IIIlllIl;
    }

    public void setMedium(String medium)
    {
        IIIlllIl = medium;
    }

    public String getThumbnail()
    {
        return IIIllllI;
    }

    public void setThumbnail(String thumbnail)
    {
        IIIllllI = thumbnail;
    }

    public Integer getOrder()
    {
        return IIIlllll;
    }

    public void setOrder(Integer order)
    {
        IIIlllll = order;
    }

    public MultipartFile getFile()
    {
        return IIlIIIII;
    }

    public void setFile(MultipartFile file)
    {
        IIlIIIII = file;
    }

    public boolean isEmpty()
    {
        return (getFile() == null || getFile().isEmpty()) && (StringUtils.isEmpty(getSource()) || StringUtils.isEmpty(getLarge()) || StringUtils.isEmpty(getMedium()) || StringUtils.isEmpty(getThumbnail()));
    }

    public int compareTo(ProductImage productImage)
    {
        return (new CompareToBuilder()).append(getOrder(), productImage.getOrder()).toComparison();
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((ProductImage)obj);
    }

    private static final long serialVersionUID = 0xf6a5e2b57dcc1655L;
    private String IIIllIlI;
    private String IIIllIll;
    private String IIIlllII;
    private String IIIlllIl;
    private String IIIllllI;
    private Integer IIIlllll;
    private MultipartFile IIlIIIII;
}
