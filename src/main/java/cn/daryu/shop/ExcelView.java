// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package cn.daryu.shop;

import java.net.URLEncoder;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.*;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class ExcelView extends AbstractExcelView
{

    public ExcelView(String filename, String sheetName, String properties[], String titles[], Integer widths[], Converter converters[], Collection data, 
            String contents[])
    {
        IIIllIll = filename;
        IIIlllII = sheetName;
        IIIlllIl = properties;
        IIIllllI = titles;
        IIIlllll = widths;
        IIlIIIII = converters;
        IIlIIIIl = data;
        IIlIIIlI = contents;
    }

    public ExcelView(String properties[], String titles[], Collection data, String contents[])
    {
        IIIlllIl = properties;
        IIIllllI = titles;
        IIlIIIIl = data;
        IIlIIIlI = contents;
    }

    public ExcelView(String properties[], String titles[], Collection data)
    {
        IIIlllIl = properties;
        IIIllllI = titles;
        IIlIIIIl = data;
    }

    public ExcelView(String properties[], Collection data)
    {
        IIIlllIl = properties;
        IIlIIIIl = data;
    }

    public void buildExcelDocument(Map model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
    {
        Assert.notEmpty(IIIlllIl);
        HSSFSheet hssfsheet;
        if(StringUtils.isNotEmpty(IIIlllII))
            hssfsheet = workbook.createSheet(IIIlllII);
        else
            hssfsheet = workbook.createSheet();
        int i = 0;
        if(IIIllllI != null && IIIllllI.length > 0)
        {
            HSSFRow hssfrow = hssfsheet.createRow(i);
            hssfrow.setHeight((short)400);
            for(int j = 0; j < IIIlllIl.length; j++)
            {
                HSSFCell hssfcell = hssfrow.createCell(j);
                HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
                hssfcellstyle.setFillForegroundColor((short)31);
                hssfcellstyle.setFillPattern((short)1);
                hssfcellstyle.setAlignment((short)2);
                hssfcellstyle.setVerticalAlignment((short)1);
                HSSFFont hssffont = workbook.createFont();
                hssffont.setFontHeightInPoints((short)11);
                hssffont.setBoldweight((short)700);
                hssfcellstyle.setFont(hssffont);
                hssfcell.setCellStyle(hssfcellstyle);
                if(j == 0)
                {
                    HSSFPatriarch hssfpatriarch = hssfsheet.createDrawingPatriarch();
                    HSSFComment hssfcomment = hssfpatriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short)1, 1, (short)4, 4));
                    hssfcomment.setString(new HSSFRichTextString("Powered By SHOP++"));
                    hssfcell.setCellComment(hssfcomment);
                }
                if(IIIllllI.length > j && IIIllllI[j] != null)
                    hssfcell.setCellValue(IIIllllI[j]);
                else
                    hssfcell.setCellValue(IIIlllIl[j]);
                if(IIIlllll != null && IIIlllll.length > j && IIIlllll[j] != null)
                    hssfsheet.setColumnWidth(j, IIIlllll[j].intValue());
                else
                    hssfsheet.autoSizeColumn(j);
            }

            i++;
        }
        if(IIlIIIIl != null)
        {
            for(Iterator iterator = IIlIIIIl.iterator(); iterator.hasNext();)
            {
                Object obj = iterator.next();
                HSSFRow hssfrow1 = hssfsheet.createRow(i);
                for(int i1 = 0; i1 < IIIlllIl.length; i1++)
                {
                    HSSFCell hssfcell1 = hssfrow1.createCell(i1);
                    if(IIlIIIII != null && IIlIIIII.length > i1 && IIlIIIII[i1] != null)
                    {
                        Class class1 = PropertyUtils.getPropertyType(obj, IIIlllIl[i1]);
                        ConvertUtils.register(IIlIIIII[i1], class1);
                        hssfcell1.setCellValue(BeanUtils.getProperty(obj, IIIlllIl[i1]));
                        ConvertUtils.deregister(class1);
                        if(class1.equals(java/util/Date))
                        {
                            DateConverter dateconverter = new DateConverter();
                            dateconverter.setPattern("yyyy-MM-dd HH:mm:ss");
                            ConvertUtils.register(dateconverter, java/util/Date);
                        }
                    } else
                    {
                        hssfcell1.setCellValue(BeanUtils.getProperty(obj, IIIlllIl[i1]));
                    }
                    if(i == 0 || i == 1)
                        if(IIIlllll != null && IIIlllll.length > i1 && IIIlllll[i1] != null)
                            hssfsheet.setColumnWidth(i1, IIIlllll[i1].intValue());
                        else
                            hssfsheet.autoSizeColumn(i1);
                }

                i++;
            }

        }
        if(IIlIIIlI != null && IIlIIIlI.length > 0)
        {
            i++;
            String as[];
            int l = (as = IIlIIIlI).length;
            for(int k = 0; k < l; k++)
            {
                String s = as[k];
                HSSFRow hssfrow2 = hssfsheet.createRow(i);
                HSSFCell hssfcell2 = hssfrow2.createCell(0);
                HSSFCellStyle hssfcellstyle1 = workbook.createCellStyle();
                HSSFFont hssffont1 = workbook.createFont();
                hssffont1.setColor((short)23);
                hssfcellstyle1.setFont(hssffont1);
                hssfcell2.setCellStyle(hssfcellstyle1);
                hssfcell2.setCellValue(s);
                i++;
            }

        }
        response.setContentType("application/force-download");
        if(StringUtils.isNotEmpty(IIIllIll))
            response.setHeader("Content-disposition", (new StringBuilder("attachment; filename=")).append(URLEncoder.encode(IIIllIll, "UTF-8")).toString());
        else
            response.setHeader("Content-disposition", "attachment");
    }

    public String getFileName()
    {
        return IIIllIll;
    }

    public void setFileName(String filename)
    {
        IIIllIll = filename;
    }

    public String getSheetName()
    {
        return IIIlllII;
    }

    public void setSheetName(String sheetName)
    {
        IIIlllII = sheetName;
    }

    public String[] getProperties()
    {
        return IIIlllIl;
    }

    public void setProperties(String properties[])
    {
        IIIlllIl = properties;
    }

    public String[] getTitles()
    {
        return IIIllllI;
    }

    public void setTitles(String titles[])
    {
        IIIllllI = titles;
    }

    public Integer[] getWidths()
    {
        return IIIlllll;
    }

    public void setWidths(Integer widths[])
    {
        IIIlllll = widths;
    }

    public Converter[] getConverters()
    {
        return IIlIIIII;
    }

    public void setConverters(Converter converters[])
    {
        IIlIIIII = converters;
    }

    public Collection getData()
    {
        return IIlIIIIl;
    }

    public void setData(Collection data)
    {
        IIlIIIIl = data;
    }

    public String[] getContents()
    {
        return IIlIIIlI;
    }

    public void setContents(String contents[])
    {
        IIlIIIlI = contents;
    }

    private static final String IIIllIlI = "yyyy-MM-dd HH:mm:ss";
    private String IIIllIll;
    private String IIIlllII;
    private String IIIlllIl[];
    private String IIIllllI[];
    private Integer IIIlllll[];
    private Converter IIlIIIII[];
    private Collection IIlIIIIl;
    private String IIlIIIlI[];

    static 
    {
        DateConverter dateconverter = new DateConverter();
        dateconverter.setPattern("yyyy-MM-dd HH:mm:ss");
        ConvertUtils.register(dateconverter, java/util/Date);
    }
}
