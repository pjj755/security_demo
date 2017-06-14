package com.iflytek.controller;


import com.alibaba.fastjson.JSONObject;
import com.iflytek.dao.ButtonDao;
import com.iflytek.dao.MenuDao;
import com.iflytek.dao.PermissionDao;
import com.iflytek.dao.RoleDao;
import com.iflytek.model.SYS_Button;
import com.iflytek.model.SYS_Menu;
import com.iflytek.model.SYS_Permission;
import com.iflytek.model.SYS_Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by admin on 2017/5/11.
 */
@Controller
public class PermissionController extends BaseController{

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private ButtonDao buttonDao;

    @Autowired
    private PermissionDao permissionDao;

    @RequestMapping("/menuAuth")
    public String authMenu(Model model){
        List<SYS_Role> roles = roleDao.findAll();
        model.addAttribute("menus",getMenu());
        model.addAttribute("roles",roles);
        return "/menuAuth";
    }

    @RequestMapping("/btnAuth")
    public String btnMenu(Model model){
        List<SYS_Role> roles = roleDao.findAll();
        model.addAttribute("menus",getMenu());
        model.addAttribute("roles",roles);
        return "/btnAuth";
    }

    @RequestMapping(value="/menuAuth/edit/getInfo/{rid}",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getMenuByRoleID(@PathVariable("rid")  int rid){
        System.out.println("----------------------------"+rid);

        List<SYS_Menu> uncheck = menuDao.getNoChoiceMenuAuth(rid);
        List<SYS_Menu> checked = menuDao.getChoiceMenuAuth(rid);


        JSONObject json = new JSONObject();
        json.put("uncheck",uncheck);
        json.put("checked",checked);

        System.out.println(json);
        return json;
    }

    @RequestMapping(value="/menuAuth/edit/submitMenuAuth/{rid}",method = RequestMethod.POST)
    public String submitMenuAuth(@RequestBody List<SYS_Menu> menus,@PathVariable("rid") int rid){
        System.out.println("----------------------------submit");
        permissionDao.deleteMenuByRoleID(rid);
        for (SYS_Menu menu : menus) {
            SYS_Permission permission = new SYS_Permission();
            permission.setRoleID(rid);
            permission.setPermissionAccess("menu");
            permission.setPermissionAccessValue(menu.getMenuID());
           permissionDao.createMenuAuth(permission);
            System.out.println(menu.getMenuID()+menu.getMenuName());
        }
        return "redirect:/menuAuth";
    }

    @RequestMapping(value="/btnAuth/edit/getInfo/{rid}",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getBtnByRoleID(@PathVariable("rid")  int rid){
        System.out.println("----------------------------"+rid);

        List<SYS_Button> uncheck = buttonDao.getNoChoiceBtnAuth(rid);
        List<SYS_Button> checked =buttonDao.getChoiceBtnAuth(rid);


        JSONObject json = new JSONObject();
        json.put("uncheck",uncheck);
        json.put("checked",checked);

        System.out.println(json);
        return json;
    }

    @RequestMapping(value="/btnAuth/edit/submitBtnAuth/{rid}",method = RequestMethod.POST)
    public String submitBtnAuth(@RequestBody List<SYS_Button> btns,@PathVariable("rid") int rid){
        System.out.println("----------------------------submit");
        permissionDao.deleteBtnByRoleID(rid);
        for (SYS_Button btn : btns) {
            SYS_Permission permission = new SYS_Permission();
            permission.setRoleID(rid);
            permission.setPermissionAccess("button");
            permission.setPermissionAccessValue(btn.getBtnID());
            permissionDao.createMenuAuth(permission);
            System.out.println(btn.getBtnID()+btn.getBtnName());
        }
        return "redirect:/btnAuth";
    }

}
