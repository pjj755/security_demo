package com.iflytek.controller;

import com.alibaba.fastjson.JSONObject;
import com.iflytek.dao.RoleDao;
import com.iflytek.dao.UserDao;
import com.iflytek.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by admin on 2017/4/20.
 */

@Controller
public class UserController extends BaseController{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @RequestMapping("/user")
    public String user(Model model){
        List<SYS_User> users = userDao.findAll();
        model.addAttribute("users",users);
        model.addAttribute("menus",getMenu());
        return "user";
    }

    @RequestMapping(value = "/user/createUser",method = RequestMethod.POST)
    public String createUser(SYS_User u){
        int i = userDao.createUser(u);
        System.out.println("----------------------------id______"+i);
        return "redirect:/user";
    }

    @RequestMapping(value="/user/UpdateRole/getInfo/{uid}",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getBtnByRoleID(@PathVariable("uid")  int uid){

        List<SYS_Role> uncheck = roleDao.getNoChoiceRole(uid);
        List<SYS_Role> checked = roleDao.getChoiceRole(uid);

        JSONObject json = new JSONObject();
        json.put("uncheck",uncheck);
        json.put("checked",checked);

        System.out.println(json);
        return json;
    }

    @RequestMapping(value="/user/UpdateRole/editUserRole/{uid}",method = RequestMethod.POST)
    public String submitBtnAuth(@RequestBody List<SYS_Role> roles, @PathVariable("uid") int uid){
        System.out.println("----------------------------submit");
        roleDao.deleteRoleByUserId(uid);
        for (SYS_Role role  : roles) {
            SYS_User_Role userRole = new SYS_User_Role();
            userRole.setUserID(uid);
            userRole.setRoleID(role.getRoleID());
            userDao.createUserRole(userRole);
        }
        return "redirect:/user";
    }


}
