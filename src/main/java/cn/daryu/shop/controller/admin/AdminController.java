// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.HashSet;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Admin;
import net.shopxx.service.AdminService;
import net.shopxx.service.RoleService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class AdminController extends BaseController
{

    public AdminController()
    {
    }

    public boolean checkUsername(String username)
    {
        if(StringUtils.isEmpty(username))
            return false;
        return !IIIlllIl.usernameExists(username);
    }

    public String add(ModelMap model)
    {
        model.addAttribute("roles", IIIllllI.findAll());
        return "/admin/admin/add";
    }

    public String save(Admin admin, Long roleIds[], RedirectAttributes redirectAttributes)
    {
        admin.setRoles(new HashSet(IIIllllI.findList(roleIds)));
        if(!IIIllIlI(admin, new Class[] {
    net/shopxx/entity/BaseEntity$Save
}))
            return "/admin/common/error";
        if(IIIlllIl.usernameExists(admin.getUsername()))
        {
            return "/admin/common/error";
        } else
        {
            admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
            admin.setIsLocked(Boolean.valueOf(false));
            admin.setLoginFailureCount(Integer.valueOf(0));
            admin.setLockedDate(null);
            admin.setLoginDate(null);
            admin.setLoginIp(null);
            admin.setOrders(null);
            IIIlllIl.save(admin);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("roles", IIIllllI.findAll());
        model.addAttribute("admin", IIIlllIl.find(id));
        return "/admin/admin/edit";
    }

    public String update(Admin admin, Long roleIds[], RedirectAttributes redirectAttributes)
    {
        admin.setRoles(new HashSet(IIIllllI.findList(roleIds)));
        if(!IIIllIlI(admin, new Class[0]))
            return "/admin/common/error";
        Admin admin1 = (Admin)IIIlllIl.find(admin.getId());
        if(admin1 == null)
            return "/admin/common/error";
        if(StringUtils.isNotEmpty(admin.getPassword()))
            admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
        else
            admin.setPassword(admin1.getPassword());
        if(admin1.getIsLocked().booleanValue() && !admin.getIsLocked().booleanValue())
        {
            admin.setLoginFailureCount(Integer.valueOf(0));
            admin.setLockedDate(null);
        } else
        {
            admin.setIsLocked(admin1.getIsLocked());
            admin.setLoginFailureCount(admin1.getLoginFailureCount());
            admin.setLockedDate(admin1.getLockedDate());
        }
        IIIlllIl.update(admin, new String[] {
            "username", "loginDate", "loginIp", "orders"
        });
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/admin/list";
    }

    public Message delete(Long ids[])
    {
        if((long)ids.length >= IIIlllIl.count())
        {
            return Message.error("admin.common.deleteAllNotAllowed", new Object[0]);
        } else
        {
            IIIlllIl.delete(ids);
            return IIIlllII;
        }
    }

    private AdminService IIIlllIl;
    private RoleService IIIllllI;
}
