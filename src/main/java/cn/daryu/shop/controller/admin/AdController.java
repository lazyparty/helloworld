// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.Date;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Ad;
import net.shopxx.entity.AdPosition;
import net.shopxx.service.AdPositionService;
import net.shopxx.service.AdService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class AdController extends BaseController
{

    public AdController()
    {
    }

    public String add(ModelMap model)
    {
        model.addAttribute("types", net.shopxx.entity.Ad.Type.values());
        model.addAttribute("adPositions", IIIllllI.findAll());
        return "/admin/ad/add";
    }

    public String save(Ad ad, Long adPositionId, RedirectAttributes redirectAttributes)
    {
        ad.setAdPosition((AdPosition)IIIllllI.find(adPositionId));
        if(!IIIllIlI(ad, new Class[0]))
            return "/admin/common/error";
        if(ad.getBeginDate() != null && ad.getEndDate() != null && ad.getBeginDate().after(ad.getEndDate()))
            return "/admin/common/error";
        if(ad.getType() == net.shopxx.entity.Ad.Type.text)
            ad.setPath(null);
        else
            ad.setContent(null);
        IIIlllIl.save(ad);
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("types", net.shopxx.entity.Ad.Type.values());
        model.addAttribute("ad", IIIlllIl.find(id));
        model.addAttribute("adPositions", IIIllllI.findAll());
        return "/admin/ad/edit";
    }

    public String update(Ad ad, Long adPositionId, RedirectAttributes redirectAttributes)
    {
        ad.setAdPosition((AdPosition)IIIllllI.find(adPositionId));
        if(!IIIllIlI(ad, new Class[0]))
            return "/admin/common/error";
        if(ad.getBeginDate() != null && ad.getEndDate() != null && ad.getBeginDate().after(ad.getEndDate()))
            return "/admin/common/error";
        if(ad.getType() == net.shopxx.entity.Ad.Type.text)
            ad.setPath(null);
        else
            ad.setContent(null);
        IIIlllIl.update(ad);
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/ad/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private AdService IIIlllIl;
    private AdPositionService IIIllllI;
}
