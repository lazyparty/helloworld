// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.shopxx.util.FreemarkerUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.springframework.core.io.ClassPathResource;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, ArticleCategory

public class Article extends BaseEntity
{

    public Article()
    {
        IIlIlIIl = new HashSet();
    }

    public String getTitle()
    {
        return IIIllllI;
    }

    public void setTitle(String title)
    {
        IIIllllI = title;
    }

    public String getAuthor()
    {
        return IIIlllll;
    }

    public void setAuthor(String author)
    {
        IIIlllll = author;
    }

    public String getContent()
    {
        if(IIlIIlll != null)
        {
            String as[] = getPageContents();
            if(IIlIIlll.intValue() < 1)
                IIlIIlll = Integer.valueOf(1);
            if(IIlIIlll.intValue() > as.length)
                IIlIIlll = Integer.valueOf(as.length);
            return as[IIlIIlll.intValue() - 1];
        } else
        {
            return IIlIIIII;
        }
    }

    public void setContent(String content)
    {
        IIlIIIII = content;
    }

    public String getSeoTitle()
    {
        return IIlIIIIl;
    }

    public void setSeoTitle(String seoTitle)
    {
        IIlIIIIl = seoTitle;
    }

    public String getSeoKeywords()
    {
        return IIlIIIlI;
    }

    public void setSeoKeywords(String seoKeywords)
    {
        if(seoKeywords != null)
            seoKeywords = seoKeywords.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
        IIlIIIlI = seoKeywords;
    }

    public String getSeoDescription()
    {
        return IIlIIIll;
    }

    public void setSeoDescription(String seoDescription)
    {
        IIlIIIll = seoDescription;
    }

    public Boolean getIsPublication()
    {
        return IIlIIlII;
    }

    public void setIsPublication(Boolean isPublication)
    {
        IIlIIlII = isPublication;
    }

    public Boolean getIsTop()
    {
        return IIlIIlIl;
    }

    public void setIsTop(Boolean isTop)
    {
        IIlIIlIl = isTop;
    }

    public Long getHits()
    {
        return IIlIIllI;
    }

    public void setHits(Long hits)
    {
        IIlIIllI = hits;
    }

    public Integer getPageNumber()
    {
        return IIlIIlll;
    }

    public void setPageNumber(Integer pageNumber)
    {
        IIlIIlll = pageNumber;
    }

    public ArticleCategory getArticleCategory()
    {
        return IIlIlIII;
    }

    public void setArticleCategory(ArticleCategory articleCategory)
    {
        IIlIlIII = articleCategory;
    }

    public Set getTags()
    {
        return IIlIlIIl;
    }

    public void setTags(Set tags)
    {
        IIlIlIIl = tags;
    }

    public String getPath()
    {
        HashMap hashmap = new HashMap();
        hashmap.put("id", getId());
        hashmap.put("createDate", getCreateDate());
        hashmap.put("modifyDate", getModifyDate());
        hashmap.put("title", getTitle());
        hashmap.put("seoTitle", getSeoTitle());
        hashmap.put("seoKeywords", getSeoKeywords());
        hashmap.put("seoDescription", getSeoDescription());
        hashmap.put("pageNumber", getPageNumber());
        hashmap.put("articleCategory", getArticleCategory());
        return FreemarkerUtils.process(IIIlllIl, hashmap);
    }

    public String getText()
    {
        if(getContent() != null)
            return Jsoup.parse(getContent()).text();
        else
            return null;
    }

    public String[] getPageContents()
    {
        if(StringUtils.isEmpty(IIlIIIII))
            return (new String[] {
                ""
            });
        if(IIlIIIII.contains("<hr class=\"pageBreak\" />"))
            return IIlIIIII.split("<hr class=\"pageBreak\" />");
        ArrayList arraylist = new ArrayList();
        org.jsoup.nodes.Document document = Jsoup.parse(IIlIIIII);
        List list = document.body().childNodes();
        if(list != null)
        {
            int i = 0;
            StringBuffer stringbuffer = new StringBuffer();
            for(Iterator iterator = list.iterator(); iterator.hasNext();)
            {
                Node node = (Node)iterator.next();
                if(node instanceof org.jsoup.nodes.Element)
                {
                    org.jsoup.nodes.Element element = (org.jsoup.nodes.Element)node;
                    stringbuffer.append(element.outerHtml());
                    i += element.text().length();
                    if(i >= 800)
                    {
                        arraylist.add(stringbuffer.toString());
                        i = 0;
                        stringbuffer.setLength(0);
                    }
                } else
                if(node instanceof TextNode)
                {
                    TextNode textnode = (TextNode)node;
                    String s1 = textnode.text();
                    String as[] = IIIlllII.split(s1);
                    Matcher matcher = IIIlllII.matcher(s1);
                    String as1[];
                    int k = (as1 = as).length;
                    for(int j = 0; j < k; j++)
                    {
                        String s2 = as1[j];
                        if(matcher.find())
                            s2 = (new StringBuilder(String.valueOf(s2))).append(matcher.group()).toString();
                        stringbuffer.append(s2);
                        i += s2.length();
                        if(i >= 800)
                        {
                            arraylist.add(stringbuffer.toString());
                            i = 0;
                            stringbuffer.setLength(0);
                        }
                    }

                }
            }

            String s = stringbuffer.toString();
            if(StringUtils.isNotEmpty(s))
                arraylist.add(s);
        }
        return (String[])arraylist.toArray(new String[arraylist.size()]);
    }

    public int getTotalPages()
    {
        return getPageContents().length;
    }

    private static final long serialVersionUID = 0x147afffe17b2244aL;
    public static final String HITS_CACHE_NAME = "articleHits";
    public static final int HITS_CACHE_INTERVAL = 0x927c0;
    private static final int IIIllIlI = 800;
    private static final String IIIllIll = "<hr class=\"pageBreak\" />";
    private static final Pattern IIIlllII = Pattern.compile("[,;\\.!?\uFF0C\uFF1B\u3002\uFF01\uFF1F]");
    private static String IIIlllIl;
    private String IIIllllI;
    private String IIIlllll;
    private String IIlIIIII;
    private String IIlIIIIl;
    private String IIlIIIlI;
    private String IIlIIIll;
    private Boolean IIlIIlII;
    private Boolean IIlIIlIl;
    private Long IIlIIllI;
    private Integer IIlIIlll;
    private ArticleCategory IIlIlIII;
    private Set IIlIlIIl;

    static 
    {
        try
        {
            java.io.File file = (new ClassPathResource("/shopxx.xml")).getFile();
            Document document = (new SAXReader()).read(file);
            Element element = (Element)document.selectSingleNode("/shopxx/template[@id='articleContent']");
            IIIlllIl = element.attributeValue("staticPath");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
