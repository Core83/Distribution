package com.core.controller;

import com.core.util.JsonMapper;
import com.core.model.ViewCache;
import com.core.service.IViewCacheService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by core on 15/11/12.
 */
@Controller
public class CacheController  {
    @Autowired
    private IViewCacheService viewCacheService;
    protected static JsonMapper binder = JsonMapper.nonDefaultMapper();
    @RequestMapping(value = "/cache", method = RequestMethod.GET)
    @ResponseBody
    public String getCache(@RequestParam(value = "dict") String dict,
                           @RequestParam(value = "pValue", required = false, defaultValue = "") String pValue) {
        List<ViewCache> caches = null;
        if (StringUtils.isNotBlank(pValue)) {
            if (StringUtils.equals("brysj", pValue))
                return "[]";
            caches = viewCacheService.findCacheByKey(dict, pValue);
        } else {
            caches = viewCacheService.findCacheByKey(dict);
        }

        return binder.toJson(caches);
    }

    /**
     * 地区处理
     * 110000, 120000, 310000, 500000 直辖市特别处理
     * @param dict  PROVINCE-CITY-COUNTY
     * @param pValue
     * @return
     */
    @RequestMapping(value = "/cache/area", method = RequestMethod.GET)
    @ResponseBody
    public String getArea(@RequestParam(value = "dict") String dict,
                          @RequestParam(value = "pValue", required = false, defaultValue = "") String pValue) {
        List<ViewCache> caches = null;
        if (StringUtils.equals("CITY", dict)) {
            if (StringUtils.equals("110000", pValue)) {
                dict = "COUNTY";
                caches = viewCacheService.findCacheByKey(dict, "110100");
                caches.addAll(viewCacheService.findCacheByKey(dict, "110200"));
            } else if (StringUtils.equals("120000", pValue)) {
                dict = "COUNTY";
                caches = viewCacheService.findCacheByKey(dict, "120100");
                caches.addAll(viewCacheService.findCacheByKey(dict, "120200"));
            } else if (StringUtils.equals("310000", pValue)) {
                dict = "COUNTY";
                caches = viewCacheService.findCacheByKey(dict, "310100");
                caches.addAll(viewCacheService.findCacheByKey(dict, "310200"));
            } else if (StringUtils.equals("500000", pValue)) {
                dict = "COUNTY";
                caches = viewCacheService.findCacheByKey(dict, "500100");
                caches.addAll(viewCacheService.findCacheByKey(dict, "500200"));
            } else {
                if (StringUtils.isNotBlank(pValue)) {
                    if (StringUtils.equals("brysj", pValue))
                        return "[]";
                    caches = viewCacheService.findCacheByKey(dict, pValue);
                }
            }

        } else {
            if (StringUtils.isNotBlank(pValue)) {
                if (StringUtils.equals("brysj", pValue))
                    return "[]";
                caches = viewCacheService.findCacheByKey(dict, pValue);
            } else {
                caches = viewCacheService.findCacheByKey(dict);
            }
        }

        return binder.toJson(caches);
    }
}
