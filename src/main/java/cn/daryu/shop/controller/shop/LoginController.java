// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import java.util.*;
import javax.servlet.http.*;
import net.shopxx.*;
import net.shopxx.entity.Cart;
import net.shopxx.entity.Member;
import net.shopxx.service.*;
import net.shopxx.util.CookieUtils;
import net.shopxx.util.SettingUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.shop:
//            BaseController

public class LoginController extends BaseController
{

    public LoginController()
    {
    }

    public Boolean check()
    {
        return Boolean.valueOf(IIIlllll.isAuthenticated());
    }

    public String index(String redirectUrl, HttpServletRequest request, ModelMap model)
    {
        Setting setting = SettingUtils.get();
        if(redirectUrl != null && !redirectUrl.equalsIgnoreCase(setting.getSiteUrl()) && !redirectUrl.startsWith((new StringBuilder(String.valueOf(request.getContextPath()))).append("/").toString()) && !redirectUrl.startsWith((new StringBuilder(String.valueOf(setting.getSiteUrl()))).append("/").toString()))
            redirectUrl = null;
        model.addAttribute("redirectUrl", redirectUrl);
        model.addAttribute("captchaId", UUID.randomUUID().toString());
        return "/shop/login/index";
    }

    public Message submit(String captchaId, String captcha, String username, HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        String s = IIIllllI.decryptParameter("enPassword", request);
        IIIllllI.removePrivateKey(request);
        if(!IIIlllIl.isValid(net.shopxx.Setting.CaptchaType.memberLogin, captchaId, captcha))
            return Message.error("shop.captcha.invalid", new Object[0]);
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(s))
            return Message.error("shop.common.invalid", new Object[0]);
        Setting setting = SettingUtils.get();
        Member member;
        if(setting.getIsEmailLogin().booleanValue() && username.contains("@"))
        {
            List list = IIIlllll.findListByEmail(username);
            if(list.isEmpty())
                member = null;
            else
            if(list.size() == 1)
                member = (Member)list.get(0);
            else
                return Message.error("shop.login.unsupportedAccount", new Object[0]);
        } else
        {
            member = IIIlllll.findByUsername(username);
        }
        if(member == null)
            return Message.error("shop.login.unknownAccount", new Object[0]);
        if(!member.getIsEnabled().booleanValue())
            return Message.error("shop.login.disabledAccount", new Object[0]);
        if(member.getIsLocked().booleanValue())
            if(ArrayUtils.contains(setting.getAccountLockTypes(), net.shopxx.Setting.AccountLockType.member))
            {
                int i = setting.getAccountLockTime().intValue();
                if(i == 0)
                    return Message.error("shop.login.lockedAccount", new Object[0]);
                Date date = member.getLockedDate();
                Date date1 = DateUtils.addMinutes(date, i);
                if((new Date()).after(date1))
                {
                    member.setLoginFailureCount(Integer.valueOf(0));
                    member.setIsLocked(Boolean.valueOf(false));
                    member.setLockedDate(null);
                    IIIlllll.update(member);
                } else
                {
                    return Message.error("shop.login.lockedAccount", new Object[0]);
                }
            } else
            {
                member.setLoginFailureCount(Integer.valueOf(0));
                member.setIsLocked(Boolean.valueOf(false));
                member.setLockedDate(null);
                IIIlllll.update(member);
            }
        if(!DigestUtils.md5Hex(s).equals(member.getPassword()))
        {
            int j = member.getLoginFailureCount().intValue() + 1;
            if(j >= setting.getAccountLockCount().intValue())
            {
                member.setIsLocked(Boolean.valueOf(true));
                member.setLockedDate(new Date());
            }
            member.setLoginFailureCount(Integer.valueOf(j));
            IIIlllll.update(member);
            if(ArrayUtils.contains(setting.getAccountLockTypes(), net.shopxx.Setting.AccountLockType.member))
                return Message.error("shop.login.accountLockCount", new Object[] {
                    setting.getAccountLockCount()
                });
            else
                return Message.error("shop.login.incorrectCredentials", new Object[0]);
        }
        member.setLoginIp(request.getRemoteAddr());
        member.setLoginDate(new Date());
        member.setLoginFailureCount(Integer.valueOf(0));
        IIIlllll.update(member);
        Cart cart = IIlIIIII.getCurrent();
        if(cart != null && cart.getMember() == null)
        {
            IIlIIIII.merge(member, cart);
            CookieUtils.removeCookie(request, response, "cartId");
            CookieUtils.removeCookie(request, response, "cartKey");
        }
        HashMap hashmap = new HashMap();
        String s1;
        for(Enumeration enumeration = session.getAttributeNames(); enumeration.hasMoreElements(); hashmap.put(s1, session.getAttribute(s1)))
            s1 = (String)enumeration.nextElement();

        session.invalidate();
        session = request.getSession();
        java.util.Map.Entry entry;
        for(Iterator iterator = hashmap.entrySet().iterator(); iterator.hasNext(); session.setAttribute((String)entry.getKey(), entry.getValue()))
            entry = (java.util.Map.Entry)iterator.next();

        session.setAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, new Principal(member.getId(), username));
        CookieUtils.addCookie(request, response, "username", member.getUsername());
        return IIIlllII;
    }

    private CaptchaService IIIlllIl;
    private RSAService IIIllllI;
    private MemberService IIIlllll;
    private CartService IIlIIIII;
}
