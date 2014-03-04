// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.math.BigDecimal;
import java.util.*;
import net.shopxx.service.OrderService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.ui.Model;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class SalesController extends BaseController
{

    public SalesController()
    {
    }

    public String view(Type type, Date beginDate, Date endDate, Model model)
    {
        if(type == null)
            type = Type.month;
        if(beginDate == null)
            beginDate = DateUtils.addMonths(new Date(), -11);
        if(endDate == null)
            endDate = new Date();
        LinkedHashMap linkedhashmap = new LinkedHashMap();
        LinkedHashMap linkedhashmap1 = new LinkedHashMap();
        Calendar calendar = DateUtils.toCalendar(beginDate);
        Calendar calendar1 = DateUtils.toCalendar(endDate);
        int i = calendar.get(1);
        int j = calendar1.get(1);
        int k = calendar.get(2);
        int l = calendar1.get(2);
        for(int i1 = i; i1 <= j; i1++)
        {
            if(linkedhashmap.size() >= 12)
                break;
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(1, i1);
            if(type == Type.year)
            {
                calendar2.set(2, calendar2.getActualMinimum(2));
                calendar2.set(5, calendar2.getActualMinimum(5));
                calendar2.set(11, calendar2.getActualMinimum(11));
                calendar2.set(12, calendar2.getActualMinimum(12));
                calendar2.set(13, calendar2.getActualMinimum(13));
                Date date = calendar2.getTime();
                calendar2.set(2, calendar2.getActualMaximum(2));
                calendar2.set(5, calendar2.getActualMaximum(5));
                calendar2.set(11, calendar2.getActualMaximum(11));
                calendar2.set(12, calendar2.getActualMaximum(12));
                calendar2.set(13, calendar2.getActualMaximum(13));
                Date date1 = calendar2.getTime();
                BigDecimal bigdecimal = IIIllllI.getSalesAmount(date, date1);
                Integer integer = IIIllllI.getSalesVolume(date, date1);
                linkedhashmap.put(date, bigdecimal == null ? ((Object) (BigDecimal.ZERO)) : ((Object) (bigdecimal)));
                linkedhashmap1.put(date, Integer.valueOf(integer == null ? 0 : integer.intValue()));
            } else
            {
                for(int j1 = i1 != i ? calendar2.getActualMinimum(2) : k; j1 <= (i1 != j ? calendar2.getActualMaximum(2) : l); j1++)
                {
                    if(linkedhashmap.size() >= 12)
                        break;
                    calendar2.set(2, j1);
                    calendar2.set(5, calendar2.getActualMinimum(5));
                    calendar2.set(11, calendar2.getActualMinimum(11));
                    calendar2.set(12, calendar2.getActualMinimum(12));
                    calendar2.set(13, calendar2.getActualMinimum(13));
                    Date date2 = calendar2.getTime();
                    calendar2.set(5, calendar2.getActualMaximum(5));
                    calendar2.set(11, calendar2.getActualMaximum(11));
                    calendar2.set(12, calendar2.getActualMaximum(12));
                    calendar2.set(13, calendar2.getActualMaximum(13));
                    Date date3 = calendar2.getTime();
                    BigDecimal bigdecimal1 = IIIllllI.getSalesAmount(date2, date3);
                    Integer integer1 = IIIllllI.getSalesVolume(date2, date3);
                    linkedhashmap.put(date2, bigdecimal1 == null ? ((Object) (BigDecimal.ZERO)) : ((Object) (bigdecimal1)));
                    linkedhashmap1.put(date2, Integer.valueOf(integer1 == null ? 0 : integer1.intValue()));
                }

            }
        }

        model.addAttribute("types", Type.values());
        model.addAttribute("type", type);
        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("salesAmountMap", linkedhashmap);
        model.addAttribute("salesVolumeMap", linkedhashmap1);
        return "/admin/sales/view";
    }

    private static final int IIIlllIl = 12;
    private OrderService IIIllllI;

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
            return (Type)Enum.valueOf(net/shopxx/controller/admin/SalesController$Type, s);
        }

        public static final Type year;
        public static final Type month;
        private static final Type ENUM$VALUES[];

        static 
        {
            year = new Type("year", 0);
            month = new Type("month", 1);
            ENUM$VALUES = (new Type[] {
                year, month
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }

}
