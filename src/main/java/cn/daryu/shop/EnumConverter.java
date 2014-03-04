// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx;

import org.apache.commons.beanutils.converters.AbstractConverter;

public class EnumConverter extends AbstractConverter
{

    public EnumConverter(Class enumClass)
    {
        this(enumClass, null);
    }

    public EnumConverter(Class enumClass, Object defaultValue)
    {
        super(defaultValue);
        IIIllIlI = enumClass;
    }

    protected Class getDefaultType()
    {
        return IIIllIlI;
    }

    protected Object convertToType(Class type, Object value)
    {
        String s = value.toString().trim();
        return Enum.valueOf(type, s);
    }

    protected String convertToString(Object value)
    {
        return value.toString();
    }

    private final Class IIIllIlI;
}
