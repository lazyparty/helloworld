// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import java.util.*;
import javax.servlet.http.HttpServletResponse;
import net.shopxx.Message;
import net.shopxx.service.FileService;
import net.shopxx.util.JsonUtils;
import org.springframework.web.multipart.MultipartFile;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class FileController extends BaseController
{

    public FileController()
    {
    }

    public void upload(net.shopxx.FileInfo.FileType fileType, MultipartFile file, HttpServletResponse response)
    {
        if(!IIIlllIl.isValid(fileType, file))
        {
            JsonUtils.toJson(response, "text/html; charset=UTF-8", Message.warn("admin.upload.invalid", new Object[0]));
        } else
        {
            String s = IIIlllIl.upload(fileType, file, false);
            if(s == null)
                JsonUtils.toJson(response, "text/html; charset=UTF-8", Message.warn("admin.upload.error", new Object[0]));
            HashMap hashmap = new HashMap();
            hashmap.put("url", s);
            JsonUtils.toJson(response, "text/html; charset=UTF-8", hashmap);
        }
    }

    public List browser(String path, net.shopxx.FileInfo.FileType fileType, net.shopxx.FileInfo.OrderType orderType)
    {
        return IIIlllIl.browser(path, fileType, orderType);
    }

    private FileService IIIlllIl;
}
