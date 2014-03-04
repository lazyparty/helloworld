// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import net.shopxx.Pageable;
import net.shopxx.ResourceNotFoundException;
import net.shopxx.entity.ArticleCategory;
import net.shopxx.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.shop:
//            BaseController

public class ArticleController extends BaseController
{

    public ArticleController()
    {
    }

    public String list(Long id, Integer pageNumber, ModelMap model)
    {
        ArticleCategory articlecategory = (ArticleCategory)IIIlllll.find(id);
        if(articlecategory == null)
        {
            throw new ResourceNotFoundException();
        } else
        {
            Pageable pageable = new Pageable(pageNumber, Integer.valueOf(20));
            model.addAttribute("articleCategory", articlecategory);
            model.addAttribute("page", IIIllllI.findPage(articlecategory, null, pageable));
            return "/shop/article/list";
        }
    }

    public String search(String keyword, Integer pageNumber, ModelMap model)
    {
        if(StringUtils.isEmpty(keyword))
        {
            return "/shop/common/error";
        } else
        {
            Pageable pageable = new Pageable(pageNumber, Integer.valueOf(20));
            model.addAttribute("articleKeyword", keyword);
            model.addAttribute("page", IIlIIIII.search(keyword, pageable));
            return "shop/article/search";
        }
    }

    public Long hits(Long id)
    {
        return Long.valueOf(IIIllllI.viewHits(id));
    }

    private static final int IIIlllIl = 20;
    private ArticleService IIIllllI;
    private ArticleCategoryService IIIlllll;
    private SearchService IIlIIIII;
}
