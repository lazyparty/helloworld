// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Review;
import net.shopxx.service.ReviewService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class ReviewController extends BaseController
{

    public ReviewController()
    {
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("review", IIIlllIl.find(id));
        return "/admin/review/edit";
    }

    public String update(Long id, Boolean isShow, RedirectAttributes redirectAttributes)
    {
        Review review = (Review)IIIlllIl.find(id);
        if(review == null)
        {
            return "/admin/common/error";
        } else
        {
            review.setIsShow(isShow);
            IIIlllIl.update(review);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(net.shopxx.entity.Review.Type type, Pageable pageable, ModelMap model)
    {
        model.addAttribute("type", type);
        model.addAttribute("types", net.shopxx.entity.Review.Type.values());
        model.addAttribute("page", IIIlllIl.findPage(null, null, type, null, pageable));
        return "/admin/review/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private ReviewService IIIlllIl;
}
