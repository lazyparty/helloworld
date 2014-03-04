// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.entity;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;
import net.shopxx.interceptor.MemberInterceptor;
import net.shopxx.util.JsonUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

// Referenced classes of package net.shopxx.entity:
//            BaseEntity, Area, MemberAttribute, SafeKey, 
//            MemberRank, Cart

public class Member extends BaseEntity
{

    public Member()
    {
        IIllllII = new HashSet();
        IIllllIl = new HashSet();
        IIlllllI = new HashSet();
        IIllllll = new HashSet();
        IlIIIIII = new HashSet();
        IlIIIIIl = new HashSet();
        IlIIIIlI = new HashSet();
        IlIIIIll = new HashSet();
        IlIIIlII = new HashSet();
        IlIIIlIl = new HashSet();
        IlIIIllI = new HashSet();
    }

    public String getUsername()
    {
        return IIIllIlI;
    }

    public void setUsername(String username)
    {
        IIIllIlI = username;
    }

    public String getPassword()
    {
        return IIIllIll;
    }

    public void setPassword(String password)
    {
        IIIllIll = password;
    }

    public String getEmail()
    {
        return IIIlllII;
    }

    public void setEmail(String email)
    {
        IIIlllII = email;
    }

    public Long getPoint()
    {
        return IIIlllIl;
    }

    public void setPoint(Long point)
    {
        IIIlllIl = point;
    }

    public BigDecimal getAmount()
    {
        return IIIllllI;
    }

    public void setAmount(BigDecimal amount)
    {
        IIIllllI = amount;
    }

    public BigDecimal getBalance()
    {
        return IIIlllll;
    }

    public void setBalance(BigDecimal balance)
    {
        IIIlllll = balance;
    }

    public Boolean getIsEnabled()
    {
        return IIlIIIII;
    }

    public void setIsEnabled(Boolean isEnabled)
    {
        IIlIIIII = isEnabled;
    }

    public Boolean getIsLocked()
    {
        return IIlIIIIl;
    }

    public void setIsLocked(Boolean isLocked)
    {
        IIlIIIIl = isLocked;
    }

    public Integer getLoginFailureCount()
    {
        return IIlIIIlI;
    }

    public void setLoginFailureCount(Integer loginFailureCount)
    {
        IIlIIIlI = loginFailureCount;
    }

    public Date getLockedDate()
    {
        return IIlIIIll;
    }

    public void setLockedDate(Date lockedDate)
    {
        IIlIIIll = lockedDate;
    }

    public String getRegisterIp()
    {
        return IIlIIlII;
    }

    public void setRegisterIp(String registerIp)
    {
        IIlIIlII = registerIp;
    }

    public String getLoginIp()
    {
        return IIlIIlIl;
    }

    public void setLoginIp(String loginIp)
    {
        IIlIIlIl = loginIp;
    }

    public Date getLoginDate()
    {
        return IIlIIllI;
    }

    public void setLoginDate(Date loginDate)
    {
        IIlIIllI = loginDate;
    }

    public String getName()
    {
        return IIlIIlll;
    }

    public void setName(String name)
    {
        IIlIIlll = name;
    }

    public Gender getGender()
    {
        return IIlIlIII;
    }

    public void setGender(Gender gender)
    {
        IIlIlIII = gender;
    }

    public Date getBirth()
    {
        return IIlIlIIl;
    }

    public void setBirth(Date birth)
    {
        IIlIlIIl = birth;
    }

    public String getAddress()
    {
        return IIlIlIlI;
    }

    public void setAddress(String address)
    {
        IIlIlIlI = address;
    }

    public String getZipCode()
    {
        return IIlIlIll;
    }

    public void setZipCode(String zipCode)
    {
        IIlIlIll = zipCode;
    }

    public String getPhone()
    {
        return IIlIllII;
    }

    public void setPhone(String phone)
    {
        IIlIllII = phone;
    }

    public String getMobile()
    {
        return IIlIllIl;
    }

    public void setMobile(String mobile)
    {
        IIlIllIl = mobile;
    }

    public String getAttributeValue0()
    {
        return IIlIlllI;
    }

