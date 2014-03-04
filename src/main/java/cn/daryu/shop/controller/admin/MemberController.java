// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import net.shopxx.*;
import net.shopxx.entity.*;
import net.shopxx.service.*;
import net.shopxx.util.SettingUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class MemberController extends BaseController
{

    public MemberController()
    {
    }

    public boolean checkUsername(String username)
    {
        if(StringUtils.isEmpty(username))
            return false;
        return !IIIlllIl.usernameDisabled(username) && !IIIlllIl.usernameExists(username);
    }

    public boolean checkEmail(String previousEmail, String email)
    {
        if(StringUtils.isEmpty(email))
            return false;
        return IIIlllIl.emailUnique(previousEmail, email);
    }

    public String view(Long id, ModelMap model)
    {
        model.addAttribute("genders", net.shopxx.entity.Member.Gender.values());
        model.addAttribute("memberAttributes", IIIlllll.findList());
        model.addAttribute("member", IIIlllIl.find(id));
        return "/admin/member/view";
    }

    public String add(ModelMap model)
    {
        model.addAttribute("genders", net.shopxx.entity.Member.Gender.values());
        model.addAttribute("memberRanks", IIIllllI.findAll());
        model.addAttribute("memberAttributes", IIIlllll.findList());
        return "/admin/member/add";
    }

    public String save(Member member, Long memberRankId, HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        Iterator iterator;
        member.setMemberRank((MemberRank)IIIllllI.find(memberRankId));
        if(!IIIllIlI(member, new Class[] {
    net/shopxx/entity/BaseEntity$Save
}))
            return "/admin/common/error";
        Setting setting = SettingUtils.get();
        if(member.getUsername().length() < setting.getUsernameMinLength().intValue() || member.getUsername().length() > setting.getUsernameMaxLength().intValue())
            return "/admin/common/error";
        if(member.getPassword().length() < setting.getPasswordMinLength().intValue() || member.getPassword().length() > setting.getPasswordMaxLength().intValue())
            return "/admin/common/error";
        if(IIIlllIl.usernameDisabled(member.getUsername()) || IIIlllIl.usernameExists(member.getUsername()))
            return "/admin/common/error";
        if(!setting.getIsDuplicateEmail().booleanValue() && IIIlllIl.emailExists(member.getEmail()))
            return "/admin/common/error";
        member.removeAttributeValue();
        iterator = IIIlllll.findList().iterator();
          goto _L1
_L3:
        MemberAttribute memberattribute;
        String s;
        memberattribute = (MemberAttribute)iterator.next();
        s = request.getParameter((new StringBuilder("memberAttribute_")).append(memberattribute.getId()).toString());
        if(memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.name || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.address || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.zipCode || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.phone || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.mobile || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.text || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.select)
        {
            if(memberattribute.getIsRequired().booleanValue() && StringUtils.isEmpty(s))
                return "/admin/common/error";
            member.setAttributeValue(memberattribute, s);
            continue; /* Loop/switch isn't completed */
        }
        if(memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.gender)
        {
            net.shopxx.entity.Member.Gender gender = StringUtils.isNotEmpty(s) ? net.shopxx.entity.Member.Gender.valueOf(s) : null;
            if(memberattribute.getIsRequired().booleanValue() && gender == null)
                return "/admin/common/error";
            member.setGender(gender);
            continue; /* Loop/switch isn't completed */
        }
        if(memberattribute.getType() != net.shopxx.entity.MemberAttribute.Type.birth)
            break MISSING_BLOCK_LABEL_482;
        java.util.Date date = StringUtils.isNotEmpty(s) ? DateUtils.parseDate(s, CommonAttributes.DATE_PATTERNS) : null;
        if(memberattribute.getIsRequired().booleanValue() && date == null)
            return "/admin/common/error";
        try
        {
            member.setBirth(date);
        }
        catch(ParseException parseexception)
        {
            return "/admin/common/error";
        }
        continue; /* Loop/switch isn't completed */
        if(memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.area)
        {
            Area area = StringUtils.isNotEmpty(s) ? (Area)IIlIIIII.find(Long.valueOf(s)) : null;
            if(area != null)
                member.setArea(area);
            else
            if(memberattribute.getIsRequired().booleanValue())
                return "/admin/common/error";
        } else
        if(memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.checkbox)
        {
            String as[] = request.getParameterValues((new StringBuilder("memberAttribute_")).append(memberattribute.getId()).toString());
            List list1 = as == null ? null : Arrays.asList(as);
            if(memberattribute.getIsRequired().booleanValue() && (list1 == null || list1.isEmpty()))
                return "/admin/common/error";
            member.setAttributeValue(memberattribute, list1);
        }
_L1:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        member.setUsername(member.getUsername().toLowerCase());
        member.setPassword(DigestUtils.md5Hex(member.getPassword()));
        member.setAmount(new BigDecimal(0));
        member.setIsLocked(Boolean.valueOf(false));
        member.setLoginFailureCount(Integer.valueOf(0));
        member.setLockedDate(null);
        member.setRegisterIp(request.getRemoteAddr());
        member.setLoginIp(null);
        member.setLoginDate(null);
        member.setSafeKey(null);
        member.setCart(null);
        member.setOrders(null);
        member.setDeposits(null);
        member.setPayments(null);
        member.setCouponCodes(null);
        member.setReceivers(null);
        member.setReviews(null);
        member.setConsultations(null);
        member.setFavoriteProducts(null);
        member.setProductNotifies(null);
        member.setInMessages(null);
        member.setOutMessages(null);
        IIIlllIl.save(member, IIlIIIIl.getCurrent());
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("genders", net.shopxx.entity.Member.Gender.values());
        model.addAttribute("memberRanks", IIIllllI.findAll());
        model.addAttribute("memberAttributes", IIIlllll.findList());
        model.addAttribute("member", IIIlllIl.find(id));
        return "/admin/member/edit";
    }

    public String update(Member member, Long memberRankId, Integer modifyPoint, BigDecimal modifyBalance, String depositMemo, HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        Member member1;
        Iterator iterator;
        member.setMemberRank((MemberRank)IIIllllI.find(memberRankId));
        if(!IIIllIlI(member, new Class[0]))
            return "/admin/common/error";
        Setting setting = SettingUtils.get();
        if(member.getPassword() != null && (member.getPassword().length() < setting.getPasswordMinLength().intValue() || member.getPassword().length() > setting.getPasswordMaxLength().intValue()))
            return "/admin/common/error";
        member1 = (Member)IIIlllIl.find(member.getId());
        if(member1 == null)
            return "/admin/common/error";
        if(!setting.getIsDuplicateEmail().booleanValue() && !IIIlllIl.emailUnique(member1.getEmail(), member.getEmail()))
            return "/admin/common/error";
        member.removeAttributeValue();
        iterator = IIIlllll.findList().iterator();
          goto _L1
_L3:
        MemberAttribute memberattribute;
        String s;
        memberattribute = (MemberAttribute)iterator.next();
        s = request.getParameter((new StringBuilder("memberAttribute_")).append(memberattribute.getId()).toString());
        if(memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.name || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.address || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.zipCode || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.phone || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.mobile || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.text || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.select)
        {
            if(memberattribute.getIsRequired().booleanValue() && StringUtils.isEmpty(s))
                return "/admin/common/error";
            member.setAttributeValue(memberattribute, s);
            continue; /* Loop/switch isn't completed */
        }
        if(memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.gender)
        {
            net.shopxx.entity.Member.Gender gender = StringUtils.isNotEmpty(s) ? net.shopxx.entity.Member.Gender.valueOf(s) : null;
            if(memberattribute.getIsRequired().booleanValue() && gender == null)
                return "/admin/common/error";
            member.setGender(gender);
            continue; /* Loop/switch isn't completed */
        }
        if(memberattribute.getType() != net.shopxx.entity.MemberAttribute.Type.birth)
            break MISSING_BLOCK_LABEL_442;
        java.util.Date date = StringUtils.isNotEmpty(s) ? DateUtils.parseDate(s, CommonAttributes.DATE_PATTERNS) : null;
        if(memberattribute.getIsRequired().booleanValue() && date == null)
            return "/admin/common/error";
        try
        {
            member.setBirth(date);
        }
        catch(ParseException parseexception)
        {
            return "/admin/common/error";
        }
        continue; /* Loop/switch isn't completed */
        if(memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.area)
        {
            Area area = StringUtils.isNotEmpty(s) ? (Area)IIlIIIII.find(Long.valueOf(s)) : null;
            if(area != null)
                member.setArea(area);
            else
            if(memberattribute.getIsRequired().booleanValue())
                return "/admin/common/error";
        } else
        if(memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.checkbox)
        {
            String as[] = request.getParameterValues((new StringBuilder("memberAttribute_")).append(memberattribute.getId()).toString());
            List list1 = as == null ? null : Arrays.asList(as);
            if(memberattribute.getIsRequired().booleanValue() && (list1 == null || list1.isEmpty()))
                return "/admin/common/error";
            member.setAttributeValue(memberattribute, list1);
        }
_L1:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        if(StringUtils.isEmpty(member.getPassword()))
            member.setPassword(member1.getPassword());
        else
            member.setPassword(DigestUtils.md5Hex(member.getPassword()));
        if(member1.getIsLocked().booleanValue() && !member.getIsLocked().booleanValue())
        {
            member.setLoginFailureCount(Integer.valueOf(0));
            member.setLockedDate(null);
        } else
        {
            member.setIsLocked(member1.getIsLocked());
            member.setLoginFailureCount(member1.getLoginFailureCount());
            member.setLockedDate(member1.getLockedDate());
        }
        BeanUtils.copyProperties(member, member1, new String[] {
            "username", "point", "amount", "balance", "registerIp", "loginIp", "loginDate", "safeKey", "cart", "orders", 
            "deposits", "payments", "couponCodes", "receivers", "reviews", "consultations", "favoriteProducts", "productNotifies", "inMessages", "outMessages"
        });
        IIIlllIl.update(member1, modifyPoint, modifyBalance, depositMemo, IIlIIIIl.getCurrent());
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("memberRanks", IIIllllI.findAll());
        model.addAttribute("memberAttributes", IIIlllll.findAll());
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/member/list";
    }

    public Message delete(Long ids[])
    {
        if(ids != null)
        {
            Long along[];
            int j = (along = ids).length;
            for(int i = 0; i < j; i++)
            {
                Long long1 = along[i];
                Member member = (Member)IIIlllIl.find(long1);
                if(member != null && member.getBalance().compareTo(new BigDecimal(0)) > 0)
                    return Message.error("admin.member.deleteExistDepositNotAllowed", new Object[] {
                        member.getUsername()
                    });
            }

            IIIlllIl.delete(ids);
        }
        return IIIlllII;
    }

    private MemberService IIIlllIl;
    private MemberRankService IIIllllI;
    private MemberAttributeService IIIlllll;
    private AreaService IIlIIIII;
    private AdminService IIlIIIIl;
}
