// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.math.BigDecimal;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import net.shopxx.*;
import net.shopxx.entity.*;
import net.shopxx.service.*;
import net.shopxx.util.SettingUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class ProductController extends BaseController
{

    public ProductController()
    {
    }

    public boolean checkSn(String previousSn, String sn)
    {
        if(StringUtils.isEmpty(sn))
            return false;
        return IIIlllIl.snUnique(previousSn, sn);
    }

    public Set parameterGroups(Long id)
    {
        ProductCategory productcategory = (ProductCategory)IIIllllI.find(id);
        return productcategory.getParameterGroups();
    }

    public Set attributes(Long id)
    {
        ProductCategory productcategory = (ProductCategory)IIIllllI.find(id);
        return productcategory.getAttributes();
    }

    public String add(ModelMap model)
    {
        model.addAttribute("productCategoryTree", IIIllllI.findTree());
        model.addAttribute("brands", IIlIIIII.findAll());
        model.addAttribute("tags", IIlIIIlI.findList(net.shopxx.entity.Tag.Type.product));
        model.addAttribute("memberRanks", IIlIIIll.findAll());
        model.addAttribute("specifications", IIlIIlIl.findAll());
        return "/admin/product/add";
    }

    public String save(Product product, Long productCategoryId, Long brandId, Long tagIds[], Long specificationIds[], HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        for(Iterator iterator = product.getProductImages().iterator(); iterator.hasNext();)
        {
            ProductImage productimage1 = (ProductImage)iterator.next();
            if(productimage1 == null || productimage1.isEmpty())
                iterator.remove();
            else
            if(productimage1.getFile() != null && !productimage1.getFile().isEmpty() && !IIlIIlll.isValid(net.shopxx.FileInfo.FileType.image, productimage1.getFile()))
            {
                IIIllIlI(redirectAttributes, Message.error("admin.upload.invalid", new Object[0]));
                return "redirect:add.jhtml";
            }
        }

        product.setProductCategory((ProductCategory)IIIllllI.find(productCategoryId));
        product.setBrand((Brand)IIlIIIII.find(brandId));
        product.setTags(new HashSet(IIlIIIlI.findList(tagIds)));
        if(!IIIllIlI(product, new Class[0]))
            return "/admin/common/error";
        if(StringUtils.isNotEmpty(product.getSn()) && IIIlllIl.snExists(product.getSn()))
            return "/admin/common/error";
        if(product.getMarketPrice() == null)
        {
            BigDecimal bigdecimal = IIIllIlI(product.getPrice());
            product.setMarketPrice(bigdecimal);
        }
        if(product.getPoint() == null)
        {
            long l = IIIllIll(product.getPrice());
            product.setPoint(Long.valueOf(l));
        }
        product.setFullName(null);
        product.setAllocatedStock(Integer.valueOf(0));
        product.setScore(Float.valueOf(0.0F));
        product.setTotalScore(Long.valueOf(0L));
        product.setScoreCount(Long.valueOf(0L));
        product.setHits(Long.valueOf(0L));
        product.setWeekHits(Long.valueOf(0L));
        product.setMonthHits(Long.valueOf(0L));
        product.setSales(Long.valueOf(0L));
        product.setWeekSales(Long.valueOf(0L));
        product.setMonthSales(Long.valueOf(0L));
        product.setWeekHitsDate(new Date());
        product.setMonthHitsDate(new Date());
        product.setWeekSalesDate(new Date());
        product.setMonthSalesDate(new Date());
        product.setReviews(null);
        product.setConsultations(null);
        product.setFavoriteMembers(null);
        product.setPromotions(null);
        product.setCartItems(null);
        product.setOrderItems(null);
        product.setGiftItems(null);
        product.setProductNotifies(null);
        for(Iterator iterator1 = IIlIIIll.findAll().iterator(); iterator1.hasNext();)
        {
            MemberRank memberrank = (MemberRank)iterator1.next();
            String s = request.getParameter((new StringBuilder("memberPrice_")).append(memberrank.getId()).toString());
            if(StringUtils.isNotEmpty(s) && (new BigDecimal(s)).compareTo(new BigDecimal(0)) >= 0)
                product.getMemberPrice().put(memberrank, new BigDecimal(s));
            else
                product.getMemberPrice().remove(memberrank);
        }

        ProductImage productimage;
        for(Iterator iterator2 = product.getProductImages().iterator(); iterator2.hasNext(); IIlIIlII.build(productimage))
            productimage = (ProductImage)iterator2.next();

        Collections.sort(product.getProductImages());
        if(product.getImage() == null && product.getThumbnail() != null)
            product.setImage(product.getThumbnail());
        for(Iterator iterator3 = product.getProductCategory().getParameterGroups().iterator(); iterator3.hasNext();)
        {
            ParameterGroup parametergroup = (ParameterGroup)iterator3.next();
            for(Iterator iterator5 = parametergroup.getParameters().iterator(); iterator5.hasNext();)
            {
                Parameter parameter = (Parameter)iterator5.next();
                String s2 = request.getParameter((new StringBuilder("parameter_")).append(parameter.getId()).toString());
                if(StringUtils.isNotEmpty(s2))
                    product.getParameterValue().put(parameter, s2);
                else
                    product.getParameterValue().remove(parameter);
            }

        }

        for(Iterator iterator4 = product.getProductCategory().getAttributes().iterator(); iterator4.hasNext();)
        {
            Attribute attribute = (Attribute)iterator4.next();
            String s1 = request.getParameter((new StringBuilder("attribute_")).append(attribute.getId()).toString());
            if(StringUtils.isNotEmpty(s1))
                product.setAttributeValue(attribute, s1);
            else
                product.setAttributeValue(attribute, null);
        }

        Goods goods = new Goods();
        ArrayList arraylist = new ArrayList();
        if(specificationIds != null && specificationIds.length > 0)
        {
            for(int i = 0; i < specificationIds.length; i++)
            {
                Specification specification = (Specification)IIlIIlIl.find(specificationIds[i]);
                String as[] = request.getParameterValues((new StringBuilder("specification_")).append(specification.getId()).toString());
                if(as != null && as.length > 0)
                {
                    for(int j = 0; j < as.length; j++)
                    {
                        if(i == 0)
                            if(j == 0)
                            {
                                product.setGoods(goods);
                                product.setSpecifications(new HashSet());
                                product.setSpecificationValues(new HashSet());
                                arraylist.add(product);
                            } else
                            {
                                Product product1 = new Product();
                                BeanUtils.copyProperties(product, product1);
                                product1.setId(null);
                                product1.setCreateDate(null);
                                product1.setModifyDate(null);
                                product1.setSn(null);
                                product1.setFullName(null);
                                product1.setAllocatedStock(Integer.valueOf(0));
                                product1.setIsList(Boolean.valueOf(false));
                                product1.setScore(Float.valueOf(0.0F));
                                product1.setTotalScore(Long.valueOf(0L));
                                product1.setScoreCount(Long.valueOf(0L));
                                product1.setHits(Long.valueOf(0L));
                                product1.setWeekHits(Long.valueOf(0L));
                                product1.setMonthHits(Long.valueOf(0L));
                                product1.setSales(Long.valueOf(0L));
                                product1.setWeekSales(Long.valueOf(0L));
                                product1.setMonthSales(Long.valueOf(0L));
                                product1.setWeekHitsDate(new Date());
                                product1.setMonthHitsDate(new Date());
                                product1.setWeekSalesDate(new Date());
                                product1.setMonthSalesDate(new Date());
                                product1.setGoods(goods);
                                product1.setReviews(null);
                                product1.setConsultations(null);
                                product1.setFavoriteMembers(null);
                                product1.setSpecifications(new HashSet());
                                product1.setSpecificationValues(new HashSet());
                                product1.setPromotions(null);
                                product1.setCartItems(null);
                                product1.setOrderItems(null);
                                product1.setGiftItems(null);
                                product1.setProductNotifies(null);
                                arraylist.add(product1);
                            }
                        Product product2 = (Product)arraylist.get(j);
                        SpecificationValue specificationvalue = (SpecificationValue)IIlIIllI.find(Long.valueOf(as[j]));
                        product2.getSpecifications().add(specification);
                        product2.getSpecificationValues().add(specificationvalue);
                    }

                }
            }

        } else
        {
            product.setGoods(goods);
            product.setSpecifications(null);
            product.setSpecificationValues(null);
            arraylist.add(product);
        }
        goods.getProducts().clear();
        goods.getProducts().addAll(arraylist);
        IIIlllll.save(goods);
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("productCategoryTree", IIIllllI.findTree());
        model.addAttribute("brands", IIlIIIII.findAll());
        model.addAttribute("tags", IIlIIIlI.findList(net.shopxx.entity.Tag.Type.product));
        model.addAttribute("memberRanks", IIlIIIll.findAll());
        model.addAttribute("specifications", IIlIIlIl.findAll());
        model.addAttribute("product", IIIlllIl.find(id));
        return "/admin/product/edit";
    }

    public String update(Product product, Long productCategoryId, Long brandId, Long tagIds[], Long specificationIds[], Long specificationProductIds[], HttpServletRequest request, 
            RedirectAttributes redirectAttributes)
    {
        for(Iterator iterator = product.getProductImages().iterator(); iterator.hasNext();)
        {
            ProductImage productimage = (ProductImage)iterator.next();
            if(productimage == null || productimage.isEmpty())
                iterator.remove();
            else
            if(productimage.getFile() != null && !productimage.getFile().isEmpty() && !IIlIIlll.isValid(net.shopxx.FileInfo.FileType.image, productimage.getFile()))
            {
                IIIllIlI(redirectAttributes, Message.error("admin.upload.invalid", new Object[0]));
                return (new StringBuilder("redirect:edit.jhtml?id=")).append(product.getId()).toString();
            }
        }

        product.setProductCategory((ProductCategory)IIIllllI.find(productCategoryId));
        product.setBrand((Brand)IIlIIIII.find(brandId));
        product.setTags(new HashSet(IIlIIIlI.findList(tagIds)));
        if(!IIIllIlI(product, new Class[0]))
            return "/admin/common/error";
        Product product1 = (Product)IIIlllIl.find(product.getId());
        if(product1 == null)
            return "/admin/common/error";
        if(StringUtils.isNotEmpty(product.getSn()) && !IIIlllIl.snUnique(product1.getSn(), product.getSn()))
            return "/admin/common/error";
        if(product.getMarketPrice() == null)
        {
            BigDecimal bigdecimal = IIIllIlI(product.getPrice());
            product.setMarketPrice(bigdecimal);
        }
        if(product.getPoint() == null)
        {
            long l = IIIllIll(product.getPrice());
            product.setPoint(Long.valueOf(l));
        }
        for(Iterator iterator1 = IIlIIIll.findAll().iterator(); iterator1.hasNext();)
        {
            MemberRank memberrank = (MemberRank)iterator1.next();
            String s = request.getParameter((new StringBuilder("memberPrice_")).append(memberrank.getId()).toString());
            if(StringUtils.isNotEmpty(s) && (new BigDecimal(s)).compareTo(new BigDecimal(0)) >= 0)
                product.getMemberPrice().put(memberrank, new BigDecimal(s));
            else
                product.getMemberPrice().remove(memberrank);
        }

        ProductImage productimage1;
        for(Iterator iterator2 = product.getProductImages().iterator(); iterator2.hasNext(); IIlIIlII.build(productimage1))
            productimage1 = (ProductImage)iterator2.next();

        Collections.sort(product.getProductImages());
        if(product.getImage() == null && product.getThumbnail() != null)
            product.setImage(product.getThumbnail());
        for(Iterator iterator3 = product.getProductCategory().getParameterGroups().iterator(); iterator3.hasNext();)
        {
            ParameterGroup parametergroup = (ParameterGroup)iterator3.next();
            for(Iterator iterator5 = parametergroup.getParameters().iterator(); iterator5.hasNext();)
            {
                Parameter parameter = (Parameter)iterator5.next();
                String s2 = request.getParameter((new StringBuilder("parameter_")).append(parameter.getId()).toString());
                if(StringUtils.isNotEmpty(s2))
                    product.getParameterValue().put(parameter, s2);
                else
                    product.getParameterValue().remove(parameter);
            }

        }

        for(Iterator iterator4 = product.getProductCategory().getAttributes().iterator(); iterator4.hasNext();)
        {
            Attribute attribute = (Attribute)iterator4.next();
            String s1 = request.getParameter((new StringBuilder("attribute_")).append(attribute.getId()).toString());
            if(StringUtils.isNotEmpty(s1))
                product.setAttributeValue(attribute, s1);
            else
                product.setAttributeValue(attribute, null);
        }

        Goods goods = product1.getGoods();
        ArrayList arraylist = new ArrayList();
        if(specificationIds != null && specificationIds.length > 0)
        {
            for(int i = 0; i < specificationIds.length; i++)
            {
                Specification specification = (Specification)IIlIIlIl.find(specificationIds[i]);
                String as[] = request.getParameterValues((new StringBuilder("specification_")).append(specification.getId()).toString());
                if(as != null && as.length > 0)
                {
                    for(int j = 0; j < as.length; j++)
                    {
                        if(i == 0)
                            if(j == 0)
                            {
                                BeanUtils.copyProperties(product, product1, new String[] {
                                    "id", "createDate", "modifyDate", "fullName", "allocatedStock", "score", "totalScore", "scoreCount", "hits", "weekHits", 
                                    "monthHits", "sales", "weekSales", "monthSales", "weekHitsDate", "monthHitsDate", "weekSalesDate", "monthSalesDate", "goods", "reviews", 
                                    "consultations", "favoriteMembers", "specifications", "specificationValues", "promotions", "cartItems", "orderItems", "giftItems", "productNotifies"
                                });
                                product1.setSpecifications(new HashSet());
                                product1.setSpecificationValues(new HashSet());
                                arraylist.add(product1);
                            } else
                            if(specificationProductIds != null && j < specificationProductIds.length)
                            {
                                Product product2 = (Product)IIIlllIl.find(specificationProductIds[j]);
                                if(product2.getGoods() != goods)
                                    return "/admin/common/error";
                                product2.setSpecifications(new HashSet());
                                product2.setSpecificationValues(new HashSet());
                                arraylist.add(product2);
                            } else
                            {
                                Product product3 = new Product();
                                BeanUtils.copyProperties(product, product3);
                                product3.setId(null);
                                product3.setCreateDate(null);
                                product3.setModifyDate(null);
                                product3.setSn(null);
                                product3.setFullName(null);
                                product3.setAllocatedStock(Integer.valueOf(0));
                                product3.setIsList(Boolean.valueOf(false));
                                product3.setScore(Float.valueOf(0.0F));
                                product3.setTotalScore(Long.valueOf(0L));
                                product3.setScoreCount(Long.valueOf(0L));
                                product3.setHits(Long.valueOf(0L));
                                product3.setWeekHits(Long.valueOf(0L));
                                product3.setMonthHits(Long.valueOf(0L));
                                product3.setSales(Long.valueOf(0L));
                                product3.setWeekSales(Long.valueOf(0L));
                                product3.setMonthSales(Long.valueOf(0L));
                                product3.setWeekHitsDate(new Date());
                                product3.setMonthHitsDate(new Date());
                                product3.setWeekSalesDate(new Date());
                                product3.setMonthSalesDate(new Date());
                                product3.setGoods(goods);
                                product3.setReviews(null);
                                product3.setConsultations(null);
                                product3.setFavoriteMembers(null);
                                product3.setSpecifications(new HashSet());
                                product3.setSpecificationValues(new HashSet());
                                product3.setPromotions(null);
                                product3.setCartItems(null);
                                product3.setOrderItems(null);
                                product3.setGiftItems(null);
                                product3.setProductNotifies(null);
                                arraylist.add(product3);
                            }
                        Product product4 = (Product)arraylist.get(j);
                        SpecificationValue specificationvalue = (SpecificationValue)IIlIIllI.find(Long.valueOf(as[j]));
                        product4.getSpecifications().add(specification);
                        product4.getSpecificationValues().add(specificationvalue);
                    }

                }
            }

        } else
        {
            product.setSpecifications(null);
            product.setSpecificationValues(null);
            BeanUtils.copyProperties(product, product1, new String[] {
                "id", "createDate", "modifyDate", "fullName", "allocatedStock", "score", "totalScore", "scoreCount", "hits", "weekHits", 
                "monthHits", "sales", "weekSales", "monthSales", "weekHitsDate", "monthHitsDate", "weekSalesDate", "monthSalesDate", "goods", "reviews", 
                "consultations", "favoriteMembers", "promotions", "cartItems", "orderItems", "giftItems", "productNotifies"
            });
            arraylist.add(product1);
        }
        goods.getProducts().clear();
        goods.getProducts().addAll(arraylist);
        IIIlllll.update(goods);
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String list(Long productCategoryId, Long brandId, Long promotionId, Long tagId, Boolean isMarketable, Boolean isList, Boolean isTop, 
            Boolean isGift, Boolean isOutOfStock, Boolean isStockAlert, Pageable pageable, ModelMap model)
    {
        ProductCategory productcategory = (ProductCategory)IIIllllI.find(productCategoryId);
        Brand brand = (Brand)IIlIIIII.find(brandId);
        Promotion promotion = (Promotion)IIlIIIIl.find(promotionId);
        List list1 = IIlIIIlI.findList(new Long[] {
            tagId
        });
        model.addAttribute("productCategoryTree", IIIllllI.findTree());
        model.addAttribute("brands", IIlIIIII.findAll());
        model.addAttribute("promotions", IIlIIIIl.findAll());
        model.addAttribute("tags", IIlIIIlI.findList(net.shopxx.entity.Tag.Type.product));
        model.addAttribute("productCategoryId", productCategoryId);
        model.addAttribute("brandId", brandId);
        model.addAttribute("promotionId", promotionId);
        model.addAttribute("tagId", tagId);
        model.addAttribute("isMarketable", isMarketable);
        model.addAttribute("isList", isList);
        model.addAttribute("isTop", isTop);
        model.addAttribute("isGift", isGift);
        model.addAttribute("isOutOfStock", isOutOfStock);
        model.addAttribute("isStockAlert", isStockAlert);
        model.addAttribute("page", IIIlllIl.findPage(productcategory, brand, promotion, list1, null, null, null, isMarketable, isList, isTop, isGift, isOutOfStock, isStockAlert, net.shopxx.entity.Product.OrderType.dateDesc, pageable));
        return "/admin/product/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private BigDecimal IIIllIlI(BigDecimal bigdecimal)
    {
        Setting setting = SettingUtils.get();
        Double double1 = setting.getDefaultMarketPriceScale();
        return setting.setScale(bigdecimal.multiply(new BigDecimal(double1.toString())));
    }

    private long IIIllIll(BigDecimal bigdecimal)
    {
        Setting setting = SettingUtils.get();
        Double double1 = setting.getDefaultPointScale();
        return bigdecimal.multiply(new BigDecimal(double1.toString())).longValue();
    }

    private ProductService IIIlllIl;
    private ProductCategoryService IIIllllI;
    private GoodsService IIIlllll;
    private BrandService IIlIIIII;
    private PromotionService IIlIIIIl;
    private TagService IIlIIIlI;
    private MemberRankService IIlIIIll;
    private ProductImageService IIlIIlII;
    private SpecificationService IIlIIlIl;
    private SpecificationValueService IIlIIllI;
    private FileService IIlIIlll;
}
