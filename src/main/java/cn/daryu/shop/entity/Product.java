// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;
import net.shopxx.util.FreemarkerUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Attribute, Brand, Goods, 
//            Member, OrderItem, ProductCategory, ProductImage, 
//            Promotion

public class Product extends BaseEntity
{

    public Product()
    {
        IlIlIllI = new ArrayList();
        IlIlIlll = new HashSet();
        IlIllIII = new HashSet();
        IlIllIIl = new HashSet();
        IlIllIlI = new HashSet();
        IlIllIll = new HashSet();
        IlIlllII = new HashSet();
        IlIlllIl = new HashSet();
        IlIllllI = new HashSet();
        IlIlllll = new HashSet();
        IllIIIII = new HashSet();
        IllIIIIl = new HashSet();
        IllIIIlI = new HashMap();
        IllIIIll = new HashMap();
    }

    public String getSn()
    {
        return IIIllIll;
    }

    public void setSn(String sn)
    {
        IIIllIll = sn;
    }

    public String getName()
    {
        return IIIlllII;
    }

    public void setName(String name)
    {
        IIIlllII = name;
    }

    public String getFullName()
    {
        return IIIlllIl;
    }

    public void setFullName(String fullName)
    {
        IIIlllIl = fullName;
    }

    public BigDecimal getPrice()
    {
        return IIIllllI;
    }

    public void setPrice(BigDecimal price)
    {
        IIIllllI = price;
    }

    public BigDecimal getCost()
    {
        return IIIlllll;
    }

    public void setCost(BigDecimal cost)
    {
        IIIlllll = cost;
    }

    public BigDecimal getMarketPrice()
    {
        return IIlIIIII;
    }

    public void setMarketPrice(BigDecimal marketPrice)
    {
        IIlIIIII = marketPrice;
    }

    public String getImage()
    {
        return IIlIIIIl;
    }

    public void setImage(String image)
    {
        IIlIIIIl = image;
    }

    public String getUnit()
    {
        return IIlIIIlI;
    }

    public void setUnit(String unit)
    {
        IIlIIIlI = unit;
    }

    public Integer getWeight()
    {
        return IIlIIIll;
    }

    public void setWeight(Integer weight)
    {
        IIlIIIll = weight;
    }

    public Integer getStock()
    {
        return IIlIIlII;
    }

    public void setStock(Integer stock)
    {
        IIlIIlII = stock;
    }

    public Integer getAllocatedStock()
    {
        return IIlIIlIl;
    }

    public void setAllocatedStock(Integer allocatedStock)
    {
        IIlIIlIl = allocatedStock;
    }

    public String getStockMemo()
    {
        return IIlIIllI;
    }

    public void setStockMemo(String stockMemo)
    {
        IIlIIllI = stockMemo;
    }

    public Long getPoint()
    {
        return IIlIIlll;
    }

    public void setPoint(Long point)
    {
        IIlIIlll = point;
    }

    public Boolean getIsMarketable()
    {
        return IIlIlIII;
    }

    public void setIsMarketable(Boolean isMarketable)
    {
        IIlIlIII = isMarketable;
    }

    public Boolean getIsList()
    {
        return IIlIlIIl;
    }

    public void setIsList(Boolean isList)
    {
        IIlIlIIl = isList;
    }

    public Boolean getIsTop()
    {
        return IIlIlIlI;
    }

    public void setIsTop(Boolean isTop)
    {
        IIlIlIlI = isTop;
    }

    public Boolean getIsGift()
    {
        return IIlIlIll;
    }

    public void setIsGift(Boolean isGift)
    {
        IIlIlIll = isGift;
    }

    public String getIntroduction()
    {
        return IIlIllII;
    }

    public void setIntroduction(String introduction)
    {
        IIlIllII = introduction;
    }

    public String getMemo()
    {
        return IIlIllIl;
    }

    public void setMemo(String memo)
    {
        IIlIllIl = memo;
    }

    public String getKeyword()
    {
        return IIlIlllI;
    }

    public void setKeyword(String keyword)
    {
        if(keyword != null)
            keyword = keyword.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
        IIlIlllI = keyword;
    }

    public String getSeoTitle()
    {
        return IIlIllll;
    }

    public void setSeoTitle(String seoTitle)
    {
        IIlIllll = seoTitle;
    }

    public String getSeoKeywords()
    {
        return IIllIIII;
    }

