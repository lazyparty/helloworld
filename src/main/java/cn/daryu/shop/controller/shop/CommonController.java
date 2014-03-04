// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;
import java.util.*;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import net.shopxx.Setting;
import net.shopxx.entity.Area;
import net.shopxx.service.*;
import net.shopxx.util.SettingUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class CommonController
{

    public CommonController()
    {
    }

    public String resourceNotFound()
    {
        return "/shop/common/resource_not_found";
    }

    public String error()
    {
        return "/shop/common/error";
    }

    public String siteClose()
    {
        Setting setting = SettingUtils.get();
        if(setting.getIsSiteEnabled().booleanValue())
            return "redirect:/";
        else
            return "/shop/common/site_close";
    }

    public Map publicKey(HttpServletRequest request)
    {
        RSAPublicKey rsapublickey = IIIllIlI.generateKey(request);
        HashMap hashmap = new HashMap();
        hashmap.put("modulus", Base64.encodeBase64String(rsapublickey.getModulus().toByteArray()));
        hashmap.put("exponent", Base64.encodeBase64String(rsapublickey.getPublicExponent().toByteArray()));
        return hashmap;
    }

    public Map area(Long parentId)
    {
        Object obj = new ArrayList();
        Area area1 = (Area)IIIllIll.find(parentId);
        if(area1 != null)
            obj = new ArrayList(area1.getChildren());
        else
            obj = IIIllIll.findRoots();
        HashMap hashmap = new HashMap();
        Area area2;
        for(Iterator iterator = ((List) (obj)).iterator(); iterator.hasNext(); hashmap.put(area2.getId(), area2.getName()))
            area2 = (Area)iterator.next();

        return hashmap;
    }

    public void image(String captchaId, HttpServletRequest request, HttpServletResponse response)
    {
        ServletOutputStream servletoutputstream;
        if(StringUtils.isEmpty(captchaId))
            captchaId = request.getSession().getId();
        String s = (new StringBuffer()).append("yB").append("-").append("der").append("ewoP").reverse().toString();
        String s1 = (new StringBuffer()).append("ten").append(".").append("xxp").append("ohs").reverse().toString();
        response.addHeader(s, s1);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0L);
        response.setContentType("image/jpeg");
        servletoutputstream = null;
        try
        {
            servletoutputstream = response.getOutputStream();
            java.awt.image.BufferedImage bufferedimage = IIIlllII.buildImage(captchaId);
            ImageIO.write(bufferedimage, "jpg", servletoutputstream);
            servletoutputstream.flush();
            break MISSING_BLOCK_LABEL_212;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        IOUtils.closeQuietly(servletoutputstream);
        break MISSING_BLOCK_LABEL_217;
        Exception exception1;
        exception1;
        IOUtils.closeQuietly(servletoutputstream);
        throw exception1;
        IOUtils.closeQuietly(servletoutputstream);
    }

    private RSAService IIIllIlI;
    private AreaService IIIllIll;
    private CaptchaService IIIlllII;
}
