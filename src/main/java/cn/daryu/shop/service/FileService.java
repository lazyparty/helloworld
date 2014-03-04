// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface FileService
{

    public abstract boolean isValid(net.shopxx.FileInfo.FileType filetype, MultipartFile multipartfile);

    public abstract String upload(net.shopxx.FileInfo.FileType filetype, MultipartFile multipartfile, boolean flag);

    public abstract String upload(net.shopxx.FileInfo.FileType filetype, MultipartFile multipartfile);

    public abstract String uploadLocal(net.shopxx.FileInfo.FileType filetype, MultipartFile multipartfile);

    public abstract List browser(String s, net.shopxx.FileInfo.FileType filetype, net.shopxx.FileInfo.OrderType ordertype);
}
