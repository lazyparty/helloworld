// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.template.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateModel;
import java.util.Map;
import net.shopxx.service.SeoService;
import net.shopxx.util.FreemarkerUtils;

// Referenced classes of package net.shopxx.template.directive:
//            BaseDirective

public class SeoDirective extends BaseDirective
{

    public SeoDirective()
    {
    }

    public void execute(Environment env, Map params, TemplateModel loopVars[], TemplateDirectiveBody body)
    {
        net.shopxx.entity.Seo.Type type = (net.shopxx.entity.Seo.Type)FreemarkerUtils.getParameter("type", net/shopxx/entity/Seo$Type, params);
        boolean flag = IIIllIlI(env, params);
        String s = IIIllIll(env, params);
        net.shopxx.entity.Seo seo;
        if(flag)
            seo = IIIlllII.find(type, s);
        else
            seo = IIIlllII.find(type);
        IIIllIlI("seo", seo, env, body);
    }

    private static final String IIIllIlI = "type";
    private static final String IIIllIll = "seo";
    private SeoService IIIlllII;
}
