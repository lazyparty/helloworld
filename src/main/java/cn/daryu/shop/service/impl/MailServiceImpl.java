// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import net.shopxx.Setting;
import net.shopxx.Template;
import net.shopxx.entity.ProductNotify;
import net.shopxx.entity.SafeKey;
import net.shopxx.service.MailService;
import net.shopxx.service.TemplateService;
import net.shopxx.util.SettingUtils;
import net.shopxx.util.SpringUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

public class MailServiceImpl
    implements MailService
{

    public MailServiceImpl()
    {
    }

    private void IIIllIlI(MimeMessage mimemessage)
    {
        try
        {
            IIIlllII.execute(new _cls1(mimemessage));
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void send(String smtpFromMail, String smtpHost, Integer smtpPort, String smtpUsername, String smtpPassword, String toMail, String subject, 
            String templatePath, Map model, boolean async)
    {
        Assert.hasText(smtpFromMail);
        Assert.hasText(smtpHost);
        Assert.notNull(smtpPort);
        Assert.hasText(smtpUsername);
        Assert.hasText(smtpPassword);
        Assert.hasText(toMail);
        Assert.hasText(subject);
        Assert.hasText(templatePath);
        try
        {
            Setting setting = SettingUtils.get();
            Configuration configuration = IIIllIlI.getConfiguration();
            freemarker.template.Template template = configuration.getTemplate(templatePath);
            String s = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            IIIllIll.setHost(smtpHost);
            IIIllIll.setPort(smtpPort.intValue());
            IIIllIll.setUsername(smtpUsername);
            IIIllIll.setPassword(smtpPassword);
            MimeMessage mimemessage = IIIllIll.createMimeMessage();
            MimeMessageHelper mimemessagehelper = new MimeMessageHelper(mimemessage, false, "utf-8");
            mimemessagehelper.setFrom((new StringBuilder(String.valueOf(MimeUtility.encodeWord(setting.getSiteName())))).append(" <").append(smtpFromMail).append(">").toString());
            mimemessagehelper.setSubject(subject);
            mimemessagehelper.setTo(toMail);
            mimemessagehelper.setText(s, true);
            if(async)
                IIIllIlI(mimemessage);
            else
                IIIllIll.send(mimemessage);
        }
        catch(TemplateException templateexception)
        {
            templateexception.printStackTrace();
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        catch(MessagingException messagingexception)
        {
            messagingexception.printStackTrace();
        }
    }

    public void send(String toMail, String subject, String templatePath, Map model, boolean async)
    {
        Setting setting = SettingUtils.get();
        send(setting.getSmtpFromMail(), setting.getSmtpHost(), setting.getSmtpPort(), setting.getSmtpUsername(), setting.getSmtpPassword(), toMail, subject, templatePath, model, async);
    }

    public void send(String toMail, String subject, String templatePath, Map model)
    {
        Setting setting = SettingUtils.get();
        send(setting.getSmtpFromMail(), setting.getSmtpHost(), setting.getSmtpPort(), setting.getSmtpUsername(), setting.getSmtpPassword(), toMail, subject, templatePath, model, true);
    }

    public void send(String toMail, String subject, String templatePath)
    {
        Setting setting = SettingUtils.get();
        send(setting.getSmtpFromMail(), setting.getSmtpHost(), setting.getSmtpPort(), setting.getSmtpUsername(), setting.getSmtpPassword(), toMail, subject, templatePath, null, true);
    }

    public void sendTestMail(String smtpFromMail, String smtpHost, Integer smtpPort, String smtpUsername, String smtpPassword, String toMail)
    {
        Setting setting = SettingUtils.get();
        String s = SpringUtils.getMessage("admin.setting.testMailSubject", new Object[] {
            setting.getSiteName()
        });
        Template template = IIIlllIl.get("testMail");
        send(smtpFromMail, smtpHost, smtpPort, smtpUsername, smtpPassword, toMail, s, template.getTemplatePath(), null, false);
    }

    public void sendFindPasswordMail(String toMail, String username, SafeKey safeKey)
    {
        Setting setting = SettingUtils.get();
        HashMap hashmap = new HashMap();
        hashmap.put("username", username);
        hashmap.put("safeKey", safeKey);
        String s = SpringUtils.getMessage("shop.password.mailSubject", new Object[] {
            setting.getSiteName()
        });
        Template template = IIIlllIl.get("findPasswordMail");
        send(toMail, s, template.getTemplatePath(), hashmap);
    }

    public void sendProductNotifyMail(ProductNotify productNotify)
    {
        Setting setting = SettingUtils.get();
        HashMap hashmap = new HashMap();
        hashmap.put("productNotify", productNotify);
        String s = SpringUtils.getMessage("admin.productNotify.mailSubject", new Object[] {
            setting.getSiteName()
        });
        Template template = IIIlllIl.get("productNotifyMail");
        send(productNotify.getEmail(), s, template.getTemplatePath(), hashmap);
    }

    static JavaMailSenderImpl IIIllIlI(MailServiceImpl mailserviceimpl)
    {
        return mailserviceimpl.IIIllIll;
    }

    private FreeMarkerConfigurer IIIllIlI;
    private JavaMailSenderImpl IIIllIll;
    private TaskExecutor IIIlllII;
    private TemplateService IIIlllIl;

    private class _cls1
        implements Runnable
    {

        public void run()
        {
            MailServiceImpl.IIIllIlI(IIIllIlI).send(IIIllIll);
        }

        final MailServiceImpl IIIllIlI;
        private final MimeMessage IIIllIll;

        _cls1(MimeMessage mimemessage)
        {
            IIIllIlI = MailServiceImpl.this;
            IIIllIll = mimemessage;
            super();
        }
    }

}
