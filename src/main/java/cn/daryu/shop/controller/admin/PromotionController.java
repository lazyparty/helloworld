// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.math.BigDecimal;
import java.util.*;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.*;
import net.shopxx.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class PromotionController extends BaseController
{

    public PromotionController()
    {
    }

    public List productSelect(String q)
    {
        ArrayList arraylist = new ArrayList();
        if(StringUtils.isNotEmpty(q))
        {
            List list1 = IIlIIIII.search(q, Boolean.valueOf(false), Integer.valueOf(20));
            HashMap hashmap;
            for(Iterator iterator = list1.iterator(); iterator.hasNext(); arraylist.add(hashmap))
            {
                Product product = (Product)iterator.next();
                hashmap = new HashMap();
                hashmap.put("id", product.getId());
                hashmap.put("sn", product.getSn());
                hashmap.put("fullName", product.getFullName());
                hashmap.put("path", product.getPath());
            }

        }
        return arraylist;
    }

    public List giftSelect(String q)
    {
        ArrayList arraylist = new ArrayList();
        if(StringUtils.isNotEmpty(q))
        {
            List list1 = IIlIIIII.search(q, Boolean.valueOf(true), Integer.valueOf(20));
            HashMap hashmap;
            for(Iterator iterator = list1.iterator(); iterator.hasNext(); arraylist.add(hashmap))
            {
                Product product = (Product)iterator.next();
                hashmap = new HashMap();
                hashmap.put("id", product.getId());
                hashmap.put("sn", product.getSn());
                hashmap.put("fullName", product.getFullName());
                hashmap.put("path", product.getPath());
            }

        }
        return arraylist;
    }

    public String add(ModelMap model)
    {
        model.addAttribute("operators", net.shopxx.entity.Promotion.Operator.values());
        model.addAttribute("memberRanks", IIIllllI.findAll());
        model.addAttribute("productCategories", IIIlllll.findAll());
        model.addAttribute("brands", IIlIIIIl.findAll());
        model.addAttribute("coupons", IIlIIIlI.findAll());
        return "/admin/promotion/add";
    }

    public String save(Promotion promotion, Long memberRankIds[], Long productCategoryIds[], Long brandIds[], Long couponIds[], Long productIds[], RedirectAttributes redirectAttributes)
    {
        promotion.setMemberRanks(new HashSet(IIIllllI.findList(memberRankIds)));
        promotion.setProductCategories(new HashSet(IIIlllll.findList(productCategoryIds)));
        promotion.setBrands(new HashSet(IIlIIIIl.findList(brandIds)));
        promotion.setCoupons(new HashSet(IIlIIIlI.findList(couponIds)));
        for(Iterator iterator1 = IIlIIIII.findList(productIds).iterator(); iterator1.hasNext();)
        {
            Product product = (Product)iterator1.next();
            if(!product.getIsGift().booleanValue())
                promotion.getProducts().add(product);
        }

        for(Iterator iterator = promotion.getGiftItems().iterator(); iterator.hasNext();)
        {
            GiftItem giftitem = (GiftItem)iterator.next();
            if(giftitem == null || giftitem.getGift() == null || giftitem.getGift().getId() == null)
            {
                iterator.remove();
            } else
            {
                giftitem.setGift((Product)IIlIIIII.find(giftitem.getGift().getId()));
                giftitem.setPromotion(promotion);
            }
        }

        if(!IIIllIlI(promotion, new Class[0]))
            return "/admin/common/error";
        if(promotion.getBeginDate() != null && promotion.getEndDate() != null && promotion.getBeginDate().after(promotion.getEndDate()))
            return "/admin/common/error";
        if(promotion.getStartPrice() != null && promotion.getEndPrice() != null && promotion.getStartPrice().compareTo(promotion.getEndPrice()) > 0)
            return "/admin/common/error";
        if(promotion.getPriceOperator() == net.shopxx.entity.Promotion.Operator.divide && promotion.getPriceValue() != null && promotion.getPriceValue().compareTo(new BigDecimal(0)) == 0)
            return "/admin/common/error";
        if(promotion.getPointOperator() == net.shopxx.entity.Promotion.Operator.divide && promotion.getPointValue() != null && promotion.getPointValue().compareTo(new BigDecimal(0)) == 0)
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.save(promotion);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("promotion", IIIlllIl.find(id));
        model.addAttribute("operators", net.shopxx.entity.Promotion.Operator.values());
        model.addAttribute("memberRanks", IIIllllI.findAll());
        model.addAttribute("productCategories", IIIlllll.findAll());
        model.addAttribute("brands", IIlIIIIl.findAll());
        model.addAttribute("coupons", IIlIIIlI.findAll());
        return "/admin/promotion/edit";
    }

    public String update(Promotion promotion, Long memberRankIds[], Long productCategoryIds[], Long brandIds[], Long couponIds[], Long productIds[], RedirectAttributes redirectAttributes)
    {
        promotion.setMemberRanks(new HashSet(IIIllllI.findList(memberRankIds)));
        promotion.setProductCategories(new HashSet(IIIlllll.findList(productCategoryIds)));
        promotion.setBrands(new HashSet(IIlIIIIl.findList(brandIds)));
        promotion.setCoupons(new HashSet(IIlIIIlI.findList(couponIds)));
        for(Iterator iterator1 = IIlIIIII.findList(productIds).iterator(); iterator1.hasNext();)
        {
            Product product = (Product)iterator1.next();
            if(!product.getIsGift().booleanValue())
                promotion.getProducts().add(product);
        }

        for(Iterator iterator = promotion.getGiftItems().iterator(); iterator.hasNext();)
        {
            GiftItem giftitem = (GiftItem)iterator.next();
            if(giftitem == null || giftitem.getGift() == null || giftitem.getGift().getId() == null)
            {
                iterator.remove();
            } else
            {
                giftitem.setGift((Product)IIlIIIII.find(giftitem.getGift().getId()));
                giftitem.setPromotion(promotion);
            }
        }

        if(!IIIllIlI(promotion, new Class[0]))
            return "/admin/common/error";
        if(promotion.getBeginDate() != null && promotion.getEndDate() != null && promotion.getBeginDate().after(promotion.getEndDate()))
            return "/admin/common/error";
        if(promotion.getPriceOperator() == net.shopxx.entity.Promotion.Operator.divide && promotion.getPriceValue() != null && promotion.getPriceValue().compareTo(new BigDecimal(0)) == 0)
            return "/admin/common/error";
        if(promotion.getPointOperator() == net.shopxx.entity.Promotion.Operator.divide && promotion.getPointValue() != null && promotion.getPointValue().compareTo(new BigDecimal(0)) == 0)
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.update(promotion);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/promotion/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private PromotionService IIIlllIl;
    private MemberRankService IIIllllI;
    private ProductCategoryService IIIlllll;
    private ProductService IIlIIIII;
    private BrandService IIlIIIIl;
    private CouponService IIlIIIlI;
}
