// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.HashSet;
import net.shopxx.Message;
import net.shopxx.Pageable;
import net.shopxx.entity.Article;
import net.shopxx.entity.ArticleCategory;
import net.shopxx.service.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class ArticleController extends BaseController
{

    public ArticleController()
    {
    }

    public String add(ModelMap model)
    {
        model.addAttribute("articleCategoryTree", IIIllllI.findTree());
        model.addAttribute("tags", IIIlllll.findList(net.shopxx.entity.Tag.Type.article));
        return "/admin/article/add";
    }

    public String save(Article article, Long articleCategoryId, Long tagIds[], RedirectAttributes redirectAttributes)
    {
        article.setArticleCategory((ArticleCategory)IIIllllI.find(articleCategoryId));
        article.setTags(new HashSet(IIIlllll.findList(tagIds)));
        if(!IIIllIlI(article, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            article.setHits(Long.valueOf(0L));
            article.setPageNumber(null);
            IIIlllIl.save(article);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("articleCategoryTree", IIIllllI.findTree());
        model.addAttribute("tags", IIIlllll.findList(net.shopxx.entity.Tag.Type.article));
        model.addAttribute("article", IIIlllIl.find(id));
        return "/admin/article/edit";
    }

    public String update(Article article, Long articleCategoryId, Long tagIds[], RedirectAttributes redirectAttributes)
    {
        article.setArticleCategory((ArticleCategory)IIIllllI.find(articleCategoryId));
        article.setTags(new HashSet(IIIlllll.findList(tagIds)));
        if(!IIIllIlI(article, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            IIIlllIl.update(article, new String[] {
                "hits", "pageNumber"
            });
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", IIIlllIl.findPage(pageable));
        return "/admin/article/list";
    }

    public Message delete(Long ids[])
    {
        IIIlllIl.delete(ids);
        return IIIlllII;
    }

    private ArticleService IIIlllIl;
    private ArticleCategoryService IIIllllI;
    private TagService IIIlllll;
}
