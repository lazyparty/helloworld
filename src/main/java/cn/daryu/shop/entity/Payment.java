// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.math.BigDecimal;
import java.util.Date;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Deposit, Member, Order

public class Payment extends BaseEntity
{

    public Payment()
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

    public Status getStatus()
    {
        return IIIlllII;
    }

    public void setStatus(Status status)
    {
        IIIlllII = status;
    }

    public String getPaymentMethod()
    {
        return IIIlllIl;
    }

    public void setPaymentMethod(String paymentMethod)
    {
        IIIlllIl = paymentMethod;
    }

    public String getBank()
    {
        return IIIllllI;
    }

    public void setBank(String bank)
    {
        IIIllllI = bank;
    }

    public String getAccount()
    {
        return IIIlllll;
    }

    public void setAccount(String account)
    {
        IIIlllll = account;
    }

    public BigDecimal getFee()
    {
        return IIlIIIII;
    }

    public void setFee(BigDecimal fee)
    {
        IIlIIIII = fee;
    }

    public BigDecimal getAmount()
    {
        return IIlIIIIl;
    }

    public void setAmount(BigDecimal amount)
    {
        IIlIIIIl = amount;
    }

    public String getPayer()
    {
        return IIlIIIlI;
    }

    public void setPayer(String payer)
    {
        IIlIIIlI = payer;
    }

    public String getOperator()
    {
        return IIlIIIll;
    }

    public void setOperator(String operator)
    {
        IIlIIIll = operator;
    }

    public Date getPaymentDate()
    {
        return IIlIIlII;
    }

    public void setPaymentDate(Date paymentDate)
    {
        IIlIIlII = paymentDate;
    }

    public String getMemo()
    {
        return IIlIIlIl;
    }

    public void setMemo(String memo)
    {
        IIlIIlIl = memo;
    }

    public String getPaymentPluginId()
    {
        return IIlIIllI;
    }

    public void setPaymentPluginId(String paymentPluginId)
    {
        IIlIIllI = paymentPluginId;
    }

    public Date getExpire()
    {
        return IIlIIlll;
    }

    public void setExpire(Date expire)
    {
        IIlIIlll = expire;
    }

    public Deposit getDeposit()
    {
        return IIlIlIII;
    }

    public void setDeposit(Deposit deposit)
    {
        IIlIlIII = deposit;
    }

    public Member getMember()
    {
        return IIlIlIIl;
    }

    public void setMember(Member member)
    {
        IIlIlIIl = member;
    }

    public Order getOrder()
    {
        return IIlIlIlI;
    }

    public void setOrder(Order order)
    {
        IIlIlIlI = order;
    }

    public boolean hasExpired()
    {
        return getExpire() != null && (new Date()).after(getExpire());
    }

    public void preRemove()
    {
        if(getDeposit() != null)
            getDeposit().setPayment(null);
    }

    private static final long serialVersionUID = 0xb9e22993f1ade456L;
    public static final String TYPE_SEPARATOR = "-";
    private String IIIllIlI;
    private Type IIIllIll;
    private Status IIIlllII;
    private String IIIlllIl;
    private String IIIllllI;
    private String IIIlllll;
    private BigDecimal IIlIIIII;
    private BigDecimal IIlIIIIl;
    private String IIlIIIlI;
    private String IIlIIIll;
    private Date IIlIIlII;
    private String IIlIIlIl;
    private String IIlIIllI;
    private Date IIlIIlll;
    private Deposit IIlIlIII;
    private Member IIlIlIIl;
    private Order IIlIlIlI;
}
