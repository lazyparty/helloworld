// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import net.shopxx.service.FriendLinkService;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.shop:
//            BaseController

public class FriendLinkController extends BaseController
{

    public FriendLinkController()
    {
    }

    public String index(ModelMap model)
    {
        model.addAttribute("textFriendLinks", IIIlllIl.findList(net.shopxx.entity.FriendLink.Type.text));
        model.addAttribute("imageFriendLinks", IIIlllIl.findList(net.shopxx.entity.FriendLink.Type.image));
        return "/shop/friend_link/index";
    }

    private FriendLinkService IIIlllIl;
}
