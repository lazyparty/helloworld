// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.time.DateUtils;

// Referenced classes of package net.shopxx:
//            CommonAttributes

public class DateEditor extends PropertyEditorSupport
{

    public DateEditor(boolean emptyAsNull)
    {
        IIIlllII = "yyyy-MM-dd HH:mm:ss";
        IIIllIll = emptyAsNull;
    }

    public DateEditor(boolean emptyAsNull, String dateFormat)
    {
        IIIlllII = "yyyy-MM-dd HH:mm:ss";
        IIIllIll = emptyAsNull;
        IIIlllII = dateFormat;
    }

    public String getAsText()
    {
        Date date = (Date)getValue();
        return date == null ? "" : (new SimpleDateFormat(IIIlllII)).format(date);
    }

    public void setAsText(String text)
    {
        if(text == null)
        {
            setValue(null);
        } else
        {
            String s = text.trim();
            if(IIIllIll && "".equals(s))
                setValue(null);
            else
                try
                {
                    setValue(DateUtils.parseDate(s, CommonAttributes.DATE_PATTERNS));
                }
                catch(ParseException parseexception)
                {
                    setValue(null);
                }
        }
    }

    private static final String IIIllIlI = "yyyy-MM-dd HH:mm:ss";
    private boolean IIIllIll;
    private String IIIlllII;
}
