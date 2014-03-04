// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.ArticleDao;
import net.shopxx.entity.Article;
import net.shopxx.entity.ArticleCategory;

// Referenced classes of package net.shopxx.dao.impl:
//            BaseDaoImpl

public class ArticleDaoImpl extends BaseDaoImpl
    implements ArticleDao
{

    public ArticleDaoImpl()
    {
    }

    public List findList(ArticleCategory articleCategory, List tags, Integer count, List filters, List orders)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Article);
        Root root = criteriaquery.from(net/shopxx/entity/Article);
        criteriaquery.select(root);
        javax.persistence.criteria.Predicate predicate = criteriabuilder.conjunction();
        predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isPublication"), Boolean.valueOf(true)));
        if(articleCategory != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.equal(root.get("articleCategory"), articleCategory), criteriabuilder.like(root.get("articleCategory").get("treePath"), (new StringBuilder("%,")).append(articleCategory.getId()).append(",").append("%").toString())));
        if(tags != null && !tags.isEmpty())
        {
            predicate = criteriabuilder.and(predicate, root.join("tags").in(tags));
            criteriaquery.distinct(true);
        }
        criteriaquery.where(predicate);
        criteriaquery.orderBy(new Order[] {
            criteriabuilder.desc(root.get("isTop"))
        });
        return super.IIIllIlI(criteriaquery, null, count, filters, orders);
    }

    public List findList(ArticleCategory articleCategory, Date beginDate, Date endDate, Integer first, Integer count)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Article);
        Root root = criteriaquery.from(net/shopxx/entity/Article);
        criteriaquery.select(root);
        javax.persistence.criteria.Predicate predicate = criteriabuilder.conjunction();
        predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isPublication"), Boolean.valueOf(true)));
        if(articleCategory != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.equal(root.get("articleCategory"), articleCategory), criteriabuilder.like(root.get("articleCategory").get("treePath"), (new StringBuilder("%,")).append(articleCategory.getId()).append(",").append("%").toString())));
        if(beginDate != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.greaterThanOrEqualTo(root.get("createDate"), beginDate));
        if(endDate != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.lessThanOrEqualTo(root.get("createDate"), endDate));
        criteriaquery.where(predicate);
        criteriaquery.orderBy(new Order[] {
            criteriabuilder.desc(root.get("isTop"))
        });
        return super.IIIllIlI(criteriaquery, first, count, null, null);
    }

    public Page findPage(ArticleCategory articleCategory, List tags, Pageable pageable)
    {
        CriteriaBuilder criteriabuilder = IIIllIlI.getCriteriaBuilder();
        CriteriaQuery criteriaquery = criteriabuilder.createQuery(net/shopxx/entity/Article);
        Root root = criteriaquery.from(net/shopxx/entity/Article);
        criteriaquery.select(root);
        javax.persistence.criteria.Predicate predicate = criteriabuilder.conjunction();
        predicate = criteriabuilder.and(predicate, criteriabuilder.equal(root.get("isPublication"), Boolean.valueOf(true)));
        if(articleCategory != null)
            predicate = criteriabuilder.and(predicate, criteriabuilder.or(criteriabuilder.equal(root.get("articleCategory"), articleCategory), criteriabuilder.like(root.get("articleCategory").get("treePath"), (new StringBuilder("%,")).append(articleCategory.getId()).append(",").append("%").toString())));
        if(tags != null && !tags.isEmpty())
        {
            predicate = criteriabuilder.and(predicate, root.join("tags").in(tags));
            criteriaquery.distinct(true);
        }
        criteriaquery.where(predicate);
        criteriaquery.orderBy(new Order[] {
            criteriabuilder.desc(root.get("isTop"))
        });
        return super.IIIllIlI(criteriaquery, pageable);
    }
}
