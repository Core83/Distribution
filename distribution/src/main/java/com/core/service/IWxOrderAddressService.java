package com.core.service;

import com.core.model.WxOrderAddress;

/**
 * Created by core on 15/11/13.
 */
public interface IWxOrderAddressService {
    void Insert(WxOrderAddress address);
    WxOrderAddress getById(Integer userId,Long orderId);
}
