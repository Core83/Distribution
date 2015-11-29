package com.core.service;

import com.core.model.WxUserInfo;
import com.iboot.weixin.handle.EventHandle;
import com.iboot.weixin.message.BaseMsg;
import com.iboot.weixin.message.req.BaseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by core on 15/11/10.
 */
@Service("unSubcriberHandler")
public class UnSubscribeHandler implements EventHandle<BaseEvent> {
    private static Logger log= LoggerFactory.getLogger(UnSubscribeHandler.class);
    @Autowired
    private  IWxUserInfoService wxUserInfoService;
    @Override
    public BaseMsg handle(BaseEvent baseEvent) {
        log.debug("=============unsubscribe====================");
        WxUserInfo user = wxUserInfoService.getUserIdByOpenIdSub(baseEvent.getFromUserName());
        user.setSubscribe(0);
        try {
            wxUserInfoService.insertOrUpdateWxUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.debug("=============unsubscribe end====================");
        return null;
    }

    @Override
    public boolean beforeHandle(BaseEvent baseEvent) {
        return "unsubscribe".equals(baseEvent.getEvent());
    }
}
