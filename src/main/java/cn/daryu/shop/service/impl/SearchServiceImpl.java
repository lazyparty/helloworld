// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import net.shopxx.*;
import net.shopxx.dao.ArticleDao;
import net.shopxx.dao.ProductDao;
import net.shopxx.entity.Article;
import net.shopxx.entity.Product;
import net.shopxx.service.SearchService;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.util.Version;
import org.hibernate.search.jpa.*;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class SearchServiceImpl
    implements SearchService
{

    public SearchServiceImpl()
    {
    }

    public void index()
    {
        index(net/shopxx/entity/Article);
        index(net/shopxx/entity/Product);
    }

    public void index(Class type)
    {
        FullTextEntityManager fulltextentitymanager = Search.getFullTextEntityManager(IIIllIlI);
        if(type == net/shopxx/entity/Article)
        {
            for(int i = 0; (long)i < IIIlllII.count(new Filter[0]); i += 20)
            {
                List list = IIIlllII.findList(Integer.valueOf(i), Integer.valueOf(20), null, null);
                Article article;
                for(Iterator iterator = list.iterator(); iterator.hasNext(); fulltextentitymanager.index(article))
                    article = (Article)iterator.next();

                fulltextentitymanager.flushToIndexes();
                fulltextentitymanager.clear();
                IIIlllII.clear();
            }

        } else
        if(type == net/shopxx/entity/Product)
        {
            for(int j = 0; (long)j < IIIlllIl.count(new Filter[0]); j += 20)
            {
                List list1 = IIIlllIl.findList(Integer.valueOf(j), Integer.valueOf(20), null, null);
                Product product;
                for(Iterator iterator1 = list1.iterator(); iterator1.hasNext(); fulltextentitymanager.index(product))
                    product = (Product)iterator1.next();

                fulltextentitymanager.flushToIndexes();
                fulltextentitymanager.clear();
                IIIlllIl.clear();
            }

        }
    }

    public void index(Article article)
    {
        if(article != null)
        {
            FullTextEntityManager fulltextentitymanager = Search.getFullTextEntityManager(IIIllIlI);
            fulltextentitymanager.index(article);
        }
    }

    public void index(Product product)
    {
        if(product != null)
        {
            FullTextEntityManager fulltextentitymanager = Search.getFullTextEntityManager(IIIllIlI);
            fulltextentitymanager.index(product);
        }
    }

    public void purge()
    {
        purge(net/shopxx/entity/Article);
        purge(net/shopxx/entity/Product);
    }

    public void purge(Class type)
    {
        FullTextEntityManager fulltextentitymanager = Search.getFullTextEntityManager(IIIllIlI);
        if(type == net/shopxx/entity/Article)
            fulltextentitymanager.purgeAll(net/shopxx/entity/Article);
        else
        if(type == net/shopxx/entity/Product)
            fulltextentitymanager.purgeAll(net/shopxx/entity/Product);
    }

    public void purge(Article article)
    {
        if(article != null)
        {
            FullTextEntityManager fulltextentitymanager = Search.getFullTextEntityManager(IIIllIlI);
            fulltextentitymanager.purge(net/shopxx/entity/Article, article.getId());
        }
    }

    public void purge(Product product)
    {
        if(product != null)
        {
            FullTextEntityManager fulltextentitymanager = Search.getFullTextEntityManager(IIIllIlI);
            fulltextentitymanager.purge(net/shopxx/entity/Product, product.getId());
        }
    }

    public Page search(String keyword, Pageable pageable)
    {
        if(StringUtils.isEmpty(keyword))
            return new Page();
        if(pageable == null)
            pageable = new Pageable();
        try
        {
            String s = QueryParser.escape(keyword);
            QueryParser queryparser = new QueryParser(Version.LUCENE_35, "title", new IKAnalyzer());
            queryparser.setDefaultOperator(QueryParser.AND_OPERATOR);
            org.apache.lucene.search.Query query = queryparser.parse(s);
            FuzzyQuery fuzzyquery = new FuzzyQuery(new Term("title", s), 0.5F);
            TermQuery termquery = new TermQuery(new Term("content", s));
            TermQuery termquery1 = new TermQuery(new Term("isPublication", "true"));
            BooleanQuery booleanquery = new BooleanQuery();
            BooleanQuery booleanquery1 = new BooleanQuery();
            booleanquery.add(query, org.apache.lucene.search.BooleanClause.Occur.SHOULD);
            booleanquery.add(fuzzyquery, org.apache.lucene.search.BooleanClause.Occur.SHOULD);
            booleanquery.add(termquery, org.apache.lucene.search.BooleanClause.Occur.SHOULD);
            booleanquery1.add(termquery1, org.apache.lucene.search.BooleanClause.Occur.MUST);
            booleanquery1.add(booleanquery, org.apache.lucene.search.BooleanClause.Occur.MUST);
            FullTextEntityManager fulltextentitymanager = Search.getFullTextEntityManager(IIIllIlI);
            FullTextQuery fulltextquery = fulltextentitymanager.createFullTextQuery(booleanquery1, new Class[] {
                net/shopxx/entity/Article
            });
            fulltextquery.setSort(new Sort(new SortField[] {
                new SortField("isTop", 3, true), new SortField(null, 0), new SortField("createDate", 6, true)
            }));
            fulltextquery.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
            fulltextquery.setMaxResults(pageable.getPageSize());
            return new Page(fulltextquery.getResultList(), fulltextquery.getResultSize(), pageable);
        }
        catch(ParseException parseexception)
        {
            parseexception.printStackTrace();
        }
        return new Page();
    }

    public Page search(String keyword, BigDecimal startPrice, BigDecimal endPrice, net.shopxx.entity.Product.OrderType orderType, Pageable pageable)
    {
        if(StringUtils.isEmpty(keyword))
            return new Page();
        if(pageable == null)
            pageable = new Pageable();
        try
        {
            String s = QueryParser.escape(keyword);
            TermQuery termquery = new TermQuery(new Term("sn", s));
            org.apache.lucene.search.Query query = (new QueryParser(Version.LUCENE_35, "keyword", new IKAnalyzer())).parse(s);
            QueryParser queryparser = new QueryParser(Version.LUCENE_35, "name", new IKAnalyzer());
            queryparser.setDefaultOperator(QueryParser.AND_OPERATOR);
            org.apache.lucene.search.Query query1 = queryparser.parse(s);
            FuzzyQuery fuzzyquery = new FuzzyQuery(new Term("name", s), 0.5F);
            TermQuery termquery1 = new TermQuery(new Term("introduction", s));
            TermQuery termquery2 = new TermQuery(new Term("isMarketable", "true"));
            TermQuery termquery3 = new TermQuery(new Term("isList", "true"));
            TermQuery termquery4 = new TermQuery(new Term("isGift", "false"));
            BooleanQuery booleanquery = new BooleanQuery();
            BooleanQuery booleanquery1 = new BooleanQuery();
            booleanquery.add(termquery, org.apache.lucene.search.BooleanClause.Occur.SHOULD);
            booleanquery.add(query, org.apache.lucene.search.BooleanClause.Occur.SHOULD);
            booleanquery.add(query1, org.apache.lucene.search.BooleanClause.Occur.SHOULD);
            booleanquery.add(fuzzyquery, org.apache.lucene.search.BooleanClause.Occur.SHOULD);
            booleanquery.add(termquery1, org.apache.lucene.search.BooleanClause.Occur.SHOULD);
            booleanquery1.add(termquery2, org.apache.lucene.search.BooleanClause.Occur.MUST);
            booleanquery1.add(termquery3, org.apache.lucene.search.BooleanClause.Occur.MUST);
            booleanquery1.add(termquery4, org.apache.lucene.search.BooleanClause.Occur.MUST);
            booleanquery1.add(booleanquery, org.apache.lucene.search.BooleanClause.Occur.MUST);
            if(startPrice != null && endPrice != null && startPrice.compareTo(endPrice) > 0)
            {
                BigDecimal bigdecimal = startPrice;
                startPrice = endPrice;
                endPrice = bigdecimal;
            }
            if(startPrice != null && startPrice.compareTo(new BigDecimal(0)) >= 0 && endPrice != null && endPrice.compareTo(new BigDecimal(0)) >= 0)
            {
                NumericRangeQuery numericrangequery = NumericRangeQuery.newDoubleRange("price", Double.valueOf(startPrice.doubleValue()), Double.valueOf(endPrice.doubleValue()), true, true);
                booleanquery1.add(numericrangequery, org.apache.lucene.search.BooleanClause.Occur.MUST);
            } else
            if(startPrice != null && startPrice.compareTo(new BigDecimal(0)) >= 0)
            {
                NumericRangeQuery numericrangequery1 = NumericRangeQuery.newDoubleRange("price", Double.valueOf(startPrice.doubleValue()), null, true, false);
                booleanquery1.add(numericrangequery1, org.apache.lucene.search.BooleanClause.Occur.MUST);
            } else
            if(endPrice != null && endPrice.compareTo(new BigDecimal(0)) >= 0)
            {
                NumericRangeQuery numericrangequery2 = NumericRangeQuery.newDoubleRange("price", null, Double.valueOf(endPrice.doubleValue()), false, true);
                booleanquery1.add(numericrangequery2, org.apache.lucene.search.BooleanClause.Occur.MUST);
            }
            FullTextEntityManager fulltextentitymanager = Search.getFullTextEntityManager(IIIllIlI);
            FullTextQuery fulltextquery = fulltextentitymanager.createFullTextQuery(booleanquery1, new Class[] {
                net/shopxx/entity/Product
            });
            SortField asortfield[] = null;
            if(orderType == net.shopxx.entity.Product.OrderType.priceAsc)
                asortfield = (new SortField[] {
                    new SortField("price", 7, false), new SortField("createDate", 6, true)
                });
            else
            if(orderType == net.shopxx.entity.Product.OrderType.priceDesc)
                asortfield = (new SortField[] {
                    new SortField("price", 7, true), new SortField("createDate", 6, true)
                });
            else
            if(orderType == net.shopxx.entity.Product.OrderType.salesDesc)
                asortfield = (new SortField[] {
                    new SortField("sales", 4, true), new SortField("createDate", 6, true)
                });
            else
            if(orderType == net.shopxx.entity.Product.OrderType.scoreDesc)
                asortfield = (new SortField[] {
                    new SortField("score", 4, true), new SortField("createDate", 6, true)
                });
            else
            if(orderType == net.shopxx.entity.Product.OrderType.dateDesc)
                asortfield = (new SortField[] {
                    new SortField("createDate", 6, true)
                });
            else
                asortfield = (new SortField[] {
                    new SortField("isTop", 3, true), new SortField(null, 0), new SortField("modifyDate", 6, true)
                });
            fulltextquery.setSort(new Sort(asortfield));
            fulltextquery.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
            fulltextquery.setMaxResults(pageable.getPageSize());
            return new Page(fulltextquery.getResultList(), fulltextquery.getResultSize(), pageable);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return new Page();
    }

    private static final float IIIllIll = 0.5F;
    protected EntityManager IIIllIlI;
    private ArticleDao IIIlllII;
    private ProductDao IIIlllIl;
}
