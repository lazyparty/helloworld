// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.template.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateModel;
import java.util.*;
import net.shopxx.util.FreemarkerUtils;

// Referenced classes of package net.shopxx.template.directive:
//            BaseDirective

public class PaginationDirective extends BaseDirective
{

    public PaginationDirective()
    {
    }

    public void execute(Environment env, Map params, TemplateModel loopVars[], TemplateDirectiveBody body)
    {
        String s = (String)FreemarkerUtils.getParameter("pattern", java/lang/String, params);
        Integer integer = (Integer)FreemarkerUtils.getParameter("pageNumber", java/lang/Integer, params);
        Integer integer1 = (Integer)FreemarkerUtils.getParameter("totalPages", java/lang/Integer, params);
        Integer integer2 = (Integer)FreemarkerUtils.getParameter("segmentCount", java/lang/Integer, params);
        if(integer == null || integer.intValue() < 1)
            integer = Integer.valueOf(1);
        if(integer1 == null || integer1.intValue() < 1)
            integer1 = Integer.valueOf(1);
        if(integer2 == null || integer2.intValue() < 1)
            integer2 = Integer.valueOf(5);
        boolean flag = integer.intValue() > 1;
        boolean flag1 = integer.intValue() < integer1.intValue();
        boolean flag2 = integer.intValue() == 1;
        boolean flag3 = integer.equals(integer1);
        int i = integer.intValue() - 1;
        int j = integer.intValue() + 1;
        int k = 1;
        int l = integer1.intValue();
        int i1 = integer.intValue() - (int)Math.floor((double)(integer2.intValue() - 1) / 2D);
        int j1 = integer.intValue() + (int)Math.ceil((double)(integer2.intValue() - 1) / 2D);
        if(i1 < 1)
            i1 = 1;
        if(j1 > integer1.intValue())
            j1 = integer1.intValue();
        ArrayList arraylist = new ArrayList();
        for(int k1 = i1; k1 <= j1; k1++)
            arraylist.add(Integer.valueOf(k1));

        HashMap hashmap = new HashMap();
        hashmap.put("pattern", s);
        hashmap.put("pageNumber", integer);
        hashmap.put("totalPages", integer1);
        hashmap.put("segmentCount", integer2);
        hashmap.put("hasPrevious", Boolean.valueOf(flag));
        hashmap.put("hasNext", Boolean.valueOf(flag1));
        hashmap.put("isFirst", Boolean.valueOf(flag2));
        hashmap.put("isLast", Boolean.valueOf(flag3));
        hashmap.put("previousPageNumber", Integer.valueOf(i));
        hashmap.put("nextPageNumber", Integer.valueOf(j));
        hashmap.put("firstPageNumber", Integer.valueOf(k));
        hashmap.put("lastPageNumber", Integer.valueOf(l));
        hashmap.put("segment", arraylist);
        IIIllIlI(hashmap, env, body);
    }

    private static final String IIIllIlI = "pattern";
    private static final String IIIllIll = "pageNumber";
    private static final String IIIlllII = "totalPages";
    private static final String IIIlllIl = "segmentCount";
    private static final String IIIllllI = "pattern";
    private static final String IIIlllll = "pageNumber";
    private static final String IIlIIIII = "totalPages";
    private static final String IIlIIIIl = "segmentCount";
    private static final String IIlIIIlI = "hasPrevious";
    private static final String IIlIIIll = "hasNext";
    private static final String IIlIIlII = "isFirst";
    private static final String IIlIIlIl = "isLast";
    private static final String IIlIIllI = "previousPageNumber";
    private static final String IIlIIlll = "nextPageNumber";
    private static final String IIlIlIII = "firstPageNumber";
    private static final String IIlIlIIl = "lastPageNumber";
    private static final String IIlIlIlI = "segment";
}