    public void setSeoKeywords(String seoKeywords)
    {
        if(seoKeywords != null)
            seoKeywords = seoKeywords.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
        IIllIIII = seoKeywords;
    }

    public String getSeoDescription()
    {
        return IIllIIIl;
    }

    public void setSeoDescription(String seoDescription)
    {
        IIllIIIl = seoDescription;
    }

    public Float getScore()
    {
        return IIllIIlI;
    }

    public void setScore(Float score)
    {
        IIllIIlI = score;
    }

    public Long getTotalScore()
    {
        return IIllIIll;
    }

    public void setTotalScore(Long totalScore)
    {
        IIllIIll = totalScore;
    }

    public Long getScoreCount()
    {
        return IIllIlII;
    }

    public void setScoreCount(Long scoreCount)
    {
        IIllIlII = scoreCount;
    }

    public Long getHits()
    {
        return IIllIlIl;
    }

    public void setHits(Long hits)
    {
        IIllIlIl = hits;
    }

    public Long getWeekHits()
    {
        return IIllIllI;
    }

    public void setWeekHits(Long weekHits)
    {
        IIllIllI = weekHits;
    }

    public Long getMonthHits()
    {
        return IIllIlll;
    }

    public void setMonthHits(Long monthHits)
    {
        IIllIlll = monthHits;
    }

    public Long getSales()
    {
        return IIlllIII;
    }

    public void setSales(Long sales)
    {
        IIlllIII = sales;
    }

    public Long getWeekSales()
    {
        return IIlllIIl;
    }

    public void setWeekSales(Long weekSales)
    {
        IIlllIIl = weekSales;
    }

    public Long getMonthSales()
    {
        return IIlllIlI;
    }

    public void setMonthSales(Long monthSales)
    {
        IIlllIlI = monthSales;
    }

    public Date getWeekHitsDate()
    {
        return IIlllIll;
    }

    public void setWeekHitsDate(Date weekHitsDate)
    {
        IIlllIll = weekHitsDate;
    }

    public Date getMonthHitsDate()
    {
        return IIllllII;
    }

    public void setMonthHitsDate(Date monthHitsDate)
    {
        IIllllII = monthHitsDate;
    }

    public Date getWeekSalesDate()
    {
        return IIllllIl;
    }

    public void setWeekSalesDate(Date weekSalesDate)
    {
        IIllllIl = weekSalesDate;
    }

    public Date getMonthSalesDate()
    {
        return IIlllllI;
    }

    public void setMonthSalesDate(Date monthSalesDate)
    {
        IIlllllI = monthSalesDate;
    }

    public String getAttributeValue0()
    {
        return IIllllll;
    }

    public void setAttributeValue0(String attributeValue0)
    {
        IIllllll = attributeValue0;
    }

    public String getAttributeValue1()
    {
        return IlIIIIII;
    }

    public void setAttributeValue1(String attributeValue1)
    {
        IlIIIIII = attributeValue1;
    }

    public String getAttributeValue2()
    {
        return IlIIIIIl;
    }

    public void setAttributeValue2(String attributeValue2)
    {
        IlIIIIIl = attributeValue2;
    }

    public String getAttributeValue3()
    {
        return IlIIIIlI;
    }

    public void setAttributeValue3(String attributeValue3)
    {
        IlIIIIlI = attributeValue3;
    }

    public String getAttributeValue4()
    {
        return IlIIIIll;
    }

    public void setAttributeValue4(String attributeValue4)
    {
        IlIIIIll = attributeValue4;
    }

    public String getAttributeValue5()
    {
        return IlIIIlII;
    }

    public void setAttributeValue5(String attributeValue5)
    {
        IlIIIlII = attributeValue5;
    }

    public String getAttributeValue6()
    {
        return IlIIIlIl;
    }

    public void setAttributeValue6(String attributeValue6)
    {
        IlIIIlIl = attributeValue6;
    }

    public String getAttributeValue7()
    {
        return IlIIIllI;
    }

    public void setAttributeValue7(String attributeValue7)
    {
        IlIIIllI = attributeValue7;
    }

    public String getAttributeValue8()
    {
        return IlIIIlll;
    }

    public void setAttributeValue8(String attributeValue8)
    {
        IlIIIlll = attributeValue8;
    }

    public String getAttributeValue9()
    {
        return IlIIlIII;
    }

    public void setAttributeValue9(String attributeValue9)
    {
        IlIIlIII = attributeValue9;
    }

    public String getAttributeValue10()
    {
        return IlIIlIIl;
    }

