package com.core.util.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by core on 15/11/28.
 */
@XmlRootElement(
        name = "xml"
)
@XmlAccessorType(XmlAccessType.FIELD)
public class PayNotify {
    @XmlElement
    private String return_code;
    @XmlElement
    private String return_msg;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }
}
