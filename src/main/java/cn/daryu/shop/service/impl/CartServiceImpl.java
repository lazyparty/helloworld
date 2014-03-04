// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service.impl;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import net.shopxx.Principal;
import net.shopxx.dao.*;
import net.shopxx.entity.*;
import net.shopxx.service.CartService;
import net.shopxx.util.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

// Referenced classes of package net.shopxx.service.impl:
//            BaseServiceImpl

public class CartServiceImpl extends BaseServiceImpl
    implements CartService
{

    public CartServiceImpl()
    {
    }

    public void setBaseDao(CartDao cartDao)
    {
        super.setBaseDao(cartDao);
    }

    public Cart getCurrent()
    {
        org.springframework.web.context.request.RequestAttributes requestattributes = RequestContextHolder.currentRequestAttributes();
        if(requestattributes != null)
        {
            HttpServletRequest httpservletrequest = ((ServletRequestAttributes)requestattributes).getRequest();
            Principal principal = (Principal)httpservletrequest.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
            Member member = principal == null ? null : (Member)IIIlllII.find(principal.getId());
            if(member != null)
            {
                Cart cart = member.getCart();
                if(cart != null)
                {
                    if(!cart.hasExpired())
                    {
                        if(!DateUtils.isSameDay(cart.getModifyDate(), new Date()))
                        {
                            cart.setModifyDate(new Date());
                            IIIllIlI.merge(cart);
                        }
                        return cart;
                    }
                    IIIllIlI.remove(cart);
                }
            } else
            {
                String s = CookieUtils.getCookie(httpservletrequest, "cartId");
                String s1 = CookieUtils.getCookie(httpservletrequest, "cartKey");
                if(StringUtils.isNotEmpty(s) && StringUtils.isNumeric(s) && StringUtils.isNotEmpty(s1))
                {
                    Cart cart1 = (Cart)IIIllIlI.find(Long.valueOf(s));
                    if(cart1 != null && cart1.getMember() == null && StringUtils.equals(cart1.getKey(), s1))
                    {
                        if(!cart1.hasExpired())
                        {
                            if(!DateUtils.isSameDay(cart1.getModifyDate(), new Date()))
                            {
                                cart1.setModifyDate(new Date());
                                IIIllIlI.merge(cart1);
                            }
                            return cart1;
                        }
                        IIIllIlI.remove(cart1);
                    }
                }
            }
        }
        return null;
    }

    public void merge(Member member, Cart cart)
    {
        if(member != null && cart != null && cart.getMember() == null)
        {
            Cart cart1 = member.getCart();
            if(cart1 != null)
            {
                for(Iterator iterator = cart.getCartItems().iterator(); iterator.hasNext();)
                {
                    CartItem cartitem = (CartItem)iterator.next();
                    net.shopxx.entity.Product product = cartitem.getProduct();
                    if(cart1.contains(product))
                    {
                        if(Cart.MAX_PRODUCT_COUNT == null || cart1.getCartItems().size() <= Cart.MAX_PRODUCT_COUNT.intValue())
                        {
                            CartItem cartitem1 = cart1.getCartItem(product);
                            cartitem1.add(cartitem.getQuantity().intValue());
                            IIIllIll.merge(cartitem1);
                        }
                    } else
                    if(Cart.MAX_PRODUCT_COUNT == null || cart1.getCartItems().size() < Cart.MAX_PRODUCT_COUNT.intValue())
                    {
                        iterator.remove();
                        cartitem.setCart(cart1);
                        cart1.getCartItems().add(cartitem);
                        IIIllIll.merge(cartitem);
                    }
                }

                IIIllIlI.remove(cart);
            } else
            {
                member.setCart(cart);
                cart.setMember(member);
                IIIllIlI.merge(cart);
            }
        }
    }

    public void evictExpired()
    {
        IIIllIlI.evictExpired();
    }

    private CartDao IIIllIlI;
    private CartItemDao IIIllIll;
    private MemberDao IIIlllII;
}