    public void setAttributeValue10(String attributeValue10)
    {
        IlIIlIIl = attributeValue10;
    }

    public String getAttributeValue11()
    {
        return IlIIlIlI;
    }

    public void setAttributeValue11(String attributeValue11)
    {
        IlIIlIlI = attributeValue11;
    }

    public String getAttributeValue12()
    {
        return IlIIlIll;
    }

    public void setAttributeValue12(String attributeValue12)
    {
        IlIIlIll = attributeValue12;
    }

    public String getAttributeValue13()
    {
        return IlIIllII;
    }

    public void setAttributeValue13(String attributeValue13)
    {
        IlIIllII = attributeValue13;
    }

    public String getAttributeValue14()
    {
        return IlIIllIl;
    }

    public void setAttributeValue14(String attributeValue14)
    {
        IlIIllIl = attributeValue14;
    }

    public String getAttributeValue15()
    {
        return IlIIlllI;
    }

    public void setAttributeValue15(String attributeValue15)
    {
        IlIIlllI = attributeValue15;
    }

    public String getAttributeValue16()
    {
        return IlIIllll;
    }

    public void setAttributeValue16(String attributeValue16)
    {
        IlIIllll = attributeValue16;
    }

    public String getAttributeValue17()
    {
        return IlIlIIII;
    }

    public void setAttributeValue17(String attributeValue17)
    {
        IlIlIIII = attributeValue17;
    }

    public String getAttributeValue18()
    {
        return IlIlIIIl;
    }

    public void setAttributeValue18(String attributeValue18)
    {
        IlIlIIIl = attributeValue18;
    }

    public String getAttributeValue19()
    {
        return IlIlIIlI;
    }

    public void setAttributeValue19(String attributeValue19)
    {
        IlIlIIlI = attributeValue19;
    }

    public ProductCategory getProductCategory()
    {
        return IlIlIIll;
    }

    public void setProductCategory(ProductCategory productCategory)
    {
        IlIlIIll = productCategory;
    }

    public Goods getGoods()
    {
        return IlIlIlII;
    }

    public void setGoods(Goods goods)
    {
        IlIlIlII = goods;
    }

    public Brand getBrand()
    {
        return IlIlIlIl;
    }

    public void setBrand(Brand brand)
    {
        IlIlIlIl = brand;
    }

    public List getProductImages()
    {
        return IlIlIllI;
    }

    public void setProductImages(List productImages)
    {
        IlIlIllI = productImages;
    }

    public Set getReviews()
    {
        return IlIlIlll;
    }

    public void setReviews(Set reviews)
    {
        IlIlIlll = reviews;
    }

    public Set getConsultations()
    {
        return IlIllIII;
    }

    public void setConsultations(Set consultations)
    {
        IlIllIII = consultations;
    }

    public Set getTags()
    {
        return IlIllIIl;
    }

    public void setTags(Set tags)
    {
        IlIllIIl = tags;
    }

    public Set getFavoriteMembers()
    {
        return IlIllIlI;
    }

    public void setFavoriteMembers(Set favoriteMembers)
    {
        IlIllIlI = favoriteMembers;
    }

    public Set getSpecifications()
    {
        return IlIllIll;
    }

    public void setSpecifications(Set specifications)
    {
        IlIllIll = specifications;
    }

    public Set getSpecificationValues()
    {
        return IlIlllII;
    }

    public void setSpecificationValues(Set specificationValues)
    {
        IlIlllII = specificationValues;
    }

    public Set getPromotions()
    {
        return IlIlllIl;
    }

    public void setPromotions(Set promotions)
    {
        IlIlllIl = promotions;
    }

    public Set getCartItems()
    {
        return IlIllllI;
    }

    public void setCartItems(Set cartItems)
    {
        IlIllllI = cartItems;
    }

    public Set getOrderItems()
    {
        return IlIlllll;
    }

    public void setOrderItems(Set orderItems)
    {
        IlIlllll = orderItems;
    }

    public Set getGiftItems()
    {
        return IllIIIII;
    }

    public void setGiftItems(Set giftItems)
    {
        IllIIIII = giftItems;
    }

    public Set getProductNotifies()
    {
        return IllIIIIl;
    }

    public void setProductNotifies(Set productNotifies)
    {
        IllIIIIl = productNotifies;
    }

    public Map getMemberPrice()
    {
        return IllIIIlI;
    }

    public void setMemberPrice(Map memberPrice)
    {
        IllIIIlI = memberPrice;
    }

