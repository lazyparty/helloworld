// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.template.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateModel;
import java.math.BigDecimal;
import java.util.*;
import net.shopxx.entity.*;
import net.shopxx.service.*;
import net.shopxx.util.FreemarkerUtils;

// Referenced classes of package net.shopxx.template.directive:
//            BaseDirective

public class ProductListDirective extends BaseDirective
{

    public ProductListDirective()
    {
    }

    public void execute(Environment env, Map params, TemplateModel loopVars[], TemplateDirectiveBody body)
    {
        Long long1 = (Long)FreemarkerUtils.getParameter("productCategoryId", java/lang/Long, params);
        Long long2 = (Long)FreemarkerUtils.getParameter("brandId", java/lang/Long, params);
        Long long3 = (Long)FreemarkerUtils.getParameter("promotionId", java/lang/Long, params);
        Long along[] = (Long[])FreemarkerUtils.getParameter("tagIds", [Ljava/lang/Long;, params);
        Map map = (Map)FreemarkerUtils.getParameter("attributeValue", java/util/Map, params);
        BigDecimal bigdecimal = (BigDecimal)FreemarkerUtils.getParameter("startPrice", java/math/BigDecimal, params);
        BigDecimal bigdecimal1 = (BigDecimal)FreemarkerUtils.getParameter("endPrice", java/math/BigDecimal, params);
        net.shopxx.entity.Product.OrderType ordertype = (net.shopxx.entity.Product.OrderType)FreemarkerUtils.getParameter("orderType", net/shopxx/entity/Product$OrderType, params);
        ProductCategory productcategory = (ProductCategory)IIlIIlII.find(long1);
        Brand brand = (Brand)IIlIIlIl.find(long2);
        Promotion promotion = (Promotion)IIlIIllI.find(long3);
        List list = IIlIlIII.findList(along);
        HashMap hashmap = new HashMap();
        if(map != null)
        {
            for(Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                Attribute attribute = (Attribute)IIlIIlll.find((Long)entry.getKey());
                if(attribute != null)
                    hashmap.put(attribute, (String)entry.getValue());
            }

        }
        Object obj;
        if(long1 != null && productcategory == null || long2 != null && brand == null || long3 != null && promotion == null || along != null && list.isEmpty() || map != null && hashmap.isEmpty())
        {
            obj = new ArrayList();
        } else
        {
            boolean flag = IIIllIlI(env, params);
            String s = IIIllIll(env, params);
            Integer integer = IIIllIll(params);
            List list1 = IIIllIlI(params, net/shopxx/entity/Article, new String[0]);
            List list2 = IIIllIlI(params, new String[0]);
            if(flag)
                obj = IIlIIIll.findList(productcategory, brand, promotion, list, hashmap, bigdecimal, bigdecimal1, Boolean.valueOf(true), Boolean.valueOf(true), null, Boolean.valueOf(false), null, null, ordertype, integer, list1, list2, s);
            else
                obj = IIlIIIll.findList(productcategory, brand, promotion, list, hashmap, bigdecimal, bigdecimal1, Boolean.valueOf(true), Boolean.valueOf(true), null, Boolean.valueOf(false), null, null, ordertype, integer, list1, list2);
        }
        IIIllIlI("products", obj, env, body);
    }

    private static final String IIIllIlI = "productCategoryId";
    private static final String IIIllIll = "brandId";
    private static final String IIIlllII = "promotionId";
    private static final String IIIlllIl = "tagIds";
    private static final String IIIllllI = "attributeValue";
    private static final String IIIlllll = "startPrice";
    private static final String IIlIIIII = "endPrice";
    private static final String IIlIIIIl = "orderType";
    private static final String IIlIIIlI = "products";
    private ProductService IIlIIIll;
    private ProductCategoryService IIlIIlII;
    private BrandService IIlIIlIl;
    private PromotionService IIlIIllI;
    private AttributeService IIlIIlll;
    private TagService IIlIlIII;
}
