// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop.member;

import java.util.Set;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.controller.shop.BaseController;
import net.shopxx.entity.Member;
import net.shopxx.entity.ProductNotify;
import net.shopxx.service.MemberService;
import net.shopxx.service.ProductNotifyService;
import org.springframework.ui.Model;

public class ProductNotifyController extends BaseController
{

    public ProductNotifyController()
    {
    }

    public String list(Integer pageNumber, Model model)
    {
        Member member = IIIllllI.getCurrent();
        Pageable pageable = new Pageable(pageNumber, Integer.valueOf(10));
        model.addAttribute("page", IIIlllIl.findPage(member, null, null, null, pageable));
        return "/shop/member/product_notify/list";
    }

    public Message delete(Long id)
    {
        ProductNotify productnotify = (ProductNotify)IIIlllIl.find(id);
        if(productnotify == null)
            return IIIllIll;
        Member member = IIIllllI.getCurrent();
        if(!member.getProductNotifies().contains(productnotify))
        {
            return IIIllIll;
        } else
        {
            IIIlllIl.delete(productnotify);
            return IIIlllII;
        }
    }

    private static final int IIIlllll = 10;
    ProductNotifyService IIIlllIl;
    MemberService IIIllllI;
}
