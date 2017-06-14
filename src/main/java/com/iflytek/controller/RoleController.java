package com.iflytek.controller;

import com.iflytek.dao.RoleDao;
import com.iflytek.model.SYS_Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by admin on 2017/4/20.
 */
@Controller
public class RoleController extends BaseController{

    @Autowired
    private RoleDao roleDao;


    @RequestMapping("/role")
    public String role(Model model){
        List<SYS_Role> roles = roleDao.findAll();
        model.addAttribute("roles",roles);
        model.addAttribute("menus",getMenu());
        return "role";
    }

    @RequestMapping(value = "/role/createRole",method = RequestMethod.POST)
    public String createRole(SYS_Role role){
        int i = roleDao.createRole(role);
        return "redirect:/role";
    }
}