    public void setAttributeValue0(String attributeValue0)
    {
        IIlIlllI = attributeValue0;
    }

    public String getAttributeValue1()
    {
        return IIlIllll;
    }

    public void setAttributeValue1(String attributeValue1)
    {
        IIlIllll = attributeValue1;
    }

    public String getAttributeValue2()
    {
        return IIllIIII;
    }

    public void setAttributeValue2(String attributeValue2)
    {
        IIllIIII = attributeValue2;
    }

    public String getAttributeValue3()
    {
        return IIllIIIl;
    }

    public void setAttributeValue3(String attributeValue3)
    {
        IIllIIIl = attributeValue3;
    }

    public String getAttributeValue4()
    {
        return IIllIIlI;
    }

    public void setAttributeValue4(String attributeValue4)
    {
        IIllIIlI = attributeValue4;
    }

    public String getAttributeValue5()
    {
        return IIllIIll;
    }

    public void setAttributeValue5(String attributeValue5)
    {
        IIllIIll = attributeValue5;
    }

    public String getAttributeValue6()
    {
        return IIllIlII;
    }

    public void setAttributeValue6(String attributeValue6)
    {
        IIllIlII = attributeValue6;
    }

    public String getAttributeValue7()
    {
        return IIllIlIl;
    }

    public void setAttributeValue7(String attributeValue7)
    {
        IIllIlIl = attributeValue7;
    }

    public String getAttributeValue8()
    {
        return IIllIllI;
    }

    public void setAttributeValue8(String attributeValue8)
    {
        IIllIllI = attributeValue8;
    }

    public String getAttributeValue9()
    {
        return IIllIlll;
    }

    public void setAttributeValue9(String attributeValue9)
    {
        IIllIlll = attributeValue9;
    }

    public SafeKey getSafeKey()
    {
        return IIlllIII;
    }

    public void setSafeKey(SafeKey safeKey)
    {
        IIlllIII = safeKey;
    }

    public Area getArea()
    {
        return IIlllIIl;
    }

    public void setArea(Area area)
    {
        IIlllIIl = area;
    }

    public MemberRank getMemberRank()
    {
        return IIlllIlI;
    }

    public void setMemberRank(MemberRank memberRank)
    {
        IIlllIlI = memberRank;
    }

    public Cart getCart()
    {
        return IIlllIll;
    }

    public void setCart(Cart cart)
    {
        IIlllIll = cart;
    }

    public Set getOrders()
    {
        return IIllllII;
    }

    public void setOrders(Set orders)
    {
        IIllllII = orders;
    }

    public Set getDeposits()
    {
        return IIllllIl;
    }

    public void setDeposits(Set deposits)
    {
        IIllllIl = deposits;
    }

    public Set getPayments()
    {
        return IIlllllI;
    }

    public void setPayments(Set payments)
    {
        IIlllllI = payments;
    }

    public Set getCouponCodes()
    {
        return IIllllll;
    }

    public void setCouponCodes(Set couponCodes)
    {
        IIllllll = couponCodes;
    }

    public Set getReceivers()
    {
        return IlIIIIII;
    }

    public void setReceivers(Set receivers)
    {
        IlIIIIII = receivers;
    }

    public Set getReviews()
    {
        return IlIIIIIl;
    }

    public void setReviews(Set reviews)
    {
        IlIIIIIl = reviews;
    }

    public Set getConsultations()
    {
        return IlIIIIlI;
    }

    public void setConsultations(Set consultations)
    {
        IlIIIIlI = consultations;
    }

    public Set getFavoriteProducts()
    {
        return IlIIIIll;
    }

    public void setFavoriteProducts(Set favoriteProducts)
    {
        IlIIIIll = favoriteProducts;
    }

    public Set getProductNotifies()
    {
        return IlIIIlII;
    }

    public void setProductNotifies(Set productNotifies)
    {
        IlIIIlII = productNotifies;
    }

    public Set getInMessages()
    {
        return IlIIIlIl;
    }

    public void setInMessages(Set inMessages)
    {
        IlIIIlIl = inMessages;
    }

