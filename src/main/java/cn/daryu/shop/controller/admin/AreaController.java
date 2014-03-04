// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.ArrayList;
import net.shopxx.Message;
import net.shopxx.entity.Area;
import net.shopxx.service.AreaService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class AreaController extends BaseController
{

    public AreaController()
    {
    }

    public String add(Long parentId, ModelMap model)
    {
        model.addAttribute("parent", IIIlllIl.find(parentId));
        return "/admin/area/add";
    }

    public String save(Area area, Long parentId, RedirectAttributes redirectAttributes)
    {
        area.setParent((Area)IIIlllIl.find(parentId));
        if(!IIIllIlI(area, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            area.setFullName(null);
            area.setTreePath(null);
            area.setChildren(null);
            area.setMembers(null);
            area.setReceivers(null);
            area.setOrders(null);
            area.setDeliveryCenters(null);
            IIIlllIl.save(area);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("area", IIIlllIl.find(id));
        return "/admin/area/edit";
    }

    public String update(Area area, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(area, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.update(area, new String[] {
                "fullName", "treePath", "parent", "children", "members", "receivers", "orders", "deliveryCenters"
            });
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(Long parentId, ModelMap model)
    {
        Area area = (Area)IIIlllIl.find(parentId);
        if(area != null)
        {
            model.addAttribute("parent", area);
            model.addAttribute("areas", new ArrayList(area.getChildren()));
        } else
        {
            model.addAttribute("areas", IIIlllIl.findRoots());
        }
        return "/admin/area/list";
    }

    public Message delete(Long id)
    {
        IIIlllIl.delete(id);
        return IIIlllII;
    }

    private AreaService IIIlllIl;
}
