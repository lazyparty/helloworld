// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import javax.servlet.http.*;
import net.shopxx.entity.Member;
import net.shopxx.util.CookieUtils;

// Referenced classes of package net.shopxx.controller.shop:
//            BaseController

public class LogoutController extends BaseController
{

    public LogoutController()
    {
    }

    public String execute(HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        session.removeAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
        CookieUtils.removeCookie(request, response, "username");
        return "redirect:/";
    }
}
