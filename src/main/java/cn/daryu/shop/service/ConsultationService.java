// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.util.List;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.entity.*;

// Referenced classes of package net.shopxx.service:
//            BaseService

public interface ConsultationService
    extends BaseService
{

    public abstract List findList(Member member, Product product, Boolean boolean1, Integer integer, List list, List list1);

    public abstract List findList(Member member, Product product, Boolean boolean1, Integer integer, List list, List list1, String s);

    public abstract Page findPage(Member member, Product product, Boolean boolean1, Pageable pageable);

    public abstract Long count(Member member, Product product, Boolean boolean1);

    public abstract void reply(Consultation consultation, Consultation consultation1);
}
