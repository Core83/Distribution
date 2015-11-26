package com.core.service;

import com.core.model.TWxApp;

/**
 * Created by core on 15/11/2.
 */
public interface IWxAppService {
    /**
     * 获取微信公众号信息
     * @param id
     * @return
     */
    public TWxApp getWxApp(String id) ;

}
