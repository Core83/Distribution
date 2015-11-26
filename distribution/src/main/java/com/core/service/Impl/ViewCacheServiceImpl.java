package com.core.service.Impl;

import com.core.dao.ViewCacheMapper;
import com.core.model.ViewCache;
import com.core.model.ViewCacheExample;
import com.core.service.IViewCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by core on 15/11/12.
 */
@Service("viewCacheService")
public class ViewCacheServiceImpl implements IViewCacheService{
    @Autowired
    private ViewCacheMapper viewCacheDAO;
    @Cacheable(
            value = {"sysCache"},
            key = "#key"
    )
    public List<ViewCache> findCacheByKey(String key) {
        ViewCacheExample example = new ViewCacheExample();
        example.createCriteria().andPKeyEqualTo(key);
        return viewCacheDAO.selectByExample(example);
    }

    @Cacheable(
            value = {"sysCache"},
            key = "#key + #value"
    )
    public ViewCache findCacheByKeyAndValue(String key, String value) {
        ViewCacheExample example = new ViewCacheExample();
        example.createCriteria().andPKeyEqualTo(key).andPValueEqualTo(Long.valueOf(value));
        List vs = viewCacheDAO.selectByExample(example);
        return vs.size() == 1?(ViewCache)vs.get(0):null;
    }

    @Cacheable(
            value = {"sysCache"},
            key = "#key + #pValue"
    )
    public List<ViewCache> findCacheByKey(String key, String pValue) {
        ViewCacheExample example = new ViewCacheExample();
        example.createCriteria().andPKeyEqualTo(key).andPPValueEqualTo(pValue);
        List vs = viewCacheDAO.selectByExample(example);
        return vs;
    }

    @Cacheable(
            value = {"sysCache"},
            key = "#key + #pValueList.toString()"
    )
    public List<ViewCache> findCacheByKey(String key, List<String> pValueList) {
        ViewCacheExample example = new ViewCacheExample();
        example.createCriteria().andPKeyEqualTo(key).andPPValueIn(pValueList);
        List vs = viewCacheDAO.selectByExample(example);
        return vs;
    }

    @Cacheable(
            value = {"sysCache"},
            key = "#key + #value + #pValue"
    )
    public ViewCache findCacheByKeyAndValueAndPvalue(String key, String value, String pValue) {
        ViewCacheExample example = new ViewCacheExample();
        example.createCriteria().andPKeyEqualTo(key).andPValueEqualTo(Long.valueOf(value)).andPPValueEqualTo(pValue);
        List vs = viewCacheDAO.selectByExample(example);
        return vs.size() == 1?(ViewCache)vs.get(0):null;
    }
}
