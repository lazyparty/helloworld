// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.*;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import net.shopxx.entity.Area;
import net.shopxx.service.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.ServletContextAware;

public class CommonController
    implements ServletContextAware
{

    public CommonController()
    {
    }

    public void setServletContext(ServletContext servletContext)
    {
        IIlIIlII = servletContext;
    }

    public String main()
    {
        return "/admin/common/main";
    }

    public String index(ModelMap model)
    {
        model.addAttribute("systemName", IIIllIlI);
        model.addAttribute("systemVersion", IIIllIll);
        model.addAttribute("systemDescription", IIIlllII);
        model.addAttribute("systemShowPowered", IIIlllIl);
        model.addAttribute("javaVersion", System.getProperty("java.version"));
        model.addAttribute("javaHome", System.getProperty("java.home"));
        model.addAttribute("osName", System.getProperty("os.name"));
        model.addAttribute("osArch", System.getProperty("os.arch"));
        model.addAttribute("serverInfo", IIlIIlII.getServerInfo());
        model.addAttribute("servletVersion", (new StringBuilder(String.valueOf(IIlIIlII.getMajorVersion()))).append(".").append(IIlIIlII.getMinorVersion()).toString());
        model.addAttribute("waitingPaymentOrderCount", IIlIIIII.waitingPaymentCount(null));
        model.addAttribute("waitingShippingOrderCount", IIlIIIII.waitingShippingCount(null));
        model.addAttribute("marketableProductCount", IIlIIIIl.count(null, Boolean.valueOf(true), null, null, Boolean.valueOf(false), null, null));
        model.addAttribute("notMarketableProductCount", IIlIIIIl.count(null, Boolean.valueOf(false), null, null, Boolean.valueOf(false), null, null));
        model.addAttribute("stockAlertProductCount", IIlIIIIl.count(null, null, null, null, Boolean.valueOf(false), null, Boolean.valueOf(true)));
        model.addAttribute("outOfStockProductCount", IIlIIIIl.count(null, null, null, null, Boolean.valueOf(false), Boolean.valueOf(true), null));
        model.addAttribute("memberCount", Long.valueOf(IIlIIIlI.count()));
        model.addAttribute("unreadMessageCount", IIlIIIll.count(null, Boolean.valueOf(false)));
        return "/admin/common/index";
    }

    public Map area(Long parentId)
    {
        Object obj = new ArrayList();
        Area area1 = (Area)IIIllllI.find(parentId);
        if(area1 != null)
            obj = new ArrayList(area1.getChildren());
        else
            obj = IIIllllI.findRoots();
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
            java.awt.image.BufferedImage bufferedimage = IIIlllll.buildImage(captchaId);
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

    private String IIIllIlI;
    private String IIIllIll;
    private String IIIlllII;
    private Boolean IIIlllIl;
    private AreaService IIIllllI;
    private CaptchaService IIIlllll;
    private OrderService IIlIIIII;
    private ProductService IIlIIIIl;
    private MemberService IIlIIIlI;
    private MessageService IIlIIIll;
    private ServletContext IIlIIlII;
}
