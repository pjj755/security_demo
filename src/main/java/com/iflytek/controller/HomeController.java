package com.iflytek.controller;

import com.iflytek.dao.MenuDao;
import com.iflytek.model.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2017/4/18.
 */

@Controller
public class HomeController extends BaseController{

    @Autowired
    private MenuDao menuDao;


    @RequestMapping("/")
    public String index(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String qx = "";
        for(GrantedAuthority authentication : userDetails.getAuthorities()){
            qx += authentication.getAuthority()+"   ";
        }
        Msg msg =  new Msg("测试标题","测试内容",qx);
        model.addAttribute("menus",getMenu());
        model.addAttribute("msg", msg);
        return "home";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello all";
    }

    @RequestMapping("/auth")
    @ResponseBody
    public String auth(){
        return "hello auth";
    }

}
