package com.core.util.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by core on 15/11/16.
 */
@XmlRootElement(
        name = "xml"
)
@XmlAccessorType(XmlAccessType.FIELD)
public class RedPacketRequest {
    @XmlElement
    private String nonce_str;
    @XmlElement
    private String sign;
    @XmlElement
    private String mch_billno;
    @XmlElement
    private String mch_id;
    @XmlElement
    private String sub_mch_id;
    @XmlElement
    private String wxappid;
    @XmlElement
    private String nick_name;
    @XmlElement
    private String send_name;
    @XmlElement
    private String re_openid;
    @XmlElement
    private String total_amount;
    @XmlElement
    private String min_value;
    @XmlElement
    private String max_value;
    @XmlElement
    private String total_num;
    @XmlElement
    private String wishing;
    @XmlElement
    private String client_ip;
    @XmlElement
    private String act_name;
    @XmlElement
    private String remark;
    @XmlElement
    private String logo_imgurl;
    @XmlElement
    private String share_content;
    @XmlElement
    private String share_url;
    @XmlElement
    private String share_imgurl;

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMch_billno() {
        return mch_billno;
    }

    public void setMch_billno(String mch_billno) {
        this.mch_billno = mch_billno;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getSub_mch_id() {
        return sub_mch_id;
    }

    public void setSub_mch_id(String sub_mch_id) {
        this.sub_mch_id = sub_mch_id;
    }

    public String getWxappid() {
        return wxappid;
    }

    public void setWxappid(String wxappid) {
        this.wxappid = wxappid;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getSend_name() {
        return send_name;
    }

    public void setSend_name(String send_name) {
        this.send_name = send_name;
    }

    public String getRe_openid() {
        return re_openid;
    }

    public void setRe_openid(String re_openid) {
        this.re_openid = re_openid;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getMin_value() {
        return min_value;
    }

    public void setMin_value(String min_value) {
        this.min_value = min_value;
    }

    public String getMax_value() {
        return max_value;
    }

    public void setMax_value(String max_value) {
        this.max_value = max_value;
    }

    public String getTotal_num() {
        return total_num;
    }

    public void setTotal_num(String total_num) {
        this.total_num = total_num;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    public String getClient_ip() {
        return client_ip;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLogo_imgurl() {
        return logo_imgurl;
    }

    public void setLogo_imgurl(String logo_imgurl) {
        this.logo_imgurl = logo_imgurl;
    }

    public String getShare_content() {
        return share_content;
    }

    public void setShare_content(String share_content) {
        this.share_content = share_content;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getShare_imgurl() {
        return share_imgurl;
    }

    public void setShare_imgurl(String share_imgurl) {
        this.share_imgurl = share_imgurl;
    }

    @Override
    public String toString() {
        return "RedPacketRequest{" +
                "nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                ", mch_billno='" + mch_billno + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", sub_mch_id='" + sub_mch_id + '\'' +
                ", wxappid='" + wxappid + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", send_name='" + send_name + '\'' +
                ", re_openid='" + re_openid + '\'' +
                ", total_amount='" + total_amount + '\'' +
                ", min_value='" + min_value + '\'' +
                ", max_value='" + max_value + '\'' +
                ", total_num='" + total_num + '\'' +
                ", wishing='" + wishing + '\'' +
                ", client_ip='" + client_ip + '\'' +
                ", act_name='" + act_name + '\'' +
                ", remark='" + remark + '\'' +
                ", logo_imgurl='" + logo_imgurl + '\'' +
                ", share_content='" + share_content + '\'' +
                ", share_url='" + share_url + '\'' +
                ", share_imgurl='" + share_imgurl + '\'' +
                '}';
    }
}
