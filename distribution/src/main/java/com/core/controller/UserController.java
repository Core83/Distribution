package com.core.controller;

import com.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by core on 15/10/29.
 */
@Controller
public class UserController {
    @Autowired
    private IUserService userService;
    @RequestMapping(value = "/showUser",method = RequestMethod.GET)
    public String showUser(Model model)  {

        try {
            model.addAttribute("user", userService.getUser("admin", "123456"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "user";
    }
}
