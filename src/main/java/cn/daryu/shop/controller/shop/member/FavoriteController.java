// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop.member;

import java.util.Set;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.controller.shop.BaseController;
import net.shopxx.entity.Member;
import net.shopxx.entity.Product;
import net.shopxx.service.MemberService;
import net.shopxx.service.ProductService;
import org.springframework.ui.ModelMap;

public class FavoriteController extends BaseController
{

    public FavoriteController()
    {
    }

    public Message add(Long id)
    {
        Product product = (Product)IIIlllll.find(id);
        if(product == null)
            return IIIllIll;
        Member member = IIIllllI.getCurrent();
        if(member.getFavoriteProducts().contains(product))
            return Message.warn("shop.member.favorite.exist", new Object[0]);
        if(Member.MAX_FAVORITE_COUNT != null && member.getFavoriteProducts().size() >= Member.MAX_FAVORITE_COUNT.intValue())
        {
            return Message.warn("shop.member.favorite.addCountNotAllowed", new Object[] {
                Member.MAX_FAVORITE_COUNT
            });
        } else
        {
            member.getFavoriteProducts().add(product);
            IIIllllI.update(member);
            return Message.success("shop.member.favorite.success", new Object[0]);
        }
    }

    public String list(Integer pageNumber, ModelMap model)
    {
        Member member = IIIllllI.getCurrent();
        Pageable pageable = new Pageable(pageNumber, Integer.valueOf(10));
        model.addAttribute("page", IIIlllll.findPage(member, pageable));
        return "shop/member/favorite/list";
    }

    public Message delete(Long id)
    {
        Product product = (Product)IIIlllll.find(id);
        if(product == null)
            return IIIllIll;
        Member member = IIIllllI.getCurrent();
        if(!member.getFavoriteProducts().contains(product))
        {
            return IIIllIll;
        } else
        {
            member.getFavoriteProducts().remove(product);
            IIIllllI.update(member);
            return IIIlllII;
        }
    }

    private static final int IIIlllIl = 10;
    private MemberService IIIllllI;
    private ProductService IIIlllll;
}
