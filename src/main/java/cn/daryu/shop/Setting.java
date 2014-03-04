// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Setting.java

package net.shopxx;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang.StringUtils;

public class Setting
    implements Serializable
{
    public static final class AccountLockType extends Enum
    {

        public static AccountLockType[] values()
        {
            AccountLockType aaccountlocktype[];
            int i;
            AccountLockType aaccountlocktype1[];
            System.arraycopy(aaccountlocktype = ENUM$VALUES, 0, aaccountlocktype1 = new AccountLockType[i = aaccountlocktype.length], 0, i);
            return aaccountlocktype1;
        }

        public static AccountLockType valueOf(String s)
        {
            return (AccountLockType)Enum.valueOf(net/shopxx/Setting$AccountLockType, s);
        }

        public static final AccountLockType member;
        public static final AccountLockType admin;
        private static final AccountLockType ENUM$VALUES[];

        static 
        {
            member = new AccountLockType("member", 0);
            admin = new AccountLockType("admin", 1);
            ENUM$VALUES = (new AccountLockType[] {
                member, admin
            });
        }

        private AccountLockType(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class CaptchaType extends Enum
    {

        public static CaptchaType[] values()
        {
            CaptchaType acaptchatype[];
            int i;
            CaptchaType acaptchatype1[];
            System.arraycopy(acaptchatype = ENUM$VALUES, 0, acaptchatype1 = new CaptchaType[i = acaptchatype.length], 0, i);
            return acaptchatype1;
        }

        public static CaptchaType valueOf(String s)
        {
            return (CaptchaType)Enum.valueOf(net/shopxx/Setting$CaptchaType, s);
        }

        public static final CaptchaType memberLogin;
        public static final CaptchaType memberRegister;
        public static final CaptchaType adminLogin;
        public static final CaptchaType review;
        public static final CaptchaType consultation;
        public static final CaptchaType findPassword;
        public static final CaptchaType resetPassword;
        public static final CaptchaType other;
        private static final CaptchaType ENUM$VALUES[];

        static 
        {
            memberLogin = new CaptchaType("memberLogin", 0);
            memberRegister = new CaptchaType("memberRegister", 1);
            adminLogin = new CaptchaType("adminLogin", 2);
            review = new CaptchaType("review", 3);
            consultation = new CaptchaType("consultation", 4);
            findPassword = new CaptchaType("findPassword", 5);
            resetPassword = new CaptchaType("resetPassword", 6);
            other = new CaptchaType("other", 7);
            ENUM$VALUES = (new CaptchaType[] {
                memberLogin, memberRegister, adminLogin, review, consultation, findPassword, resetPassword, other
            });
        }

        private CaptchaType(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class ConsultationAuthority extends Enum
    {

        public static ConsultationAuthority[] values()
        {
            ConsultationAuthority aconsultationauthority[];
            int i;
            ConsultationAuthority aconsultationauthority1[];
            System.arraycopy(aconsultationauthority = ENUM$VALUES, 0, aconsultationauthority1 = new ConsultationAuthority[i = aconsultationauthority.length], 0, i);
            return aconsultationauthority1;
        }

        public static ConsultationAuthority valueOf(String s)
        {
            return (ConsultationAuthority)Enum.valueOf(net/shopxx/Setting$ConsultationAuthority, s);
        }

        public static final ConsultationAuthority anyone;
        public static final ConsultationAuthority member;
        private static final ConsultationAuthority ENUM$VALUES[];

        static 
        {
            anyone = new ConsultationAuthority("anyone", 0);
            member = new ConsultationAuthority("member", 1);
            ENUM$VALUES = (new ConsultationAuthority[] {
                anyone, member
            });
        }

        private ConsultationAuthority(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class ReviewAuthority extends Enum
    {

        public static ReviewAuthority[] values()
        {
            ReviewAuthority areviewauthority[];
            int i;
            ReviewAuthority areviewauthority1[];
            System.arraycopy(areviewauthority = ENUM$VALUES, 0, areviewauthority1 = new ReviewAuthority[i = areviewauthority.length], 0, i);
            return areviewauthority1;
        }

        public static ReviewAuthority valueOf(String s)
        {
            return (ReviewAuthority)Enum.valueOf(net/shopxx/Setting$ReviewAuthority, s);
        }

        public static final ReviewAuthority anyone;
        public static final ReviewAuthority member;
        public static final ReviewAuthority purchased;
        private static final ReviewAuthority ENUM$VALUES[];

        static 
        {
            anyone = new ReviewAuthority("anyone", 0);
            member = new ReviewAuthority("member", 1);
            purchased = new ReviewAuthority("purchased", 2);
            ENUM$VALUES = (new ReviewAuthority[] {
                anyone, member, purchased
            });
        }

        private ReviewAuthority(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class RoundType extends Enum
    {

        public static RoundType[] values()
        {
            RoundType aroundtype[];
            int i;
            RoundType aroundtype1[];
            System.arraycopy(aroundtype = ENUM$VALUES, 0, aroundtype1 = new RoundType[i = aroundtype.length], 0, i);
            return aroundtype1;
        }

        public static RoundType valueOf(String s)
        {
            return (RoundType)Enum.valueOf(net/shopxx/Setting$RoundType, s);
        }

        public static final RoundType roundHalfUp;
        public static final RoundType roundUp;
        public static final RoundType roundDown;
        private static final RoundType ENUM$VALUES[];

        static 
        {
            roundHalfUp = new RoundType("roundHalfUp", 0);
            roundUp = new RoundType("roundUp", 1);
            roundDown = new RoundType("roundDown", 2);
            ENUM$VALUES = (new RoundType[] {
                roundHalfUp, roundUp, roundDown
            });
        }

        private RoundType(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class StockAllocationTime extends Enum
    {

        public static StockAllocationTime[] values()
        {
            StockAllocationTime astockallocationtime[];
            int i;
            StockAllocationTime astockallocationtime1[];
            System.arraycopy(astockallocationtime = ENUM$VALUES, 0, astockallocationtime1 = new StockAllocationTime[i = astockallocationtime.length], 0, i);
            return astockallocationtime1;
        }

        public static StockAllocationTime valueOf(String s)
        {
            return (StockAllocationTime)Enum.valueOf(net/shopxx/Setting$StockAllocationTime, s);
        }

        public static final StockAllocationTime order;
        public static final StockAllocationTime payment;
        public static final StockAllocationTime ship;
        private static final StockAllocationTime ENUM$VALUES[];

        static 
        {
            order = new StockAllocationTime("order", 0);
            payment = new StockAllocationTime("payment", 1);
            ship = new StockAllocationTime("ship", 2);
            ENUM$VALUES = (new StockAllocationTime[] {
                order, payment, ship
            });
        }

        private StockAllocationTime(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class WatermarkPosition extends Enum
    {

        public static WatermarkPosition[] values()
        {
            WatermarkPosition awatermarkposition[];
            int i;
            WatermarkPosition awatermarkposition1[];
            System.arraycopy(awatermarkposition = ENUM$VALUES, 0, awatermarkposition1 = new WatermarkPosition[i = awatermarkposition.length], 0, i);
            return awatermarkposition1;
        }

        public static WatermarkPosition valueOf(String s)
        {
            return (WatermarkPosition)Enum.valueOf(net/shopxx/Setting$WatermarkPosition, s);
        }

        public static final WatermarkPosition no;
        public static final WatermarkPosition topLeft;
        public static final WatermarkPosition topRight;
        public static final WatermarkPosition center;
        public static final WatermarkPosition bottomLeft;
        public static final WatermarkPosition bottomRight;
        private static final WatermarkPosition ENUM$VALUES[];

        static 
        {
            no = new WatermarkPosition("no", 0);
            topLeft = new WatermarkPosition("topLeft", 1);
            topRight = new WatermarkPosition("topRight", 2);
            center = new WatermarkPosition("center", 3);
            bottomLeft = new WatermarkPosition("bottomLeft", 4);
            bottomRight = new WatermarkPosition("bottomRight", 5);
            ENUM$VALUES = (new WatermarkPosition[] {
                no, topLeft, topRight, center, bottomLeft, bottomRight
            });
        }

        private WatermarkPosition(String s, int i)
        {
            super(s, i);
        }
    }


    public Setting()
    {
    }

    public String getSiteName()
    {
        return siteName;
    }

    public void setSiteName(String siteName)
    {
        this.siteName = siteName;
    }

    public String getSiteUrl()
    {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl)
    {
        this.siteUrl = StringUtils.removeEnd(siteUrl, "/");
    }

    public String getLogo()
    {
        return logo;
    }

    public void setLogo(String logo)
    {
        this.logo = logo;
    }

    public String getHotSearch()
    {
        return hotSearch;
    }

    public void setHotSearch(String hotSearch)
    {
        if(hotSearch != null)
            hotSearch = hotSearch.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
        this.hotSearch = hotSearch;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getCerttext()
    {
        return certtext;
    }

    public void setCerttext(String certtext)
    {
        this.certtext = certtext;
    }

    public Boolean getIsSiteEnabled()
    {
        return isSiteEnabled;
    }

    public void setIsSiteEnabled(Boolean isSiteEnabled)
    {
        this.isSiteEnabled = isSiteEnabled;
    }

    public String getSiteCloseMessage()
    {
        return siteCloseMessage;
    }

    public void setSiteCloseMessage(String siteCloseMessage)
    {
        this.siteCloseMessage = siteCloseMessage;
    }

    public Integer getLargeProductImageWidth()
    {
        return largeProductImageWidth;
    }

    public void setLargeProductImageWidth(Integer largeProductImageWidth)
    {
        this.largeProductImageWidth = largeProductImageWidth;
    }

    public Integer getLargeProductImageHeight()
    {
        return largeProductImageHeight;
    }

    public void setLargeProductImageHeight(Integer largeProductImageHeight)
    {
        this.largeProductImageHeight = largeProductImageHeight;
    }

    public Integer getMediumProductImageWidth()
    {
        return mediumProductImageWidth;
    }

    public void setMediumProductImageWidth(Integer mediumProductImageWidth)
    {
        this.mediumProductImageWidth = mediumProductImageWidth;
    }

    public Integer getMediumProductImageHeight()
    {
        return mediumProductImageHeight;
    }

    public void setMediumProductImageHeight(Integer mediumProductImageHeight)
    {
        this.mediumProductImageHeight = mediumProductImageHeight;
    }

    public Integer getThumbnailProductImageWidth()
    {
        return thumbnailProductImageWidth;
    }

    public void setThumbnailProductImageWidth(Integer thumbnailProductImageWidth)
    {
        this.thumbnailProductImageWidth = thumbnailProductImageWidth;
    }

    public Integer getThumbnailProductImageHeight()
    {
        return thumbnailProductImageHeight;
    }

    public void setThumbnailProductImageHeight(Integer thumbnailProductImageHeight)
    {
        this.thumbnailProductImageHeight = thumbnailProductImageHeight;
    }

    public String getDefaultLargeProductImage()
    {
        return defaultLargeProductImage;
    }

    public void setDefaultLargeProductImage(String defaultLargeProductImage)
    {
        this.defaultLargeProductImage = defaultLargeProductImage;
    }

    public String getDefaultMediumProductImage()
    {
        return defaultMediumProductImage;
    }

    public void setDefaultMediumProductImage(String defaultMediumProductImage)
    {
        this.defaultMediumProductImage = defaultMediumProductImage;
    }

    public String getDefaultThumbnailProductImage()
    {
        return defaultThumbnailProductImage;
    }

    public void setDefaultThumbnailProductImage(String defaultThumbnailProductImage)
    {
        this.defaultThumbnailProductImage = defaultThumbnailProductImage;
    }

    public Integer getWatermarkAlpha()
    {
        return watermarkAlpha;
    }

    public void setWatermarkAlpha(Integer watermarkAlpha)
    {
        this.watermarkAlpha = watermarkAlpha;
    }

    public String getWatermarkImage()
    {
        return watermarkImage;
    }

    public void setWatermarkImage(String watermarkImage)
    {
        this.watermarkImage = watermarkImage;
    }

    public WatermarkPosition getWatermarkPosition()
    {
        return watermarkPosition;
    }

    public void setWatermarkPosition(WatermarkPosition watermarkPosition)
    {
        this.watermarkPosition = watermarkPosition;
    }

    public Integer getPriceScale()
    {
        return priceScale;
    }

    public void setPriceScale(Integer priceScale)
    {
        this.priceScale = priceScale;
    }

    public RoundType getPriceRoundType()
    {
        return priceRoundType;
    }

    public void setPriceRoundType(RoundType priceRoundType)
    {
        this.priceRoundType = priceRoundType;
    }

    public Boolean getIsShowMarketPrice()
    {
        return isShowMarketPrice;
    }

    public void setIsShowMarketPrice(Boolean isShowMarketPrice)
    {
        this.isShowMarketPrice = isShowMarketPrice;
    }

    public Double getDefaultMarketPriceScale()
    {
        return defaultMarketPriceScale;
    }

    public void setDefaultMarketPriceScale(Double defaultMarketPriceScale)
    {
        this.defaultMarketPriceScale = defaultMarketPriceScale;
    }

    public Boolean getIsRegisterEnabled()
    {
        return isRegisterEnabled;
    }

    public void setIsRegisterEnabled(Boolean isRegisterEnabled)
    {
        this.isRegisterEnabled = isRegisterEnabled;
    }

    public Boolean getIsDuplicateEmail()
    {
        return isDuplicateEmail;
    }

    public void setIsDuplicateEmail(Boolean isDuplicateEmail)
    {
        this.isDuplicateEmail = isDuplicateEmail;
    }

    public String getDisabledUsername()
    {
        return disabledUsername;
    }

    public void setDisabledUsername(String disabledUsername)
    {
        if(disabledUsername != null)
            disabledUsername = disabledUsername.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
        this.disabledUsername = disabledUsername;
    }

    public Integer getUsernameMinLength()
    {
        return usernameMinLength;
    }

    public void setUsernameMinLength(Integer usernameMinLength)
    {
        this.usernameMinLength = usernameMinLength;
    }

    public Integer getUsernameMaxLength()
    {
        return usernameMaxLength;
    }

    public void setUsernameMaxLength(Integer usernameMaxLength)
    {
        this.usernameMaxLength = usernameMaxLength;
    }

    public Integer getPasswordMinLength()
    {
        return passwordMinLength;
    }

    public void setPasswordMinLength(Integer passwordMinLength)
    {
        this.passwordMinLength = passwordMinLength;
    }

    public Integer getPasswordMaxLength()
    {
        return passwordMaxLength;
    }

    public void setPasswordMaxLength(Integer passwordMaxLength)
    {
        this.passwordMaxLength = passwordMaxLength;
    }

    public Long getRegisterPoint()
    {
        return registerPoint;
    }

    public void setRegisterPoint(Long registerPoint)
    {
        this.registerPoint = registerPoint;
    }

    public String getRegisterAgreement()
    {
        return registerAgreement;
    }

    public void setRegisterAgreement(String registerAgreement)
    {
        this.registerAgreement = registerAgreement;
    }

    public Boolean getIsEmailLogin()
    {
        return isEmailLogin;
    }

    public void setIsEmailLogin(Boolean isEmailLogin)
    {
        this.isEmailLogin = isEmailLogin;
    }

    public CaptchaType[] getCaptchaTypes()
    {
        return captchaTypes;
    }

    public void setCaptchaTypes(CaptchaType captchaTypes[])
    {
        this.captchaTypes = captchaTypes;
    }

    public AccountLockType[] getAccountLockTypes()
    {
        return accountLockTypes;
    }

    public void setAccountLockTypes(AccountLockType accountLockTypes[])
    {
        this.accountLockTypes = accountLockTypes;
    }

    public Integer getAccountLockCount()
    {
        return accountLockCount;
    }

    public void setAccountLockCount(Integer accountLockCount)
    {
        this.accountLockCount = accountLockCount;
    }

    public Integer getAccountLockTime()
    {
        return accountLockTime;
    }

    public void setAccountLockTime(Integer accountLockTime)
    {
        this.accountLockTime = accountLockTime;
    }

    public Integer getSafeKeyExpiryTime()
    {
        return safeKeyExpiryTime;
    }

    public void setSafeKeyExpiryTime(Integer safeKeyExpiryTime)
    {
        this.safeKeyExpiryTime = safeKeyExpiryTime;
    }

    public Integer getUploadMaxSize()
    {
        return uploadMaxSize;
    }

    public void setUploadMaxSize(Integer uploadMaxSize)
    {
        this.uploadMaxSize = uploadMaxSize;
    }

    public String getUploadImageExtension()
    {
        return uploadImageExtension;
    }

    public void setUploadImageExtension(String uploadImageExtension)
    {
        if(uploadImageExtension != null)
            uploadImageExtension = uploadImageExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
        this.uploadImageExtension = uploadImageExtension;
    }

    public String getUploadFlashExtension()
    {
        return uploadFlashExtension;
    }

    public void setUploadFlashExtension(String uploadFlashExtension)
    {
        if(uploadFlashExtension != null)
            uploadFlashExtension = uploadFlashExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
        this.uploadFlashExtension = uploadFlashExtension;
    }

    public String getUploadMediaExtension()
    {
        return uploadMediaExtension;
    }

    public void setUploadMediaExtension(String uploadMediaExtension)
    {
        if(uploadMediaExtension != null)
            uploadMediaExtension = uploadMediaExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
        this.uploadMediaExtension = uploadMediaExtension;
    }

    public String getUploadFileExtension()
    {
        return uploadFileExtension;
    }

    public void setUploadFileExtension(String uploadFileExtension)
    {
        if(uploadFileExtension != null)
            uploadFileExtension = uploadFileExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
        this.uploadFileExtension = uploadFileExtension;
    }

    public String getImageUploadPath()
    {
        return imageUploadPath;
    }

    public void setImageUploadPath(String imageUploadPath)
    {
        if(imageUploadPath != null)
        {
            if(!imageUploadPath.startsWith("/"))
                imageUploadPath = (new StringBuilder("/")).append(imageUploadPath).toString();
            if(!imageUploadPath.endsWith("/"))
                imageUploadPath = (new StringBuilder(String.valueOf(imageUploadPath))).append("/").toString();
        }
        this.imageUploadPath = imageUploadPath;
    }

    public String getFlashUploadPath()
    {
        return flashUploadPath;
    }

    public void setFlashUploadPath(String flashUploadPath)
    {
        if(flashUploadPath != null)
        {
            if(!flashUploadPath.startsWith("/"))
                flashUploadPath = (new StringBuilder("/")).append(flashUploadPath).toString();
            if(!flashUploadPath.endsWith("/"))
                flashUploadPath = (new StringBuilder(String.valueOf(flashUploadPath))).append("/").toString();
        }
        this.flashUploadPath = flashUploadPath;
    }

    public String getMediaUploadPath()
    {
        return mediaUploadPath;
    }

    public void setMediaUploadPath(String mediaUploadPath)
    {
        if(mediaUploadPath != null)
        {
            if(!mediaUploadPath.startsWith("/"))
                mediaUploadPath = (new StringBuilder("/")).append(mediaUploadPath).toString();
            if(!mediaUploadPath.endsWith("/"))
                mediaUploadPath = (new StringBuilder(String.valueOf(mediaUploadPath))).append("/").toString();
        }
        this.mediaUploadPath = mediaUploadPath;
    }

    public String getFileUploadPath()
    {
        return fileUploadPath;
    }

    public void setFileUploadPath(String fileUploadPath)
    {
        if(fileUploadPath != null)
        {
            if(!fileUploadPath.startsWith("/"))
                fileUploadPath = (new StringBuilder("/")).append(fileUploadPath).toString();
            if(!fileUploadPath.endsWith("/"))
                fileUploadPath = (new StringBuilder(String.valueOf(fileUploadPath))).append("/").toString();
        }
        this.fileUploadPath = fileUploadPath;
    }

    public String getSmtpFromMail()
    {
        return smtpFromMail;
    }

    public void setSmtpFromMail(String smtpFromMail)
    {
        this.smtpFromMail = smtpFromMail;
    }

    public String getSmtpHost()
    {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost)
    {
        this.smtpHost = smtpHost;
    }

    public Integer getSmtpPort()
    {
        return smtpPort;
    }

    public void setSmtpPort(Integer smtpPort)
    {
        this.smtpPort = smtpPort;
    }

    public String getSmtpUsername()
    {
        return smtpUsername;
    }

    public void setSmtpUsername(String smtpUsername)
    {
        this.smtpUsername = smtpUsername;
    }

    public String getSmtpPassword()
    {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword)
    {
        this.smtpPassword = smtpPassword;
    }

    public String getCurrencySign()
    {
        return currencySign;
    }

    public void setCurrencySign(String currencySign)
    {
        this.currencySign = currencySign;
    }

    public String getCurrencyUnit()
    {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit)
    {
        this.currencyUnit = currencyUnit;
    }

    public Integer getStockAlertCount()
    {
        return stockAlertCount;
    }

    public void setStockAlertCount(Integer stockAlertCount)
    {
        this.stockAlertCount = stockAlertCount;
    }

    public StockAllocationTime getStockAllocationTime()
    {
        return stockAllocationTime;
    }

    public void setStockAllocationTime(StockAllocationTime stockAllocationTime)
    {
        this.stockAllocationTime = stockAllocationTime;
    }

    public Double getDefaultPointScale()
    {
        return defaultPointScale;
    }

    public void setDefaultPointScale(Double defaultPointScale)
    {
        this.defaultPointScale = defaultPointScale;
    }

    public Boolean getIsDevelopmentEnabled()
    {
        return isDevelopmentEnabled;
    }

    public void setIsDevelopmentEnabled(Boolean isDevelopmentEnabled)
    {
        this.isDevelopmentEnabled = isDevelopmentEnabled;
    }

    public Boolean getIsReviewEnabled()
    {
        return isReviewEnabled;
    }

    public void setIsReviewEnabled(Boolean isReviewEnabled)
    {
        this.isReviewEnabled = isReviewEnabled;
    }

    public Boolean getIsReviewCheck()
    {
        return isReviewCheck;
    }

    public void setIsReviewCheck(Boolean isReviewCheck)
    {
        this.isReviewCheck = isReviewCheck;
    }

    public ReviewAuthority getReviewAuthority()
    {
        return reviewAuthority;
    }

    public void setReviewAuthority(ReviewAuthority reviewAuthority)
    {
        this.reviewAuthority = reviewAuthority;
    }

    public Boolean getIsConsultationEnabled()
    {
        return isConsultationEnabled;
    }

    public void setIsConsultationEnabled(Boolean isConsultationEnabled)
    {
        this.isConsultationEnabled = isConsultationEnabled;
    }

    public Boolean getIsConsultationCheck()
    {
        return isConsultationCheck;
    }

    public void setIsConsultationCheck(Boolean isConsultationCheck)
    {
        this.isConsultationCheck = isConsultationCheck;
    }

    public ConsultationAuthority getConsultationAuthority()
    {
        return consultationAuthority;
    }

    public void setConsultationAuthority(ConsultationAuthority consultationAuthority)
    {
        this.consultationAuthority = consultationAuthority;
    }

    public Boolean getIsInvoiceEnabled()
    {
        return isInvoiceEnabled;
    }

    public void setIsInvoiceEnabled(Boolean isInvoiceEnabled)
    {
        this.isInvoiceEnabled = isInvoiceEnabled;
    }

    public Boolean getIsTaxPriceEnabled()
    {
        return isTaxPriceEnabled;
    }

    public void setIsTaxPriceEnabled(Boolean isTaxPriceEnabled)
    {
        this.isTaxPriceEnabled = isTaxPriceEnabled;
    }

    public Double getTaxRate()
    {
        return taxRate;
    }

    public void setTaxRate(Double taxRate)
    {
        this.taxRate = taxRate;
    }

    public String getCookiePath()
    {
        return cookiePath;
    }

    public void setCookiePath(String cookiePath)
    {
        if(cookiePath != null && !cookiePath.endsWith("/"))
            cookiePath = (new StringBuilder(String.valueOf(cookiePath))).append("/").toString();
        this.cookiePath = cookiePath;
    }

    public String getCookieDomain()
    {
        return cookieDomain;
    }

    public void setCookieDomain(String cookieDomain)
    {
        this.cookieDomain = cookieDomain;
    }

    public String getKuaidi100Key()
    {
        return kuaidi100Key;
    }

    public void setKuaidi100Key(String kuaidi100Key)
    {
        this.kuaidi100Key = kuaidi100Key;
    }

    public Boolean getIsCnzzEnabled()
    {
        return isCnzzEnabled;
    }

    public void setIsCnzzEnabled(Boolean isCnzzEnabled)
    {
        this.isCnzzEnabled = isCnzzEnabled;
    }

    public String getCnzzSiteId()
    {
        return cnzzSiteId;
    }

    public void setCnzzSiteId(String cnzzSiteId)
    {
        this.cnzzSiteId = cnzzSiteId;
    }

    public String getCnzzPassword()
    {
        return cnzzPassword;
    }

    public void setCnzzPassword(String cnzzPassword)
    {
        this.cnzzPassword = cnzzPassword;
    }

    public String[] getHotSearches()
    {
        return StringUtils.split(hotSearch, ",");
    }

    public String[] getDisabledUsernames()
    {
        return StringUtils.split(disabledUsername, ",");
    }

    public String[] getUploadImageExtensions()
    {
        return StringUtils.split(uploadImageExtension, ",");
    }

    public String[] getUploadFlashExtensions()
    {
        return StringUtils.split(uploadFlashExtension, ",");
    }

    public String[] getUploadMediaExtensions()
    {
        return StringUtils.split(uploadMediaExtension, ",");
    }

    public String[] getUploadFileExtensions()
    {
        return StringUtils.split(uploadFileExtension, ",");
    }

    public BigDecimal setScale(BigDecimal amount)
    {
        if(amount == null)
            return null;
        int roundingMode;
        if(getPriceRoundType() == RoundType.roundUp)
            roundingMode = 0;
        else
        if(getPriceRoundType() == RoundType.roundDown)
            roundingMode = 1;
        else
            roundingMode = 4;
        return amount.setScale(getPriceScale().intValue(), roundingMode);
    }

    private static final long serialVersionUID = 0xeb79896fb9ddde18L;
    public static final String CACHE_NAME = "setting";
    public static final Integer CACHE_KEY = Integer.valueOf(0);
    private static final String SEPARATOR = ",";
    private String siteName;
    private String siteUrl;
    private String logo;
    private String hotSearch;
    private String address;
    private String phone;
    private String zipCode;
    private String email;
    private String certtext;
    private Boolean isSiteEnabled;
    private String siteCloseMessage;
    private Integer largeProductImageWidth;
    private Integer largeProductImageHeight;
    private Integer mediumProductImageWidth;
    private Integer mediumProductImageHeight;
    private Integer thumbnailProductImageWidth;
    private Integer thumbnailProductImageHeight;
    private String defaultLargeProductImage;
    private String defaultMediumProductImage;
    private String defaultThumbnailProductImage;
    private Integer watermarkAlpha;
    private String watermarkImage;
    private WatermarkPosition watermarkPosition;
    private Integer priceScale;
    private RoundType priceRoundType;
    private Boolean isShowMarketPrice;
    private Double defaultMarketPriceScale;
    private Boolean isRegisterEnabled;
    private Boolean isDuplicateEmail;
    private String disabledUsername;
    private Integer usernameMinLength;
    private Integer usernameMaxLength;
    private Integer passwordMinLength;
    private Integer passwordMaxLength;
    private Long registerPoint;
    private String registerAgreement;
    private Boolean isEmailLogin;
    private CaptchaType captchaTypes[];
    private AccountLockType accountLockTypes[];
    private Integer accountLockCount;
    private Integer accountLockTime;
    private Integer safeKeyExpiryTime;
    private Integer uploadMaxSize;
    private String uploadImageExtension;
    private String uploadFlashExtension;
    private String uploadMediaExtension;
    private String uploadFileExtension;
    private String imageUploadPath;
    private String flashUploadPath;
    private String mediaUploadPath;
    private String fileUploadPath;
    private String smtpFromMail;
    private String smtpHost;
    private Integer smtpPort;
    private String smtpUsername;
    private String smtpPassword;
    private String currencySign;
    private String currencyUnit;
    private Integer stockAlertCount;
    private StockAllocationTime stockAllocationTime;
    private Double defaultPointScale;
    private Boolean isDevelopmentEnabled;
    private Boolean isReviewEnabled;
    private Boolean isReviewCheck;
    private ReviewAuthority reviewAuthority;
    private Boolean isConsultationEnabled;
    private Boolean isConsultationCheck;
    private ConsultationAuthority consultationAuthority;
    private Boolean isInvoiceEnabled;
    private Boolean isTaxPriceEnabled;
    private Double taxRate;
    private String cookiePath;
    private String cookieDomain;
    private String kuaidi100Key;
    private Boolean isCnzzEnabled;
    private String cnzzSiteId;
    private String cnzzPassword;

}
