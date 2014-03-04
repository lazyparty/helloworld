// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import com.sun.mail.smtp.SMTPSendFailedException;
import com.sun.mail.smtp.SMTPSenderFailedException;
import java.io.FileOutputStream;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Properties;
import javax.mail.MessagingException;
import net.shopxx.Message;
import net.shopxx.Setting;
import net.shopxx.service.*;
import net.shopxx.util.SettingUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class SettingController extends BaseController
{

    public SettingController()
    {
    }

    public Message mailTest(String smtpFromMail, String smtpHost, Integer smtpPort, String smtpUsername, String smtpPassword, String toMail)
    {
        if(StringUtils.isEmpty(toMail))
            return IIIllIll;
        Setting setting = SettingUtils.get();
        if(StringUtils.isEmpty(smtpPassword))
            smtpPassword = setting.getSmtpPassword();
        try
        {
            if(!IIIllIlI(net/shopxx/Setting, "smtpFromMail", smtpFromMail, new Class[0]) || !IIIllIlI(net/shopxx/Setting, "smtpHost", smtpHost, new Class[0]) || !IIIllIlI(net/shopxx/Setting, "smtpPort", smtpPort, new Class[0]) || !IIIllIlI(net/shopxx/Setting, "smtpUsername", smtpUsername, new Class[0]))
                return IIIllIll;
        }
        catch(MailSendException mailsendexception)
        {
            Exception aexception[] = mailsendexception.getMessageExceptions();
            if(aexception != null)
            {
                Exception aexception1[];
                int j = (aexception1 = aexception).length;
                for(int i = 0; i < j; i++)
                {
                    Exception exception1 = aexception1[i];
                    if(exception1 instanceof SMTPSendFailedException)
                    {
                        SMTPSendFailedException smtpsendfailedexception = (SMTPSendFailedException)exception1;
                        Exception exception2 = smtpsendfailedexception.getNextException();
                        if(exception2 instanceof SMTPSenderFailedException)
                            return Message.error("admin.setting.mailTestSenderFailed", new Object[0]);
                    } else
                    if(exception1 instanceof MessagingException)
                    {
                        MessagingException messagingexception = (MessagingException)exception1;
                        Exception exception3 = messagingexception.getNextException();
                        if(exception3 instanceof UnknownHostException)
                            return Message.error("admin.setting.mailTestUnknownHost", new Object[0]);
                        if(exception3 instanceof ConnectException)
                            return Message.error("admin.setting.mailTestConnect", new Object[0]);
                    }
                }

            }
            return Message.error("admin.setting.mailTestError", new Object[0]);
        }
        catch(MailAuthenticationException mailauthenticationexception)
        {
            return Message.error("admin.setting.mailTestAuthentication", new Object[0]);
        }
        catch(Exception exception)
        {
            return Message.error("admin.setting.mailTestError", new Object[0]);
        }
        IIIllllI.sendTestMail(smtpFromMail, smtpHost, smtpPort, smtpUsername, smtpPassword, toMail);
        return Message.success("admin.setting.mailTestSuccess", new Object[0]);
    }

    public String edit(ModelMap model)
    {
        model.addAttribute("watermarkPositions", net.shopxx.Setting.WatermarkPosition.values());
        model.addAttribute("roundTypes", net.shopxx.Setting.RoundType.values());
        model.addAttribute("captchaTypes", net.shopxx.Setting.CaptchaType.values());
        model.addAttribute("accountLockTypes", net.shopxx.Setting.AccountLockType.values());
        model.addAttribute("stockAllocationTimes", net.shopxx.Setting.StockAllocationTime.values());
        model.addAttribute("reviewAuthorities", net.shopxx.Setting.ReviewAuthority.values());
        model.addAttribute("consultationAuthorities", net.shopxx.Setting.ConsultationAuthority.values());
        return "/admin/setting/edit";
    }

    public String update(Setting setting, MultipartFile watermarkImageFile, RedirectAttributes redirectAttributes)
    {
        FileOutputStream fileoutputstream;
        if(!IIIllIlI(setting, new Class[0]))
            return "/admin/common/error";
        if(setting.getUsernameMinLength().intValue() > setting.getUsernameMaxLength().intValue() || setting.getPasswordMinLength().intValue() > setting.getPasswordMinLength().intValue())
            return "/admin/common/error";
        Setting setting1 = SettingUtils.get();
        if(StringUtils.isEmpty(setting.getSmtpPassword()))
            setting.setSmtpPassword(setting1.getSmtpPassword());
        if(watermarkImageFile != null && !watermarkImageFile.isEmpty())
        {
            if(!IIIlllIl.isValid(net.shopxx.FileInfo.FileType.image, watermarkImageFile))
            {
                IIIllIlI(redirectAttributes, Message.error("admin.upload.invalid", new Object[0]));
                return "redirect:edit.jhtml";
            }
            String s = IIIlllIl.uploadLocal(net.shopxx.FileInfo.FileType.image, watermarkImageFile);
            setting.setWatermarkImage(s);
        } else
        {
            setting.setWatermarkImage(setting1.getWatermarkImage());
        }
        setting.setCnzzSiteId(setting1.getCnzzSiteId());
        setting.setCnzzPassword(setting1.getCnzzPassword());
        SettingUtils.set(setting);
        IIIlllll.clear();
        IIlIIIII.buildIndex();
        IIlIIIII.buildOther();
        fileoutputstream = null;
        try
        {
            ClassPathResource classpathresource = new ClassPathResource("/shopxx.properties");
            Properties properties = PropertiesLoaderUtils.loadProperties(classpathresource);
            String s1 = properties.getProperty("template.update_delay");
            String s2 = properties.getProperty("message.cache_seconds");
            if(setting.getIsDevelopmentEnabled().booleanValue())
            {
                if(!s1.equals("0") || !s2.equals("0"))
                {
                    fileoutputstream = new FileOutputStream(classpathresource.getFile());
                    properties.setProperty("template.update_delay", "0");
                    properties.setProperty("message.cache_seconds", "0");
                    properties.store(fileoutputstream, "SHOP++ PROPERTIES");
                }
            } else
            if(s1.equals("0") || s2.equals("0"))
            {
                fileoutputstream = new FileOutputStream(classpathresource.getFile());
                properties.setProperty("template.update_delay", "3600");
                properties.setProperty("message.cache_seconds", "3600");
                properties.store(fileoutputstream, "SHOP++ PROPERTIES");
            }
            break MISSING_BLOCK_LABEL_416;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        IOUtils.closeQuietly(fileoutputstream);
        break MISSING_BLOCK_LABEL_421;
        Exception exception1;
        exception1;
        IOUtils.closeQuietly(fileoutputstream);
        throw exception1;
        IOUtils.closeQuietly(fileoutputstream);
        IIIllIlI(redirectAttributes, IIIlllII);
        return "redirect:edit.jhtml";
    }

    private FileService IIIlllIl;
    private MailService IIIllllI;
    private CacheService IIIlllll;
    private StaticService IIlIIIII;
}
