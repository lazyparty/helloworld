// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx;

import java.beans.PropertyEditorSupport;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class HtmlCleanEditor extends PropertyEditorSupport
{

    public HtmlCleanEditor(boolean trim, boolean emptyAsNull)
    {
        IIIlllII = Whitelist.none();
        IIIllIlI = trim;
        IIIllIll = emptyAsNull;
    }

    public HtmlCleanEditor(boolean trim, boolean emptyAsNull, Whitelist whitelist)
    {
        IIIlllII = Whitelist.none();
        IIIllIlI = trim;
        IIIllIll = emptyAsNull;
        IIIlllII = whitelist;
    }

    public String getAsText()
    {
        Object obj = getValue();
        return obj == null ? "" : obj.toString();
    }

    public void setAsText(String text)
    {
        if(text != null)
        {
            String s = IIIllIlI ? text.trim() : text;
            s = Jsoup.clean(s, IIIlllII);
            if(IIIllIll && "".equals(s))
                s = null;
            setValue(s);
        } else
        {
            setValue(null);
        }
    }

    private boolean IIIllIlI;
    private boolean IIIllIll;
    private Whitelist IIIlllII;
}
