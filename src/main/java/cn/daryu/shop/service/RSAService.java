// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.security.interfaces.RSAPublicKey;
import javax.servlet.http.HttpServletRequest;

public interface RSAService
{

    public abstract RSAPublicKey generateKey(HttpServletRequest httpservletrequest);

    public abstract void removePrivateKey(HttpServletRequest httpservletrequest);

    public abstract String decryptParameter(String s, HttpServletRequest httpservletrequest);
}
