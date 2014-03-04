// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.template.directive;

import freemarker.core.Environment;
import freemarker.template.*;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import net.shopxx.entity.AdPosition;
import net.shopxx.service.AdPositionService;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

// Referenced classes of package net.shopxx.template.directive:
//            BaseDirective

public class AdPositionDirective extends BaseDirective
{

    public AdPositionDirective()
    {
    }

    public void execute(Environment env, Map params, TemplateModel loopVars[], TemplateDirectiveBody body)
    {
        Long long1 = IIIllIlI(params);
        boolean flag = IIIllIlI(env, params);
        String s = IIIllIll(env, params);
        AdPosition adposition;
        if(flag)
            adposition = IIIlllII.find(long1, s);
        else
            adposition = (AdPosition)IIIlllII.find(long1);
        if(body != null)
            IIIllIlI("adPosition", adposition, env, body);
        else
        if(adposition != null && adposition.getTemplate() != null)
            try
            {
                HashMap hashmap = new HashMap();
                hashmap.put("adPosition", adposition);
                java.io.Writer writer = env.getOut();
                (new Template("adTemplate", new StringReader(adposition.getTemplate()), IIIllIll.getConfiguration())).process(hashmap, writer);
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
    }

    private static final String IIIllIlI = "adPosition";
    private FreeMarkerConfigurer IIIllIll;
    private AdPositionService IIIlllII;
}
