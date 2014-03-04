// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.math.BigDecimal;
import java.util.Set;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.MemberRank;
import net.shopxx.service.MemberRankService;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class MemberRankController extends BaseController
{

    public MemberRankController()
    {
    }

    public boolean checkName(String previousName, String name)
    {
        if(StringUtils.isEmpty(name))
            return false;
        return IIIlllIl.nameUnique(previousName, name);
    }

    public boolean checkAmount(BigDecimal previousAmount, BigDecimal amount)
    {
        if(amount == null)
            return false;
        return IIIlllIl.amountUnique(previousAmount, amount);
    }

    public String add(ModelMap model)
    {
        return "/admin/member_rank/add";
    }

    public String save(MemberRank memberRank, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(memberRank, new Class[0]))
            return "/admin/common/error";
        if(IIIlllIl.nameExists(memberRank.getName()))
            return "/admin/common/error";
        if(memberRank.getIsSpecial().booleanValue())
            memberRank.setAmount(null);
        else
        if(memberRank.getAmount() == null || IIIlllIl.amountExists(memberRank.getAmount()))
            return "/admin/common/error";
        memberRank.setMembers(null);
        memberRank.setPromotions(null);
        IIIlllIl.save(memberRank);
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("memberRank", IIIlllIl.find(id));
        return "/admin/member_rank/edit";
    }

    public String update(MemberRank memberRank, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(memberRank, new Class[0]))
            return "/admin/common/error";
        MemberRank memberrank = (MemberRank)IIIlllIl.find(memberRank.getId());
        if(memberrank == null)
            return "/admin/common/error";
        if(!IIIlllIl.nameUnique(memberrank.getName(), memberRank.getName()))
            return "/admin/common/error";
        if(memberrank.getIsDefault().booleanValue())
            memberRank.setIsDefault(Boolean.valueOf(true));
        if(memberRank.getIsSpecial().booleanValue())
            memberRank.setAmount(null);
        else
        if(memberRank.getAmount() == null || !IIIlllIl.amountUnique(memberrank.getAmount(), memberRank.getAmount()))
            return "/admin/common/error";
        IIIlllIl.update(memberRank, new String[] {
            "members", "promotions"
        });
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/member_rank/list";
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
                MemberRank memberrank = (MemberRank)IIIlllIl.find(long1);
                if(memberrank != null && memberrank.getMembers() != null && !memberrank.getMembers().isEmpty())
                    return Message.error("admin.memberRank.deleteExistNotAllowed", new Object[] {
                        memberrank.getName()
                    });
            }

            long l = IIIlllIl.count();
            if((long)ids.length >= l)
                return Message.error("admin.common.deleteAllNotAllowed", new Object[0]);
            IIIlllIl.delete(ids);
        }
        return IIIlllII;
    }

    private MemberRankService IIIlllIl;
}
