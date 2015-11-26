package com.core.service.Impl;

import com.core.dao.WxOrderInfoMapper;
import com.core.model.WxOrderInfo;
import com.core.model.WxOrderInfoExample;
import com.core.service.IWxOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by core on 15/11/8.
 */
@Service
public class WxOrderServiceImpl implements IWxOrderService{
    @Autowired
    private WxOrderInfoMapper orderInfoMapper;
    @Override
    public List<WxOrderInfo> getListByUser(Integer userId) {
        if(userId!=null){
            WxOrderInfoExample example=new WxOrderInfoExample();
            example.createCriteria().andUserIdEqualTo(userId);
            return orderInfoMapper.selectByExample(example);
        }
        return null;
    }
    @Override
    public void insertWxOrder(WxOrderInfo orderInfo) throws Exception {
        orderInfoMapper.insertSelective(orderInfo);
    }

    @Override
    public Integer insertOrUpdateWxOrder(WxOrderInfo orderInfo) {
        if(orderInfo.getOrderId()!=null&&orderInfoMapper.selectByPrimaryKey(orderInfo.getOrderId())!=null){
            return  orderInfoMapper.updateByPrimaryKey(orderInfo);
        }else{
            return orderInfoMapper.insertSelective(orderInfo);
        }
    }

    @Override
    public WxOrderInfo getById(Long orderId) {
        return orderInfoMapper.selectByPrimaryKey(orderId);
    }
}
