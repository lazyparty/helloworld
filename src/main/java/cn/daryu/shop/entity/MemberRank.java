// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.math.BigDecimal;
import java.util.*;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Promotion

public class MemberRank extends BaseEntity
{

    public MemberRank()
    {
        IIIlllll = new HashSet();
        IIlIIIII = new HashSet();
    }

    public String getName()
    {
        return IIIllIlI;
    }

    public void setName(String name)
    {
        IIIllIlI = name;
    }

    public Double getScale()
    {
        return IIIllIll;
    }

    public void setScale(Double scale)
    {
        IIIllIll = scale;
    }

    public BigDecimal getAmount()
    {
        return IIIlllII;
    }

    public void setAmount(BigDecimal amount)
    {
        IIIlllII = amount;
    }

    public Boolean getIsDefault()
    {
        return IIIlllIl;
    }

    public void setIsDefault(Boolean isDefault)
    {
        IIIlllIl = isDefault;
    }

    public Boolean getIsSpecial()
    {
        return IIIllllI;
    }

    public void setIsSpecial(Boolean isSpecial)
    {
        IIIllllI = isSpecial;
    }

    public Set getMembers()
    {
        return IIIlllll;
    }

    public void setMembers(Set members)
    {
        IIIlllll = members;
    }

    public Set getPromotions()
    {
        return IIlIIIII;
    }

    public void setPromotions(Set promotions)
    {
        IIlIIIII = promotions;
    }

    public void preRemove()
    {
        Set set = getPromotions();
        if(set != null)
        {
            Promotion promotion;
            for(Iterator iterator = set.iterator(); iterator.hasNext(); promotion.getMemberRanks().remove(this))
                promotion = (Promotion)iterator.next();

        }
    }

    private static final long serialVersionUID = 0x31f252215ed1d269L;
    private String IIIllIlI;
    private Double IIIllIll;
    private BigDecimal IIIlllII;
    private Boolean IIIlllIl;
    private Boolean IIIllllI;
    private Set IIIlllll;
    private Set IIlIIIII;
}
