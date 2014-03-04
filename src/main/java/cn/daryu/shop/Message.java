// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx;

import net.shopxx.util.SpringUtils;

public class Message
{

    public Message()
    {
    }

    public Message(Type type, String content)
    {
        IIIllIlI = type;
        IIIllIll = content;
    }

    public transient Message(Type type, String content, Object args[])
    {
        IIIllIlI = type;
        IIIllIll = SpringUtils.getMessage(content, args);
    }

    public static transient Message success(String content, Object args[])
    {
        return new Message(Type.success, content, args);
    }

    public static transient Message warn(String content, Object args[])
    {
        return new Message(Type.warn, content, args);
    }

    public static transient Message error(String content, Object args[])
    {
        return new Message(Type.error, content, args);
    }

    public Type getType()
    {
        return IIIllIlI;
    }

    public void setType(Type type)
    {
        IIIllIlI = type;
    }

    public String getContent()
    {
        return IIIllIll;
    }

    public void setContent(String content)
    {
        IIIllIll = content;
    }

    public String toString()
    {
        return SpringUtils.getMessage(IIIllIll, new Object[0]);
    }

    private Type IIIllIlI;
    private String IIIllIll;

    private class Type extends Enum
    {

        public static Type[] values()
        {
            Type atype[];
            int i;
            Type atype1[];
            System.arraycopy(atype = ENUM$VALUES, 0, atype1 = new Type[i = atype.length], 0, i);
            return atype1;
        }

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(net/shopxx/Message$Type, s);
        }

        public static final Type success;
        public static final Type warn;
        public static final Type error;
        private static final Type ENUM$VALUES[];

        static 
        {
            success = new Type("success", 0);
            warn = new Type("warn", 1);
            error = new Type("error", 2);
            ENUM$VALUES = (new Type[] {
                success, warn, error
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }

}
