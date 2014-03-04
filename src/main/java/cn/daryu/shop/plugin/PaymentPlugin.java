// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.plugin;

import java.math.BigDecimal;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.shopxx.Setting;
import net.shopxx.entity.PluginConfig;
import net.shopxx.service.PluginConfigService;
import net.shopxx.util.SettingUtils;
import org.apache.commons.lang.builder.*;
import org.springframework.stereotype.Component;

public abstract class PaymentPlugin
    implements Comparable
{

    public PaymentPlugin()
    {
    }

    public final String getId()
    {
        return ((Component)getClass().getAnnotation(org/springframework/stereotype/Component)).value();
    }

    public abstract String getName();

    public abstract String getVersion();

    public abstract String getAuthor();

    public abstract String getSiteUrl();

    public abstract String getInstallUrl();

    public abstract String getUninstallUrl();

    public abstract String getSettingUrl();

    public boolean getIsInstalled()
    {
        return IIIllIlI.pluginIdExists(getId());
    }

    public PluginConfig getPluginConfig()
    {
        return IIIllIlI.findByPluginId(getId());
    }

    public boolean getIsEnabled()
    {
        PluginConfig pluginconfig = getPluginConfig();
        return pluginconfig == null ? false : pluginconfig.getIsEnabled().booleanValue();
    }

    public String getAttribute(String name)
    {
        PluginConfig pluginconfig = getPluginConfig();
        return pluginconfig == null ? null : pluginconfig.getAttribute(name);
    }

    public Integer getOrder()
    {
        PluginConfig pluginconfig = getPluginConfig();
        return pluginconfig == null ? null : pluginconfig.getOrder();
    }

    public String getPaymentName()
    {
        PluginConfig pluginconfig = getPluginConfig();
        return pluginconfig == null ? null : pluginconfig.getAttribute("paymentName");
    }

    public FeeType getFeeType()
    {
        PluginConfig pluginconfig = getPluginConfig();
        return pluginconfig == null ? null : FeeType.valueOf(pluginconfig.getAttribute("feeType"));
    }

    public BigDecimal getFee()
    {
        PluginConfig pluginconfig = getPluginConfig();
        return pluginconfig == null ? null : new BigDecimal(pluginconfig.getAttribute("fee"));
    }

    public String getLogo()
    {
        PluginConfig pluginconfig = getPluginConfig();
        return pluginconfig == null ? null : pluginconfig.getAttribute("logo");
    }

    public String getDescription()
    {
        PluginConfig pluginconfig = getPluginConfig();
        return pluginconfig == null ? null : pluginconfig.getAttribute("description");
    }

    public abstract String getUrl();

    public abstract Method getMethod();

    public abstract Integer getTimeout();

    public abstract Map getParameterMap(String s, BigDecimal bigdecimal, String s1, HttpServletRequest httpservletrequest);

    public abstract boolean verify(String s, HttpServletRequest httpservletrequest);

    public abstract BigDecimal getAmount(String s, HttpServletRequest httpservletrequest);

    public abstract String getNotifyContext(String s, HttpServletRequest httpservletrequest);

    protected String IIIllIlI(String s)
    {
        Setting setting = SettingUtils.get();
        return (new StringBuilder(String.valueOf(setting.getSiteUrl()))).append("/payment/return/").append(s).append(".jhtml").toString();
    }

    protected String IIIllIll(String s)
    {
        Setting setting = SettingUtils.get();
        return (new StringBuilder(String.valueOf(setting.getSiteUrl()))).append("/payment/notify/").append(s).append(".jhtml").toString();
    }

    public final BigDecimal getFee(BigDecimal amount)
    {
        Setting setting = SettingUtils.get();
        BigDecimal bigdecimal;
        if(getFeeType() == FeeType.scale)
            bigdecimal = amount.multiply(getFee());
        else
            bigdecimal = getFee();
        return setting.setScale(bigdecimal);
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        if(this == obj)
        {
            return true;
        } else
        {
            PaymentPlugin paymentplugin = (PaymentPlugin)obj;
            return (new EqualsBuilder()).append(getId(), paymentplugin.getId()).isEquals();
        }
    }

    public int hashCode()
    {
        return (new HashCodeBuilder(17, 37)).append(getId()).toHashCode();
    }

    public int compareTo(PaymentPlugin paymentPlugin)
    {
        return (new CompareToBuilder()).append(getOrder(), paymentPlugin.getOrder()).append(getId(), paymentPlugin.getId()).toComparison();
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((PaymentPlugin)obj);
    }

    public static final String PAYMENT_NAME_ATTRIBUTE_NAME = "paymentName";
    public static final String FEE_TYPE_ATTRIBUTE_NAME = "feeType";
    public static final String FEE_ATTRIBUTE_NAME = "fee";
    public static final String LOGO_ATTRIBUTE_NAME = "logo";
    public static final String DESCRIPTION_ATTRIBUTE_NAME = "description";
    private PluginConfigService IIIllIlI;

    private class FeeType extends Enum
    {

        public static FeeType[] values()
        {
            FeeType afeetype[];
            int i;
            FeeType afeetype1[];
            System.arraycopy(afeetype = ENUM$VALUES, 0, afeetype1 = new FeeType[i = afeetype.length], 0, i);
            return afeetype1;
        }

        public static FeeType valueOf(String s)
        {
            return (FeeType)Enum.valueOf(net/shopxx/plugin/PaymentPlugin$FeeType, s);
        }

        public static final FeeType scale;
        public static final FeeType fixed;
        private static final FeeType ENUM$VALUES[];

        static 
        {
            scale = new FeeType("scale", 0);
            fixed = new FeeType("fixed", 1);
            ENUM$VALUES = (new FeeType[] {
                scale, fixed
            });
        }

        private FeeType(String s, int i)
        {
            super(s, i);
        }
    }

}
