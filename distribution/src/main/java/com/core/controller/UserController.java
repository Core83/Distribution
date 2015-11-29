package com.core.controller;

import com.core.WeChat.Config;
import com.core.model.SysUser;
import com.core.model.WxOrderAddress;
import com.core.model.WxUserInfo;
import com.core.service.IUserService;
import com.core.service.IWxOrderAddressService;
import com.core.service.IWxUserInfoService;
import com.core.util.CachedDict;
import com.core.util.HttpClientUtil;
import com.iboot.weixin.api.BaseAPI;
import com.iboot.weixin.api.OauthAPI;
import com.iboot.weixin.api.QrcodeAPI;
import com.iboot.weixin.api.config.ApiConfig;
import com.iboot.weixin.api.enums.OauthScope;
import com.iboot.weixin.api.enums.QrcodeType;
import com.iboot.weixin.api.response.BaseResponse;
import com.iboot.weixin.api.response.OauthGetTokenResponse;
import com.iboot.weixin.api.response.QrcodeResponse;
import com.iboot.weixin.client.LocalHttpClient;
import com.iboot.weixin.util.NetWorkCenter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by core on 15/10/29.
 */
@Controller
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IWxUserInfoService userInfoService;
    @Autowired
    private IWxOrderAddressService addressService;
    private ApiConfig config=new ApiConfig(Config.APPID,Config.AppSecret,true);
    public static final Log log = LogFactory.getLog(UserController.class);
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(Model model,HttpServletRequest request)  {
        return "login";
    }
    @RequestMapping(value = "/showUser",method = RequestMethod.GET)
    public String showUser(Model model,HttpServletRequest request)  {

        try {
            QrcodeAPI qrcodeAPI=new QrcodeAPI(config);
            QrcodeResponse response=qrcodeAPI.createQrcode(QrcodeType.QR_LIMIT_SCENE,"100000",0);
            model.addAttribute("result",response.getTicket());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "user";
    }
    @RequestMapping("/user/initUser")
    public String InitUser(Model model,HttpServletRequest request)  {
        String openId=request.getParameter("openId");
        WxUserInfo user= userInfoService.getUserIdByOpenId(openId);
        int conut=userInfoService.countFamily(user);
        String level= CachedDict.getCachedName("LEVEL",String.valueOf(user.getUserId()),"");
        model.addAttribute("user",user);
        if (conut>0||level!=null){
            model.addAttribute("family","是");
        }else{
            model.addAttribute("family","否(<a href=\"/distribution/mer/init\" >点击成为族长</a>)");
        }
        if (user.getParentid()==null||userInfoService.getUserById(user.getParentid())==null){
            model.addAttribute("parent","乐享微赢");
        }else{
            model.addAttribute("parent",userInfoService.getUserById(user.getParentid()).getNickname());
        }
        model.addAttribute("firstFans",conut);
        int sonCount=userInfoService.countSenFans(user);
        int thirdCount=0;
        model.addAttribute("senFans",sonCount);
        if (sonCount!=0){
            thirdCount=userInfoService.countThirdFans(user);
        }
        model.addAttribute("thirdCount",thirdCount);
        model.addAttribute("openId",openId);
        return "user/userInit";
    }
    @RequestMapping("/user/code")
    public String getOauthCode(HttpServletRequest request){
        log.debug("===========================Oauth begin=========================");
        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");
        String orderId=request.getParameter("state");
        // 用户同意授权
        if (!"authdeny".equals(code)) {
            ApiConfig config=new ApiConfig(Config.APPID,Config.AppSecret,true);
            OauthAPI oauthAPI=new OauthAPI(config);
            OauthGetTokenResponse response=oauthAPI.getToken(code);
            log.info("=======OauthGetTokenResponse.OpenId:"+response.getOpenid()+"=====");
            if (response!=null){
                return "forward:/user/initUser?openId="+response.getOpenid();
            }
        }
        return "/404";
    }
    @RequestMapping("/user/list")
    public String getUserList(HttpServletRequest request,Model model){
        Integer userId=Integer.valueOf(request.getParameter("userId"));
        String type=request.getParameter("type");
        Integer page=Integer.valueOf(request.getParameter("page"));
        Integer start=(page-1)*5;
        List<WxUserInfo> userInfoList=new ArrayList<>();
        if ("one".equals(type)){
            userInfoList= userInfoService.getFirstList(userId, start);

        }else if("second".equals(type)){
            userInfoList=userInfoService.getSecondList(userId,start);
        }else if("third".equals(type)){
            userInfoList=userInfoService.getThirdList(userId,start);
        }
        model.addAttribute("list",userInfoList);
        return "/user/fansDetail";
    }

}
