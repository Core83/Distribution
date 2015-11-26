package com.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by core on 15/11/15.
 */
public class SequenceUtil {
    /**
     * 根据前缀、当前时间yyyyMMddHHmmssms、后缀生成ID
     *
     * @param prefix
     *            可空
     * @param suffix
     *            可空
     * @return
     */
    public static Long genarateIdyyyyMMdd(String prefix, String suffix) {
        StringBuffer sb = new StringBuffer();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssms");
        Date dt = new Date();
        sb.append(prefix).append(format.format(dt));
        return Long.valueOf(sb.toString());
    }
    public static void main(String[] args)  {
        System.out.println(genarateIdyyyyMMdd("1", "2"));
    }
}
