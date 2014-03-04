// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.*;
import net.shopxx.entity.*;
import net.shopxx.service.*;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class StaticController extends BaseController
{

    public StaticController()
    {
    }

    public String build(ModelMap model)
    {
        model.addAttribute("buildTypes", BuildType.values());
        model.addAttribute("defaultBeginDate", DateUtils.addDays(new Date(), -7));
        model.addAttribute("defaultEndDate", new Date());
        model.addAttribute("articleCategoryTree", IIIllllI.findChildren(null, null));
        model.addAttribute("productCategoryTree", IIlIIIII.findChildren(null, null));
        return "/admin/static/build";
    }

    public Map build(BuildType buildType, Long articleCategoryId, Long productCategoryId, Date beginDate, Date endDate, Integer first, Integer count)
    {
        long l = System.currentTimeMillis();
        if(beginDate != null)
        {
            Calendar calendar = DateUtils.toCalendar(beginDate);
            calendar.set(11, calendar.getActualMinimum(11));
            calendar.set(12, calendar.getActualMinimum(12));
            calendar.set(13, calendar.getActualMinimum(13));
            beginDate = calendar.getTime();
        }
        if(endDate != null)
        {
            Calendar calendar1 = DateUtils.toCalendar(endDate);
            calendar1.set(11, calendar1.getActualMaximum(11));
            calendar1.set(12, calendar1.getActualMaximum(12));
            calendar1.set(13, calendar1.getActualMaximum(13));
            endDate = calendar1.getTime();
        }
        if(first == null || first.intValue() < 0)
            first = Integer.valueOf(0);
        if(count == null || count.intValue() <= 0)
            count = Integer.valueOf(50);
        int i = 0;
        boolean flag = true;
        if(buildType == BuildType.index)
            i = IIlIIIIl.buildIndex();
        else
        if(buildType == BuildType.article)
        {
            ArticleCategory articlecategory = (ArticleCategory)IIIllllI.find(articleCategoryId);
            List list = IIIlllIl.findList(articlecategory, beginDate, endDate, first, count);
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                Article article = (Article)iterator.next();
                i += IIlIIIIl.build(article);
            }

            first = Integer.valueOf(first.intValue() + list.size());
            if(list.size() == count.intValue())
                flag = false;
        } else
        if(buildType == BuildType.product)
        {
            ProductCategory productcategory = (ProductCategory)IIlIIIII.find(productCategoryId);
            List list1 = IIIlllll.findList(productcategory, beginDate, endDate, first, count);
            for(Iterator iterator1 = list1.iterator(); iterator1.hasNext();)
            {
                Product product = (Product)iterator1.next();
                i += IIlIIIIl.build(product);
            }

            first = Integer.valueOf(first.intValue() + list1.size());
            if(list1.size() == count.intValue())
                flag = false;
        } else
        if(buildType == BuildType.other)
            i = IIlIIIIl.buildOther();
        long l1 = System.currentTimeMillis();
        HashMap hashmap = new HashMap();
        hashmap.put("first", first);
        hashmap.put("buildCount", Integer.valueOf(i));
        hashmap.put("buildTime", Long.valueOf(l1 - l));
        hashmap.put("isCompleted", Boolean.valueOf(flag));
        return hashmap;
    }

    private ArticleService IIIlllIl;
    private ArticleCategoryService IIIllllI;
    private ProductService IIIlllll;
    private ProductCategoryService IIlIIIII;
    private StaticService IIlIIIIl;

    private class BuildType extends Enum
    {

        public static BuildType[] values()
        {
            BuildType abuildtype[];
            int i;
            BuildType abuildtype1[];
            System.arraycopy(abuildtype = ENUM$VALUES, 0, abuildtype1 = new BuildType[i = abuildtype.length], 0, i);
            return abuildtype1;
        }

        public static BuildType valueOf(String s)
        {
            return (BuildType)Enum.valueOf(net/shopxx/controller/admin/StaticController$BuildType, s);
        }

        public static final BuildType index;
        public static final BuildType article;
        public static final BuildType product;
        public static final BuildType other;
        private static final BuildType ENUM$VALUES[];

        static 
        {
            index = new BuildType("index", 0);
            article = new BuildType("article", 1);
            product = new BuildType("product", 2);
            other = new BuildType("other", 3);
            ENUM$VALUES = (new BuildType[] {
                index, article, product, other
            });
        }

        private BuildType(String s, int i)
        {
            super(s, i);
        }
    }

}
