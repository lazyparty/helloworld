// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import net.shopxx.*;
import net.shopxx.entity.Consultation;
import net.shopxx.entity.Product;
import net.shopxx.service.*;
import net.shopxx.util.SettingUtils;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.shop:
//            BaseController

public class ConsultationController extends BaseController
{

    public ConsultationController()
    {
    }

    public String add(Long id, ModelMap model)
    {
        Setting setting = SettingUtils.get();
        if(!setting.getIsConsultationEnabled().booleanValue())
            throw new ResourceNotFoundException();
        Product product = (Product)IIIlllll.find(id);
        if(product == null)
        {
            throw new ResourceNotFoundException();
        } else
        {
            model.addAttribute("product", product);
            model.addAttribute("captchaId", UUID.randomUUID().toString());
            return "/shop/consultation/add";
        }
    }

    public String content(Long id, Integer pageNumber, ModelMap model)
    {
        Setting setting = SettingUtils.get();
        if(!setting.getIsConsultationEnabled().booleanValue())
            throw new ResourceNotFoundException();
        Product product = (Product)IIIlllll.find(id);
        if(product == null)
        {
            throw new ResourceNotFoundException();
        } else
        {
            Pageable pageable = new Pageable(pageNumber, Integer.valueOf(10));
            model.addAttribute("product", product);
            model.addAttribute("page", IIIllllI.findPage(null, product, Boolean.valueOf(true), pageable));
            return "/shop/consultation/content";
        }
    }

    public Message save(String captchaId, String captcha, Long id, String content, HttpServletRequest request)
    {
        if(!IIlIIIIl.isValid(net.shopxx.Setting.CaptchaType.consultation, captchaId, captcha))
            return Message.error("shop.captcha.invalid", new Object[0]);
        Setting setting = SettingUtils.get();
        if(!setting.getIsConsultationEnabled().booleanValue())
            return Message.error("shop.consultation.disabled", new Object[0]);
        if(!IIIllIlI(net/shopxx/entity/Consultation, "content", content, new Class[0]))
            return IIIllIll;
        net.shopxx.entity.Member member = IIlIIIII.getCurrent();
        if(setting.getConsultationAuthority() != net.shopxx.Setting.ConsultationAuthority.anyone && member == null)
            return Message.error("shop.consultation.accessDenied", new Object[0]);
        Product product = (Product)IIIlllll.find(id);
        if(product == null)
            return IIIllIll;
        Consultation consultation = new Consultation();
        consultation.setContent(content);
        consultation.setIp(request.getRemoteAddr());
        consultation.setMember(member);
        consultation.setProduct(product);
        if(setting.getIsConsultationCheck().booleanValue())
        {
            consultation.setIsShow(Boolean.valueOf(false));
            IIIllllI.save(consultation);
            return Message.success("shop.consultation.check", new Object[0]);
        } else
        {
            consultation.setIsShow(Boolean.valueOf(true));
            IIIllllI.save(consultation);
            return Message.success("shop.consultation.success", new Object[0]);
        }
    }

    private static final int IIIlllIl = 10;
    private ConsultationService IIIllllI;
    private ProductService IIIlllll;
    private MemberService IIlIIIII;
    private CaptchaService IIlIIIIl;
}
