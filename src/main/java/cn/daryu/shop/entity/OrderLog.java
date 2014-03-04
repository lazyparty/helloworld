// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;


// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Order

public class OrderLog extends BaseEntity
{

    public OrderLog()
    {
    }

    public OrderLog(Type type, String operator)
    {
        IIIllIlI = type;
        IIIllIll = operator;
    }

    public OrderLog(Type type, String operator, String content)
    {
        IIIllIlI = type;
        IIIllIll = operator;
        IIIlllII = content;
    }

    public Type getType()
    {
        return IIIllIlI;
    }

    public void setType(Type type)
    {
        IIIllIlI = type;
    }

    public String getOperator()
    {
        return IIIllIll;
    }

    public void setOperator(String operator)
    {
        IIIllIll = operator;
    }

    public String getContent()
    {
        return IIIlllII;
    }

    public void setContent(String content)
    {
        IIIlllII = content;
    }

    public Order getOrder()
    {
        return IIIlllIl;
    }

    public void setOrder(Order order)
    {
        IIIlllIl = order;
    }

    private static final long serialVersionUID = 0xda78e992634fac7dL;
    private Type IIIllIlI;
    private String IIIllIll;
    private String IIIlllII;
    private Order IIIlllIl;
}
