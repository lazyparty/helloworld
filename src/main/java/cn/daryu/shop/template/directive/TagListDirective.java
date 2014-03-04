// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.template.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateModel;
import java.util.Map;
import net.shopxx.entity.Tag;
import net.shopxx.service.TagService;

// Referenced classes of package net.shopxx.template.directive:
//            BaseDirective

public class TagListDirective extends BaseDirective
{

    public TagListDirective()
    {
    }

    public void execute(Environment env, Map params, TemplateModel loopVars[], TemplateDirectiveBody body)
    {
        boolean flag = IIIllIlI(env, params);
        String s = IIIllIll(env, params);
        Integer integer = IIIllIll(params);
        java.util.List list1 = IIIllIlI(params, net/shopxx/entity/Tag, new String[0]);
        java.util.List list2 = IIIllIlI(params, new String[0]);
        java.util.List list;
        if(flag)
            list = IIIllIll.findList(integer, list1, list2, s);
        else
            list = IIIllIll.findList(integer, list1, list2);
        IIIllIlI("tags", list, env, body);
    }

    private static final String IIIllIlI = "tags";
    private TagService IIIllIll;
}
