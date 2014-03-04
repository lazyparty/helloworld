// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.template.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateModel;
import java.util.Map;
import net.shopxx.entity.Promotion;
import net.shopxx.service.PromotionService;
import net.shopxx.util.FreemarkerUtils;

// Referenced classes of package net.shopxx.template.directive:
//            BaseDirective

public class PromotionListDirective extends BaseDirective
{

    public PromotionListDirective()
    {
    }

    public void execute(Environment env, Map params, TemplateModel loopVars[], TemplateDirectiveBody body)
    {
        Boolean boolean1 = (Boolean)FreemarkerUtils.getParameter("hasBegun", java/lang/Boolean, params);
        Boolean boolean2 = (Boolean)FreemarkerUtils.getParameter("hasEnded", java/lang/Boolean, params);
        boolean flag = IIIllIlI(env, params);
        String s = IIIllIll(env, params);
        Integer integer = IIIllIll(params);
        java.util.List list1 = IIIllIlI(params, net/shopxx/entity/Promotion, new String[0]);
        java.util.List list2 = IIIllIlI(params, new String[0]);
        java.util.List list;
        if(flag)
            list = IIIlllIl.findList(boolean1, boolean2, integer, list1, list2, s);
        else
            list = IIIlllIl.findList(boolean1, boolean2, integer, list1, list2);
        IIIllIlI("promotions", list, env, body);
    }

    private static final String IIIllIlI = "hasBegun";
    private static final String IIIllIll = "hasEnded";
    private static final String IIIlllII = "promotions";
    private PromotionService IIIlllIl;
}
