package com.core.util;

import com.core.WeChat.Config;
import com.iboot.weixin.api.OauthAPI;
import com.iboot.weixin.api.config.ApiConfig;
import com.iboot.weixin.api.entity.Menu;
import com.iboot.weixin.api.entity.MenuButton;
import com.iboot.weixin.api.enums.MenuType;
import com.iboot.weixin.api.enums.OauthScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by core on 15/11/7.
 */
public class MenuManager {
    private static Logger log= LoggerFactory.getLogger(MenuManager.class);
    private static ApiConfig config=new ApiConfig(Config.APPID,Config.AppSecret,true);
    private static OauthAPI oauthAPI = new OauthAPI(config);
    public  Menu getMenu(){
        log.debug("============="+ Config.SER_URL+"=================");
        Menu menu=new Menu();
        List<MenuButton> button=new ArrayList<>(3);
        MenuButton store=new MenuButton();
        store.setName("商城");
        store.setUrl(oauthAPI.getOauthPageUrl(Config.SER_URL+"/distribution/mer/code", OauthScope.SNSAPI_BASE, ""));
        store.setType(MenuType.VIEW);
        MenuButton family=new MenuButton();
        family.setName("我的家族");
        family.setUrl( oauthAPI.getOauthPageUrl(Config.SER_URL+"/distribution/user/code", OauthScope.SNSAPI_BASE, ""));
        family.setType(MenuType.VIEW);
        MenuButton QrCard=new MenuButton();
        QrCard.setName("二维码");
        QrCard.setUrl( oauthAPI.getOauthPageUrl(Config.SER_URL + "/distribution/card/code", OauthScope.SNSAPI_BASE, ""));
        QrCard.setType(MenuType.VIEW);
        button.add(store);
        button.add(family);
        button.add(QrCard);
        menu.setButton(button);
        return menu;
    }
    public static void main(String[] arg) throws UnsupportedEncodingException {
        System.out.println(URLEncoder.encode("https%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60", "utf-8"));
    }
}
