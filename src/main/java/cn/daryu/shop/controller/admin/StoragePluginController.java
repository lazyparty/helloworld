// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.controller.admin;

import net.shopxx.service.PluginService;
import org.springframework.ui.ModelMap;

// Referenced classes of package net.shopxx.controller.admin:
//            BaseController

public class StoragePluginController extends BaseController
{

    public StoragePluginController()
    {
    }

    public String list(ModelMap model)
    {
        model.addAttribute("storagePlugins", IIIlllIl.getStoragePlugins());
        return "/admin/storage_plugin/list";
    }

    private PluginService IIIlllIl;
}
