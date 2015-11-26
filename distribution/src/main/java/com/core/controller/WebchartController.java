package com.core.controller;

import com.core.WeChat.Config;
import com.core.WeChat.aes.AesException;
import com.core.WeChat.aes.WXBizMsgCrypt;
import com.core.model.TWxApp;
import com.core.service.IWxAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by core on 15/11/2.
 */
@Controller
public class WebchartController {
    @Autowired
    private IWxAppService wxAppService;
    private WXBizMsgCrypt wxcpt;
    /**
     * 处理微信服务器验证
     * id为webchart_app表中的id，用来区分是哪个公众号发过来的消息
     */
    @RequestMapping(value="/webchat/{id}",method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
    protected void doGet(@PathVariable String id,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String signature = request.getParameter(Config.SIGNATURE);// 微信加密签名
        String timestamp = request.getParameter(Config.TIME_STAMP);// 时间戳
        String nonce = request.getParameter(Config.NONCE);// 随机数
        String echostr = request.getParameter(Config.ECHO_STR);// 随机字符串
        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        String result = null;
        try {
            TWxApp wxApp = wxAppService.getWxApp(id);
            wxcpt = new WXBizMsgCrypt(wxApp.getToken(),wxApp.getEncodingaeskey(),wxApp.getAppid());

            result = wxcpt.VerifyURL(signature, timestamp, nonce, echostr);
        } catch (AesException e) {
            e.printStackTrace();
        }
        out.print(result);
        out.close();
        out = null;
    }
}
