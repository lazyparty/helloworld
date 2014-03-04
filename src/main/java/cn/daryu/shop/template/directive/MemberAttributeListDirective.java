// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.template.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateModel;
import java.util.Map;
import net.shopxx.service.MemberAttributeService;

// Referenced classes of package net.shopxx.template.directive:
//            BaseDirective

public class MemberAttributeListDirective extends BaseDirective
{

    public MemberAttributeListDirective()
    {
    }

    public void execute(Environment env, Map params, TemplateModel loopVars[], TemplateDirectiveBody body)
    {
        boolean flag = IIIllIlI(env, params);
        String s = IIIllIll(env, params);
        java.util.List list;
        if(flag)
            list = IIIllIll.findList(s);
        else
            list = IIIllIll.findList();
        IIIllIlI("memberAttributes", list, env, body);
    }

    private static final String IIIllIlI = "memberAttributes";
    private MemberAttributeService IIIllIll;
}
