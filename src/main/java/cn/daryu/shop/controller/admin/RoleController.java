// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.Set;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Role;
import net.shopxx.service.RoleService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class RoleController extends BaseController
{

    public RoleController()
    {
    }

    public String add()
    {
        return "/admin/role/add";
    }

    public String save(Role role, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(role, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            role.setIsSystem(Boolean.valueOf(false));
            role.setAdmins(null);
            IIIlllIl.save(role);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("role", IIIlllIl.find(id));
        return "/admin/role/edit";
    }

    public String update(Role role, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(role, new Class[0]))
            return "/admin/common/error";
        Role role1 = (Role)IIIlllIl.find(role.getId());
        if(role1 == null || role1.getIsSystem().booleanValue())
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.update(role, new String[] {
                "isSystem", "admins"
            });
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/role/list";
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
                Role role = (Role)IIIlllIl.find(long1);
                if(role != null && (role.getIsSystem().booleanValue() || role.getAdmins() != null && !role.getAdmins().isEmpty()))
                    return Message.error("admin.role.deleteExistNotAllowed", new Object[] {
                        role.getName()
                    });
            }

            IIIlllIl.delete(ids);
        }
        return IIIlllII;
    }

    private RoleService IIIlllIl;
}
