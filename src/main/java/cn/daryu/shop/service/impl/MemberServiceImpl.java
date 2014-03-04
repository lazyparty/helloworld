// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.LockModeType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.shopxx.*;
import net.shopxx.dao.DepositDao;
import net.shopxx.dao.MemberDao;
import net.shopxx.entity.*;
import net.shopxx.service.MemberService;
import net.shopxx.util.SettingUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class MemberServiceImpl extends BaseServiceImpl
    implements MemberService
{

    public MemberServiceImpl()
    {
    }

    public void setBaseDao(MemberDao memberDao)
    {
        super.setBaseDao(memberDao);
    }

    public boolean usernameExists(String username)
    {
        return IIIllIlI.usernameExists(username);
    }

    public boolean usernameDisabled(String username)
    {
        Assert.hasText(username);
        Setting setting = SettingUtils.get();
        if(setting.getDisabledUsernames() != null)
        {
            String as[];
            int j = (as = setting.getDisabledUsernames()).length;
            for(int i = 0; i < j; i++)
            {
                String s = as[i];
                if(StringUtils.containsIgnoreCase(username, s))
                    return true;
            }

        }
        return false;
    }

    public boolean emailExists(String email)
    {
        return IIIllIlI.emailExists(email);
    }

    public boolean emailUnique(String previousEmail, String currentEmail)
    {
        if(StringUtils.equalsIgnoreCase(previousEmail, currentEmail))
            return true;
        return !IIIllIlI.emailExists(currentEmail);
    }

    public void save(Member member, Admin operator)
    {
        Assert.notNull(member);
        IIIllIlI.persist(member);
        if(member.getBalance().compareTo(new BigDecimal(0)) > 0)
        {
            Deposit deposit = new Deposit();
            deposit.setType(operator == null ? net.shopxx.entity.Deposit.Type.memberRecharge : net.shopxx.entity.Deposit.Type.adminRecharge);
            deposit.setCredit(member.getBalance());
            deposit.setDebit(new BigDecimal(0));
            deposit.setBalance(member.getBalance());
            deposit.setOperator(operator == null ? null : operator.getUsername());
            deposit.setMember(member);
            IIIllIll.persist(deposit);
        }
    }

    public void update(Member member, Integer modifyPoint, BigDecimal modifyBalance, String depositMemo, Admin operator)
    {
        Assert.notNull(member);
        IIIllIlI.lock(member, LockModeType.PESSIMISTIC_WRITE);
        if(modifyPoint != null && modifyPoint.intValue() != 0 && member.getPoint().longValue() + (long)modifyPoint.intValue() >= 0L)
            member.setPoint(Long.valueOf(member.getPoint().longValue() + (long)modifyPoint.intValue()));
        if(modifyBalance != null && modifyBalance.compareTo(new BigDecimal(0)) != 0 && member.getBalance().add(modifyBalance).compareTo(new BigDecimal(0)) >= 0)
        {
            member.setBalance(member.getBalance().add(modifyBalance));
            Deposit deposit = new Deposit();
            if(modifyBalance.compareTo(new BigDecimal(0)) > 0)
            {
                deposit.setType(operator == null ? net.shopxx.entity.Deposit.Type.memberRecharge : net.shopxx.entity.Deposit.Type.adminRecharge);
                deposit.setCredit(modifyBalance);
                deposit.setDebit(new BigDecimal(0));
            } else
            {
                deposit.setType(operator == null ? net.shopxx.entity.Deposit.Type.memberPayment : net.shopxx.entity.Deposit.Type.adminChargeback);
                deposit.setCredit(new BigDecimal(0));
                deposit.setDebit(modifyBalance);
            }
            deposit.setBalance(member.getBalance());
            deposit.setOperator(operator == null ? null : operator.getUsername());
            deposit.setMemo(depositMemo);
            deposit.setMember(member);
            IIIllIll.persist(deposit);
        }
        IIIllIlI.merge(member);
    }

    public Member findByUsername(String username)
    {
        return IIIllIlI.findByUsername(username);
    }

    public List findListByEmail(String email)
    {
        return IIIllIlI.findListByEmail(email);
    }

    public Page findPurchasePage(Date beginDate, Date endDate, Pageable pageable)
    {
        return IIIllIlI.findPurchasePage(beginDate, endDate, pageable);
    }

    public boolean isAuthenticated()
    {
        org.springframework.web.context.request.RequestAttributes requestattributes = RequestContextHolder.currentRequestAttributes();
        if(requestattributes != null)
        {
            HttpServletRequest httpservletrequest = ((ServletRequestAttributes)requestattributes).getRequest();
            Principal principal = (Principal)httpservletrequest.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
            if(principal != null)
                return true;
        }
        return false;
    }

    public Member getCurrent()
    {
        org.springframework.web.context.request.RequestAttributes requestattributes = RequestContextHolder.currentRequestAttributes();
        if(requestattributes != null)
        {
            HttpServletRequest httpservletrequest = ((ServletRequestAttributes)requestattributes).getRequest();
            Principal principal = (Principal)httpservletrequest.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
            if(principal != null)
                return (Member)IIIllIlI.find(principal.getId());
        }
        return null;
    }

    public String getCurrentUsername()
    {
        org.springframework.web.context.request.RequestAttributes requestattributes = RequestContextHolder.currentRequestAttributes();
        if(requestattributes != null)
        {
            HttpServletRequest httpservletrequest = ((ServletRequestAttributes)requestattributes).getRequest();
            Principal principal = (Principal)httpservletrequest.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
            if(principal != null)
                return principal.getUsername();
        }
        return null;
    }

    private MemberDao IIIllIlI;
    private DepositDao IIIllIll;
}
