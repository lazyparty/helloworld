// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.test;

import java.util.*;
import net.shopxx.dao.*;
import net.shopxx.entity.*;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArticleDaoImplTest
{

    public ArticleDaoImplTest()
    {
    }

    public void prepareTestData()
    {
        for(int i = 0; i < IIIllllI.length; i++)
        {
            String s = (new StringBuilder("test")).append(i).toString();
            ArticleCategory articlecategory = new ArticleCategory();
            if(i < 20)
            {
                articlecategory.setName(s);
                articlecategory.setOrder(Integer.valueOf(i));
                IIIllIlI.persist(articlecategory);
            } else
            {
                articlecategory.setName(s);
                articlecategory.setOrder(Integer.valueOf(i));
                articlecategory.setParent((ArticleCategory)IIIllIlI.find(IIIllllI[0]));
                IIIllIlI.persist(articlecategory);
            }
            IIIllllI[i] = articlecategory.getId();
        }

        IIIllIlI.flush();
        IIIllIlI.clear();
        for(int j = 0; j < IIlIIIII.length; j++)
        {
            String s1 = (new StringBuilder("test")).append(j).toString();
            Tag tag = new Tag();
            tag.setName(s1);
            tag.setOrder(Integer.valueOf(j));
            tag.setType(net.shopxx.entity.Tag.Type.article);
            IIIllIll.persist(tag);
            IIlIIIII[j] = tag.getId();
        }

        IIIllIll.flush();
        IIIllIll.clear();
        for(int k = 0; k < IIIlllll.length; k++)
        {
            String s2 = (new StringBuilder("test")).append(k).toString();
            Article article = new Article();
            article.setTitle(s2);
            article.setContent(s2);
            article.setIsPublication(Boolean.valueOf(true));
            article.setIsTop(Boolean.valueOf(false));
            article.setHits(Long.valueOf(0L));
            if(k < 20)
                article.setArticleCategory((ArticleCategory)IIIllIlI.find(IIIllllI[0]));
            else
                article.setArticleCategory((ArticleCategory)IIIllIlI.find(IIIllllI[1]));
            if(k < 20)
            {
                HashSet hashset = new HashSet();
                if(k < 10)
                {
                    hashset.add((Tag)IIIllIll.find(IIlIIIII[0]));
                    hashset.add((Tag)IIIllIll.find(IIlIIIII[1]));
                }
                hashset.add((Tag)IIIllIll.find(IIlIIIII[2]));
                article.setTags(hashset);
            }
            IIIlllII.persist(article);
            IIIlllll[k] = article.getId();
        }

        IIIlllII.flush();
        IIIlllII.clear();
        IIIlllIl.info("prepare test data");
    }

    public void testFindList()
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add((Tag)IIIllIll.find(IIlIIIII[0]));
        arraylist.add((Tag)IIIllIll.find(IIlIIIII[2]));
        MatcherAssert.assertThat(Integer.valueOf(IIIlllII.findList(null, arraylist, null, null, null).size()), Matchers.is(Integer.valueOf(80)));
    }

    private ArticleCategoryDao IIIllIlI;
    private TagDao IIIllIll;
    private ArticleDao IIIlllII;
    private static final Logger IIIlllIl = LoggerFactory.getLogger(net/shopxx/test/ArticleDaoImplTest);
    private static Long IIIllllI[] = new Long[100];
    private static Long IIIlllll[] = new Long[100];
    private static Long IIlIIIII[] = new Long[20];

}
