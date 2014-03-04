// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.math.BigDecimal;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Order

public class Refunds extends BaseEntity
{

    public Refunds()
    {
    }

    public String getSn()
    {
        return IIIllIlI;
    }

    public void setSn(String sn)
    {
        IIIllIlI = sn;
    }

    public Type getType()
    {
        return IIIllIll;
    }

    public void setType(Type type)
    {
        IIIllIll = type;
    }

    public String getPaymentMethod()
    {
        return IIIlllII;
    }

    public void setPaymentMethod(String paymentMethod)
    {
        IIIlllII = paymentMethod;
    }

    public String getBank()
    {
        return IIIlllIl;
    }

    public void setBank(String bank)
    {
        IIIlllIl = bank;
    }

    public String getAccount()
    {
        return IIIllllI;
    }

    public void setAccount(String account)
    {
        IIIllllI = account;
    }

    public BigDecimal getAmount()
    {
        return IIIlllll;
    }

    public void setAmount(BigDecimal amount)
    {
        IIIlllll = amount;
    }

    public String getPayee()
    {
        return IIlIIIII;
    }

    public void setPayee(String payee)
    {
        IIlIIIII = payee;
    }

    public String getOperator()
    {
        return IIlIIIIl;
    }

    public void setOperator(String operator)
    {
        IIlIIIIl = operator;
    }

    public String getMemo()
    {
        return IIlIIIlI;
    }

    public void setMemo(String memo)
    {
        IIlIIIlI = memo;
    }

    public Order getOrder()
    {
        return IIlIIIll;
    }

    public void setOrder(Order order)
    {
        IIlIIIll = order;
    }

    private static final long serialVersionUID = 0x4ecce3967c4a050L;
    private String IIIllIlI;
    private Type IIIllIll;
    private String IIIlllII;
    private String IIIlllIl;
    private String IIIllllI;
    private BigDecimal IIIlllll;
    private String IIlIIIII;
    private String IIlIIIIl;
    private String IIlIIIlI;
    private Order IIlIIIll;
}
