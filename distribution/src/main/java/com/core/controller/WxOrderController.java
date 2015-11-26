package com.core.controller;

import com.core.WeChat.Config;
import com.core.model.*;
import com.core.service.*;
import com.core.util.CachedDict;
import com.core.util.RedPacketAPI;
import com.core.util.SequenceUtil;
import com.core.util.entity.RedPacketRequest;
import com.core.util.entity.RedPacketResult;
import com.iboot.weixin.util.MapUtil;
import com.iboot.weixin.util.PayUtil;
import com.iboot.weixin.util.SignatureUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by core on 15/11/8.
 */
@Controller
@RequestMapping("/order")
public class WxOrderController {
    @Autowired
    private IWxOrderService orderService;
    @Autowired
    private IWxUserInfoService userInfoService;
    @Autowired
    private IWxPayInfoService wxservice;
    @Autowired
    private IWxUserExtService extService;

    @Autowired
    private IWxOrderAddressService addressService;

    public static final Log log = LogFactory.getLog(WxOrderController.class);
    @RequestMapping("/init")
    public String merInit (Model model,HttpServletRequest request)  {
        String openId=request.getParameter("openId");
        WxUserInfo user=userInfoService.getUserIdByOpenId(openId);
        List<WxOrderInfo> list=orderService.getListByUser(user.getUserId());
        model.addAttribute("openId",openId);
        model.addAttribute("orders",list);
        return "order/orderList";
    }
    @RequestMapping("/detail")
    public String show(Model model,HttpServletRequest request) throws Exception {
        String openId=request.getParameter("openId");
        String orderId=request.getParameter("orderId");
        WxOrderInfo order=orderService.getById(Long.valueOf(orderId));
        Integer userId=order.getUserId();
        WxOrderAddress address=addressService.getById(userId,Long.valueOf(orderId));
        model.addAttribute("Address",address );
        model.addAttribute("order",order);
        model.addAttribute("openId",openId);
        return "order/orderDetail";
    }
    @RequestMapping(value="/notify")
    public @ResponseBody void weixinnotify(HttpServletRequest request){
        try {
            ServletInputStream in = request.getInputStream();
            SAXReader saxReader = new SAXReader();
            InputStreamReader strInStream = new InputStreamReader(in, "UTF-8");
            Document document = saxReader.read(strInStream);
            log.debug(document.asXML());
            Element root = document.getRootElement();
            String total_fee = checkElementIsNotNull(root.element("total_fee"));
            String order_id = checkElementIsNotNull(root.element("out_trade_no"));
            String time_end = checkElementIsNotNull(root.element("time_end"));
            Integer payId=Integer.valueOf(order_id.substring(order_id.length() - 9, order_id.length() - 1));
            if(!"".equals(order_id)){
                List<WxPayInfo> listPayInfo = wxservice.getPayInfoByOrderId(payId);
                if(listPayInfo==null || listPayInfo.size()==0){
                    WxPayInfo payInfo = new WxPayInfo();
                    payInfo.setOrderId(payId);
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
                    Timestamp ts = new Timestamp(sdf.parse(time_end).getTime());
                    payInfo.setPayTime(ts);
                    payInfo.setPayAmount(Integer.parseInt(total_fee));
                    wxservice.insertWxPayInfo(payInfo);
                    WxOrderInfo wxorder = new WxOrderInfo();
                    wxorder.setOrderId(Long.parseLong(order_id));
                    wxorder.setPayStatus(1);
                    wxorder.setStatus(2);
                    orderService.insertOrUpdateWxOrder(wxorder);
                    wxorder=orderService.getById(Long.parseLong(order_id));
                    WxUserInfo parent=  userInfoService.getParentOpenId(orderService.getById(Long.parseLong(order_id)).getUserId());
                    Integer parentId=parent.getParentid();
                    WxUserInfo grandPa=userInfoService.getParentOpenId(parentId);
                    WxUserExt ext=new WxUserExt();
                    ext.setUserId(wxorder.getUserId());
                    ext.setExtKey("LEVEL");
                    int count=wxorder.getGdsCount()%6;
                    switch (count){
                        case 1:ext.setExtValue("1");red(parent.getOpenId(), String.valueOf(wxorder.getGdsAmount()* 0.05));break;
                        case 2:ext.setExtValue("2");
                               if(Integer.valueOf(CachedDict.getCachedName("LEVEL",String.valueOf(parent.getUserId()),""))>=2){
                                   red(parent.getOpenId(), String.valueOf(wxorder.getGdsAmount()* 0.05));
                               }
                               if(Integer.valueOf(CachedDict.getCachedName("LEVEL",String.valueOf(grandPa.getUserId()),""))>=2){
                                    red(parent.getOpenId(), String.valueOf(wxorder.getGdsAmount()* 0.1));
                               }
                               break;
                        case 3:
                        case 4:
                        case 5:
                        case 0:ext.setExtValue("3");
                            if(Integer.valueOf(CachedDict.getCachedName("LEVEL",String.valueOf(parent.getUserId()),""))>=3){
                            red(parent.getOpenId(), String.valueOf(wxorder.getGdsAmount()* 0.05));
                            }
                            if(Integer.valueOf(CachedDict.getCachedName("LEVEL",String.valueOf(grandPa.getUserId()),""))>=3){
                                red(parent.getOpenId(), String.valueOf(wxorder.getGdsAmount()* 0.1));
                            }
                            if(Integer.valueOf(CachedDict.getCachedName("LEVEL",String.valueOf(grandPa.getParentid()),""))>=3){
                                WxUserInfo info=  userInfoService.getUserById(grandPa.getParentid());
                                red(info.getOpenId(), String.valueOf(wxorder.getGdsAmount() * 0.15));
                            }
                            break;
                    }
                    extService.insert(ext);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("--------weixin notify--------");
    }
    private String checkElementIsNotNull(Element e){
        if(e!=null){
            return e.getText();
        }else{
            return "";
        }
    }
    private RedPacketResult red(String openId,String totalAmount) throws UnknownHostException{
        RedPacketRequest re=new RedPacketRequest();
        re.setMch_id(Config.MCHID);
        re.setWxappid(Config.APPID);
        re.setNonce_str(PayUtil.getNonceStr());
        re.setNick_name(Config.APPNAME);
        re.setSend_name(Config.APPNAME);
        re.setRe_openid(openId);
        if (Integer.valueOf(totalAmount)>2000){
         totalAmount=String.valueOf(Integer.valueOf(totalAmount)-2000);
         red(openId,"2000");
        }else if(Integer.valueOf(totalAmount)<100 ){
            totalAmount="100";
        }
        re.setTotal_amount(totalAmount);
        re.setMch_billno(Config.MCHID+ SequenceUtil.redId());
        re.setMax_value(totalAmount);
        re.setMin_value(totalAmount);
        re.setTotal_num("1");
        re.setWishing("恭喜发财！");
        InetAddress ia=InetAddress.getLocalHost();
        re.setClient_ip(ia.getHostAddress().toString());
        re.setAct_name("佣金返还活动");
        re.setRemark("佣金返还");
        Map<String, String> map = MapUtil.objectToMap(re, null);
        String sign = SignatureUtil.generateSign(map, Config.MCHID);
        re.setSign(sign);
        RedPacketResult result=RedPacketAPI.payRed(re);
        return result;
    }

}
