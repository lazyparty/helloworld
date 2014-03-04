// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.template.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateModel;
import java.util.ArrayList;
import java.util.Map;
import net.shopxx.entity.ArticleCategory;
import net.shopxx.service.ArticleCategoryService;
import net.shopxx.util.FreemarkerUtils;

// Referenced classes of package net.shopxx.template.directive:
//            BaseDirective

public class ArticleCategoryChildrenListDirective extends BaseDirective
{

    public ArticleCategoryChildrenListDirective()
    {
    }

    public void execute(Environment env, Map params, TemplateModel loopVars[], TemplateDirectiveBody body)
    {
        Long long1 = (Long)FreemarkerUtils.getParameter("articleCategoryId", java/lang/Long, params);
        ArticleCategory articlecategory = (ArticleCategory)IIIlllII.find(long1);
        Object obj;
        if(long1 != null && articlecategory == null)
        {
            obj = new ArrayList();
        } else
        {
            boolean flag = IIIllIlI(env, params);
            String s = IIIllIll(env, params);
            Integer integer = IIIllIll(params);
            if(flag)
                obj = IIIlllII.findChildren(articlecategory, integer, s);
            else
                obj = IIIlllII.findChildren(articlecategory, integer);
        }
        IIIllIlI("articleCategories", obj, env, body);
    }

    private static final String IIIllIlI = "articleCategoryId";
    private static final String IIIllIll = "articleCategories";
    private ArticleCategoryService IIIlllII;
}
