package com.core.service.Impl;

import com.core.dao.WxOrderAddressMapper;
import com.core.model.WxOrderAddress;
import com.core.model.WxOrderAddressExample;
import com.core.service.IWxOrderAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by core on 15/11/23.
 */
@Service
public class WxOrderAddressServiceImpl implements IWxOrderAddressService {
    @Autowired
    private WxOrderAddressMapper orderAddressMapper;
    @Override
    public void Insert(WxOrderAddress address) {
        orderAddressMapper.insertSelective(address);
    }

    @Override
    public WxOrderAddress getById(Integer userId, Long orderId) {
        WxOrderAddressExample example=new WxOrderAddressExample();
        example.createCriteria().andUserIdEqualTo(userId).andOrderIdEqualTo(orderId);
        return orderAddressMapper.selectByExample(example)==null?null:orderAddressMapper.selectByExample(example).get(0);
    }
}
