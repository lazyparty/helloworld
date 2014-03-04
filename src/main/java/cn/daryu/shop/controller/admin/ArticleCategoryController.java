// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.List;
import java.util.Set;
import net.shopxx.Message;
import net.shopxx.entity.ArticleCategory;
import net.shopxx.service.ArticleCategoryService;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class ArticleCategoryController extends BaseController
{

    public ArticleCategoryController()
    {
    }

    public String add(ModelMap model)
    {
        model.addAttribute("articleCategoryTree", IIIlllIl.findTree());
        return "/admin/article_category/add";
    }

    public String save(ArticleCategory articleCategory, Long parentId, RedirectAttributes redirectAttributes)
    {
        articleCategory.setParent((ArticleCategory)IIIlllIl.find(parentId));
        if(!IIIllIlI(articleCategory, new Class[0]))
        {
            return "/admin/common/error";
        } else
        {
            articleCategory.setTreePath(null);
            articleCategory.setGrade(null);
            articleCategory.setChildren(null);
            articleCategory.setArticles(null);
            IIIlllIl.save(articleCategory);
            IIIllIlI(redirectAttributes, IIIlllII);
            return "redirect:list.jhtml";
        }
    }

    public String edit(Long id, ModelMap model)
    {
        ArticleCategory articlecategory = (ArticleCategory)IIIlllIl.find(id);
        model.addAttribute("articleCategoryTree", IIIlllIl.findTree());
        model.addAttribute("articleCategory", articlecategory);
        model.addAttribute("children", IIIlllIl.findChildren(articlecategory));
        return "/admin/article_category/edit";
    }

    public String update(ArticleCategory articleCategory, Long parentId, RedirectAttributes redirectAttributes)
    {
        articleCategory.setParent((ArticleCategory)IIIlllIl.find(parentId));
        if(!IIIllIlI(articleCategory, new Class[0]))
            return "/admin/common/error";
        if(articleCategory.getParent() != null)
        {
            ArticleCategory articlecategory = articleCategory.getParent();
            if(articlecategory.equals(articleCategory))
                return "/admin/common/error";
            List list1 = IIIlllIl.findChildren(articlecategory);
            if(list1 != null && list1.contains(articlecategory))
                return "/admin/common/error";
        }
        IIIlllIl.update(articleCategory, new String[] {
            "treePath", "grade", "children", "articles"
        });
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:list.jhtml";
    }

    public String list(ModelMap model)
    {
        model.addAttribute("articleCategoryTree", IIIlllIl.findTree());
        return "/admin/article_category/list";
    }

    public Message delete(Long id)
    {
        ArticleCategory articlecategory = (ArticleCategory)IIIlllIl.find(id);
        if(articlecategory == null)
            return IIIllIll;
        Set set = articlecategory.getChildren();
        if(set != null && !set.isEmpty())
            return Message.error("admin.articleCategory.deleteExistChildrenNotAllowed", new Object[0]);
        Set set1 = articlecategory.getArticles();
        if(set1 != null && !set1.isEmpty())
        {
            return Message.error("admin.articleCategory.deleteExistArticleNotAllowed", new Object[0]);
        } else
        {
            IIIlllIl.delete(id);
            return IIIlllII;
        }
    }

    private ArticleCategoryService IIIlllIl;
}
