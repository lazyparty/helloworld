// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package net.shopxx:
//            Pageable

public class Page
    implements Serializable
{

    public Page()
    {
        IIIllIlI = new ArrayList();
        IIIllIll = 0L;
        IIIlllII = new Pageable();
    }

    public Page(List content, long total, Pageable pageable)
    {
        IIIllIlI = new ArrayList();
        IIIllIlI.addAll(content);
        IIIllIll = total;
        IIIlllII = pageable;
    }

    public int getPageNumber()
    {
        return IIIlllII.getPageNumber();
    }

    public int getPageSize()
    {
        return IIIlllII.getPageSize();
    }

    public String getSearchProperty()
    {
        return IIIlllII.getSearchProperty();
    }

    public String getSearchValue()
    {
        return IIIlllII.getSearchValue();
    }

    public String getOrderProperty()
    {
        return IIIlllII.getOrderProperty();
    }

    public Order.Direction getOrderDirection()
    {
        return IIIlllII.getOrderDirection();
    }

    public List getOrders()
    {
        return IIIlllII.getOrders();
    }

    public List getFilters()
    {
        return IIIlllII.getFilters();
    }

    public int getTotalPages()
    {
        return (int)Math.ceil((double)getTotal() / (double)getPageSize());
    }

    public List getContent()
    {
        return IIIllIlI;
    }

    public long getTotal()
    {
        return IIIllIll;
    }

    public Pageable getPageable()
    {
        return IIIlllII;
    }

    private static final long serialVersionUID = 0xe37f6f3da0577f53L;
    private final List IIIllIlI;
    private final long IIIllIll;
    private final Pageable IIIlllII;
}
