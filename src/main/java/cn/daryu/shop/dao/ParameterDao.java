// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.shopxx.dao;

import java.util.List;
import java.util.Set;
import net.shopxx.entity.ParameterGroup;

// Referenced classes of package net.shopxx.dao:
//            BaseDao

public interface ParameterDao
    extends BaseDao
{

    public abstract List findList(ParameterGroup parametergroup, Set set);
}
