// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.awt.image.BufferedImage;

public interface CaptchaService
{

    public abstract BufferedImage buildImage(String s);

    public abstract boolean isValid(net.shopxx.Setting.CaptchaType captchatype, String s, String s1);
}
