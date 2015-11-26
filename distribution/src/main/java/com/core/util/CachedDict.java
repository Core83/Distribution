package com.core.util;

import com.core.model.ViewCache;
import com.core.service.IViewCacheService;
import org.apache.commons.lang.StringUtils;

/**
 * Created by core on 15/11/12.
 */
public class CachedDict {
    public static String getCachedName(String key, String value, String parent) {
        if (value != null && StringUtils.isNotEmpty(value)) {
            IViewCacheService viewCacheService = (IViewCacheService) BeanFactory.getObject(IViewCacheService.class);
            ViewCache vc = viewCacheService.findCacheByKeyAndValue(key, value);
            return vc != null ? vc.getpDesc() : value;
        } else
        {
            return "";
        }
    }
}
