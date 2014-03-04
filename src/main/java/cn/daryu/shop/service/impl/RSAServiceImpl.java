// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.shopxx.service.RSAService;
import net.shopxx.util.RSAUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

public class RSAServiceImpl
    implements RSAService
{

    public RSAServiceImpl()
    {
    }

    public RSAPublicKey generateKey(HttpServletRequest request)
    {
        Assert.notNull(request);
        KeyPair keypair = RSAUtils.generateKeyPair();
        RSAPublicKey rsapublickey = (RSAPublicKey)keypair.getPublic();
        RSAPrivateKey rsaprivatekey = (RSAPrivateKey)keypair.getPrivate();
        HttpSession httpsession = request.getSession();
        httpsession.setAttribute("privateKey", rsaprivatekey);
        return rsapublickey;
    }

    public void removePrivateKey(HttpServletRequest request)
    {
        Assert.notNull(request);
        HttpSession httpsession = request.getSession();
        httpsession.removeAttribute("privateKey");
    }

    public String decryptParameter(String name, HttpServletRequest request)
    {
        Assert.notNull(request);
        if(name != null)
        {
            HttpSession httpsession = request.getSession();
            RSAPrivateKey rsaprivatekey = (RSAPrivateKey)httpsession.getAttribute("privateKey");
            String s = request.getParameter(name);
            if(rsaprivatekey != null && StringUtils.isNotEmpty(s))
                return RSAUtils.decrypt(rsaprivatekey, s);
        }
        return null;
    }

    private static final String IIIllIlI = "privateKey";
}