    public Set getOutMessages()
    {
        return IlIIIllI;
    }

    public void setOutMessages(Set outMessages)
    {
        IlIIIllI = outMessages;
    }

    public Object getAttributeValue(MemberAttribute memberAttribute)
    {
        if(memberAttribute != null)
        {
            if(memberAttribute.getType() == MemberAttribute.Type.name)
                return getName();
            if(memberAttribute.getType() == MemberAttribute.Type.gender)
                return getGender();
            if(memberAttribute.getType() == MemberAttribute.Type.birth)
                return getBirth();
            if(memberAttribute.getType() == MemberAttribute.Type.area)
                return getArea();
            if(memberAttribute.getType() == MemberAttribute.Type.address)
                return getAddress();
            if(memberAttribute.getType() == MemberAttribute.Type.zipCode)
                return getZipCode();
            if(memberAttribute.getType() == MemberAttribute.Type.phone)
                return getPhone();
            if(memberAttribute.getType() == MemberAttribute.Type.mobile)
                return getMobile();
            if(memberAttribute.getType() == MemberAttribute.Type.checkbox)
            {
                if(memberAttribute.getPropertyIndex() != null)
                    try
                    {
                        String s = (new StringBuilder("attributeValue")).append(memberAttribute.getPropertyIndex()).toString();
                        String s2 = (String)PropertyUtils.getProperty(this, s);
                        if(s2 != null)
                            return JsonUtils.toObject(s2, java/util/List);
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
            } else
            if(memberAttribute.getPropertyIndex() != null)
                try
                {
                    String s1 = (new StringBuilder("attributeValue")).append(memberAttribute.getPropertyIndex()).toString();
                    return (String)PropertyUtils.getProperty(this, s1);
                }
                catch(IllegalAccessException illegalaccessexception1)
                {
                    illegalaccessexception1.printStackTrace();
                }
                catch(InvocationTargetException invocationtargetexception1)
                {
                    invocationtargetexception1.printStackTrace();
                }
                catch(NoSuchMethodException nosuchmethodexception1)
                {
                    nosuchmethodexception1.printStackTrace();
                }
        }
        return null;
    }

    public void setAttributeValue(MemberAttribute memberAttribute, Object attributeValue)
    {
        if(memberAttribute != null)
        {
            if((attributeValue instanceof String) && StringUtils.isEmpty((String)attributeValue))
                attributeValue = null;
            if(memberAttribute.getType() == MemberAttribute.Type.name && ((attributeValue instanceof String) || attributeValue == null))
                setName((String)attributeValue);
            else
            if(memberAttribute.getType() == MemberAttribute.Type.gender && ((attributeValue instanceof Gender) || attributeValue == null))
                setGender((Gender)attributeValue);
            else
            if(memberAttribute.getType() == MemberAttribute.Type.birth && ((attributeValue instanceof Date) || attributeValue == null))
                setBirth((Date)attributeValue);
            else
            if(memberAttribute.getType() == MemberAttribute.Type.area && ((attributeValue instanceof Area) || attributeValue == null))
                setArea((Area)attributeValue);
            else
            if(memberAttribute.getType() == MemberAttribute.Type.address && ((attributeValue instanceof String) || attributeValue == null))
                setAddress((String)attributeValue);
            else
            if(memberAttribute.getType() == MemberAttribute.Type.zipCode && ((attributeValue instanceof String) || attributeValue == null))
                setZipCode((String)attributeValue);
            else
            if(memberAttribute.getType() == MemberAttribute.Type.phone && ((attributeValue instanceof String) || attributeValue == null))
                setPhone((String)attributeValue);
            else
            if(memberAttribute.getType() == MemberAttribute.Type.mobile && ((attributeValue instanceof String) || attributeValue == null))
                setMobile((String)attributeValue);
            else
            if(memberAttribute.getType() == MemberAttribute.Type.checkbox && ((attributeValue instanceof List) || attributeValue == null))
            {
                if(memberAttribute.getPropertyIndex() != null && (attributeValue == null || memberAttribute.getOptions() != null && memberAttribute.getOptions().containsAll((List)attributeValue)))
                    try
                    {
                        String s = (new StringBuilder("attributeValue")).append(memberAttribute.getPropertyIndex()).toString();
                        PropertyUtils.setProperty(this, s, JsonUtils.toJson(attributeValue));
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
            } else
            if(memberAttribute.getPropertyIndex() != null)
                try
                {
                    String s1 = (new StringBuilder("attributeValue")).append(memberAttribute.getPropertyIndex()).toString();
                    PropertyUtils.setProperty(this, s1, attributeValue);
                }
                catch(IllegalAccessException illegalaccessexception1)
                {
                    illegalaccessexception1.printStackTrace();
                }
                catch(InvocationTargetException invocationtargetexception1)
                {
                    invocationtargetexception1.printStackTrace();
                }
                catch(NoSuchMethodException nosuchmethodexception1)
                {
                    nosuchmethodexception1.printStackTrace();
                }
        }
    }

    public void removeAttributeValue()
    {
        setName(null);
        setGender(null);
        setBirth(null);
        setArea(null);
        setAddress(null);
        setZipCode(null);
        setPhone(null);
        setMobile(null);
        for(int i = 0; i < 10; i++)
        {
            String s = (new StringBuilder("attributeValue")).append(i).toString();
            try
            {
                PropertyUtils.setProperty(this, s, null);
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

    private static final long serialVersionUID = 0x1546c63c97e1c5cbL;
    public static final String PRINCIPAL_ATTRIBUTE_NAME = (new StringBuilder(String.valueOf(net/shopxx/interceptor/MemberInterceptor.getName()))).append(".PRINCIPAL").toString();
    public static final String USERNAME_COOKIE_NAME = "username";
    public static final int ATTRIBUTE_VALUE_PROPERTY_COUNT = 10;
    public static final String ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX = "attributeValue";
    public static final Integer MAX_FAVORITE_COUNT = Integer.valueOf(10);
    private String IIIllIlI;
    private String IIIllIll;
    private String IIIlllII;
    private Long IIIlllIl;
    private BigDecimal IIIllllI;
    private BigDecimal IIIlllll;
    private Boolean IIlIIIII;
    private Boolean IIlIIIIl;
    private Integer IIlIIIlI;
    private Date IIlIIIll;
    private String IIlIIlII;
    private String IIlIIlIl;
    private Date IIlIIllI;
    private String IIlIIlll;
    private Gender IIlIlIII;
    private Date IIlIlIIl;
    private String IIlIlIlI;
    private String IIlIlIll;
    private String IIlIllII;
    private String IIlIllIl;
    private String IIlIlllI;
    private String IIlIllll;
    private String IIllIIII;
    private String IIllIIIl;
    private String IIllIIlI;
    private String IIllIIll;
    private String IIllIlII;
    private String IIllIlIl;
    private String IIllIllI;
    private String IIllIlll;
    private SafeKey IIlllIII;
    private Area IIlllIIl;
    private MemberRank IIlllIlI;
    private Cart IIlllIll;
    private Set IIllllII;
    private Set IIllllIl;
    private Set IIlllllI;
    private Set IIllllll;
    private Set IlIIIIII;
    private Set IlIIIIIl;
    private Set IlIIIIlI;
    private Set IlIIIIll;
    private Set IlIIIlII;
    private Set IlIIIlIl;
    private Set IlIIIllI;


    private class Gender extends Enum
    {

        public static Gender[] values()
        {
            Gender agender[];
            int i;
            Gender agender1[];
            System.arraycopy(agender = ENUM$VALUES, 0, agender1 = new Gender[i = agender.length], 0, i);
            return agender1;
        }

        public static Gender valueOf(String s)
        {
            return (Gender)Enum.valueOf(net/shopxx/entity/Member$Gender, s);
        }

        public static final Gender male;
        public static final Gender female;
        private static final Gender ENUM$VALUES[];

        static 
        {
            male = new Gender("male", 0);
            female = new Gender("female", 1);
            ENUM$VALUES = (new Gender[] {
                male, female
            });
        }

        private Gender(String s, int i)
        {
            super(s, i);
        }
    }

}
