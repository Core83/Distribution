package com.core.controller;

import com.core.WeChat.Config;
import com.core.util.MenuManager;
import com.iboot.weixin.api.MenuAPI;
import com.iboot.weixin.api.config.ApiConfig;
import com.iboot.weixin.api.entity.Menu;
import com.iboot.weixin.api.enums.ResultType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by core on 15/11/11.
 */
@Controller
public class MenuController {
    @RequestMapping("/createMenu")
    public String InitUser(Model model,HttpServletRequest request)  {
        ApiConfig config=new ApiConfig(Config.APPID,Config.AppSecret,true);
        Menu menu=new MenuManager().getMenu();
        ResultType resultType=new MenuAPI(config).createMenu(menu);
        model.addAttribute("result",resultType.toString());
        return "user";
    }
}