    public Map getParameterValue()
    {
        return IllIIIll;
    }

    public void setParameterValue(Map parameterValue)
    {
        IllIIIll = parameterValue;
    }

    public String getAttributeValue(Attribute attribute)
    {
        if(attribute != null && attribute.getPropertyIndex() != null)
            try
            {
                String s = (new StringBuilder("attributeValue")).append(attribute.getPropertyIndex()).toString();
                return (String)PropertyUtils.getProperty(this, s);
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                illegalaccessexception.printStackTrace();
            }
            catch(InvocationTargetException invocationtargetexception)
            {
                invocationtargetexception.printStackTrace();
            }
            catch(NoSuchMethodException nosuchmethodexception)
            {
                nosuchmethodexception.printStackTrace();
            }
        return null;
    }

    public void setAttributeValue(Attribute attribute, String value)
    {
        if(attribute != null && attribute.getPropertyIndex() != null)
        {
            if(StringUtils.isEmpty(value))
                value = null;
            if(value == null || attribute.getOptions() != null && attribute.getOptions().contains(value))
                try
                {
                    String s = (new StringBuilder("attributeValue")).append(attribute.getPropertyIndex()).toString();
                    PropertyUtils.setProperty(this, s, value);
                }
                catch(IllegalAccessException illegalaccessexception)
                {
                    illegalaccessexception.printStackTrace();
                }
                catch(InvocationTargetException invocationtargetexception)
                {
                    invocationtargetexception.printStackTrace();
                }
                catch(NoSuchMethodException nosuchmethodexception)
                {
                    nosuchmethodexception.printStackTrace();
                }
        }
    }

    public List getSiblings()
    {
        ArrayList arraylist = new ArrayList();
        if(getGoods() != null && getGoods().getProducts() != null)
        {
            for(Iterator iterator = getGoods().getProducts().iterator(); iterator.hasNext();)
            {
                Product product = (Product)iterator.next();
                if(!equals(product))
                    arraylist.add(product);
            }

        }
        return arraylist;
    }

    public String getPath()
    {
        HashMap hashmap = new HashMap();
        hashmap.put("id", getId());
        hashmap.put("createDate", getCreateDate());
        hashmap.put("modifyDate", getModifyDate());
        hashmap.put("sn", getSn());
        hashmap.put("name", getName());
        hashmap.put("fullName", getFullName());
        hashmap.put("seoTitle", getSeoTitle());
        hashmap.put("seoKeywords", getSeoKeywords());
        hashmap.put("seoDescription", getSeoDescription());
        hashmap.put("productCategory", getProductCategory());
        return FreemarkerUtils.process(IIIllIlI, hashmap);
    }

    public String getThumbnail()
    {
        if(getProductImages() != null && !getProductImages().isEmpty())
            return ((ProductImage)getProductImages().get(0)).getThumbnail();
        else
            return null;
    }

    public Set getValidPromotions()
    {
        HashSet hashset = new HashSet();
        if(getPromotions() != null)
            hashset.addAll(getPromotions());
        if(getProductCategory() != null && getProductCategory().getPromotions() != null)
            hashset.addAll(getProductCategory().getPromotions());
        if(getBrand() != null && getBrand().getPromotions() != null)
            hashset.addAll(getBrand().getPromotions());
        TreeSet treeset = new TreeSet();
        for(Iterator iterator = hashset.iterator(); iterator.hasNext();)
        {
            Promotion promotion = (Promotion)iterator.next();
            if(promotion != null && promotion.hasBegun() && !promotion.hasEnded())
                treeset.add(promotion);
        }

        return treeset;
    }

    public Integer getAvailableStock()
    {
        Integer integer = null;
        if(getStock() != null && getAllocatedStock() != null)
        {
            integer = Integer.valueOf(getStock().intValue() - getAllocatedStock().intValue());
            if(integer.intValue() < 0)
                integer = Integer.valueOf(0);
        }
        return integer;
    }

    public Boolean getIsOutOfStock()
    {
        if(getStock() != null && getAllocatedStock() != null && getAllocatedStock().intValue() >= getStock().intValue())
            return Boolean.valueOf(true);
        else
            return Boolean.valueOf(false);
    }

