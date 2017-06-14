package com.iflytek.dao;

import com.iflytek.model.SYS_User;
import com.iflytek.model.SYS_User_Role;

import java.util.List;

/**
 * Created by admin on 2017/4/6.
 */

public interface UserDao {
    public List<SYS_User> findAll();
    public SYS_User findByAccount(String s);
    public int createUser(SYS_User u);
    public int createUserRole(SYS_User_Role userRole);
}
