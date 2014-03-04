// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import javax.servlet.http.*;
import net.shopxx.*;
import net.shopxx.entity.*;
import net.shopxx.service.*;
import net.shopxx.util.CookieUtils;
import net.shopxx.util.SettingUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.shop:
//            BaseController

public class RegisterController extends BaseController
{

    public RegisterController()
    {
    }

    public boolean checkUsername(String username)
    {
        if(StringUtils.isEmpty(username))
            return false;
        return !IIIlllll.usernameDisabled(username) && !IIIlllll.usernameExists(username);
    }

    public boolean checkEmail(String email)
    {
        if(StringUtils.isEmpty(email))
            return false;
        return !IIIlllll.emailExists(email);
    }

    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model)
    {
        model.addAttribute("genders", net.shopxx.entity.Member.Gender.values());
        model.addAttribute("captchaId", UUID.randomUUID().toString());
        return "/shop/register/index";
    }

    public Message submit(String captchaId, String captcha, String username, String email, HttpServletRequest request, HttpServletResponse response, HttpSession session)
    {
        String s;
        Setting setting;
        Member member;
        Iterator iterator;
        s = IIIllllI.decryptParameter("enPassword", request);
        IIIllllI.removePrivateKey(request);
        if(!IIIlllIl.isValid(net.shopxx.Setting.CaptchaType.memberRegister, captchaId, captcha))
            return Message.error("shop.captcha.invalid", new Object[0]);
        setting = SettingUtils.get();
        if(!setting.getIsRegisterEnabled().booleanValue())
            return Message.error("shop.register.disabled", new Object[0]);
        if(!IIIllIlI(net/shopxx/entity/Member, "username", username, new Class[] {
    net/shopxx/entity/BaseEntity$Save
}) || !IIIllIlI(net/shopxx/entity/Member, "password", s, new Class[] {
    net/shopxx/entity/BaseEntity$Save
}) || !IIIllIlI(net/shopxx/entity/Member, "email", email, new Class[] {
    net/shopxx/entity/BaseEntity$Save
}))
            return Message.error("shop.common.invalid", new Object[0]);
        if(username.length() < setting.getUsernameMinLength().intValue() || username.length() > setting.getUsernameMaxLength().intValue())
            return Message.error("shop.common.invalid", new Object[0]);
        if(s.length() < setting.getPasswordMinLength().intValue() || s.length() > setting.getPasswordMaxLength().intValue())
            return Message.error("shop.common.invalid", new Object[0]);
        if(IIIlllll.usernameDisabled(username) || IIIlllll.usernameExists(username))
            return Message.error("shop.register.disabledExist", new Object[0]);
        if(!setting.getIsDuplicateEmail().booleanValue() && IIIlllll.emailExists(email))
            return Message.error("shop.register.emailExist", new Object[0]);
        member = new Member();
        List list = IIlIIIIl.findList();
        iterator = list.iterator();
          goto _L1
_L3:
        MemberAttribute memberattribute;
        String s1;
        memberattribute = (MemberAttribute)iterator.next();
        s1 = request.getParameter((new StringBuilder("memberAttribute_")).append(memberattribute.getId()).toString());
        if(memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.name || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.address || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.zipCode || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.phone || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.mobile || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.text || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.select)
        {
            if(memberattribute.getIsRequired().booleanValue() && StringUtils.isEmpty(s1))
                return Message.error("shop.common.invalid", new Object[0]);
            member.setAttributeValue(memberattribute, s1);
            continue; /* Loop/switch isn't completed */
        }
        if(memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.gender)
        {
            net.shopxx.entity.Member.Gender gender = StringUtils.isNotEmpty(s1) ? net.shopxx.entity.Member.Gender.valueOf(s1) : null;
            if(memberattribute.getIsRequired().booleanValue() && gender == null)
                return Message.error("shop.common.invalid", new Object[0]);
            member.setGender(gender);
            continue; /* Loop/switch isn't completed */
        }
        if(memberattribute.getType() != net.shopxx.entity.MemberAttribute.Type.birth)
            break MISSING_BLOCK_LABEL_645;
        Date date;
        try
        {
            date = StringUtils.isNotEmpty(s1) ? DateUtils.parseDate(s1, CommonAttributes.DATE_PATTERNS) : null;
            if(memberattribute.getIsRequired().booleanValue() && date == null)
                return Message.error("shop.common.invalid", new Object[0]);
        }
        catch(ParseException parseexception)
        {
            return Message.error("shop.common.invalid", new Object[0]);
        }
        member.setBirth(date);
        continue; /* Loop/switch isn't completed */
        if(memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.area)
        {
            Area area = StringUtils.isNotEmpty(s1) ? (Area)IIlIIIlI.find(Long.valueOf(s1)) : null;
            if(area != null)
                member.setArea(area);
            else
            if(memberattribute.getIsRequired().booleanValue())
                return Message.error("shop.common.invalid", new Object[0]);
        } else
        if(memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.checkbox)
        {
            String as[] = request.getParameterValues((new StringBuilder("memberAttribute_")).append(memberattribute.getId()).toString());
            List list1 = as == null ? null : Arrays.asList(as);
            if(memberattribute.getIsRequired().booleanValue() && (list1 == null || list1.isEmpty()))
                return Message.error("shop.common.invalid", new Object[0]);
            member.setAttributeValue(memberattribute, list1);
        }
_L1:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        member.setUsername(username.toLowerCase());
        member.setPassword(DigestUtils.md5Hex(s));
        member.setEmail(email);
        member.setPoint(setting.getRegisterPoint());
        member.setAmount(new BigDecimal(0));
        member.setBalance(new BigDecimal(0));
        member.setIsEnabled(Boolean.valueOf(true));
        member.setIsLocked(Boolean.valueOf(false));
        member.setLoginFailureCount(Integer.valueOf(0));
        member.setLockedDate(null);
        member.setRegisterIp(request.getRemoteAddr());
        member.setLoginIp(request.getRemoteAddr());
        member.setLoginDate(new Date());
        member.setSafeKey(null);
        member.setMemberRank(IIlIIIII.findDefault());
        member.setFavoriteProducts(null);
        IIIlllll.save(member);
        Cart cart = IIlIIIll.getCurrent();
        if(cart != null && cart.getMember() == null)
        {
            IIlIIIll.merge(member, cart);
            CookieUtils.removeCookie(request, response, "cartId");
            CookieUtils.removeCookie(request, response, "cartKey");
        }
        HashMap hashmap = new HashMap();
        String s2;
        for(Enumeration enumeration = session.getAttributeNames(); enumeration.hasMoreElements(); hashmap.put(s2, session.getAttribute(s2)))
            s2 = (String)enumeration.nextElement();

        session.invalidate();
        session = request.getSession();
        java.util.Map.Entry entry;
        for(Iterator iterator1 = hashmap.entrySet().iterator(); iterator1.hasNext(); session.setAttribute((String)entry.getKey(), entry.getValue()))
            entry = (java.util.Map.Entry)iterator1.next();

        session.setAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, new Principal(member.getId(), member.getUsername()));
        CookieUtils.addCookie(request, response, "username", member.getUsername());
        return Message.success("shop.register.success", new Object[0]);
    }

    private CaptchaService IIIlllIl;
    private RSAService IIIllllI;
    private MemberService IIIlllll;
    private MemberRankService IIlIIIII;
    private MemberAttributeService IIlIIIIl;
    private AreaService IIlIIIlI;
    private CartService IIlIIIll;
}
