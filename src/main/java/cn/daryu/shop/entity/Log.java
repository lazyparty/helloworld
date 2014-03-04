// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;


// Referenced classes of package net.shopxx.entity:
//            BaseEntity

public class Log extends BaseEntity
{

    public Log()
    {
    }

    public String getOperation()
    {
        return IIIllIlI;
    }

    public void setOperation(String operation)
    {
        IIIllIlI = operation;
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

    public String getParameter()
    {
        return IIIlllIl;
    }

    public void setParameter(String parameter)
    {
        IIIlllIl = parameter;
    }

    public String getIp()
    {
        return IIIllllI;
    }

    public void setIp(String ip)
    {
        IIIllllI = ip;
    }

    private static final long serialVersionUID = 0xc1a19705c4962f66L;
    public static final String LOG_CONTENT_ATTRIBUTE_NAME = (new StringBuilder(String.valueOf(net/shopxx/entity/Log.getName()))).append(".CONTENT").toString();
    private String IIIllIlI;
    private String IIIllIll;
    private String IIIlllII;
    private String IIIlllIl;
    private String IIIllllI;

}
