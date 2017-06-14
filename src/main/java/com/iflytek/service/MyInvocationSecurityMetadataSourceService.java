package com.iflytek.service;

import com.iflytek.dao.ButtonDao;
import com.iflytek.dao.MenuDao;
import com.iflytek.dao.PermissionDao;
import com.iflytek.model.SYS_Button;
import com.iflytek.model.SYS_Menu;
import com.iflytek.model.SYS_Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by admin on 2017/4/12.
 */
@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private ButtonDao buttonDao;

    private HashMap<String, Collection<ConfigAttribute>> map =null;

    //加载权限表中所有权限
    public void loadResourceDefine(){
        map = new HashMap<>();
        Collection<ConfigAttribute> atts;
        List<SYS_Permission> permissions = permissionDao.findAll();
        for(SYS_Permission permission : permissions) {
            atts = new ArrayList<>();
            ConfigAttribute cfg = new SecurityConfig(permission.getPermissionAccess() + "_" + permission.getPermissionAccessValue());
            atts.add(cfg);
            if (permission.getPermissionAccess().equals("menu")){
                SYS_Menu sys_menu = menuDao.findById(permission.getPermissionAccessValue());
                map.put(sys_menu.getUrl(),atts);
            }else if (permission.getPermissionAccess().equals("button")){
                SYS_Button sys_button = buttonDao.findById(permission.getPermissionAccessValue());
                map.put(sys_button.getUrl(),atts);
            }
        }

    }

    //此方法是为了判定用户请求是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if(map ==null) loadResourceDefine();
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        Iterator<String> ite = map.keySet().iterator();
        while (ite.hasNext()){
            resUrl = ite.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
