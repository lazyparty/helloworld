// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Filter
    implements Serializable
{

    public Filter()
    {
        IIIllllI = Boolean.valueOf(false);
    }

    public Filter(String property, Operator operator, Object value)
    {
        IIIllllI = Boolean.valueOf(false);
        IIIllIll = property;
        IIIlllII = operator;
        IIIlllIl = value;
    }

    public Filter(String property, Operator operator, Object value, boolean ignoreCase)
    {
        IIIllllI = Boolean.valueOf(false);
        IIIllIll = property;
        IIIlllII = operator;
        IIIlllIl = value;
        IIIllllI = Boolean.valueOf(ignoreCase);
    }

    public static Filter eq(String property, Object value)
    {
        return new Filter(property, Operator.eq, value);
    }

    public static Filter eq(String property, Object value, boolean ignoreCase)
    {
        return new Filter(property, Operator.eq, value, ignoreCase);
    }

    public static Filter ne(String property, Object value)
    {
        return new Filter(property, Operator.ne, value);
    }

    public static Filter ne(String property, Object value, boolean ignoreCase)
    {
        return new Filter(property, Operator.ne, value, ignoreCase);
    }

    public static Filter gt(String property, Object value)
    {
        return new Filter(property, Operator.gt, value);
    }

    public static Filter lt(String property, Object value)
    {
        return new Filter(property, Operator.lt, value);
    }

    public static Filter ge(String property, Object value)
    {
        return new Filter(property, Operator.ge, value);
    }

    public static Filter le(String property, Object value)
    {
        return new Filter(property, Operator.le, value);
    }

    public static Filter like(String property, Object value)
    {
        return new Filter(property, Operator.like, value);
    }

    public static Filter in(String property, Object value)
    {
        return new Filter(property, Operator.in, value);
    }

    public static Filter isNull(String property)
    {
        return new Filter(property, Operator.isNull, null);
    }

    public static Filter isNotNull(String property)
    {
        return new Filter(property, Operator.isNotNull, null);
    }

    public Filter ignoreCase()
    {
        IIIllllI = Boolean.valueOf(true);
        return this;
    }

    public String getProperty()
    {
        return IIIllIll;
    }

    public void setProperty(String property)
    {
        IIIllIll = property;
    }

    public Operator getOperator()
    {
        return IIIlllII;
    }

    public void setOperator(Operator operator)
    {
        IIIlllII = operator;
    }

    public Object getValue()
    {
        return IIIlllIl;
    }

    public void setValue(Object value)
    {
        IIIlllIl = value;
    }

    public Boolean getIgnoreCase()
    {
        return IIIllllI;
    }

    public void setIgnoreCase(Boolean ignoreCase)
    {
        IIIllllI = ignoreCase;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        if(this == obj)
        {
            return true;
        } else
        {
            Filter filter = (Filter)obj;
            return (new EqualsBuilder()).append(getProperty(), filter.getProperty()).append(getOperator(), filter.getOperator()).append(getValue(), filter.getValue()).isEquals();
        }
    }

    public int hashCode()
    {
        return (new HashCodeBuilder(17, 37)).append(getProperty()).append(getOperator()).append(getValue()).toHashCode();
    }

    private static final long serialVersionUID = 0x87176667b9bd858dL;
    private static final boolean IIIllIlI = false;
    private String IIIllIll;
    private Operator IIIlllII;
    private Object IIIlllIl;
    private Boolean IIIllllI;

    private class Operator extends Enum
    {

        public static Operator fromString(String value)
        {
            return valueOf(value.toLowerCase());
        }

        public static Operator[] values()
        {
            Operator aoperator[];
            int i;
            Operator aoperator1[];
            System.arraycopy(aoperator = ENUM$VALUES, 0, aoperator1 = new Operator[i = aoperator.length], 0, i);
            return aoperator1;
        }

        public static Operator valueOf(String s)
        {
            return (Operator)Enum.valueOf(net/shopxx/Filter$Operator, s);
        }

        public static final Operator eq;
        public static final Operator ne;
        public static final Operator gt;
        public static final Operator lt;
        public static final Operator ge;
        public static final Operator le;
        public static final Operator like;
        public static final Operator in;
        public static final Operator isNull;
        public static final Operator isNotNull;
        private static final Operator ENUM$VALUES[];

        static 
        {
            eq = new Operator("eq", 0);
            ne = new Operator("ne", 1);
            gt = new Operator("gt", 2);
            lt = new Operator("lt", 3);
            ge = new Operator("ge", 4);
            le = new Operator("le", 5);
            like = new Operator("like", 6);
            in = new Operator("in", 7);
            isNull = new Operator("isNull", 8);
            isNotNull = new Operator("isNotNull", 9);
            ENUM$VALUES = (new Operator[] {
                eq, ne, gt, lt, ge, le, like, in, isNull, isNotNull
            });
        }

        private Operator(String s, int i)
        {
            super(s, i);
        }
    }

}
