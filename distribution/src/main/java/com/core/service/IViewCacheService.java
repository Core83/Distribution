package com.core.service;

import com.core.model.ViewCache;

import java.util.List;

/**
 * Created by core on 15/11/12.
 */
public interface IViewCacheService {
    List<ViewCache> findCacheByKey(String var1);

    ViewCache findCacheByKeyAndValue(String var1, String var2);

    List<ViewCache> findCacheByKey(String var1, String var2);

    List<ViewCache> findCacheByKey(String var1, List<String> var2);

    ViewCache findCacheByKeyAndValueAndPvalue(String var1, String var2, String var3);
}
