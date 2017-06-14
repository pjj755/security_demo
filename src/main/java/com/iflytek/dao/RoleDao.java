package com.iflytek.dao;

import com.iflytek.model.SYS_Role;

import java.util.List;

/**
 * Created by admin on 2017/4/6.
 */


public interface RoleDao {

    public List<SYS_Role> findByUserId(int uid);

    public List<SYS_Role> findAll();

    public int createRole(SYS_Role role);

    public List<SYS_Role> getNoChoiceRole(int uid);

    public List<SYS_Role> getChoiceRole(int uid);

    public boolean deleteRoleByUserId(int uid);

}
