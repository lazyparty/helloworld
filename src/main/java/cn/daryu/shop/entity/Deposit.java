// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.math.BigDecimal;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Member, Order, Payment

public class Deposit extends BaseEntity
{

    public Deposit()
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

    public BigDecimal getCredit()
    {
        return IIIllIll;
    }

    public void setCredit(BigDecimal credit)
    {
        IIIllIll = credit;
    }

    public BigDecimal getDebit()
    {
        return IIIlllII;
    }

    public void setDebit(BigDecimal debit)
    {
        IIIlllII = debit;
    }

    public BigDecimal getBalance()
    {
        return IIIlllIl;
    }

    public void setBalance(BigDecimal balance)
    {
        IIIlllIl = balance;
    }

    public String getOperator()
    {
        return IIIllllI;
    }

    public void setOperator(String operator)
    {
        IIIllllI = operator;
    }

    public String getMemo()
    {
        return IIIlllll;
    }

    public void setMemo(String memo)
    {
        IIIlllll = memo;
    }

    public Member getMember()
    {
        return IIlIIIII;
    }

    public void setMember(Member member)
    {
        IIlIIIII = member;
    }

    public Order getOrder()
    {
        return IIlIIIIl;
    }

    public void setOrder(Order order)
    {
        IIlIIIIl = order;
    }

    public Payment getPayment()
    {
        return IIlIIIlI;
    }

    public void setPayment(Payment payment)
    {
        IIlIIIlI = payment;
    }

    private static final long serialVersionUID = 0x8c7d27b625548306L;
    private Type IIIllIlI;
    private BigDecimal IIIllIll;
    private BigDecimal IIIlllII;
    private BigDecimal IIIlllIl;
    private String IIIllllI;
    private String IIIlllll;
    private Member IIlIIIII;
    private Order IIlIIIIl;
    private Payment IIlIIIlI;
}
