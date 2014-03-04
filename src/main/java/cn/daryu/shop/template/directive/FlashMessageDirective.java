// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.template.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateModel;
import java.io.Writer;
import java.util.Map;
import net.shopxx.Message;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

// Referenced classes of package net.shopxx.template.directive:
//            BaseDirective

public class FlashMessageDirective extends BaseDirective
{

    public FlashMessageDirective()
    {
    }

    public void execute(Environment env, Map params, TemplateModel loopVars[], TemplateDirectiveBody body)
    {
        RequestAttributes requestattributes = RequestContextHolder.currentRequestAttributes();
        if(requestattributes != null)
        {
            Message message = (Message)requestattributes.getAttribute(FLASH_MESSAGE_ATTRIBUTE_NAME, 0);
            if(body != null)
                IIIllIlI("flashMessage", message, env, body);
            else
            if(message != null)
            {
                Writer writer = env.getOut();
                writer.write((new StringBuilder("$.message(\"")).append(message.getType()).append("\", \"").append(message.getContent()).append("\");").toString());
            }
        }
    }

    public static final String FLASH_MESSAGE_ATTRIBUTE_NAME = (new StringBuilder(String.valueOf(net/shopxx/template/directive/FlashMessageDirective.getName()))).append(".FLASH_MESSAGE").toString();
    private static final String IIIllIlI = "flashMessage";

}
