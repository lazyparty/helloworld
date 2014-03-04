// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop.member;

import java.text.ParseException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import net.shopxx.CommonAttributes;
import net.shopxx.Setting;
import net.shopxx.controller.shop.BaseController;
import net.shopxx.entity.*;
import net.shopxx.service.*;
import net.shopxx.util.SettingUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ProfileController extends BaseController
{

    public ProfileController()
    {
    }

    public boolean checkEmail(String email)
    {
        if(StringUtils.isEmpty(email))
            return false;
        Member member = IIIlllIl.getCurrent();
        return IIIlllIl.emailUnique(member.getEmail(), email);
    }

    public String edit(ModelMap model)
    {
        model.addAttribute("genders", net.shopxx.entity.Member.Gender.values());
        model.addAttribute("memberAttributes", IIIllllI.findList());
        return "shop/member/profile/edit";
    }

    public String update(String email, HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        Member member;
        Iterator iterator;
        if(!IIIllIlI(net/shopxx/entity/Member, "email", email, new Class[0]))
            return "/shop/common/error";
        Setting setting = SettingUtils.get();
        member = IIIlllIl.getCurrent();
        if(!setting.getIsDuplicateEmail().booleanValue() && !IIIlllIl.emailUnique(member.getEmail(), email))
            return "/shop/common/error";
        member.setEmail(email);
        List list = IIIllllI.findList();
        iterator = list.iterator();
          goto _L1
_L3:
        MemberAttribute memberattribute;
        String s;
        memberattribute = (MemberAttribute)iterator.next();
        s = request.getParameter((new StringBuilder("memberAttribute_")).append(memberattribute.getId()).toString());
        if(memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.name || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.address || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.zipCode || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.phone || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.mobile || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.text || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.select)
        {
            if(memberattribute.getIsRequired().booleanValue() && StringUtils.isEmpty(s))
                return "/shop/common/error";
            member.setAttributeValue(memberattribute, s);
            continue; /* Loop/switch isn't completed */
        }
        if(memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.gender)
        {
            net.shopxx.entity.Member.Gender gender = StringUtils.isNotEmpty(s) ? net.shopxx.entity.Member.Gender.valueOf(s) : null;
            if(memberattribute.getIsRequired().booleanValue() && gender == null)
                return "/shop/common/error";
            member.setGender(gender);
            continue; /* Loop/switch isn't completed */
        }
        if(memberattribute.getType() != net.shopxx.entity.MemberAttribute.Type.birth)
            break MISSING_BLOCK_LABEL_373;
        java.util.Date date = StringUtils.isNotEmpty(s) ? DateUtils.parseDate(s, CommonAttributes.DATE_PATTERNS) : null;
        if(memberattribute.getIsRequired().booleanValue() && date == null)
            return "/shop/common/error";
        try
        {
            member.setBirth(date);
        }
        catch(ParseException parseexception)
        {
            return "/shop/common/error";
        }
        continue; /* Loop/switch isn't completed */
        if(memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.area)
        {
            Area area = StringUtils.isNotEmpty(s) ? (Area)IIIlllll.find(Long.valueOf(s)) : null;
            if(area != null)
                member.setArea(area);
            else
            if(memberattribute.getIsRequired().booleanValue())
                return "/shop/common/error";
        } else
        if(memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.checkbox)
        {
            String as[] = request.getParameterValues((new StringBuilder("memberAttribute_")).append(memberattribute.getId()).toString());
            List list1 = as == null ? null : Arrays.asList(as);
            if(memberattribute.getIsRequired().booleanValue() && (list1 == null || list1.isEmpty()))
                return "/shop/common/error";
            member.setAttributeValue(memberattribute, list1);
        }
_L1:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        IIIlllIl.update(member);
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:edit.jhtml";
    }

    private MemberService IIIlllIl;
    private MemberAttributeService IIIllllI;
    private AreaService IIIlllll;
}
