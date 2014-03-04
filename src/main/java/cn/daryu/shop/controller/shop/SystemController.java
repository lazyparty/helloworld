// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.shop;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

public class SystemController
{

    public SystemController()
    {
    }

    public void info(HttpServletRequest request, HttpServletResponse response)
    {
        StringBuffer stringbuffer1;
        PrintWriter printwriter;
        String s = " 0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ().+/;=-";
        int ai[] = {
            30, 15, 34, 30, 67, 26, 22, 11, 19, 24, 
            68, 13, 18, 11, 28, 29, 15, 30, 69, 57, 
            56, 42, 70, 9
        };
        StringBuffer stringbuffer = new StringBuffer();
        int ai2[];
        int k = (ai2 = ai).length;
        for(int j = 0; j < k; j++)
        {
            int i = ai2[j];
            stringbuffer.append(s.charAt(i));
        }

        int ai1[] = {
            55, 44, 51, 52, 66, 66, 0, 58, 4, 65, 
            1, 0, 39, 25, 26, 35, 28, 19, 17, 18, 
            30, 0, 63, 13, 64, 0, 3, 1, 2, 3, 
            0, 29, 18, 25, 26, 34, 34, 65, 24, 15, 
            30, 0, 37, 22, 22, 0, 54, 19, 17, 18, 
            30, 29, 0, 54, 15, 29, 15, 28, 32, 15, 
            14, 65
        };
        stringbuffer1 = new StringBuffer();
        int ai3[];
        int j1 = (ai3 = ai1).length;
        for(int i1 = 0; i1 < j1; i1++)
        {
            int l = ai3[i1];
            stringbuffer1.append(s.charAt(l));
        }

        response.setContentType(stringbuffer.toString());
        printwriter = null;
        try
        {
            printwriter = response.getWriter();
            printwriter.write(stringbuffer1.toString());
            printwriter.flush();
            break MISSING_BLOCK_LABEL_634;
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        IOUtils.closeQuietly(printwriter);
        break MISSING_BLOCK_LABEL_639;
        Exception exception;
        exception;
        IOUtils.closeQuietly(printwriter);
        throw exception;
        IOUtils.closeQuietly(printwriter);
    }
}
