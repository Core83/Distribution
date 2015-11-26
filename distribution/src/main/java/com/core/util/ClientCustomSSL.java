package com.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.Map;

import javax.net.ssl.SSLContext;

import com.core.WeChat.Config;
import com.core.util.entity.RedPacketRequest;
import com.iboot.weixin.util.MapUtil;
import com.iboot.weixin.util.PayUtil;
import com.iboot.weixin.util.SignatureUtil;
import com.iboot.weixin.util.XMLConverUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by core on 15/11/18.
 */
public class ClientCustomSSL {
    public final static void main(String[] args) throws Exception {
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File("/Users/core/Documents/gitRepository/distribution/target/distribution/statics/cert/apiclient_cert.p12"));
        try {
            keyStore.load(instream, "1285414101".toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, "1285414101".toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
            RedPacketRequest re=new RedPacketRequest();
            re.setMch_id(Config.MCHID);
            re.setWxappid(Config.APPID);
            re.setNonce_str(PayUtil.getNonceStr());
            re.setNick_name(Config.APPNAME);
            re.setSend_name(Config.APPNAME);
            re.setRe_openid("o0kTKwrbr49aaVSNvtuRIffV9QWc");
            re.setTotal_amount("100");
            re.setMax_value("100");
            re.setMin_value("100");
            re.setTotal_num("1");
            re.setWishing("恭喜发财！");
            re.setMch_billno(Config.MCHID+"201511191234567891");
            InetAddress ia=InetAddress.getLocalHost();
            re.setClient_ip(ia.getHostAddress().toString());
            re.setAct_name("佣金返还");
            re.setRemark("佣金返还");
            Map<String, String> map = MapUtil.objectToMap(re, null);
            String sign = SignatureUtil.generateSign(map,"128541410112854141011285414101sf");
            re.setSign(sign);
            String xml= XMLConverUtil.convertToXml(re, "UTF-8");
            HttpPost httpPost=new HttpPost("https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack");
            httpPost.setEntity(new StringEntity(xml, Charset.forName("utf-8")));
//            HttpGet httpget = new HttpGet("https://api.mch.weixin.qq.com/secapi/pay/refund");

            System.out.println("executing request" + httpPost.getRequestLine());

            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        System.out.println(text);
                    }

                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
    public  static CloseableHttpClient getCloseableHttpClient(String fileName,String key)throws Exception{
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(fileName));
        try {
            keyStore.load(instream, key.toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, key.toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        return  httpclient;
    }
}
