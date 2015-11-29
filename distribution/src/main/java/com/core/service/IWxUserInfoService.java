package com.core.service;

import com.core.model.WxUserInfo;

import java.util.List;

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
    int countThirdFans(WxUserInfo obj);
    WxUserInfo getUserIdByOpenIdSub(String openId);
    List<WxUserInfo> getFirstList(Integer userId,Integer pageNo);
    List<WxUserInfo> getSecondList(Integer userId,Integer pageNo);
    List<WxUserInfo> getThirdList(Integer userId,Integer pageNo);
}
