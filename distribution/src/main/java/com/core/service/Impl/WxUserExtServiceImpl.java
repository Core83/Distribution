package com.core.service.Impl;

import com.core.dao.WxUserExtMapper;
import com.core.model.WxUserExt;
import com.core.service.IWxUserExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by core on 15/11/16.
 */
@Service
public class WxUserExtServiceImpl implements IWxUserExtService {
    @Autowired
    private WxUserExtMapper userExtMapper;
    @Override
    public void insert(WxUserExt ext) {
        userExtMapper.insertSelective(ext);
    }
}
