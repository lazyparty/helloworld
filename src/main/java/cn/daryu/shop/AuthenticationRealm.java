// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package cn.daryu.shop;

import java.util.*;
import net.shopxx.entity.Admin;
import net.shopxx.service.AdminService;
import net.shopxx.service.CaptchaService;
import net.shopxx.util.SettingUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

// Referenced classes of package net.shopxx:
//            AuthenticationToken, Principal, Setting

public class AuthenticationRealm extends AuthorizingRealm
{

    public AuthenticationRealm()
    {
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
    {
        net.shopxx.AuthenticationToken authenticationtoken = (net.shopxx.AuthenticationToken)token;
        String s = authenticationtoken.getUsername();
        String s1 = new String(authenticationtoken.getPassword());
        String s2 = authenticationtoken.getCaptchaId();
        String s3 = authenticationtoken.getCaptcha();
        String s4 = authenticationtoken.getHost();
        if(!IIIllIlI.isValid(Setting.CaptchaType.adminLogin, s2, s3))
            throw new UnsupportedTokenException();
        if(s != null && s1 != null)
        {
            Admin admin = IIIllIll.findByUsername(s);
            if(admin == null)
                throw new UnknownAccountException();
            if(!admin.getIsEnabled().booleanValue())
                throw new DisabledAccountException();
            Setting setting = SettingUtils.get();
            if(admin.getIsLocked().booleanValue())
                if(ArrayUtils.contains(setting.getAccountLockTypes(), Setting.AccountLockType.admin))
                {
                    int i = setting.getAccountLockTime().intValue();
                    if(i == 0)
                        throw new LockedAccountException();
                    Date date = admin.getLockedDate();
                    Date date1 = DateUtils.addMinutes(date, i);
                    if((new Date()).after(date1))
                    {
                        admin.setLoginFailureCount(Integer.valueOf(0));
                        admin.setIsLocked(Boolean.valueOf(false));
                        admin.setLockedDate(null);
                        IIIllIll.update(admin);
                    } else
                    {
                        throw new LockedAccountException();
                    }
                } else
                {
                    admin.setLoginFailureCount(Integer.valueOf(0));
                    admin.setIsLocked(Boolean.valueOf(false));
                    admin.setLockedDate(null);
                    IIIllIll.update(admin);
                }
            if(!DigestUtils.md5Hex(s1).equals(admin.getPassword()))
            {
                int j = admin.getLoginFailureCount().intValue() + 1;
                if(j >= setting.getAccountLockCount().intValue())
                {
                    admin.setIsLocked(Boolean.valueOf(true));
                    admin.setLockedDate(new Date());
                }
                admin.setLoginFailureCount(Integer.valueOf(j));
                IIIllIll.update(admin);
                throw new IncorrectCredentialsException();
            } else
            {
                admin.setLoginIp(s4);
                admin.setLoginDate(new Date());
                admin.setLoginFailureCount(Integer.valueOf(0));
                IIIllIll.update(admin);
                return new SimpleAuthenticationInfo(new Principal(admin.getId(), s), s1, getName());
            }
        } else
        {
            throw new UnknownAccountException();
        }
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        Principal principal = (Principal)principals.fromRealm(getName()).iterator().next();
        if(principal != null)
        {
            java.util.List list = IIIllIll.findAuthorities(principal.getId());
            if(list != null)
            {
                SimpleAuthorizationInfo simpleauthorizationinfo = new SimpleAuthorizationInfo();
                simpleauthorizationinfo.addStringPermissions(list);
                return simpleauthorizationinfo;
            }
        }
        return null;
    }

    private CaptchaService IIIllIlI;
    private AdminService IIIllIll;
}
