package com.core.controller;

import com.core.WeChat.Config;
import com.core.model.WxOrderInfo;
import com.core.model.WxUserInfo;
import com.core.service.IWxOrderService;
import com.core.service.IWxUserInfoService;
import com.core.util.CachedDict;
import com.core.util.QrCodeUtil;
import com.iboot.weixin.api.OauthAPI;
import com.iboot.weixin.api.QrcodeAPI;
import com.iboot.weixin.api.config.ApiConfig;
import com.iboot.weixin.api.enums.OauthScope;
import com.iboot.weixin.api.enums.QrcodeType;
import com.iboot.weixin.api.response.BaseResponse;
import com.iboot.weixin.api.response.OauthGetTokenResponse;
import com.iboot.weixin.api.response.QrcodeResponse;
import com.iboot.weixin.util.NetWorkCenter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by core on 15/11/23.
 */
@Controller
@RequestMapping("/card")
public class QrCodeController {
    @Autowired
    private IWxUserInfoService userInfoService;
    @Autowired
    private IWxOrderService orderService;

    private ApiConfig config=new ApiConfig(Config.APPID,Config.AppSecret,true);

    public static final Log log = LogFactory.getLog(QrCodeController.class);
    @RequestMapping("/init")
    public String merInit (Model model,HttpServletRequest request)  {
        try {
            String openId = request.getParameter("openId");
            model.addAttribute("openId",openId);
            WxUserInfo user=userInfoService.getUserIdByOpenId(openId);
            String ticket = user.getTicket();
            String qrCode="";
            if (StringUtils.isNotBlank(ticket)) {
                qrCode=QrCodeUtil.DecoderQRCode("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + URLEncoder.encode(ticket, "UTF-8"));
                model.addAttribute("qrCode", qrCode);
            }else{
//               List<WxOrderInfo> orders= orderService.getListByUser(user.getUserId());
               String level= CachedDict.getCachedName("LEVEL", String.valueOf(user.getUserId()), "");
                if (StringUtils.isNotBlank(level)){
                    QrcodeAPI qrcodeAPI=new QrcodeAPI(config);
                    QrcodeResponse response=qrcodeAPI.createQrcode(QrcodeType.QR_LIMIT_SCENE,String.valueOf(user.getUserId()),0);
                    user.setTicket(response.getTicket());
                    userInfoService.insertOrUpdateWxUser(user);
                    qrCode= QrCodeUtil.DecoderQRCode("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + URLEncoder.encode(response.getTicket(), "UTF-8"));
                    model.addAttribute("qrCode", qrCode);
                }
            }
            model.addAttribute("img",user.getHeadimgurl());
        }catch (Exception e){
            e.printStackTrace();
        }

        return "card/qrcode";
    }
    @RequestMapping("/code")
    public String getOauthCode(HttpServletRequest request){
        log.debug("===========================Oauth begin=========================");
        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");
        String orderId=request.getParameter("state");
        // 用户同意授权
        if (!"authdeny".equals(code)) {

            OauthAPI oauthAPI=new OauthAPI(config);
            OauthGetTokenResponse response=oauthAPI.getToken(code);
            if (response!=null){
                log.debug("===========================openId="+response.getOpenid()+"=========================");
                return "forward:/card/init?openId="+response.getOpenid();
            }
        }
        return "/404";
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+URLEncoder.encode("gQG87zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL3FUbXU5YXJsSjAxZXNJWUZ3UlVXAAIEbL5SVgMEAAAAAA==", "UTF-8"));
    }
}
