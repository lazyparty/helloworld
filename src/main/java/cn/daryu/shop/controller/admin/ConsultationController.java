// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import javax.servlet.http.HttpServletRequest;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Consultation;
import net.shopxx.service.ConsultationService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class ConsultationController extends BaseController
{

    public ConsultationController()
    {
    }

    public String reply(Long id, ModelMap model)
    {
        model.addAttribute("consultation", IIIlllIl.find(id));
        return "/admin/consultation/reply";
    }

    public String reply(Long id, String content, HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(net/shopxx/entity/Consultation, "content", content, new Class[0]))
            return "/admin/common/error";
        Consultation consultation = (Consultation)IIIlllIl.find(id);
        if(consultation == null)
        {
            return "/admin/common/error";
        } else
        {
            Consultation consultation1 = new Consultation();
            consultation1.setContent(content);
            consultation1.setIp(request.getRemoteAddr());
            IIIlllIl.reply(consultation, consultation1);
            IIIllIlI(redirectAttributes, IIIlllII);
            return (new StringBuilder("redirect:reply.jhtml?id=")).append(id).toString();
        }
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("consultation", IIIlllIl.find(id));
        return "/admin/consultation/edit";
    }

    public String update(Long id, Boolean isShow, RedirectAttributes redirectAttributes)
    {
        Consultation consultation = (Consultation)IIIlllIl.find(id);
        if(consultation == null)
            return "/admin/common/error";
        if(isShow != consultation.getIsShow())
        {
            consultation.setIsShow(isShow);
            IIIlllIl.update(consultation);
        }
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(null, null, null, pageable));
        return "/admin/consultation/list";
    }

    public Message deleteReply(Long id)
    {
        Consultation consultation = (Consultation)IIIlllIl.find(id);
        if(consultation == null || consultation.getForConsultation() == null)
        {
            return IIIllIll;
        } else
        {
            IIIlllIl.delete(consultation);
            return IIIlllII;
        }
    }

    public Message delete(Long ids[])
    {
        if(ids != null)
            IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private ConsultationService IIIlllIl;
}
