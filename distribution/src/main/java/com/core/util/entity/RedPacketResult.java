package com.core.util.entity;

import com.iboot.weixin.bean.AdaptorCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Created by core on 15/11/16.
 */
@XmlRootElement(
        name = "xml"
)
@XmlAccessorType(XmlAccessType.FIELD)
public class RedPacketResult {
    @XmlElement
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String return_code;
    @XmlElement
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String return_msg;
    @XmlElement
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String sign;
    @XmlElement
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String result_code;
    @XmlElement
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String err_code;
    @XmlElement
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String err_code_des;
    @XmlElement
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String mch_id;
    @XmlElement
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String mch_billno;
    @XmlElement
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String wxappid;
    @XmlElement
    @XmlJavaTypeAdapter(AdaptorCDATA.class)
    private String re_openid;

    public String getTotal_amount() {
        return total_amount;
    }

    public String getReturn_code() {
        return return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public String getSign() {
        return sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public String getMch_id() {
        return mch_id;
    }

    public String getMch_billno() {
        return mch_billno;
    }

    public String getWxappid() {
        return wxappid;
    }

    public String getRe_openid() {
        return re_openid;
    }

    @XmlElement
    @XmlJavaTypeAdapter(AdaptorCDATA.class)

    private String total_amount;

    @Override
    public String toString() {
        return "RedPacketResult{" +
                "return_code='" + return_code + '\'' +
                ", return_msg='" + return_msg + '\'' +
                ", sign='" + sign + '\'' +
                ", result_code='" + result_code + '\'' +
                ", err_code='" + err_code + '\'' +
                ", err_code_des='" + err_code_des + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", mch_billno='" + mch_billno + '\'' +
                ", wxappid='" + wxappid + '\'' +
                ", re_openid='" + re_openid + '\'' +
                ", total_amount='" + total_amount + '\'' +
                '}';
    }
}
