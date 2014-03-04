// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import javax.servlet.http.HttpServletRequest;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.service.MemberService;
import net.shopxx.service.MessageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class MessageController extends BaseController
{

    public MessageController()
    {
    }

    public boolean checkUsername(String username)
    {
        return IIIllllI.usernameExists(username);
    }

    public String send(Long draftMessageId, Model model)
    {
        net.shopxx.entity.Message message = (net.shopxx.entity.Message)IIIlllIl.find(draftMessageId);
        if(message != null && message.getIsDraft().booleanValue() && message.getSender() == null)
            model.addAttribute("draftMessage", message);
        return "admin/message/send";
    }

    public String send(Long draftMessageId, String username, String title, String content, Boolean isDraft, HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(net/shopxx/entity/Message, "content", content, new Class[0]))
            return "/admin/common/error";
        net.shopxx.entity.Message message = (net.shopxx.entity.Message)IIIlllIl.find(draftMessageId);
        if(message != null && message.getIsDraft().booleanValue() && message.getSender() == null)
            IIIlllIl.delete(message);
        net.shopxx.entity.Member member = null;
        if(StringUtils.isNotEmpty(username))
        {
            member = IIIllllI.findByUsername(username);
            if(member == null)
                return "/admin/common/error";
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
        message1.setSender(null);
        message1.setReceiver(member);
        message1.setForMessage(null);
        message1.setReplyMessages(null);
        IIIlllIl.save(message1);
        if(isDraft.booleanValue())
        {
            IIIllIlI(redirectAttributes, Message.success("admin.message.saveDraftSuccess", new Object[0]));
            return "redirect:draft.jhtml";
        } else
        {
            IIIllIlI(redirectAttributes, Message.success("admin.message.sendSuccess", new Object[0]));
            return "redirect:list.jhtml";
        }
    }

    public String view(Long id, Model model)
    {
        net.shopxx.entity.Message message = (net.shopxx.entity.Message)IIIlllIl.find(id);
        if(message == null || message.getIsDraft().booleanValue() || message.getForMessage() != null)
            return "/admin/common/error";
        if(message.getSender() != null && message.getReceiver() != null || message.getReceiver() == null && message.getReceiverDelete().booleanValue() || message.getSender() == null && message.getSenderDelete().booleanValue())
            return "/admin/common/error";
        if(message.getReceiver() == null)
            message.setReceiverRead(Boolean.valueOf(true));
        else
            message.setSenderRead(Boolean.valueOf(true));
        IIIlllIl.update(message);
        model.addAttribute("adminMessage", message);
        return "/admin/message/view";
    }

    public String reply(Long id, String content, HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(net/shopxx/entity/Message, "content", content, new Class[0]))
            return "/admin/common/error";
        net.shopxx.entity.Message message = (net.shopxx.entity.Message)IIIlllIl.find(id);
        if(message == null || message.getIsDraft().booleanValue() || message.getForMessage() != null)
            return "/admin/common/error";
        if(message.getSender() != null && message.getReceiver() != null || message.getReceiver() == null && message.getReceiverDelete().booleanValue() || message.getSender() == null && message.getSenderDelete().booleanValue())
            return "/admin/common/error";
        net.shopxx.entity.Message message1 = new net.shopxx.entity.Message();
        message1.setTitle((new StringBuilder("reply: ")).append(message.getTitle()).toString());
        message1.setContent(content);
        message1.setIp(request.getRemoteAddr());
        message1.setIsDraft(Boolean.valueOf(false));
        message1.setSenderRead(Boolean.valueOf(true));
        message1.setReceiverRead(Boolean.valueOf(false));
        message1.setSenderDelete(Boolean.valueOf(false));
        message1.setReceiverDelete(Boolean.valueOf(false));
        message1.setSender(null);
        message1.setReceiver(message.getReceiver() != null ? message.getReceiver() : message.getSender());
        if(message.getReceiver() == null && !message.getSenderDelete().booleanValue() || message.getSender() == null && !message.getReceiverDelete().booleanValue())
            message1.setForMessage(message);
        message1.setReplyMessages(null);
        IIIlllIl.save(message1);
        if(message.getSender() == null)
        {
            message.setSenderRead(Boolean.valueOf(true));
            message.setReceiverRead(Boolean.valueOf(false));
        } else
        {
            message.setSenderRead(Boolean.valueOf(false));
            message.setReceiverRead(Boolean.valueOf(true));
        }
        IIIlllIl.update(message);
        if(message.getReceiver() == null && !message.getSenderDelete().booleanValue() || message.getSender() == null && !message.getReceiverDelete().booleanValue())
        {
            IIIllIlI(redirectAttributes, IIIlllII);
            return (new StringBuilder("redirect:view.jhtml?id=")).append(message.getId()).toString();
        } else
        {
            IIIllIlI(redirectAttributes, Message.success("admin.message.replySuccess", new Object[0]));
            return "redirect:list.jhtml";
        }
    }

    public String list(Pageable pageable, Model model)
    {
        model.addAttribute("page", IIIlllIl.findPage(null, pageable));
        return "/admin/message/list";
    }

    public String draft(Pageable pageable, Model model)
    {
        model.addAttribute("page", IIIlllIl.findDraftPage(null, pageable));
        return "/admin/message/draft";
    }

    public Message delete(Long ids[])
    {
        if(ids != null)
        {
            Long along[];
            int j = (along = ids).length;
            for(int i = 0; i < j; i++)
            {
                Long long1 = along[i];
                IIIlllIl.delete(long1, null);
            }

        }
        return IIIlllII;
    }

    MessageService IIIlllIl;
    MemberService IIIllllI;
}
