package com.core.service;

import com.core.model.WxUserAddress;

/**
 * Created by core on 15/11/13.
 */
public interface IWxOrderAddressService {
    void Insert(WxUserAddress address);
    WxUserAddress getById(Integer userId);
}
