// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.im4java.core.*;
import org.springframework.util.Assert;

public final class ImageUtils
{

    private ImageUtils()
    {
    }

    public static void zoom(File srcFile, File destFile, int destWidth, int destHeight)
    {
        Graphics2D graphics2d;
        ImageOutputStream imageoutputstream;
        ImageWriter imagewriter;
        Assert.notNull(srcFile);
        Assert.notNull(destFile);
        Assert.state(destWidth > 0);
        Assert.state(destHeight > 0);
        if(IIIllIlI != Type.jdk)
            break MISSING_BLOCK_LABEL_402;
        graphics2d = null;
        imageoutputstream = null;
        imagewriter = null;
        try
        {
            BufferedImage bufferedimage = ImageIO.read(srcFile);
            int i = bufferedimage.getWidth();
            int j = bufferedimage.getHeight();
            int k = destWidth;
            int l = destHeight;
            if(j >= i)
                k = (int)Math.round((((double)destHeight * 1.0D) / (double)j) * (double)i);
            else
                l = (int)Math.round((((double)destWidth * 1.0D) / (double)i) * (double)j);
            BufferedImage bufferedimage1 = new BufferedImage(destWidth, destHeight, 1);
            graphics2d = bufferedimage1.createGraphics();
            graphics2d.setBackground(IIIlllIl);
            graphics2d.clearRect(0, 0, destWidth, destHeight);
            graphics2d.drawImage(bufferedimage.getScaledInstance(k, l, 4), destWidth / 2 - k / 2, destHeight / 2 - l / 2, null);
            imageoutputstream = ImageIO.createImageOutputStream(destFile);
            imagewriter = (ImageWriter)ImageIO.getImageWritersByFormatName(FilenameUtils.getExtension(destFile.getName())).next();
            imagewriter.setOutput(imageoutputstream);
            ImageWriteParam imagewriteparam = imagewriter.getDefaultWriteParam();
            imagewriteparam.setCompressionMode(2);
            imagewriteparam.setCompressionQuality(0.88F);
            imagewriter.write(null, new IIOImage(bufferedimage1, null, null), imagewriteparam);
            imageoutputstream.flush();
            break MISSING_BLOCK_LABEL_362;
        }
        catch(IOException ioexception2)
        {
            ioexception2.printStackTrace();
        }
        if(graphics2d != null)
            graphics2d.dispose();
        if(imagewriter != null)
            imagewriter.dispose();
        if(imageoutputstream != null)
            try
            {
                imageoutputstream.close();
            }
            catch(IOException ioexception3) { }
        break MISSING_BLOCK_LABEL_647;
        Exception exception;
        exception;
        if(graphics2d != null)
            graphics2d.dispose();
        if(imagewriter != null)
            imagewriter.dispose();
        if(imageoutputstream != null)
            try
            {
                imageoutputstream.close();
            }
            catch(IOException ioexception4) { }
        throw exception;
        if(graphics2d != null)
            graphics2d.dispose();
        if(imagewriter != null)
            imagewriter.dispose();
        if(imageoutputstream != null)
            try
            {
                imageoutputstream.close();
            }
            catch(IOException ioexception5) { }
        break MISSING_BLOCK_LABEL_647;
        IMOperation imoperation = new IMOperation();
        imoperation.thumbnail(Integer.valueOf(destWidth), Integer.valueOf(destHeight));
        imoperation.gravity("center");
        imoperation.background(IIIllIlI(IIIlllIl));
        imoperation.extent(Integer.valueOf(destWidth), Integer.valueOf(destHeight));
        imoperation.quality(Double.valueOf(88D));
        imoperation.addImage(new String[] {
            srcFile.getPath()
        });
        imoperation.addImage(new String[] {
            destFile.getPath()
        });
        if(IIIllIlI == Type.graphicsMagick)
        {
            ConvertCmd convertcmd = new ConvertCmd(true);
            if(IIIllIll != null)
                convertcmd.setSearchPath(IIIllIll);
            try
            {
                convertcmd.run(imoperation, new Object[0]);
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
            }
            catch(InterruptedException interruptedexception)
            {
                interruptedexception.printStackTrace();
            }
            catch(IM4JavaException im4javaexception)
            {
                im4javaexception.printStackTrace();
            }
        } else
        {
            ConvertCmd convertcmd1 = new ConvertCmd(false);
            if(IIIlllII != null)
                convertcmd1.setSearchPath(IIIlllII);
            try
            {
                convertcmd1.run(imoperation, new Object[0]);
            }
            catch(IOException ioexception1)
            {
                ioexception1.printStackTrace();
            }
            catch(InterruptedException interruptedexception1)
            {
                interruptedexception1.printStackTrace();
            }
            catch(IM4JavaException im4javaexception1)
            {
                im4javaexception1.printStackTrace();
            }
        }
    }

