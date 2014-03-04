// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.util;

import java.security.*;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.Assert;

public final class RSAUtils
{

    private RSAUtils()
    {
    }

    public static KeyPair generateKeyPair()
    {
        try
        {
            KeyPairGenerator keypairgenerator = KeyPairGenerator.getInstance("RSA", IIIllIlI);
            keypairgenerator.initialize(1024, new SecureRandom());
            return keypairgenerator.generateKeyPair();
        }
        catch(NoSuchAlgorithmException nosuchalgorithmexception)
        {
            nosuchalgorithmexception.printStackTrace();
        }
        return null;
    }

    public static byte[] encrypt(PublicKey publicKey, byte data[])
    {
        Assert.notNull(publicKey);
        Assert.notNull(data);
        try
        {
            Cipher cipher = Cipher.getInstance("RSA", IIIllIlI);
            cipher.init(1, publicKey);
            return cipher.doFinal(data);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    public static String encrypt(PublicKey publicKey, String text)
    {
        Assert.notNull(publicKey);
        Assert.notNull(text);
        byte abyte0[] = encrypt(publicKey, text.getBytes());
        return abyte0 == null ? null : Base64.encodeBase64String(abyte0);
    }

    public static byte[] decrypt(PrivateKey privateKey, byte data[])
    {
        Assert.notNull(privateKey);
        Assert.notNull(data);
        try
        {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", IIIllIlI);
            cipher.init(2, privateKey);
            return cipher.doFinal(data);
        }
        catch(Exception exception)
        {
            return null;
        }
    }

    public static String decrypt(PrivateKey privateKey, String text)
    {
        Assert.notNull(privateKey);
        Assert.notNull(text);
        byte abyte0[] = decrypt(privateKey, Base64.decodeBase64(text));
        return abyte0 == null ? null : new String(abyte0);
    }

    private static final Provider IIIllIlI = new BouncyCastleProvider();
    private static final int IIIllIll = 1024;

}
