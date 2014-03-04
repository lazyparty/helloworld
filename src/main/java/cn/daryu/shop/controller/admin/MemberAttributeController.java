// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.Iterator;
import java.util.List;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.MemberAttribute;
import net.shopxx.service.MemberAttributeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class MemberAttributeController extends BaseController
{

    public MemberAttributeController()
    {
    }

    public String add(ModelMap model, RedirectAttributes redirectAttributes)
    {
        if(IIIlllIl.count() - 8L >= 10L)
            IIIllIlI(redirectAttributes, Message.warn("admin.memberAttribute.addCountNotAllowed", new Object[] {
                Integer.valueOf(10)
            }));
        return "/admin/member_attribute/add";
    }

    public String save(MemberAttribute memberAttribute, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(memberAttribute, new Class[] {
    net/shopxx/entity/BaseEntity$Save
}))
            return "/admin/common/error";
        if(memberAttribute.getType() == net.shopxx.entity.MemberAttribute.Type.select || memberAttribute.getType() == net.shopxx.entity.MemberAttribute.Type.checkbox)
        {
            List list1 = memberAttribute.getOptions();
            if(list1 != null)
            {
                for(Iterator iterator = list1.iterator(); iterator.hasNext();)
                {
                    String s = (String)iterator.next();
                    if(StringUtils.isEmpty(s))
                        iterator.remove();
                }

            }
            if(list1 == null || list1.isEmpty())
                return "/admin/common/error";
        } else
        if(memberAttribute.getType() == net.shopxx.entity.MemberAttribute.Type.text)
            memberAttribute.setOptions(null);
        else
            return "/admin/common/error";
        Integer integer = IIIlllIl.findUnusedPropertyIndex();
        if(integer == null)
        {
            return "/admin/common/error";
        } else
        {
            memberAttribute.setPropertyIndex(integer);
            IIIlllIl.save(memberAttribute);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("memberAttribute", IIIlllIl.find(id));
        return "/admin/member_attribute/edit";
    }

    public String update(MemberAttribute memberAttribute, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(memberAttribute, new Class[0]))
            return "/admin/common/error";
        MemberAttribute memberattribute = (MemberAttribute)IIIlllIl.find(memberAttribute.getId());
        if(memberattribute == null)
            return "/admin/common/error";
        if(memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.select || memberattribute.getType() == net.shopxx.entity.MemberAttribute.Type.checkbox)
        {
            List list1 = memberAttribute.getOptions();
            if(list1 != null)
            {
                for(Iterator iterator = list1.iterator(); iterator.hasNext();)
                {
                    String s = (String)iterator.next();
                    if(StringUtils.isEmpty(s))
                        iterator.remove();
                }

            }
            if(list1 == null || list1.isEmpty())
                return "/admin/common/error";
        } else
        {
            memberAttribute.setOptions(null);
        }
        IIIlllIl.update(memberAttribute, new String[] {
            "type", "propertyIndex"
        });
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/member_attribute/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private MemberAttributeService IIIlllIl;
}