    public static void addWatermark(File srcFile, File destFile, File watermarkFile, net.shopxx.Setting.WatermarkPosition watermarkPosition, int alpha)
    {
        Graphics2D graphics2d;
        ImageOutputStream imageoutputstream;
        ImageWriter imagewriter;
        Assert.notNull(srcFile);
        Assert.notNull(destFile);
        Assert.state(alpha >= 0);
        Assert.state(alpha <= 100);
        if(watermarkFile == null || !watermarkFile.exists() || watermarkPosition == null || watermarkPosition == net.shopxx.Setting.WatermarkPosition.no)
        {
            try
            {
                FileUtils.copyFile(srcFile, destFile);
            }
            catch(IOException ioexception)
            {
                ioexception.printStackTrace();
            }
            return;
        }
        if(IIIllIlI != Type.jdk)
            break MISSING_BLOCK_LABEL_546;
        graphics2d = null;
        imageoutputstream = null;
        imagewriter = null;
        try
        {
            BufferedImage bufferedimage = ImageIO.read(srcFile);
            int i = bufferedimage.getWidth();
            int j = bufferedimage.getHeight();
            BufferedImage bufferedimage1 = new BufferedImage(i, j, 1);
            graphics2d = bufferedimage1.createGraphics();
            graphics2d.setBackground(IIIlllIl);
            graphics2d.clearRect(0, 0, i, j);
            graphics2d.drawImage(bufferedimage, 0, 0, null);
            graphics2d.setComposite(AlphaComposite.getInstance(10, (float)alpha / 100F));
            BufferedImage bufferedimage2 = ImageIO.read(watermarkFile);
            int k = bufferedimage2.getWidth();
            int l = bufferedimage2.getHeight();
            int i1 = i - k;
            int j1 = j - l;
            if(watermarkPosition == net.shopxx.Setting.WatermarkPosition.topLeft)
            {
                i1 = 0;
                j1 = 0;
            } else
            if(watermarkPosition == net.shopxx.Setting.WatermarkPosition.topRight)
            {
                i1 = i - k;
                j1 = 0;
            } else
            if(watermarkPosition == net.shopxx.Setting.WatermarkPosition.center)
            {
                i1 = (i - k) / 2;
                j1 = (j - l) / 2;
            } else
            if(watermarkPosition == net.shopxx.Setting.WatermarkPosition.bottomLeft)
            {
                i1 = 0;
                j1 = j - l;
            } else
            if(watermarkPosition == net.shopxx.Setting.WatermarkPosition.bottomRight)
            {
                i1 = i - k;
                j1 = j - l;
            }
            graphics2d.drawImage(bufferedimage2, i1, j1, k, l, null);
            imageoutputstream = ImageIO.createImageOutputStream(destFile);
            imagewriter = (ImageWriter)ImageIO.getImageWritersByFormatName(FilenameUtils.getExtension(destFile.getName())).next();
            imagewriter.setOutput(imageoutputstream);
            ImageWriteParam imagewriteparam = imagewriter.getDefaultWriteParam();
            imagewriteparam.setCompressionMode(2);
            imagewriteparam.setCompressionQuality(0.88F);
            imagewriter.write(null, new IIOImage(bufferedimage1, null, null), imagewriteparam);
            imageoutputstream.flush();
            break MISSING_BLOCK_LABEL_506;
        }
        catch(IOException ioexception1)
        {
            ioexception1.printStackTrace();
        }
        if(graphics2d != null)
            graphics2d.dispose();
        if(imagewriter != null)
            imagewriter.dispose();
        if(imageoutputstream != null)
            try
            {
                imageoutputstream.close();
            }
            catch(IOException ioexception4) { }
        break MISSING_BLOCK_LABEL_850;
        Exception exception;
        exception;
        if(graphics2d != null)
            graphics2d.dispose();
        if(imagewriter != null)
            imagewriter.dispose();
        if(imageoutputstream != null)
            try
            {
                imageoutputstream.close();
            }
            catch(IOException ioexception5) { }
        throw exception;
        if(graphics2d != null)
            graphics2d.dispose();
        if(imagewriter != null)
            imagewriter.dispose();
        if(imageoutputstream != null)
            try
            {
                imageoutputstream.close();
            }
            catch(IOException ioexception6) { }
        break MISSING_BLOCK_LABEL_850;
        String s = "SouthEast";
        if(watermarkPosition == net.shopxx.Setting.WatermarkPosition.topLeft)
            s = "NorthWest";
        else
        if(watermarkPosition == net.shopxx.Setting.WatermarkPosition.topRight)
            s = "NorthEast";
        else
        if(watermarkPosition == net.shopxx.Setting.WatermarkPosition.center)
            s = "Center";
        else
        if(watermarkPosition == net.shopxx.Setting.WatermarkPosition.bottomLeft)
            s = "SouthWest";
        else
        if(watermarkPosition == net.shopxx.Setting.WatermarkPosition.bottomRight)
            s = "SouthEast";
        IMOperation imoperation = new IMOperation();
        imoperation.gravity(s);
        imoperation.dissolve(Integer.valueOf(alpha));
        imoperation.quality(Double.valueOf(88D));
        imoperation.addImage(new String[] {
            watermarkFile.getPath()
        });
        imoperation.addImage(new String[] {
            srcFile.getPath()
        });
        imoperation.addImage(new String[] {
            destFile.getPath()
        });
        if(IIIllIlI == Type.graphicsMagick)
        {
            CompositeCmd compositecmd = new CompositeCmd(true);
            if(IIIllIll != null)
                compositecmd.setSearchPath(IIIllIll);
            try
            {
                compositecmd.run(imoperation, new Object[0]);
            }
            catch(IOException ioexception2)
            {
                ioexception2.printStackTrace();
            }
            catch(InterruptedException interruptedexception)
            {
                interruptedexception.printStackTrace();
            }
            catch(IM4JavaException im4javaexception)
            {
                im4javaexception.printStackTrace();
            }
        } else
        {
            CompositeCmd compositecmd1 = new CompositeCmd(false);
            if(IIIlllII != null)
                compositecmd1.setSearchPath(IIIlllII);
            try
            {
                compositecmd1.run(imoperation, new Object[0]);
            }
            catch(IOException ioexception3)
            {
                ioexception3.printStackTrace();
            }
            catch(InterruptedException interruptedexception1)
            {
                interruptedexception1.printStackTrace();
            }
            catch(IM4JavaException im4javaexception1)
            {
                im4javaexception1.printStackTrace();
            }
        }
    }

