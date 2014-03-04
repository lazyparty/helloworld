// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.util.List;
import javax.persistence.LockModeType;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.CouponCodeDao;
import net.shopxx.dao.MemberDao;
import net.shopxx.entity.*;
import net.shopxx.service.CouponCodeService;
import org.springframework.util.Assert;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class CouponCodeServiceImpl extends BaseServiceImpl
    implements CouponCodeService
{

    public CouponCodeServiceImpl()
    {
    }

    public void setBaseDao(CouponCodeDao couponCodeDao)
    {
        super.setBaseDao(couponCodeDao);
    }

    public boolean codeExists(String code)
    {
        return IIIllIlI.codeExists(code);
    }

    public CouponCode findByCode(String code)
    {
        return IIIllIlI.findByCode(code);
    }

    public CouponCode build(Coupon coupon, Member member)
    {
        return IIIllIlI.build(coupon, member);
    }

    public List build(Coupon coupon, Member member, Integer count)
    {
        return IIIllIlI.build(coupon, member, count);
    }

    public CouponCode exchange(Coupon coupon, Member member)
    {
        Assert.notNull(coupon);
        Assert.notNull(member);
        IIIllIll.lock(member, LockModeType.PESSIMISTIC_WRITE);
        member.setPoint(Long.valueOf(member.getPoint().longValue() - (long)coupon.getPoint().intValue()));
        IIIllIll.merge(member);
        return IIIllIlI.build(coupon, member);
    }

    public Page findPage(Member member, Pageable pageable)
    {
        return IIIllIlI.findPage(member, pageable);
    }

    public Long count(Coupon coupon, Member member, Boolean hasBegun, Boolean hasExpired, Boolean isUsed)
    {
        return IIIllIlI.count(coupon, member, hasBegun, hasExpired, isUsed);
    }

    private CouponCodeDao IIIllIlI;
    private MemberDao IIIllIll;
}
