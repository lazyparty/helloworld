// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.validation.Validator;
import net.shopxx.*;
import net.shopxx.template.directive.FlashMessageDirective;
import net.shopxx.util.SettingUtils;
import net.shopxx.util.SpringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class BaseController
{

    public BaseController()
    {
    }

    protected void IIIllIlI(WebDataBinder webdatabinder)
    {
        webdatabinder.registerCustomEditor(java/lang/String, new HtmlCleanEditor(true, true));
        webdatabinder.registerCustomEditor(java/util/Date, new DateEditor(true));
    }

    protected transient boolean IIIllIlI(Object obj, Class aclass[])
    {
        Set set = IIIllllI.validate(obj, aclass);
        if(set.isEmpty())
        {
            return true;
        } else
        {
            RequestAttributes requestattributes = RequestContextHolder.currentRequestAttributes();
            requestattributes.setAttribute("constraintViolations", set, 0);
            return false;
        }
    }

    protected transient boolean IIIllIlI(Class class1, String s, Object obj, Class aclass[])
    {
        Set set = IIIllllI.validateValue(class1, s, obj, aclass);
        if(set.isEmpty())
        {
            return true;
        } else
        {
            RequestAttributes requestattributes = RequestContextHolder.currentRequestAttributes();
            requestattributes.setAttribute("constraintViolations", set, 0);
            return false;
        }
    }

    protected String IIIllIlI(BigDecimal bigdecimal, boolean flag, boolean flag1)
    {
        Setting setting = SettingUtils.get();
        String s = setting.setScale(bigdecimal).toString();
        if(flag)
            s = (new StringBuilder(String.valueOf(setting.getCurrencySign()))).append(s).toString();
        if(flag1)
            s = (new StringBuilder(String.valueOf(s))).append(setting.getCurrencyUnit()).toString();
        return s;
    }

    protected transient String IIIllIlI(String s, Object aobj[])
    {
        return SpringUtils.getMessage(s, aobj);
    }

    protected void IIIllIlI(RedirectAttributes redirectattributes, Message message)
    {
        if(redirectattributes != null && message != null)
            redirectattributes.addFlashAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME, message);
    }

    protected static final String IIIllIlI = "/shop/common/error";
    protected static final Message IIIllIll = Message.error("shop.message.error", new Object[0]);
    protected static final Message IIIlllII = Message.success("shop.message.success", new Object[0]);
    private static final String IIIlllIl = "constraintViolations";
    private Validator IIIllllI;

}
