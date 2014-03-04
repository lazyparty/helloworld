// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.service;

import java.util.List;
import net.shopxx.Template;

public interface TemplateService
{

    public abstract List getAll();

    public abstract List getList(net.shopxx.Template.Type type);

    public abstract Template get(String s);

    public abstract String read(String s);

    public abstract String read(Template template);

    public abstract void write(String s, String s1);

    public abstract void write(Template template, String s);
}
