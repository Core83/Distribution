package com.core.service.Impl;

import com.core.dao.TWxAppMapper;
import com.core.model.TWxApp;
import com.core.service.IWxAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by core on 15/11/2.
 */
@Service
public class WxAppServiceImpl implements IWxAppService {
    @Autowired
     private TWxAppMapper wxAppMapper;
    @Override
    public TWxApp getWxApp(String id) {
        return wxAppMapper.selectByPrimaryKey(id);
    }
}
