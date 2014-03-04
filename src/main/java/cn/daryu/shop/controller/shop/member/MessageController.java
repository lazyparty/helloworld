// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop.member;

import javax.servlet.http.HttpServletRequest;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.controller.shop.BaseController;
import net.shopxx.entity.Member;
import net.shopxx.service.MemberService;
import net.shopxx.service.MessageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class MessageController extends BaseController
{

    public MessageController()
    {
    }

    public boolean checkUsername(String username)
    {
        return !StringUtils.equalsIgnoreCase(username, IIIllllI.getCurrentUsername()) && IIIllllI.usernameExists(username);
    }

    public String send(Long draftMessageId, Model model)
    {
        net.shopxx.entity.Message message = (net.shopxx.entity.Message)IIIlllIl.find(draftMessageId);
        if(message != null && message.getIsDraft().booleanValue() && message.getSender() == IIIllllI.getCurrent())
            model.addAttribute("draftMessage", message);
        return "/shop/member/message/send";
    }

    public String send(Long draftMessageId, String username, String title, String content, Boolean isDraft, HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(net/shopxx/entity/Message, "content", content, new Class[0]))
            return "/shop/common/error";
        Member member = IIIllllI.getCurrent();
        net.shopxx.entity.Message message = (net.shopxx.entity.Message)IIIlllIl.find(draftMessageId);
        if(message != null && message.getIsDraft().booleanValue() && message.getSender() == member)
            IIIlllIl.delete(message);
        Member member1 = null;
        if(StringUtils.isNotEmpty(username))
        {
            member1 = IIIllllI.findByUsername(username);
            if(member1 == null || member1 == member)
                return "/shop/common/error";
        }
        net.shopxx.entity.Message message1 = new net.shopxx.entity.Message();
        message1.setTitle(title);
        message1.setContent(content);
        message1.setIp(request.getRemoteAddr());
        message1.setIsDraft(isDraft);
        message1.setSenderRead(Boolean.valueOf(true));
        message1.setReceiverRead(Boolean.valueOf(false));
        message1.setSenderDelete(Boolean.valueOf(false));
        message1.setReceiverDelete(Boolean.valueOf(false));
        message1.setSender(member);
        message1.setReceiver(member1);
        IIIlllIl.save(message1);
        if(isDraft.booleanValue())
        {
            IIIllIlI(redirectAttributes, Message.success("shop.member.message.saveDraftSuccess", new Object[0]));
            return "redirect:draft.jhtml";
        } else
        {
            IIIllIlI(redirectAttributes, Message.success("shop.member.message.sendSuccess", new Object[0]));
            return "redirect:list.jhtml";
        }
    }

    public String view(Long id, Model model)
    {
        net.shopxx.entity.Message message = (net.shopxx.entity.Message)IIIlllIl.find(id);
        if(message == null || message.getIsDraft().booleanValue() || message.getForMessage() != null)
            return "/shop/common/error";
        Member member = IIIllllI.getCurrent();
        if(message.getSender() != member && message.getReceiver() != member || message.getReceiver() == member && message.getReceiverDelete().booleanValue() || message.getSender() == member && message.getSenderDelete().booleanValue())
            return "/shop/common/error";
        if(member == message.getReceiver())
            message.setReceiverRead(Boolean.valueOf(true));
        else
            message.setSenderRead(Boolean.valueOf(true));
        IIIlllIl.update(message);
        model.addAttribute("memberMessage", message);
        return "/shop/member/message/view";
    }

    public String reply(Long id, String content, HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(net/shopxx/entity/Message, "content", content, new Class[0]))
            return "/shop/common/error";
        net.shopxx.entity.Message message = (net.shopxx.entity.Message)IIIlllIl.find(id);
        if(message == null || message.getIsDraft().booleanValue() || message.getForMessage() != null)
            return "/shop/common/error";
        Member member = IIIllllI.getCurrent();
        if(message.getSender() != member && message.getReceiver() != member || message.getReceiver() == member && message.getReceiverDelete().booleanValue() || message.getSender() == member && message.getSenderDelete().booleanValue())
            return "/shop/common/error";
        net.shopxx.entity.Message message1 = new net.shopxx.entity.Message();
        message1.setTitle((new StringBuilder("reply: ")).append(message.getTitle()).toString());
        message1.setContent(content);
        message1.setIp(request.getRemoteAddr());
        message1.setIsDraft(Boolean.valueOf(false));
        message1.setSenderRead(Boolean.valueOf(true));
        message1.setReceiverRead(Boolean.valueOf(false));
        message1.setSenderDelete(Boolean.valueOf(false));
        message1.setReceiverDelete(Boolean.valueOf(false));
        message1.setSender(member);
        message1.setReceiver(member != message.getReceiver() ? message.getReceiver() : message.getSender());
        if(member == message.getReceiver() && !message.getSenderDelete().booleanValue() || member == message.getSender() && !message.getReceiverDelete().booleanValue())
            message1.setForMessage(message);
        IIIlllIl.save(message1);
        if(member.equals(message.getSender()))
        {
            message.setSenderRead(Boolean.valueOf(true));
            message.setReceiverRead(Boolean.valueOf(false));
        } else
        {
            message.setSenderRead(Boolean.valueOf(false));
            message.setReceiverRead(Boolean.valueOf(true));
        }
        IIIlllIl.update(message);
        if(member == message.getReceiver() && !message.getSenderDelete().booleanValue() || member == message.getSender() && !message.getReceiverDelete().booleanValue())
        {
            IIIllIlI(redirectAttributes, IIIlllII);
            return (new StringBuilder("redirect:view.jhtml?id=")).append(message.getId()).toString();
        } else
        {
            IIIllIlI(redirectAttributes, Message.success("shop.member.message.replySuccess", new Object[0]));
            return "redirect:list.jhtml";
        }
    }

    public String list(Integer pageNumber, Model model)
    {
        Pageable pageable = new Pageable(pageNumber, Integer.valueOf(10));
        Member member = IIIllllI.getCurrent();
        model.addAttribute("page", IIIlllIl.findPage(member, pageable));
        return "/shop/member/message/list";
    }

    public String draft(Integer pageNumber, Model model)
    {
        Pageable pageable = new Pageable(pageNumber, Integer.valueOf(10));
        Member member = IIIllllI.getCurrent();
        model.addAttribute("page", IIIlllIl.findDraftPage(member, pageable));
        return "/shop/member/message/draft";
    }

    public Message delete(Long id)
    {
        Member member = IIIllllI.getCurrent();
        IIIlllIl.delete(id, member);
        return IIIlllII;
    }

    private static final int IIIlllll = 10;
    MessageService IIIlllIl;
    MemberService IIIllllI;
}
