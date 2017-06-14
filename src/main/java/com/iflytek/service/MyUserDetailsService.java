package com.iflytek.service;

import com.iflytek.dao.PermissionDao;
import com.iflytek.dao.UserDao;
import com.iflytek.model.SYS_Permission;
import com.iflytek.model.SYS_User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/6.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SYS_User u = userDao.findByAccount(s);
        if (u == null){
            throw new UsernameNotFoundException(s+"用户名不存在");
        }

        //根据用户获取权限
        List<SYS_Permission> permissions = permissionDao.findByUserId(u.getUserID());

        //定义权限集合
        List<GrantedAuthority> grantedAuthorities = new ArrayList();
        //添加权限到集合中
        for (SYS_Permission permission: permissions){
            if (permission !=null && permission.getPermissionOperation()==true) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getPermissionAccess() + "_" + permission.getPermissionAccessValue());
                grantedAuthorities.add(grantedAuthority);
            }
        }



        org.springframework.security.core.userdetails.User user = new User(u.getAccount(),u.getPassword(),u.getStatus(),true,true, true, grantedAuthorities);
        return user;
    }
}
