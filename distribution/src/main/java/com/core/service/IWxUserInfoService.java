package com.core.service;

import com.core.model.WxUserInfo;

/**
 * Created by core on 15/11/4.
 */
public interface IWxUserInfoService {
    Integer insertOrUpdateWxUser(WxUserInfo user) throws Exception;
    WxUserInfo getUserIdByOpenId(String openId);
    WxUserInfo getUserByTicket(String ticket);
    WxUserInfo getUserById(Integer userId);
    WxUserInfo getParentOpenId(Integer userId);
    int countFamily(WxUserInfo obj);
    int countSenFans(WxUserInfo obj);


}
