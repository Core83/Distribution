package com.core.service;

import com.core.model.WxOrderInfo;

import java.util.List;

/**
 * Created by core on 15/11/8.
 */
public interface IWxOrderService  {
    List<WxOrderInfo> getListByUser(Integer userId);
    void insertWxOrder(WxOrderInfo orderInfo) throws Exception;
    Integer insertOrUpdateWxOrder(WxOrderInfo orderInfo);
    WxOrderInfo getById(Long orderId);
}
