// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx;

import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Order
    implements Serializable
{

    public Order()
    {
        IIIlllII = IIIllIlI;
    }

    public Order(String property, Direction direction)
    {
        IIIlllII = IIIllIlI;
        IIIllIll = property;
        IIIlllII = direction;
    }

    public static Order asc(String property)
    {
        return new Order(property, Direction.asc);
    }

    public static Order desc(String property)
    {
        return new Order(property, Direction.desc);
    }

    public String getProperty()
    {
        return IIIllIll;
    }

    public void setProperty(String property)
    {
        IIIllIll = property;
    }

    public Direction getDirection()
    {
        return IIIlllII;
    }

    public void setDirection(Direction direction)
    {
        IIIlllII = direction;
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
            Order order = (Order)obj;
            return (new EqualsBuilder()).append(getProperty(), order.getProperty()).append(getDirection(), order.getDirection()).isEquals();
        }
    }

    public int hashCode()
    {
        return (new HashCodeBuilder(17, 37)).append(getProperty()).append(getDirection()).toHashCode();
    }

    private static final long serialVersionUID = 0xd5478786394c55d0L;
    private static final Direction IIIllIlI;
    private String IIIllIll;
    private Direction IIIlllII;

    static 
    {
        IIIllIlI = Direction.desc;
    }

    private class Direction extends Enum
    {

        public static Direction fromString(String value)
        {
            return valueOf(value.toLowerCase());
        }

        public static Direction[] values()
        {
            Direction adirection[];
            int i;
            Direction adirection1[];
            System.arraycopy(adirection = ENUM$VALUES, 0, adirection1 = new Direction[i = adirection.length], 0, i);
            return adirection1;
        }

        public static Direction valueOf(String s)
        {
            return (Direction)Enum.valueOf(net/shopxx/Order$Direction, s);
        }

        public static final Direction asc;
        public static final Direction desc;
        private static final Direction ENUM$VALUES[];

        static 
        {
            asc = new Direction("asc", 0);
            desc = new Direction("desc", 1);
            ENUM$VALUES = (new Direction[] {
                asc, desc
            });
        }

        private Direction(String s, int i)
        {
            super(s, i);
        }
    }

}
