// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import java.math.BigDecimal;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import net.shopxx.Pageable;
import net.shopxx.ResourceNotFoundException;
import net.shopxx.entity.*;
import net.shopxx.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.shop:
//            BaseController

public class ProductController extends BaseController
{

    public ProductController()
    {
    }

    public List history(Long ids[])
    {
        return IIIlllIl.findList(ids);
    }

    public String list(Long productCategoryId, Long brandId, Long promotionId, Long tagIds[], BigDecimal startPrice, BigDecimal endPrice, net.shopxx.entity.Product.OrderType orderType, 
            Integer pageNumber, Integer pageSize, HttpServletRequest request, ModelMap model)
    {
        ProductCategory productcategory = (ProductCategory)IIIllllI.find(productCategoryId);
        if(productcategory == null)
            throw new ResourceNotFoundException();
        Brand brand = (Brand)IIIlllll.find(brandId);
        Promotion promotion = (Promotion)IIlIIIII.find(promotionId);
        List list1 = IIlIIIIl.findList(tagIds);
        HashMap hashmap = new HashMap();
        if(productcategory != null)
        {
            Set set = productcategory.getAttributes();
            for(Iterator iterator = set.iterator(); iterator.hasNext();)
            {
                Attribute attribute = (Attribute)iterator.next();
                String s = request.getParameter((new StringBuilder("attribute_")).append(attribute.getId()).toString());
                if(StringUtils.isNotEmpty(s))
                    hashmap.put(attribute, s);
            }

        }
        Pageable pageable = new Pageable(pageNumber, pageSize);
        model.addAttribute("orderTypes", net.shopxx.entity.Product.OrderType.values());
        model.addAttribute("productCategory", productcategory);
        model.addAttribute("brand", brand);
        model.addAttribute("promotion", promotion);
        model.addAttribute("tags", list1);
        model.addAttribute("attributeValue", hashmap);
        model.addAttribute("startPrice", startPrice);
        model.addAttribute("endPrice", endPrice);
        model.addAttribute("orderType", orderType);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("page", IIIlllIl.findPage(productcategory, brand, promotion, list1, hashmap, startPrice, endPrice, Boolean.valueOf(true), Boolean.valueOf(true), null, Boolean.valueOf(false), null, null, orderType, pageable));
        return "/shop/product/list";
    }

    public String list(Long brandId, Long promotionId, Long tagIds[], BigDecimal startPrice, BigDecimal endPrice, net.shopxx.entity.Product.OrderType orderType, Integer pageNumber, 
            Integer pageSize, HttpServletRequest request, ModelMap model)
    {
        Brand brand = (Brand)IIIlllll.find(brandId);
        Promotion promotion = (Promotion)IIlIIIII.find(promotionId);
        List list1 = IIlIIIIl.findList(tagIds);
        Pageable pageable = new Pageable(pageNumber, pageSize);
        model.addAttribute("orderTypes", net.shopxx.entity.Product.OrderType.values());
        model.addAttribute("brand", brand);
        model.addAttribute("promotion", promotion);
        model.addAttribute("tags", list1);
        model.addAttribute("startPrice", startPrice);
        model.addAttribute("endPrice", endPrice);
        model.addAttribute("orderType", orderType);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("page", IIIlllIl.findPage(null, brand, promotion, list1, null, startPrice, endPrice, Boolean.valueOf(true), Boolean.valueOf(true), null, Boolean.valueOf(false), null, null, orderType, pageable));
        return "/shop/product/list";
    }

    public String search(String keyword, BigDecimal startPrice, BigDecimal endPrice, net.shopxx.entity.Product.OrderType orderType, Integer pageNumber, Integer pageSize, ModelMap model)
    {
        if(StringUtils.isEmpty(keyword))
        {
            return "/shop/common/error";
        } else
        {
            Pageable pageable = new Pageable(pageNumber, pageSize);
            model.addAttribute("orderTypes", net.shopxx.entity.Product.OrderType.values());
            model.addAttribute("productKeyword", keyword);
            model.addAttribute("startPrice", startPrice);
            model.addAttribute("endPrice", endPrice);
            model.addAttribute("orderType", orderType);
            model.addAttribute("page", IIlIIIlI.search(keyword, startPrice, endPrice, orderType, pageable));
            return "shop/product/search";
        }
    }

    public Long hits(Long id)
    {
        return Long.valueOf(IIIlllIl.viewHits(id));
    }

    private ProductService IIIlllIl;
    private ProductCategoryService IIIllllI;
    private BrandService IIIlllll;
    private PromotionService IIlIIIII;
    private TagService IIlIIIIl;
    private SearchService IIlIIIlI;
}
