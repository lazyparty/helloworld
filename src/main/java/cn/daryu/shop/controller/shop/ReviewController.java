// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import net.shopxx.*;
import net.shopxx.entity.Product;
import net.shopxx.entity.Review;
import net.shopxx.service.*;
import net.shopxx.util.SettingUtils;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.shop:
//            BaseController

public class ReviewController extends BaseController
{

    public ReviewController()
    {
    }

    public String add(Long id, ModelMap model)
    {
        Setting setting = SettingUtils.get();
        if(!setting.getIsReviewEnabled().booleanValue())
            throw new ResourceNotFoundException();
        Product product = (Product)IIIlllll.find(id);
        if(product == null)
        {
            throw new ResourceNotFoundException();
        } else
        {
            model.addAttribute("product", product);
            model.addAttribute("captchaId", UUID.randomUUID().toString());
            return "/shop/review/add";
        }
    }

    public String content(Long id, Integer pageNumber, ModelMap model)
    {
        Setting setting = SettingUtils.get();
        if(!setting.getIsReviewEnabled().booleanValue())
            throw new ResourceNotFoundException();
        Product product = (Product)IIIlllll.find(id);
        if(product == null)
        {
            throw new ResourceNotFoundException();
        } else
        {
            Pageable pageable = new Pageable(pageNumber, Integer.valueOf(10));
            model.addAttribute("product", product);
            model.addAttribute("page", IIIllllI.findPage(null, product, null, Boolean.valueOf(true), pageable));
            return "/shop/review/content";
        }
    }

    public Message save(String captchaId, String captcha, Long id, Integer score, String content, HttpServletRequest request)
    {
        if(!IIlIIIIl.isValid(net.shopxx.Setting.CaptchaType.review, captchaId, captcha))
            return Message.error("shop.captcha.invalid", new Object[0]);
        Setting setting = SettingUtils.get();
        if(!setting.getIsReviewEnabled().booleanValue())
            return Message.error("shop.review.disabled", new Object[0]);
        if(!IIIllIlI(net/shopxx/entity/Review, "score", score, new Class[0]) || !IIIllIlI(net/shopxx/entity/Review, "content", content, new Class[0]))
            return IIIllIll;
        Product product = (Product)IIIlllll.find(id);
        if(product == null)
            return IIIllIll;
        net.shopxx.entity.Member member = IIlIIIII.getCurrent();
        if(setting.getReviewAuthority() != net.shopxx.Setting.ReviewAuthority.anyone && member == null)
            return Message.error("shop.review.accessDenied", new Object[0]);
        if(setting.getReviewAuthority() == net.shopxx.Setting.ReviewAuthority.purchased)
        {
            if(!IIIlllll.isPurchased(member, product))
                return Message.error("shop.review.noPurchased", new Object[0]);
            if(IIIllllI.isReviewed(member, product))
                return Message.error("shop.review.reviewed", new Object[0]);
        }
        Review review = new Review();
        review.setScore(score);
        review.setContent(content);
        review.setIp(request.getRemoteAddr());
        review.setMember(member);
        review.setProduct(product);
        if(setting.getIsReviewCheck().booleanValue())
        {
            review.setIsShow(Boolean.valueOf(false));
            IIIllllI.save(review);
            return Message.success("shop.review.check", new Object[0]);
        } else
        {
            review.setIsShow(Boolean.valueOf(true));
            IIIllllI.save(review);
            return Message.success("shop.review.success", new Object[0]);
        }
    }

    private static final int IIIlllIl = 10;
    private ReviewService IIIllllI;
    private ProductService IIIlllll;
    private MemberService IIlIIIII;
    private CaptchaService IIlIIIIl;
}
