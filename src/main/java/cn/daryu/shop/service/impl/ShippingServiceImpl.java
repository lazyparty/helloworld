// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.shopxx.Setting;
import net.shopxx.dao.ShippingDao;
import net.shopxx.entity.Shipping;
import net.shopxx.service.ShippingService;
import net.shopxx.util.SettingUtils;
import org.apache.commons.lang.StringUtils;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class ShippingServiceImpl extends BaseServiceImpl
    implements ShippingService
{

    public ShippingServiceImpl()
    {
    }

    public void setBaseDao(ShippingDao shippingDao)
    {
        super.setBaseDao(shippingDao);
    }

    public Shipping findBySn(String sn)
    {
        return IIIllIlI.findBySn(sn);
    }

    public Map query(Shipping shipping)
    {
        Setting setting = SettingUtils.get();
        Object obj = new HashMap();
        if(shipping != null && StringUtils.isNotEmpty(setting.getKuaidi100Key()) && StringUtils.isNotEmpty(shipping.getDeliveryCorpCode()) && StringUtils.isNotEmpty(shipping.getTrackingNo()))
            try
            {
                ObjectMapper objectmapper = new ObjectMapper();
                URL url = new URL((new StringBuilder("http://api.kuaidi100.com/api?id=")).append(setting.getKuaidi100Key()).append("&com=").append(shipping.getDeliveryCorpCode()).append("&nu=").append(shipping.getTrackingNo()).append("&show=0&muti=1&order=asc").toString());
                obj = (Map)objectmapper.readValue(url, java/util/Map);
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
            }
        return ((Map) (obj));
    }

    private ShippingDao IIIllIlI;
}
