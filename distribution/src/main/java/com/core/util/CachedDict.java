package com.core.util;

import com.core.model.ViewCache;
import com.core.service.IViewCacheService;
import com.core.service.Impl.ViewCacheServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by core on 15/11/12.
 */
public class CachedDict {
//    @Autowired
//    private static IViewCacheService viewCacheService;
    public static String getCachedName(String key, String value, String parent) {
        if (value != null && StringUtils.isNotEmpty(value)) {
            IViewCacheService viewCacheService = (ViewCacheServiceImpl) BeanFactory.getObject("viewCacheService",ViewCacheServiceImpl.class);
//            IViewCacheService viewCacheService =new ViewCacheServiceImpl();
            ViewCache vc=null;
            if(parent!=null&&StringUtils.isNotBlank(parent)){
                vc=viewCacheService.findCacheByKeyAndValueAndPvalue(key,value,parent);
            }else {
                vc = viewCacheService.findCacheByKeyAndValue(key, value);
            }
            return vc != null ? vc.getpDesc() : null;
        } else
        {
            return "";
        }
    }
}
