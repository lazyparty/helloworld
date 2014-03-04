// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import java.util.List;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.ConsultationDao;
import net.shopxx.entity.*;
import net.shopxx.service.ConsultationService;
import net.shopxx.service.StaticService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class ConsultationServiceImpl extends BaseServiceImpl
    implements ConsultationService
{

    public ConsultationServiceImpl()
    {
    }

    public void setBaseDao(ConsultationDao consultationDao)
    {
        super.setBaseDao(consultationDao);
    }

    public List findList(Member member, Product product, Boolean isShow, Integer count, List filters, List orders)
    {
        return IIIllIlI.findList(member, product, isShow, count, filters, orders);
    }

    public List findList(Member member, Product product, Boolean isShow, Integer count, List filters, List orders, String cacheRegion)
    {
        return IIIllIlI.findList(member, product, isShow, count, filters, orders);
    }

    public Page findPage(Member member, Product product, Boolean isShow, Pageable pageable)
    {
        return IIIllIlI.findPage(member, product, isShow, pageable);
    }

    public Long count(Member member, Product product, Boolean isShow)
    {
        return IIIllIlI.count(member, product, isShow);
    }

    public void reply(Consultation consultation, Consultation replyConsultation)
    {
        if(consultation == null || replyConsultation == null)
            return;
        consultation.setIsShow(Boolean.valueOf(true));
        IIIllIlI.merge(consultation);
        replyConsultation.setIsShow(Boolean.valueOf(true));
        replyConsultation.setProduct(consultation.getProduct());
        replyConsultation.setForConsultation(consultation);
        IIIllIlI.persist(replyConsultation);
        Product product = consultation.getProduct();
        if(product != null)
        {
            IIIllIlI.flush();
            IIIllIll.build(product);
        }
    }

    public void save(Consultation consultation)
    {
        super.save(consultation);
        Product product = consultation.getProduct();
        if(product != null)
        {
            IIIllIlI.flush();
            IIIllIll.build(product);
        }
    }

    public Consultation update(Consultation consultation)
    {
        Consultation consultation1 = (Consultation)super.update(consultation);
        Product product = consultation1.getProduct();
        if(product != null)
        {
            IIIllIlI.flush();
            IIIllIll.build(product);
        }
        return consultation1;
    }

    public transient Consultation update(Consultation consultation, String ignoreProperties[])
    {
        return (Consultation)super.update(consultation, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(Consultation consultation)
    {
        if(consultation != null)
        {
            super.delete(consultation);
            Product product = consultation.getProduct();
            if(product != null)
            {
                IIIllIlI.flush();
                IIIllIll.build(product);
            }
        }
    }

    public volatile void save(Object obj)
    {
        save((Consultation)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((Consultation)obj);
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
        return update((Consultation)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((Consultation)obj);
    }

    private ConsultationDao IIIllIlI;
    private StaticService IIIllIll;
}
