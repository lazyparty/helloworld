// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.template.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateModel;
import java.util.Map;
import net.shopxx.interceptor.ExecuteTimeInterceptor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

// Referenced classes of package net.shopxx.template.directive:
//            BaseDirective

public class ExecuteTimeDirective extends BaseDirective
{

    public ExecuteTimeDirective()
    {
    }

    public void execute(Environment env, Map params, TemplateModel loopVars[], TemplateDirectiveBody body)
    {
        RequestAttributes requestattributes = RequestContextHolder.currentRequestAttributes();
        if(requestattributes != null)
        {
            Long long1 = (Long)requestattributes.getAttribute(ExecuteTimeInterceptor.EXECUTE_TIME_ATTRIBUTE_NAME, 0);
            if(long1 != null)
                IIIllIlI("executeTime", long1, env, body);
        }
    }

    private static final String IIIllIlI = "executeTime";
}