    public static void initialize()
    {
    }

    private static String IIIllIlI(Color color)
    {
        StringBuffer stringbuffer = new StringBuffer();
        String s = Integer.toHexString(color.getRed());
        String s1 = Integer.toHexString(color.getGreen());
        String s2 = Integer.toHexString(color.getBlue());
        s = s.length() != 1 ? s : (new StringBuilder("0")).append(s).toString();
        s1 = s1.length() != 1 ? s1 : (new StringBuilder("0")).append(s1).toString();
        s2 = s2.length() != 1 ? s2 : (new StringBuilder("0")).append(s2).toString();
        stringbuffer.append("#");
        stringbuffer.append(s);
        stringbuffer.append(s1);
        stringbuffer.append(s2);
        return stringbuffer.toString();
    }

    private static Type IIIllIlI;
    private static String IIIllIll;
    private static String IIIlllII;
    private static final Color IIIlllIl;
    private static final int IIIllllI = 88;

    static 
    {
        IIIllIlI = Type.auto;
        IIIlllIl = Color.white;
        if(IIIllIll == null)
        {
            String s = System.getProperty("os.name").toLowerCase();
            if(s.indexOf("windows") >= 0)
            {
                String s2 = System.getenv("Path");
                if(s2 != null)
                {
                    String as[] = s2.split(";");
                    String as2[];
                    int k = (as2 = as).length;
                    for(int i = 0; i < k; i++)
                    {
                        String s4 = as2[i];
                        File file = new File((new StringBuilder(String.valueOf(s4.trim()))).append("/gm.exe").toString());
                        File file2 = new File((new StringBuilder(String.valueOf(s4.trim()))).append("/gmdisplay.exe").toString());
                        if(!file.exists() || !file2.exists())
                            continue;
                        IIIllIll = s4.trim();
                        break;
                    }

                }
            }
        }
        if(IIIlllII == null)
        {
            String s1 = System.getProperty("os.name").toLowerCase();
            if(s1.indexOf("windows") >= 0)
            {
                String s3 = System.getenv("Path");
                if(s3 != null)
                {
                    String as1[] = s3.split(";");
                    String as3[];
                    int l = (as3 = as1).length;
                    for(int j = 0; j < l; j++)
                    {
                        String s5 = as3[j];
                        File file1 = new File((new StringBuilder(String.valueOf(s5.trim()))).append("/convert.exe").toString());
                        File file3 = new File((new StringBuilder(String.valueOf(s5.trim()))).append("/composite.exe").toString());
                        if(!file1.exists() || !file3.exists())
                            continue;
                        IIIlllII = s5.trim();
                        break;
                    }

                }
            }
        }
        if(IIIllIlI == Type.auto)
            try
            {
                IMOperation imoperation = new IMOperation();
                imoperation.version();
                IdentifyCmd identifycmd = new IdentifyCmd(true);
                if(IIIllIll != null)
                    identifycmd.setSearchPath(IIIllIll);
                identifycmd.run(imoperation, new Object[0]);
                IIIllIlI = Type.graphicsMagick;
            }
            catch(Throwable throwable)
            {
                try
                {
                    IMOperation imoperation1 = new IMOperation();
                    imoperation1.version();
                    IdentifyCmd identifycmd1 = new IdentifyCmd(false);
                    identifycmd1.run(imoperation1, new Object[0]);
                    if(IIIlllII != null)
                        identifycmd1.setSearchPath(IIIlllII);
                    IIIllIlI = Type.imageMagick;
                }
                catch(Throwable throwable1)
                {
                    IIIllIlI = Type.jdk;
                }
            }
    }

    private class Type extends Enum
    {

        public static Type[] values()
        {
            Type atype[];
            int i;
            Type atype1[];
            System.arraycopy(atype = ENUM$VALUES, 0, atype1 = new Type[i = atype.length], 0, i);
            return atype1;
        }

        public static Type valueOf(String s)
        {
            return (Type)Enum.valueOf(net/shopxx/util/ImageUtils$Type, s);
        }

        public static final Type auto;
        public static final Type jdk;
        public static final Type graphicsMagick;
        public static final Type imageMagick;
        private static final Type ENUM$VALUES[];

        static 
        {
            auto = new Type("auto", 0);
            jdk = new Type("jdk", 1);
            graphicsMagick = new Type("graphicsMagick", 2);
            imageMagick = new Type("imageMagick", 3);
            ENUM$VALUES = (new Type[] {
                auto, jdk, graphicsMagick, imageMagick
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }

}
