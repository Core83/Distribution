package com.core.controller;

import com.core.WeChat.Config;
import com.core.util.CachedDict;
import com.core.util.HttpClientUtil;
import com.core.util.SequenceUtil;
import com.core.model.*;
import com.core.service.*;
import com.iboot.weixin.api.OauthAPI;
import com.iboot.weixin.api.PayMchAPI;
import com.iboot.weixin.api.config.ApiConfig;
import com.iboot.weixin.api.enums.OauthScope;
import com.iboot.weixin.api.response.BaseResponse;
import com.iboot.weixin.api.response.OauthGetTokenResponse;
import com.iboot.weixin.bean.pay.PayJsRequest;
import com.iboot.weixin.bean.paymch.Unifiedorder;
import com.iboot.weixin.bean.paymch.UnifiedorderResult;
import com.iboot.weixin.util.MapUtil;
import com.iboot.weixin.util.NetWorkCenter;
import com.iboot.weixin.util.PayUtil;
import com.iboot.weixin.util.SignatureUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by core on 15/11/8.
 */
@Controller
@RequestMapping("/mer")
public class MerController {
    @Autowired
    private IWxMerInfoService merInfoService;
    @Autowired
    private IWxMerPicService picService;
    @Autowired
    private IWxUserInfoService userInfoService;
    @Autowired
    private IWxOrderService orderService;
    @Autowired
    private IWxOrderAddressService addressService;
    @Autowired
    private IViewCacheService viewCacheService;
    @Autowired
    private IWxUserExtService extService;
    private ApiConfig config=new ApiConfig(Config.APPID,Config.AppSecret,true);
    public static final Log log = LogFactory.getLog(MerController.class);
    @RequestMapping("/init")
     public String merInit (Model model,HttpServletRequest request)  {
        List<WxMerInfo> list=merInfoService.getMerLists();
        model.addAttribute("mers",list);
        String openId=request.getParameter("openId");
        model.addAttribute("openId",openId);
        return "mer/goodList";
    }
    @RequestMapping("/detail")
    public String merDetail (Model model,@RequestParam("gdsId") Integer gdsId,HttpServletRequest request)  {
        List<WxMerPic> list=picService.getListByExample(gdsId);
        model.addAttribute("mer",merInfoService.getOneById(gdsId));
        model.addAttribute("merPics",list);
        String openId=request.getParameter("openId");
        model.addAttribute("openId",openId);
        WxUserInfo user=userInfoService.getUserIdByOpenId(openId);
        ViewCache vc=viewCacheService.findCacheByKeyAndValue("LEVEL", String.valueOf(user.getUserId()));
        String level=vc==null?"-1":vc.getpDesc();
        model.addAttribute("level",level);
        return "mer/goodDetail";
    }

    /**
     * 进入下单界面
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/orderInit")
    public String orderInit (Model model,HttpServletRequest request,WxOrderInfo orderInfo) throws Exception {
        if (orderInfo.getOrderId()==null){
            orderInfo.setOrderId(SequenceUtil.genarateIdyyyyMMdd("1",""));
            orderInfo.setStatus(1);
            Integer price=Integer.valueOf(request.getParameter("gdsPrice"));
            int count=orderInfo.getGdsAmount()/price;
            orderInfo.setGdsCount(count);
            orderInfo.setCreateTime(new Date());
            orderService.insertOrUpdateWxOrder(orderInfo);
        }else{
            orderInfo=orderService.getById(orderInfo.getOrderId());
        }
        String openId=request.getParameter("openId");
        orderInfo.setUserId(userInfoService.getUserIdByOpenId(openId).getUserId());
        orderService.insertOrUpdateWxOrder(orderInfo);
        log.info("==============orderInit;openid:"+openId+"================");
        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("openId",openId);
        return "mer/goodReview";
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
                return "forward:/mer/init?openId="+response.getOpenid();
            }
        }
        return "/404";
    }
    /**
     * 微信提交订单
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/submit")
    public ModelAndView submit(Model model, WxOrderAddress address,@RequestParam("orderId") Long orderId, HttpServletRequest request) throws Exception {
        WxOrderInfo orderInfo=orderService.getById(orderId);
        String payType=request.getParameter("payType");
        String openId=request.getParameter("openId");
        log.info("==============openid:"+openId+"================");
        orderInfo.setPayType(payType);
        orderInfo.setUserId(userInfoService.getUserIdByOpenId(openId).getUserId());
        orderService.insertOrUpdateWxOrder(orderInfo);
        Integer userId=orderInfo.getUserId();
        address.setUserId(userId);
        addressService.Insert(address);
        model.addAttribute("orderId",orderId);
        Unifiedorder unifiedorder = new Unifiedorder();
        unifiedorder.setAppid(Config.APPID);
        unifiedorder.setMch_id(Config.MCHID);
        unifiedorder.setNonce_str(PayUtil.getNonceStr());
        unifiedorder.setBody(CachedDict.getCachedName("MER_NAME",String.valueOf(orderInfo.getGdsId()),""));
        unifiedorder.setOut_trade_no(orderId + "");
        unifiedorder.setTotal_fee(orderInfo.getGdsAmount().toString());
        unifiedorder.setSpbill_create_ip(InetAddress.getLocalHost().getHostAddress());
        unifiedorder.setNotify_url(Config.SER_URL+"/distribution/order/notify");
        unifiedorder.setTrade_type("JSAPI");
        unifiedorder.setGoods_tag("1");
        unifiedorder.setOpenid(openId);
        unifiedorder.setProduct_id(orderInfo.getGdsId().toString());
        Map<String, String> map = MapUtil.objectToMap(unifiedorder, null);
        String sign = SignatureUtil.generateSign(map, Config.singKey);
        unifiedorder.setSign(sign);
        UnifiedorderResult result = PayMchAPI.payUnifiedorder(unifiedorder);
        log.info("========return_code:" + result.getReturn_code() + "return_msg:"+result.getReturn_msg()+"==prepay_id:"+ result.getPrepay_id() + "======");
        String prepay_id = result.getPrepay_id();
        String package_ = "prepay_id=" + prepay_id;
        WxUserExt ext=new WxUserExt(orderInfo.getUserId(),String.valueOf(orderInfo.getOrderId()),"0");
        ext.setExtValue(package_);
        extService.insert(ext);
        PayJsRequest payJsRequest = new PayJsRequest();
        payJsRequest.setAppId(Config.APPID);
        String nonceStr = PayUtil.getNonceStr();
        payJsRequest.setNonceStr(nonceStr);
        payJsRequest.setPackage_(package_);
        payJsRequest.setSignType("MD5");
        String timeString=System.currentTimeMillis() / 1000 + "";
        payJsRequest.setTimeStamp(timeString);
        Map<String, String> mapS = MapUtil.objectToMap(payJsRequest);
        String paySign = SignatureUtil.generateSign(mapS, Config.singKey);
        payJsRequest.setPaySign(paySign);
        model.addAttribute("appid", Config.APPID);
        model.addAttribute("nonceStr", nonceStr);
        model.addAttribute("timeStamp", timeString);
        model.addAttribute("package", package_);
        model.addAttribute("paySign", paySign);
        model.addAttribute("openId", request.getParameter("openId"));
        return new ModelAndView("/wx/pay");
    }

}