    public void preRemove()
    {
        Set set = getFavoriteMembers();
        if(set != null)
        {
            Member member;
            for(Iterator iterator = set.iterator(); iterator.hasNext(); member.getFavoriteProducts().remove(this))
                member = (Member)iterator.next();

        }
        Set set1 = getPromotions();
        if(set1 != null)
        {
            Promotion promotion;
            for(Iterator iterator1 = set1.iterator(); iterator1.hasNext(); promotion.getProducts().remove(this))
                promotion = (Promotion)iterator1.next();

        }
        Set set2 = getOrderItems();
        if(set2 != null)
        {
            OrderItem orderitem;
            for(Iterator iterator2 = set2.iterator(); iterator2.hasNext(); orderitem.setProduct(null))
                orderitem = (OrderItem)iterator2.next();

        }
    }

    public void prePersist()
    {
        if(getStock() == null)
            setAllocatedStock(Integer.valueOf(0));
        setScore(Float.valueOf(0.0F));
    }

    public void preUpdate()
    {
        if(getStock() == null)
            setAllocatedStock(Integer.valueOf(0));
        if(getTotalScore() != null && getScoreCount() != null && getScoreCount().longValue() != 0L)
            setScore(Float.valueOf((float)getTotalScore().longValue() / (float)getScoreCount().longValue()));
        else
            setScore(Float.valueOf(0.0F));
    }

    public String toString()
    {
        return getFullName();
    }

    private static final long serialVersionUID = 0x1e15ae4a73a2554dL;
    public static final String HITS_CACHE_NAME = "productHits";
    public static final int HITS_CACHE_INTERVAL = 0x927c0;
    public static final int ATTRIBUTE_VALUE_PROPERTY_COUNT = 20;
    public static final String ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX = "attributeValue";
    public static final String FULL_NAME_SPECIFICATION_PREFIX = "[";
    public static final String FULL_NAME_SPECIFICATION_SUFFIX = "]";
    public static final String FULL_NAME_SPECIFICATION_SEPARATOR = " ";
    private static String IIIllIlI;
    private String IIIllIll;
    private String IIIlllII;
    private String IIIlllIl;
    private BigDecimal IIIllllI;
    private BigDecimal IIIlllll;
    private BigDecimal IIlIIIII;
    private String IIlIIIIl;
    private String IIlIIIlI;
    private Integer IIlIIIll;
    private Integer IIlIIlII;
    private Integer IIlIIlIl;
    private String IIlIIllI;
    private Long IIlIIlll;
    private Boolean IIlIlIII;
    private Boolean IIlIlIIl;
    private Boolean IIlIlIlI;
    private Boolean IIlIlIll;
    private String IIlIllII;
    private String IIlIllIl;
    private String IIlIlllI;
    private String IIlIllll;
    private String IIllIIII;
    private String IIllIIIl;
    private Float IIllIIlI;
    private Long IIllIIll;
    private Long IIllIlII;
    private Long IIllIlIl;
    private Long IIllIllI;
    private Long IIllIlll;
    private Long IIlllIII;
    private Long IIlllIIl;
    private Long IIlllIlI;
    private Date IIlllIll;
    private Date IIllllII;
    private Date IIllllIl;
    private Date IIlllllI;
    private String IIllllll;
    private String IlIIIIII;
    private String IlIIIIIl;
    private String IlIIIIlI;
    private String IlIIIIll;
    private String IlIIIlII;
    private String IlIIIlIl;
    private String IlIIIllI;
    private String IlIIIlll;
    private String IlIIlIII;
    private String IlIIlIIl;
    private String IlIIlIlI;
    private String IlIIlIll;
    private String IlIIllII;
    private String IlIIllIl;
    private String IlIIlllI;
    private String IlIIllll;
    private String IlIlIIII;
    private String IlIlIIIl;
    private String IlIlIIlI;
    private ProductCategory IlIlIIll;
    private Goods IlIlIlII;
    private Brand IlIlIlIl;
    private List IlIlIllI;
    private Set IlIlIlll;
    private Set IlIllIII;
    private Set IlIllIIl;
    private Set IlIllIlI;
    private Set IlIllIll;
    private Set IlIlllII;
    private Set IlIlllIl;
    private Set IlIllllI;
    private Set IlIlllll;
    private Set IllIIIII;
    private Set IllIIIIl;
    private Map IllIIIlI;
    private Map IllIIIll;

    static 
    {
        try
        {
            java.io.File file = (new ClassPathResource("/shopxx.xml")).getFile();
            Document document = (new SAXReader()).read(file);
            Element element = (Element)document.selectSingleNode("/shopxx/template[@id='productContent']");
            IIIllIlI = element.attributeValue("staticPath");
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
