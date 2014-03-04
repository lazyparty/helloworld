// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.entity.Admin;
import net.shopxx.service.AdminService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class ProfileController extends BaseController
{

    public ProfileController()
    {
    }

    public boolean checkCurrentPassword(String currentPassword)
    {
        if(StringUtils.isEmpty(currentPassword))
            return false;
        Admin admin = IIIlllIl.getCurrent();
        return StringUtils.equals(DigestUtils.md5Hex(currentPassword), admin.getPassword());
    }

    public String edit(ModelMap model)
    {
        model.addAttribute("admin", IIIlllIl.getCurrent());
        return "/admin/profile/edit";
    }

    public String update(String currentPassword, String password, String email, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(net/shopxx/entity/Admin, "email", email, new Class[0]))
            return "/admin/common/error";
        Admin admin = IIIlllIl.getCurrent();
        if(StringUtils.isNotEmpty(currentPassword) && StringUtils.isNotEmpty(password))
        {
            if(!IIIllIlI(net/shopxx/entity/Admin, "password", password, new Class[0]))
                return "/admin/common/error";
            if(!StringUtils.equals(DigestUtils.md5Hex(currentPassword), admin.getPassword()))
                return "/admin/common/error";
            admin.setPassword(DigestUtils.md5Hex(password));
        }
        admin.setEmail(email);
        IIIlllIl.update(admin);
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:edit.jhtml";
    }

    private AdminService IIIlllIl;
}
