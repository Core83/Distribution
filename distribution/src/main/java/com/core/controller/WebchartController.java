package com.core.controller;

import com.core.WeChat.Config;
import com.core.service.IWxAppService;
import com.core.service.ScanHandler;
import com.core.service.SubscribeHandler;
import com.core.service.UnSubscribeHandler;
import com.iboot.weixin.handle.EventHandle;
import com.iboot.weixin.handle.MessageHandle;
import com.iboot.weixin.message.BaseMsg;
import com.iboot.weixin.message.TextMsg;
import com.iboot.weixin.message.aes.AesException;
import com.iboot.weixin.message.aes.WXBizMsgCrypt;
import com.iboot.weixin.message.req.BaseEvent;
import com.iboot.weixin.message.req.TextReqMsg;
import com.iboot.weixin.servlet.WeixinControllerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by core on 15/11/2.
 */
@Controller
@RequestMapping("/weixin")
public class WebchartController extends WeixinControllerSupport {
    private static final Logger log = LoggerFactory.getLogger(WebchartController.class);
    private static final String TOKEN = Config.TOKEN;
    private static final String APPID = Config.APPID;
    private static final String AESKey = Config.AESKey;
    @Autowired
    private IWxAppService wxAppService;
    @Autowired
    SubscribeHandler subcriberHandler;
    @Autowired
    UnSubscribeHandler unSubcriberHandler;
    @Autowired
    ScanHandler scanHandler;
//    private WXBizMsgCrypt wxcpt;
//    /**
//     * 处理微信服务器验证
//     * id为webchart_app表中的id，用来区分是哪个公众号发过来的消息
//     */
//    @RequestMapping(value="/webchat/{id}",method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
//    protected void doGet(@PathVariable String id,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//        String signature = request.getParameter(Config.SIGNATURE);// 微信加密签名
//        String timestamp = request.getParameter(Config.TIME_STAMP);// 时间戳
//        String nonce = request.getParameter(Config.NONCE);// 随机数
//        String echostr = request.getParameter(Config.ECHO_STR);// 随机字符串
//        PrintWriter out = response.getWriter();
//        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
//        String result = null;
//        try {
//            TWxApp wxApp = wxAppService.getWxApp(id);
//            wxcpt = new WXBizMsgCrypt(wxApp.getToken(),wxApp.getEncodingaeskey(),wxApp.getAppid());
//
//            result = wxcpt.VerifyURL(signature, timestamp, nonce, echostr);
//        } catch (AesException e) {
//            e.printStackTrace();
//        }
//        out.print(result);
//        out.close();
//        out = null;
//    }

    @Override
    protected String getToken() {
        return TOKEN;
    }

    @Override
    protected String getAppId() {
        return APPID;
    }

    @Override
    protected String getAESKey() {
        return AESKey;
    }
    @Override
    protected BaseMsg handleTextMsg(TextReqMsg msg) {
        String content = msg.getContent();
        log.debug("用户发送到服务器的内容:{}", content);
        return new TextMsg("Hello~!");
    }

    @Override
    protected List<MessageHandle> initMessageHandles() {
        List<MessageHandle> handles = new ArrayList<MessageHandle>();
        return handles;
    }

    @Override
    protected List<EventHandle> initEventHandles() {
        List<EventHandle> handles = new ArrayList<EventHandle>();
        handles.add(subcriberHandler);
        handles.add(unSubcriberHandler);
        handles.add(scanHandler);
//        handles.add(qiangMenuHandler);
//        handles.add(orderMenuHandler);
        return handles;
    }


    public static void main(String[] args) throws AesException {
        WXBizMsgCrypt pc = new WXBizMsgCrypt(Config.TOKEN, Config.AESKey, Config.APPID);
        String message = pc.decryptMsg("d35e0a920860b718544690d13872eb1111a9c0a3", "1448644223", "405130753", "<xml>\n" +
                "    <ToUserName><![CDATA[gh_0ef31c6e6d1f]]></ToUserName>\n" +
                "    <Encrypt><![CDATA[dg2SKeAFoqhdthtXrXwJSH8RxgVYg85sd2q1z30ari8/P7+WxhxreuK/BdOQ+hUnf0cG+eMoOovajY4gAhIuMvSufzC/5ZkWFZXnb5ozMtT+9+8v/PwqJMkiVIL6+BsOnDpVQKTl8YMqpiyIYEW8Y6O9rpz/Qi+8hanq4Zrw4AYRw1KYhDT64PmzQsjgJYG17nI5eEN4VhyVJpfbStcst9YFyB30Yr6uJD28Fd4ij4QHlNhmixcT3tzuBdW6Awd6GOIdHR4RRPdjCSQ6+x7r825Id7dqFho2apa+X6yPJnIso6EIzMjUI3/s7k2A4IX4LbmwGOxzdpzHuPXYmqhahwLds753vvocximc5I3W0rq55qUvlQ5ulLyGLT9mqFzju6FxdOhDAUyxZPamYNFbN91Fh5G4ZZhnQgjnFYm8sBU=]]></Encrypt>\n" +
                "</xml>");
        System.out.println(message);
        System.out.println(BaseEvent.class);

    }
}
