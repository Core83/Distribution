package com.core.service.Impl;

import com.core.dao.WxPayInfoMapper;
import com.core.model.WxPayInfo;
import com.core.model.WxPayInfoExample;
import com.core.service.IWxPayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by core on 15/11/13.
 */
@Service
public class WxPayInfoServiceImpl implements IWxPayInfoService {
    @Autowired
    private WxPayInfoMapper payInfoMapper;
    @Override
    public List<WxPayInfo> getPayInfoByOrderId(Integer orderId) {
        WxPayInfoExample example=new WxPayInfoExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        return payInfoMapper.selectByExample(example);
    }

    @Override
    public void insertWxPayInfo(WxPayInfo payInfo) {
        payInfoMapper.insertSelective(payInfo);
    }

}
