// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.template.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateModel;
import java.util.*;
import net.shopxx.entity.Article;
import net.shopxx.entity.ArticleCategory;
import net.shopxx.service.*;
import net.shopxx.util.FreemarkerUtils;

// Referenced classes of package net.shopxx.template.directive:
//            BaseDirective

public class ArticleListDirective extends BaseDirective
{

    public ArticleListDirective()
    {
    }

    public void execute(Environment env, Map params, TemplateModel loopVars[], TemplateDirectiveBody body)
    {
        Long long1 = (Long)FreemarkerUtils.getParameter("articleCategoryId", java/lang/Long, params);
        Long along[] = (Long[])FreemarkerUtils.getParameter("tagIds", [Ljava/lang/Long;, params);
        ArticleCategory articlecategory = (ArticleCategory)IIIllllI.find(long1);
        List list = IIIlllll.findList(along);
        Object obj;
        if(long1 != null && articlecategory == null || along != null && list.isEmpty())
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
                obj = IIIlllIl.findList(articlecategory, list, integer, list1, list2, s);
            else
                obj = IIIlllIl.findList(articlecategory, list, integer, list1, list2);
        }
        IIIllIlI("articles", obj, env, body);
    }

    private static final String IIIllIlI = "articleCategoryId";
    private static final String IIIllIll = "tagIds";
    private static final String IIIlllII = "articles";
    private ArticleService IIIlllIl;
    private ArticleCategoryService IIIllllI;
    private TagService IIIlllll;
}
