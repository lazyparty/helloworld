// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import com.octo.captcha.service.CaptchaService;
import java.awt.image.BufferedImage;
import net.shopxx.Setting;
import net.shopxx.util.SettingUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

public class CaptchaServiceImpl
    implements net.shopxx.service.CaptchaService
{

    public CaptchaServiceImpl()
    {
    }

    public BufferedImage buildImage(String captchaId)
    {
        return (BufferedImage)IIIllIlI.getChallengeForID(captchaId);
    }

    public boolean isValid(net.shopxx.Setting.CaptchaType captchaType, String captchaId, String captcha)
    {
        Setting setting = SettingUtils.get();
        if(captchaType == null || ArrayUtils.contains(setting.getCaptchaTypes(), captchaType))
        {
            if(StringUtils.isNotEmpty(captchaId) && StringUtils.isNotEmpty(captcha))
                try
                {
                    return IIIllIlI.validateResponseForID(captchaId, captcha.toUpperCase()).booleanValue();
                }
                catch(Exception exception)
                {
                    return false;
                }
            else
                return false;
        } else
        {
            return true;
        }
    }

    private CaptchaService IIIllIlI;
}
