// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import java.util.HashMap;
import java.util.Map;
import net.shopxx.Message;
import net.shopxx.entity.*;
import net.shopxx.service.*;

// Referenced classes of package net.shopxx.controller.shop:
//            BaseController

public class ProductNotifyController extends BaseController
{

    public ProductNotifyController()
    {
    }

    public Map email()
    {
        Member member = IIIllllI.getCurrent();
        String s = member == null ? null : member.getEmail();
        HashMap hashmap = new HashMap();
        hashmap.put("email", s);
        return hashmap;
    }

    public Map save(String email, Long productId)
    {
        HashMap hashmap = new HashMap();
        if(!IIIllIlI(net/shopxx/entity/ProductNotify, "email", email, new Class[0]))
        {
            hashmap.put("message", IIIllIll);
            return hashmap;
        }
        Product product = (Product)IIIlllll.find(productId);
        if(product == null)
        {
            hashmap.put("message", Message.warn("shop.productNotify.productNotExist", new Object[0]));
            return hashmap;
        }
        if(!product.getIsMarketable().booleanValue())
        {
            hashmap.put("message", Message.warn("shop.productNotify.productNotMarketable", new Object[0]));
            return hashmap;
        }
        if(!product.getIsOutOfStock().booleanValue())
            hashmap.put("message", Message.warn("shop.productNotify.productInStock", new Object[0]));
        if(IIIlllIl.exists(product, email))
        {
            hashmap.put("message", Message.warn("shop.productNotify.exist", new Object[0]));
        } else
        {
            ProductNotify productnotify = new ProductNotify();
            productnotify.setEmail(email);
            productnotify.setHasSent(Boolean.valueOf(false));
            productnotify.setMember(IIIllllI.getCurrent());
            productnotify.setProduct(product);
            IIIlllIl.save(productnotify);
            hashmap.put("message", IIIlllII);
        }
        return hashmap;
    }

    private ProductNotifyService IIIlllIl;
    private MemberService IIIllllI;
    private ProductService IIIlllll;
}
