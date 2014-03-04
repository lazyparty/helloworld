// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.*;
import net.shopxx.entity.Article;
import net.shopxx.entity.Product;
import net.shopxx.service.*;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class IndexController extends BaseController
{

    public IndexController()
    {
    }

    public String build(ModelMap model)
    {
        model.addAttribute("buildTypes", BuildType.values());
        return "/admin/index/build";
    }

    public Map build(BuildType buildType, Boolean isPurge, Integer first, Integer count)
    {
        long l = System.currentTimeMillis();
        if(first == null || first.intValue() < 0)
            first = Integer.valueOf(0);
        if(count == null || count.intValue() <= 0)
            count = Integer.valueOf(50);
        int i = 0;
        boolean flag = true;
        if(buildType == BuildType.article)
        {
            if(first.intValue() == 0 && isPurge != null && isPurge.booleanValue())
                IIIlllll.purge(net/shopxx/entity/Article);
            List list = IIIlllIl.findList(null, null, null, first, count);
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                Article article = (Article)iterator.next();
                IIIlllll.index(article);
                i++;
            }

            first = Integer.valueOf(first.intValue() + list.size());
            if(list.size() == count.intValue())
                flag = false;
        } else
        if(buildType == BuildType.product)
        {
            if(first.intValue() == 0 && isPurge != null && isPurge.booleanValue())
                IIIlllll.purge(net/shopxx/entity/Product);
            List list1 = IIIllllI.findList(null, null, null, first, count);
            for(Iterator iterator1 = list1.iterator(); iterator1.hasNext();)
            {
                Product product = (Product)iterator1.next();
                IIIlllll.index(product);
                i++;
            }

            first = Integer.valueOf(first.intValue() + list1.size());
            if(list1.size() == count.intValue())
                flag = false;
        }
        long l1 = System.currentTimeMillis();
        HashMap hashmap = new HashMap();
        hashmap.put("first", first);
        hashmap.put("buildCount", Integer.valueOf(i));
        hashmap.put("buildTime", Long.valueOf(l1 - l));
        hashmap.put("isCompleted", Boolean.valueOf(flag));
        return hashmap;
    }

    private ArticleService IIIlllIl;
    private ProductService IIIllllI;
    private SearchService IIIlllll;

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
            return (BuildType)Enum.valueOf(net/shopxx/controller/admin/IndexController$BuildType, s);
        }

        public static final BuildType article;
        public static final BuildType product;
        private static final BuildType ENUM$VALUES[];

        static 
        {
            article = new BuildType("article", 0);
            product = new BuildType("product", 1);
            ENUM$VALUES = (new BuildType[] {
                article, product
            });
        }

        private BuildType(String s, int i)
        {
            super(s, i);
        }
    }

}
