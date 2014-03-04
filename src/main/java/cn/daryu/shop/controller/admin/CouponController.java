// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.shopxx.*;
import net.shopxx.entity.Coupon;
import net.shopxx.service.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class CouponController extends BaseController
{

    public CouponController()
    {
    }

    public String add(ModelMap model)
    {
        model.addAttribute("operators", net.shopxx.entity.Coupon.Operator.values());
        return "/admin/coupon/add";
    }

    public String save(Coupon coupon, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(coupon, new Class[0]))
            return "/admin/common/error";
        if(coupon.getBeginDate() != null && coupon.getEndDate() != null && coupon.getBeginDate().after(coupon.getEndDate()))
            return "/admin/common/error";
        if(coupon.getStartPrice() != null && coupon.getEndPrice() != null && coupon.getStartPrice().compareTo(coupon.getEndPrice()) > 0)
            return "/admin/common/error";
        if(coupon.getIsExchange().booleanValue() && coupon.getPoint() == null)
            return "/admin/common/error";
        if(coupon.getPriceOperator() == net.shopxx.entity.Coupon.Operator.divide && coupon.getPriceValue() != null && coupon.getPriceValue().compareTo(new BigDecimal(0)) == 0)
            return "/admin/common/error";
        if(!coupon.getIsExchange().booleanValue())
            coupon.setPoint(null);
        coupon.setCouponCodes(null);
        coupon.setPromotions(null);
        coupon.setOrders(null);
        IIIlllIl.save(coupon);
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("coupon", IIIlllIl.find(id));
        model.addAttribute("operators", net.shopxx.entity.Coupon.Operator.values());
        return "/admin/coupon/edit";
    }

    public String update(Coupon coupon, RedirectAttributes redirectAttributes)
    {
        if(!IIIllIlI(coupon, new Class[0]))
            return "/admin/common/error";
        if(coupon.getBeginDate() != null && coupon.getEndDate() != null && coupon.getBeginDate().after(coupon.getEndDate()))
            return "/admin/common/error";
        if(coupon.getStartPrice() != null && coupon.getEndPrice() != null && coupon.getStartPrice().compareTo(coupon.getEndPrice()) > 0)
            return "/admin/common/error";
        if(coupon.getIsExchange().booleanValue() && coupon.getPoint() == null)
            return "/admin/common/error";
        if(coupon.getPriceOperator() == net.shopxx.entity.Coupon.Operator.divide && coupon.getPriceValue() != null && coupon.getPriceValue().compareTo(new BigDecimal(0)) == 0)
            return "/admin/common/error";
        if(!coupon.getIsExchange().booleanValue())
            coupon.setPoint(null);
        IIIlllIl.update(coupon, new String[] {
            "couponCodes", "promotions", "orders"
        });
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/coupon/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    public String build(Long id, ModelMap model)
    {
        Coupon coupon = (Coupon)IIIlllIl.find(id);
        model.addAttribute("coupon", coupon);
        model.addAttribute("totalCount", IIIllllI.count(coupon, null, null, null, null));
        model.addAttribute("usedCount", IIIllllI.count(coupon, null, null, null, Boolean.valueOf(true)));
        return "/admin/coupon/build";
    }

    public ModelAndView download(Long id, Integer count, ModelMap model)
    {
        if(count == null || count.intValue() <= 0)
            count = Integer.valueOf(50);
        Coupon coupon = (Coupon)IIIlllIl.find(id);
        java.util.List list1 = IIIllllI.build(coupon, null, count);
        String s = (new StringBuilder("coupon_code_")).append((new SimpleDateFormat("yyyyMM")).format(new Date())).append(".xls").toString();
        String as[] = new String[4];
        as[0] = (new StringBuilder(String.valueOf(IIIllIlI("admin.coupon.type", new Object[0])))).append(": ").append(coupon.getName()).toString();
        as[1] = (new StringBuilder(String.valueOf(IIIllIlI("admin.coupon.count", new Object[0])))).append(": ").append(count).toString();
        as[2] = (new StringBuilder(String.valueOf(IIIllIlI("admin.coupon.operator", new Object[0])))).append(": ").append(IIIlllll.getCurrentUsername()).toString();
        as[3] = (new StringBuilder(String.valueOf(IIIllIlI("admin.coupon.date", new Object[0])))).append(": ").append((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())).toString();
        return new ModelAndView(new ExcelView(s, null, new String[] {
            "code"
        }, new String[] {
            IIIllIlI("admin.coupon.title", new Object[0])
        }, null, null, list1, as), model);
    }

    private CouponService IIIlllIl;
    private CouponCodeService IIIllllI;
    private AdminService IIIlllll;
}
