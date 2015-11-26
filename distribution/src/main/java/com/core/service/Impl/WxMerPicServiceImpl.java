package com.core.service.Impl;

import com.core.dao.WxMerPicMapper;
import com.core.model.WxMerPic;
import com.core.model.WxMerPicExample;
import com.core.service.IWxMerInfoService;
import com.core.service.IWxMerPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by core on 15/11/8.
 */
@Service
public class WxMerPicServiceImpl implements IWxMerPicService {
    @Autowired
    private WxMerPicMapper picMapper;
    @Override
    public List<WxMerPic> getListByExample(Integer gdsId) {
        WxMerPicExample example=new WxMerPicExample();
        example.createCriteria().andGdsIdEqualTo(gdsId);
        return picMapper.selectByExample(example);
    }
}
