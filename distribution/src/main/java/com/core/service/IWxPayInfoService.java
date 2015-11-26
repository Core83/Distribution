package com.core.service;

import com.core.model.WxPayInfo;

import java.util.List;

/**
 * Created by core on 15/11/13.
 */
public interface IWxPayInfoService {
    List<WxPayInfo> getPayInfoByOrderId(Integer orderId);
    void insertWxPayInfo(WxPayInfo payInfo);
}
