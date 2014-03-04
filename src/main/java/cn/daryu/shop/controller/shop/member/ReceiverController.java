// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop.member;

import java.util.Set;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.controller.shop.BaseController;
import net.shopxx.entity.*;
import net.shopxx.service.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ReceiverController extends BaseController
{

    public ReceiverController()
    {
    }

    public String list(Integer pageNumber, ModelMap model)
    {
        Member member = IIIllllI.getCurrent();
        Pageable pageable = new Pageable(pageNumber, Integer.valueOf(10));
        model.addAttribute("page", IIlIIIII.findPage(member, pageable));
        return "shop/member/receiver/list";
    }

    public String add(RedirectAttributes redirectAttributes)
    {
        Member member = IIIllllI.getCurrent();
        if(Receiver.MAX_RECEIVER_COUNT != null && member.getReceivers().size() >= Receiver.MAX_RECEIVER_COUNT.intValue())
        {
            IIIllIlI(redirectAttributes, Message.warn("shop.member.receiver.addCountNotAllowed", new Object[] {
                Receiver.MAX_RECEIVER_COUNT
            }));
            return "redirect:list.jhtml";
        } else
        {
            return "shop/member/receiver/add";
        }
    }

    public String save(Receiver receiver, Long areaId, RedirectAttributes redirectAttributes)
    {
        receiver.setArea((Area)IIIlllll.find(areaId));
        if(!IIIllIlI(receiver, new Class[0]))
            return "/shop/common/error";
        Member member = IIIllllI.getCurrent();
        if(Receiver.MAX_RECEIVER_COUNT != null && member.getReceivers().size() >= Receiver.MAX_RECEIVER_COUNT.intValue())
        {
            return "/shop/common/error";
        } else
        {
            receiver.setMember(member);
            IIlIIIII.save(receiver);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, ModelMap model, RedirectAttributes redirectAttributes)
    {
        Receiver receiver = (Receiver)IIlIIIII.find(id);
        if(receiver == null)
            return "/shop/common/error";
        Member member = IIIllllI.getCurrent();
        if(receiver.getMember() != member)
        {
            return "/shop/common/error";
        } else
        {
            model.addAttribute("receiver", receiver);
            return "shop/member/receiver/edit";
        }
    }

    public String update(Receiver receiver, Long id, Long areaId, RedirectAttributes redirectAttributes)
    {
        receiver.setArea((Area)IIIlllll.find(areaId));
        if(!IIIllIlI(receiver, new Class[0]))
            return "/shop/common/error";
        Receiver receiver1 = (Receiver)IIlIIIII.find(id);
        if(receiver1 == null)
            return "/shop/common/error";
        Member member = IIIllllI.getCurrent();
        if(receiver1.getMember() != member)
        {
            return "/shop/common/error";
        } else
        {
            IIlIIIII.update(receiver, new String[] {
                "member"
            });
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public Message delete(Long id)
    {
        Receiver receiver = (Receiver)IIlIIIII.find(id);
        if(receiver == null)
            return IIIllIll;
        Member member = IIIllllI.getCurrent();
        if(receiver.getMember() != member)
        {
            return IIIllIll;
        } else
        {
            IIlIIIII.delete(id);
            return IIIlllII;
        }
    }

    private static final int IIIlllIl = 10;
    private MemberService IIIllllI;
    private AreaService IIIlllll;
    private ReceiverService IIlIIIII;
}
