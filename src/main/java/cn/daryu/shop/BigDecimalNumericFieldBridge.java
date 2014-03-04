// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx;

import java.math.BigDecimal;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Fieldable;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.builtin.NumericFieldBridge;

public class BigDecimalNumericFieldBridge extends NumericFieldBridge
{

    public BigDecimalNumericFieldBridge()
    {
    }

    public Object get(String name, Document document)
    {
        return new BigDecimal(document.getFieldable(name).stringValue());
    }

    public void set(String name, Object value, Document document, LuceneOptions luceneOptions)
    {
        if(value != null)
        {
            BigDecimal bigdecimal = (BigDecimal)value;
            luceneOptions.addNumericFieldToDocument(name, Double.valueOf(bigdecimal.doubleValue()), document);
        }
    }
}
