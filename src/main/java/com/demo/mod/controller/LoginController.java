package com.demo.mod.controller;

import com.demo.mod.entity.User;
import com.demo.mod.service.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final static Logger LOGGER= LoggerFactory.getLogger(LoginController.class);
    @Autowired
    MainService mainService;

    /**
     * 登陆页
     * @param map
     * @return
     */
    @RequestMapping("/goLogin")
    public String goLogin(@RequestParam Map<String,Object> map,Map viewMap){
        User user = mainService.userLogin((String) map.get("username"), (String) map.get("password"));
        LOGGER.info(user.toString());
        viewMap.put("msg","登陆成功");
        if (user==null){
            viewMap.put("msg","登陆失败");
            return "/login";
        }
        return "/index";
    }
}
