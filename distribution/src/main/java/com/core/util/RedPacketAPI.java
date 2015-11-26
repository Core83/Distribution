package com.core.util;

import com.core.WeChat.Config;
import com.core.util.entity.RedPacketRequest;
import com.core.util.entity.RedPacketResult;
import com.iboot.weixin.api.BasePayAPI;
import com.iboot.weixin.util.MapUtil;
import com.iboot.weixin.util.PayUtil;
import com.iboot.weixin.util.SignatureUtil;
import com.iboot.weixin.util.XMLConverUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by core on 15/11/16.
 */
public class RedPacketAPI extends BasePayAPI {
    private static final Logger LOG = LoggerFactory.getLogger(RedPacketAPI.class);

    public RedPacketAPI() {
        super();
    }
    public static RedPacketResult payRed(RedPacketRequest request){
        LOG.debug("===========red packing=====================");
        try {
            CloseableHttpClient httpclient=ClientCustomSSL.getCloseableHttpClient(Config.keyStoreFilePath,Config.MCHID);
            String xml= XMLConverUtil.convertToXml(request, "UTF-8");
            System.out.println(xml);
            HttpPost httpPost=new HttpPost("https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack");
            httpPost.setEntity(new StringEntity(xml, Charset.forName("UTF-8")));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            int status = response.getStatusLine().getStatusCode();
            if(status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                String str = EntityUtils.toString(entity);
                return XMLConverUtil.convertToObject(RedPacketResult.class, str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
//        HttpUriRequest httpUriRequest = RequestBuilder.post().setHeader(xmlHeader).setUri("https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack").setEntity(new StringEntity(xml, Charset.forName("utf-8"))).build();
//        LocalHttpClient.initMchKeyStore("PKCS12", Config.keyStoreFilePath, Config.MCHID, 100, 10);
//        return LocalHttpClient.keyStoreExecuteXmlResult(Config.MCHID,httpUriRequest, RedPacketResult.class);
    }

    public static void main(String[] args) throws UnknownHostException {
        RedPacketRequest re=new RedPacketRequest();
        re.setMch_id(Config.MCHID);
        re.setWxappid(Config.APPID);
        re.setNonce_str(PayUtil.getNonceStr());
        re.setNick_name("pxh");
        re.setSend_name("pxh");
        re.setRe_openid("o0kTKwrbr49aaVSNvtuRIffV9QWc");
        re.setTotal_amount("4000");
        re.setMax_value("4000");
        re.setMin_value("4000");
        re.setTotal_num("1");
        re.setWishing("sfsd");
        re.setMch_billno(Config.MCHID+"201511191234567895");
        InetAddress ia=InetAddress.getLocalHost();
        re.setClient_ip(ia.getHostAddress().toString());
        re.setAct_name("yjfh");
        re.setRemark("yjfh");
        Map<String, String> map = MapUtil.objectToMap(re, null);
        String sign = SignatureUtil.generateSign(map, Config.singKey);
        re.setSign(sign);
        RedPacketResult req= RedPacketAPI.payRed(re);
        System.out.println(req.toString());
    }
}
