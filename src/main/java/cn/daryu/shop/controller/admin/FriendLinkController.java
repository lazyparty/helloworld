// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.FriendLink;
import net.shopxx.service.FriendLinkService;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class FriendLinkController extends BaseController
{

    public FriendLinkController()
    {
    }

    public String add(ModelMap model)
    {
        model.addAttribute("types", net.shopxx.entity.FriendLink.Type.values());
        return "/admin/friend_link/add";
    }

    public String save(FriendLink friendLink, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(friendLink, new Class[0]))
            return "/admin/common/error";
        if(friendLink.getType() == net.shopxx.entity.FriendLink.Type.text)
            friendLink.setLogo(null);
        else
        if(StringUtils.isEmpty(friendLink.getLogo()))
            return "/admin/common/error";
        IIIlllIl.save(friendLink);
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("types", net.shopxx.entity.FriendLink.Type.values());
        model.addAttribute("friendLink", IIIlllIl.find(id));
        return "/admin/friend_link/edit";
    }

    public String update(FriendLink friendLink, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(friendLink, new Class[0]))
            return "/admin/common/error";
        if(friendLink.getType() == net.shopxx.entity.FriendLink.Type.text)
            friendLink.setLogo(null);
        else
        if(StringUtils.isEmpty(friendLink.getLogo()))
            return "/admin/common/error";
        IIIlllIl.update(friendLink);
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/friend_link/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private FriendLinkService IIIlllIl;
}
