// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.template.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateModel;
import java.util.ArrayList;
import java.util.Map;
import net.shopxx.entity.ProductCategory;
import net.shopxx.service.ProductCategoryService;
import net.shopxx.util.FreemarkerUtils;

// Referenced classes of package net.shopxx.template.directive:
//            BaseDirective

public class ProductCategoryChildrenListDirective extends BaseDirective
{

    public ProductCategoryChildrenListDirective()
    {
    }

    public void execute(Environment env, Map params, TemplateModel loopVars[], TemplateDirectiveBody body)
    {
        Long long1 = (Long)FreemarkerUtils.getParameter("productCategoryId", java/lang/Long, params);
        ProductCategory productcategory = (ProductCategory)IIIlllII.find(long1);
        Object obj;
        if(long1 != null && productcategory == null)
        {
            obj = new ArrayList();
        } else
        {
            boolean flag = IIIllIlI(env, params);
            String s = IIIllIll(env, params);
            Integer integer = IIIllIll(params);
            if(flag)
                obj = IIIlllII.findChildren(productcategory, integer, s);
            else
                obj = IIIlllII.findChildren(productcategory, integer);
        }
        IIIllIlI("productCategories", obj, env, body);
    }

    private static final String IIIllIlI = "productCategoryId";
    private static final String IIIllIll = "productCategories";
    private ProductCategoryService IIIlllII;
}
