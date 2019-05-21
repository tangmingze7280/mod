package com.demo.mod.controller;

import com.demo.mod.entity.User;
import com.demo.mod.result.CommonResult;
import com.demo.mod.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final static Logger LOGGER= LoggerFactory.getLogger(LoginController.class);
    @Autowired
    MainService mainService;

    /**
     * 登陆页
     * @param username
     * @param password
     * @param viewMap
     * @param servletRequest
     * @return
     */
    @PostMapping("/goLogin")
    public String goLogin(@RequestParam("username") String username,@RequestParam("password")String password,Map viewMap,HttpServletRequest servletRequest){
        LOGGER.info(username+password);
        User user = mainService.userLogin(username,password);
        LOGGER.info(user.toString());
        viewMap.put("msg","登陆成功");
        viewMap.put("userInfo",user);
        if (user==null){
            viewMap.put("msg","登陆失败");
            return "login";
        }
        servletRequest.getSession().setAttribute("user",user);
        return "index";
    }
}
