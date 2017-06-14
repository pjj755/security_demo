package com.iflytek.controller;

import com.iflytek.dao.MenuDao;
import com.iflytek.model.SYS_Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Created by admin on 2017/4/21.
 */
public class BaseController {

    @Autowired
    private MenuDao menuDao;

    public List<SYS_Menu> getMenu(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return menuDao.findByAccount(userDetails.getUsername());
    }
}
