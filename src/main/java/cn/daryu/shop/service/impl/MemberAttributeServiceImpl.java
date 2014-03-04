// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import java.util.List;
import net.shopxx.dao.MemberAttributeDao;
import net.shopxx.entity.MemberAttribute;
import net.shopxx.service.MemberAttributeService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class MemberAttributeServiceImpl extends BaseServiceImpl
    implements MemberAttributeService
{

    public MemberAttributeServiceImpl()
    {
    }

    public void setBaseDao(MemberAttributeDao memberAttributeDao)
    {
        super.setBaseDao(memberAttributeDao);
    }

    public Integer findUnusedPropertyIndex()
    {
        return IIIllIlI.findUnusedPropertyIndex();
    }

    public List findList()
    {
        return IIIllIlI.findList();
    }

    public List findList(String cacheRegion)
    {
        return IIIllIlI.findList();
    }

    public void save(MemberAttribute memberAttribute)
    {
        super.save(memberAttribute);
    }

    public MemberAttribute update(MemberAttribute memberAttribute)
    {
        return (MemberAttribute)super.update(memberAttribute);
    }

    public transient MemberAttribute update(MemberAttribute memberAttribute, String ignoreProperties[])
    {
        return (MemberAttribute)super.update(memberAttribute, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(MemberAttribute memberAttribute)
    {
        super.delete(memberAttribute);
    }

    public volatile void save(Object obj)
    {
        save((MemberAttribute)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((MemberAttribute)obj);
    }

    public volatile transient void delete(Serializable aserializable[])
    {
        delete((Long[])aserializable);
    }

    public volatile void delete(Serializable serializable)
    {
        delete((Long)serializable);
    }

    public volatile transient Object update(Object obj, String as[])
    {
        return update((MemberAttribute)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((MemberAttribute)obj);
    }

    private MemberAttributeDao IIIllIlI;
}
