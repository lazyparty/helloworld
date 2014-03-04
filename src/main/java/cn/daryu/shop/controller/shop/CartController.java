// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.shopxx.Message;
import net.shopxx.entity.*;
import net.shopxx.service.*;
import net.shopxx.util.CookieUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.shop:
//            BaseController

public class CartController extends BaseController
{

    public CartController()
    {
    }

    public Message add(Long id, Integer quantity, HttpServletRequest request, HttpServletResponse response)
    {
        if(quantity == null || quantity.intValue() < 1)
            return IIIllIll;
        Product product = (Product)IIIllllI.find(id);
        if(product == null)
            return Message.warn("shop.cart.productNotExsit", new Object[0]);
        if(!product.getIsMarketable().booleanValue())
            return Message.warn("shop.cart.productNotMarketable", new Object[0]);
        if(product.getIsGift().booleanValue())
            return Message.warn("shop.cart.notForSale", new Object[0]);
        Cart cart = IIIlllll.getCurrent();
        net.shopxx.entity.Member member = IIIlllIl.getCurrent();
        if(cart == null)
        {
            cart = new Cart();
            cart.setKey((new StringBuilder(String.valueOf(UUID.randomUUID().toString()))).append(DigestUtils.md5Hex(RandomStringUtils.randomAlphabetic(30))).toString());
            cart.setMember(member);
            IIIlllll.save(cart);
        }
        if(Cart.MAX_PRODUCT_COUNT != null && cart.getCartItems().size() >= Cart.MAX_PRODUCT_COUNT.intValue())
            return Message.warn("shop.cart.addCountNotAllowed", new Object[] {
                Cart.MAX_PRODUCT_COUNT
            });
        if(cart.contains(product))
        {
            CartItem cartitem = cart.getCartItem(product);
            if(CartItem.MAX_QUANTITY != null && cartitem.getQuantity().intValue() + quantity.intValue() > CartItem.MAX_QUANTITY.intValue())
                return Message.warn("shop.cart.maxCartItemQuantity", new Object[] {
                    CartItem.MAX_QUANTITY
                });
            if(product.getStock() != null && cartitem.getQuantity().intValue() + quantity.intValue() > product.getAvailableStock().intValue())
                return Message.warn("shop.cart.productLowStock", new Object[0]);
            cartitem.add(quantity.intValue());
            IIlIIIII.update(cartitem);
        } else
        {
            if(CartItem.MAX_QUANTITY != null && quantity.intValue() > CartItem.MAX_QUANTITY.intValue())
                return Message.warn("shop.cart.maxCartItemQuantity", new Object[] {
                    CartItem.MAX_QUANTITY
                });
            if(product.getStock() != null && quantity.intValue() > product.getAvailableStock().intValue())
                return Message.warn("shop.cart.productLowStock", new Object[0]);
            CartItem cartitem1 = new CartItem();
            cartitem1.setQuantity(quantity);
            cartitem1.setProduct(product);
            cartitem1.setCart(cart);
            IIlIIIII.save(cartitem1);
            cart.getCartItems().add(cartitem1);
        }
        if(member == null)
        {
            CookieUtils.addCookie(request, response, "cartId", cart.getId().toString(), Integer.valueOf(0x93a80));
            CookieUtils.addCookie(request, response, "cartKey", cart.getKey(), Integer.valueOf(0x93a80));
        }
        return Message.success("shop.cart.addSuccess", new Object[] {
            Integer.valueOf(cart.getQuantity()), IIIllIlI(cart.getAmount(), true, false)
        });
    }

    public String list(ModelMap model)
    {
        model.addAttribute("cart", IIIlllll.getCurrent());
        return "/shop/cart/list";
    }

    public Map edit(Long id, Integer quantity)
    {
        HashMap hashmap = new HashMap();
        if(quantity == null || quantity.intValue() < 1)
        {
            hashmap.put("message", IIIllIll);
            return hashmap;
        }
        Cart cart = IIIlllll.getCurrent();
        if(cart == null || cart.isEmpty())
        {
            hashmap.put("message", Message.error("shop.cart.notEmpty", new Object[0]));
            return hashmap;
        }
        CartItem cartitem = (CartItem)IIlIIIII.find(id);
        Set set = cart.getCartItems();
        if(cartitem == null || set == null || !set.contains(cartitem))
        {
            hashmap.put("message", Message.error("shop.cart.cartItemNotExsit", new Object[0]));
            return hashmap;
        }
        if(CartItem.MAX_QUANTITY != null && quantity.intValue() > CartItem.MAX_QUANTITY.intValue())
        {
            hashmap.put("message", Message.warn("shop.cart.maxCartItemQuantity", new Object[] {
                CartItem.MAX_QUANTITY
            }));
            return hashmap;
        }
        Product product = cartitem.getProduct();
        if(product.getStock() != null && quantity.intValue() > product.getAvailableStock().intValue())
        {
            hashmap.put("message", Message.warn("shop.cart.productLowStock", new Object[0]));
            return hashmap;
        } else
        {
            cartitem.setQuantity(quantity);
            IIlIIIII.update(cartitem);
            hashmap.put("message", IIIlllII);
            hashmap.put("subtotal", cartitem.getSubtotal());
            hashmap.put("isLowStock", Boolean.valueOf(cartitem.getIsLowStock()));
            hashmap.put("quantity", Integer.valueOf(cart.getQuantity()));
            hashmap.put("point", Integer.valueOf(cart.getPoint()));
            hashmap.put("amount", cart.getAmount());
            hashmap.put("promotions", cart.getPromotions());
            hashmap.put("giftItems", cart.getGiftItems());
            return hashmap;
        }
    }

    public Map delete(Long id)
    {
        HashMap hashmap = new HashMap();
        Cart cart = IIIlllll.getCurrent();
        if(cart == null || cart.isEmpty())
        {
            hashmap.put("message", Message.error("shop.cart.notEmpty", new Object[0]));
            return hashmap;
        }
        CartItem cartitem = (CartItem)IIlIIIII.find(id);
        Set set = cart.getCartItems();
        if(cartitem == null || set == null || !set.contains(cartitem))
        {
            hashmap.put("message", Message.error("shop.cart.cartItemNotExsit", new Object[0]));
            return hashmap;
        } else
        {
            set.remove(cartitem);
            IIlIIIII.delete(cartitem);
            hashmap.put("message", IIIlllII);
            hashmap.put("quantity", Integer.valueOf(cart.getQuantity()));
            hashmap.put("point", Integer.valueOf(cart.getPoint()));
            hashmap.put("amount", cart.getAmount());
            hashmap.put("promotions", cart.getPromotions());
            hashmap.put("isLowStock", Boolean.valueOf(cart.getIsLowStock()));
            return hashmap;
        }
    }

    public Message clear()
    {
        Cart cart = IIIlllll.getCurrent();
        IIIlllll.delete(cart);
        return IIIlllII;
    }

    private MemberService IIIlllIl;
    private ProductService IIIllllI;
    private CartService IIIlllll;
    private CartItemService IIlIIIII;
}
