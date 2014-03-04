// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.util.Map;
import net.shopxx.entity.Shipping;

// Referenced classes of package net.shopxx.service:
//            BaseService

public interface ShippingService
    extends BaseService
{

    public abstract Shipping findBySn(String s);

    public abstract Map query(Shipping shipping);
}
