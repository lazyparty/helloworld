// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx;

import org.apache.shiro.authc.UsernamePasswordToken;

public class AuthenticationToken extends UsernamePasswordToken
{

    public AuthenticationToken(String username, String password, String captchaId, String captcha, boolean rememberMe, String host)
    {
        super(username, password, rememberMe);
        IIIllIlI = captchaId;
        IIIllIll = captcha;
    }

    public String getCaptchaId()
    {
        return IIIllIlI;
    }

    public void setCaptchaId(String captchaId)
    {
        IIIllIlI = captchaId;
    }

    public String getCaptcha()
    {
        return IIIllIll;
    }

    public void setCaptcha(String captcha)
    {
        IIIllIll = captcha;
    }

    private static final long serialVersionUID = 0x51db795489a1b546L;
    private String IIIllIlI;
    private String IIIllIll;
}
