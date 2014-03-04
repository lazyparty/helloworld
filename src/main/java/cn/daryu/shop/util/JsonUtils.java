// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.util.Assert;

public final class JsonUtils
{

    private JsonUtils()
    {
    }

    public static String toJson(Object value)
    {
        try
        {
            return IIIllIlI.writeValueAsString(value);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    public static void toJson(HttpServletResponse response, String contentType, Object value)
    {
        Assert.notNull(response);
        Assert.hasText(contentType);
        try
        {
            response.setContentType(contentType);
            IIIllIlI.writeValue(response.getWriter(), value);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static void toJson(HttpServletResponse response, Object value)
    {
        PrintWriter printwriter;
        Assert.notNull(response);
        printwriter = null;
        try
        {
            printwriter = response.getWriter();
            IIIllIlI.writeValue(printwriter, value);
            printwriter.flush();
            break MISSING_BLOCK_LABEL_49;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        IOUtils.closeQuietly(printwriter);
        break MISSING_BLOCK_LABEL_53;
        Exception exception1;
        exception1;
        IOUtils.closeQuietly(printwriter);
        throw exception1;
        IOUtils.closeQuietly(printwriter);
    }

    public static Object toObject(String json, Class valueType)
    {
        Assert.hasText(json);
        Assert.notNull(valueType);
        try
        {
            return IIIllIlI.readValue(json, valueType);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    public static Object toObject(String json, TypeReference typeReference)
    {
        Assert.hasText(json);
        Assert.notNull(typeReference);
        try
        {
            return IIIllIlI.readValue(json, typeReference);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    public static Object toObject(String json, JavaType javaType)
    {
        Assert.hasText(json);
        Assert.notNull(javaType);
        try
        {
            return IIIllIlI.readValue(json, javaType);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    private static ObjectMapper IIIllIlI = new ObjectMapper();

}
