// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.template.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateModel;
import java.util.ArrayList;
import java.util.Map;
import net.shopxx.entity.*;
import net.shopxx.service.*;
import net.shopxx.util.FreemarkerUtils;

// Referenced classes of package net.shopxx.template.directive:
//            BaseDirective

public class ReviewListDirective extends BaseDirective
{

    public ReviewListDirective()
    {
    }

    public void execute(Environment env, Map params, TemplateModel loopVars[], TemplateDirectiveBody body)
    {
        Long long1 = (Long)FreemarkerUtils.getParameter("memberId", java/lang/Long, params);
        Long long2 = (Long)FreemarkerUtils.getParameter("productId", java/lang/Long, params);
        net.shopxx.entity.Review.Type type = (net.shopxx.entity.Review.Type)FreemarkerUtils.getParameter("type", net/shopxx/entity/Review$Type, params);
        Member member = (Member)IIIlllll.find(long1);
        Product product = (Product)IIlIIIII.find(long2);
        Object obj;
        if(long1 != null && member == null || long2 != null && product == null)
        {
            obj = new ArrayList();
        } else
        {
            boolean flag = IIIllIlI(env, params);
            String s = IIIllIll(env, params);
            Integer integer = IIIllIll(params);
            java.util.List list = IIIllIlI(params, net/shopxx/entity/Review, new String[0]);
            java.util.List list1 = IIIllIlI(params, new String[0]);
            if(flag)
                obj = IIIllllI.findList(member, product, type, Boolean.valueOf(true), integer, list, list1, s);
            else
                obj = IIIllllI.findList(member, product, type, Boolean.valueOf(true), integer, list, list1);
        }
        IIIllIlI("reviews", obj, env, body);
    }

    private static final String IIIllIlI = "memberId";
    private static final String IIIllIll = "productId";
    private static final String IIIlllII = "type";
    private static final String IIIlllIl = "reviews";
    private ReviewService IIIllllI;
    private MemberService IIIlllll;
    private ProductService IIlIIIII;
}
