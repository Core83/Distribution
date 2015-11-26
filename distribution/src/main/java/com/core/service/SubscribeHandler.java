package com.core.service;

import com.core.WeChat.Config;
import com.core.util.DateUtil;
import com.core.model.WxUserInfo;
import com.iboot.weixin.api.UserAPI;
import com.iboot.weixin.api.config.ApiConfig;
import com.iboot.weixin.api.response.GetUserInfoResponse;
import com.iboot.weixin.handle.EventHandle;
import com.iboot.weixin.message.BaseMsg;
import com.iboot.weixin.message.TextMsg;
import com.iboot.weixin.message.req.BaseEvent;
import com.iboot.weixin.message.req.QrCodeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by core on 15/11/4.
 */
@Service("subcriberHandler")
public class SubscribeHandler implements EventHandle<QrCodeEvent> {
    private static Logger log= LoggerFactory.getLogger(SubscribeHandler.class);
    @Autowired
    private  IWxUserInfoService wxUserInfoService;
    @Override
    public BaseMsg handle(QrCodeEvent qrCodeEvent) {
       log.debug("=============subscribe====================");
        WxUserInfo user = new WxUserInfo();
        user=wxUserInfoService.getUserIdByOpenId(qrCodeEvent.getFromUserName());
        WxUserInfo parent=null;
        if(qrCodeEvent.getTicket()!=null){
            parent=wxUserInfoService.getUserByTicket(qrCodeEvent.getTicket());
        }
        String message="亲，您终于来了！\n";
        try{
            ApiConfig config=new ApiConfig(Config.APPID,Config.AppSecret,true);
            GetUserInfoResponse userInfoResponse=new UserAPI(config).getUserInfo(qrCodeEvent.getFromUserName());
            user.setOpenId(userInfoResponse.getOpenid());
            user.setNickname(userInfoResponse.getNickname());
            user.setCity(userInfoResponse.getCity());
            user.setCountry(userInfoResponse.getCountry());
            user.setHeadimgurl(userInfoResponse.getHeadimgurl());
            user.setProvince(userInfoResponse.getProvince());
            user.setSex(userInfoResponse.getSex());
            user.setCreatetime(DateUtil.getCurrent());
            user.setSubscribe(1);
            if (parent!=null){
                user.setParentid(parent.getUserId());
                message+="恭喜你由"+parent.getNickname()+"推荐成为"+parent.getNickname()+"家族的成员。";
            }
          wxUserInfoService.insertOrUpdateWxUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.debug("=============subscribe end====================");
        return new TextMsg(message);


    }

    @Override
    public boolean beforeHandle(QrCodeEvent qrCodeEvent) {
        return "subscribe".equals(qrCodeEvent.getEvent());
    }

}
