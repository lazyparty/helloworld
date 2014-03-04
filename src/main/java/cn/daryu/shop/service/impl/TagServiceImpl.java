// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.io.Serializable;
import java.util.List;
import net.shopxx.dao.TagDao;
import net.shopxx.entity.Tag;
import net.shopxx.service.TagService;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class TagServiceImpl extends BaseServiceImpl
    implements TagService
{

    public TagServiceImpl()
    {
    }

    public void setBaseDao(TagDao tagDao)
    {
        super.setBaseDao(tagDao);
    }

    public List findList(net.shopxx.entity.Tag.Type type)
    {
        return IIIllIlI.findList(type);
    }

    public List findList(Integer count, List filters, List orders, String cacheRegion)
    {
        return IIIllIlI.findList(null, count, filters, orders);
    }

    public void save(Tag tag)
    {
        super.save(tag);
    }

    public Tag update(Tag tag)
    {
        return (Tag)super.update(tag);
    }

    public transient Tag update(Tag tag, String ignoreProperties[])
    {
        return (Tag)super.update(tag, ignoreProperties);
    }

    public void delete(Long id)
    {
        super.delete(id);
    }

    public transient void delete(Long ids[])
    {
        super.delete(ids);
    }

    public void delete(Tag tag)
    {
        super.delete(tag);
    }

    public volatile void save(Object obj)
    {
        save((Tag)obj);
    }

    public volatile void delete(Object obj)
    {
        delete((Tag)obj);
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
        return update((Tag)obj, as);
    }

    public volatile Object update(Object obj)
    {
        return update((Tag)obj);
    }

    private TagDao IIIllIlI;
}
