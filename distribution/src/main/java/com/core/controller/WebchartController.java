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


    public static void main(String[] args) {
//        WXBizMsgCrypt pc = new WXBizMsgCrypt(Config.TOKEN, Config.AESKey, Config.APPID);
//        String message = pc.decryptMsg("7da55c334e6b5a51180c92ddf416e78417b61af4", "1448162519", "844826633", "<xml>\n" +
//                "    <ToUserName><![CDATA[gh_0ef31c6e6d1f]]></ToUserName>\n" +
//                "    <Encrypt><![CDATA[0ksH+cLJTf61prVomjX96sJ+MdQpGlI9AJzfDGdR+1Sd7OU7Vn0F0D1FPjQp6m7KcwGDzS72oNxTj+mn07uc2lWXgjKpmd4j/ZOEk20Ra2YTVAHJXyQNCsQX1Vd+Fijf1XvJnA5xeRxVVUcsz2UvQ59Uc4XD0Fh7kPVN6FTR3Fb91gfhSE/9gJrXBrt+UAcCUOs8Tn6wcQi35VQz2I5iC80XnZ70N6HSbs6hw1j/fJEcyhWSPrKYy1djGxuxJXmrjll3L/Vk7hwQRskihp56Ajdb2OiYXjn/cEnc8pZ4HowPr2n402Gtolp/defVNe2Lo7drIkvkYseV5Kbo7ms26R2lNygpN+mp6Tu+BYg4TvJbI/VNaoZ9HdCZJI+6lUcpK0Zum5OzZ5ieNtFdp7STnwIC2Qb5wZX27TRHzzW3r1Q=]]></Encrypt>\n" +
//                "</xml>\n");
//        System.out.println(message);

    }
}
