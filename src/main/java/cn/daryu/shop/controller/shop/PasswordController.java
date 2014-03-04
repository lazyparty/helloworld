// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import java.util.Date;
import java.util.UUID;
import net.shopxx.Message;
import net.shopxx.Setting;
import net.shopxx.entity.Member;
import net.shopxx.entity.SafeKey;
import net.shopxx.service.*;
import net.shopxx.util.SettingUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.ui.Model;

// Referenced classes of package net.shopxx.controller.shop:
//            BaseController

public class PasswordController extends BaseController
{

    public PasswordController()
    {
    }

    public String find(Model model)
    {
        model.addAttribute("captchaId", UUID.randomUUID().toString());
        return "/shop/password/find";
    }

    public Message find(String captchaId, String captcha, String username, String email)
    {
        if(!IIIlllIl.isValid(net.shopxx.Setting.CaptchaType.findPassword, captchaId, captcha))
            return Message.error("shop.captcha.invalid", new Object[0]);
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(email))
            return Message.error("shop.common.invalid", new Object[0]);
        Member member = IIIllllI.findByUsername(username);
        if(member == null)
            return Message.error("shop.password.memberNotExist", new Object[0]);
        if(!member.getEmail().equalsIgnoreCase(email))
        {
            return Message.error("shop.password.invalidEmail", new Object[0]);
        } else
        {
            Setting setting = SettingUtils.get();
            SafeKey safekey = new SafeKey();
            safekey.setValue((new StringBuilder(String.valueOf(UUID.randomUUID().toString()))).append(DigestUtils.md5Hex(RandomStringUtils.randomAlphabetic(30))).toString());
            safekey.setExpire(setting.getSafeKeyExpiryTime().intValue() == 0 ? null : DateUtils.addMinutes(new Date(), setting.getSafeKeyExpiryTime().intValue()));
            member.setSafeKey(safekey);
            IIIllllI.update(member);
            IIIlllll.sendFindPasswordMail(member.getEmail(), member.getUsername(), safekey);
            return Message.success("shop.password.mailSuccess", new Object[0]);
        }
    }

    public String reset(String username, String key, Model model)
    {
        Member member = IIIllllI.findByUsername(username);
        if(member == null)
            return "/shop/common/error";
        SafeKey safekey = member.getSafeKey();
        if(safekey == null || safekey.getValue() == null || !safekey.getValue().equals(key))
            return "/shop/common/error";
        if(safekey.hasExpired())
        {
            model.addAttribute("erroInfo", Message.warn("shop.password.hasExpired", new Object[0]));
            return "/shop/common/error";
        } else
        {
            model.addAttribute("captchaId", UUID.randomUUID().toString());
            model.addAttribute("member", member);
            model.addAttribute("key", key);
            return "/shop/password/reset";
        }
    }

    public Message reset(String captchaId, String captcha, String username, String newPassword, String key)
    {
        if(!IIIlllIl.isValid(net.shopxx.Setting.CaptchaType.resetPassword, captchaId, captcha))
            return Message.error("shop.captcha.invalid", new Object[0]);
        Member member = IIIllllI.findByUsername(username);
        if(member == null)
            return IIIllIll;
        if(!IIIllIlI(net/shopxx/entity/Member, "password", newPassword, new Class[] {
    net/shopxx/entity/BaseEntity$Save
}))
            return Message.warn("shop.password.invalidPassword", new Object[0]);
        Setting setting = SettingUtils.get();
        if(newPassword.length() < setting.getPasswordMinLength().intValue() || newPassword.length() > setting.getPasswordMaxLength().intValue())
            return Message.warn("shop.password.invalidPassword", new Object[0]);
        SafeKey safekey = member.getSafeKey();
        if(safekey == null || safekey.getValue() == null || !safekey.getValue().equals(key))
            return IIIllIll;
        if(safekey.hasExpired())
        {
            return Message.error("shop.password.hasExpired", new Object[0]);
        } else
        {
            member.setPassword(DigestUtils.md5Hex(newPassword));
            safekey.setExpire(new Date());
            safekey.setValue(null);
            IIIllllI.update(member);
            return Message.success("shop.password.resetSuccess", new Object[0]);
        }
    }

    private CaptchaService IIIlllIl;
    private MemberService IIIllllI;
    private MailService IIIlllll;
}
