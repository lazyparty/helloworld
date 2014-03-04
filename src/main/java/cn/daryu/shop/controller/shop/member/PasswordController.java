// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop.member;

import javax.servlet.http.HttpServletRequest;
import net.shopxx.Setting;
import net.shopxx.controller.shop.BaseController;
import net.shopxx.entity.Member;
import net.shopxx.service.MemberService;
import net.shopxx.util.SettingUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class PasswordController extends BaseController
{

    public PasswordController()
    {
    }

    public boolean checkCurrentPassword(String currentPassword)
    {
        if(StringUtils.isEmpty(currentPassword))
            return false;
        Member member = IIIlllIl.getCurrent();
        return StringUtils.equals(DigestUtils.md5Hex(currentPassword), member.getPassword());
    }

    public String edit()
    {
        return "shop/member/password/edit";
    }

    public String update(String currentPassword, String password, HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        if(StringUtils.isEmpty(password) || StringUtils.isEmpty(currentPassword))
            return "/shop/common/error";
        if(!IIIllIlI(net/shopxx/entity/Member, "password", password, new Class[0]))
            return "/shop/common/error";
        Setting setting = SettingUtils.get();
        if(password.length() < setting.getPasswordMinLength().intValue() || password.length() > setting.getPasswordMaxLength().intValue())
            return "/shop/common/error";
        Member member = IIIlllIl.getCurrent();
        if(!StringUtils.equals(DigestUtils.md5Hex(currentPassword), member.getPassword()))
        {
            return "/shop/common/error";
        } else
        {
            member.setPassword(DigestUtils.md5Hex(password));
            IIIlllIl.update(member);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:edit.jhtml";
        }
    }

    private MemberService IIIlllIl;
}
