package com.iflytek.dao;

import com.iflytek.model.SYS_Permission;
import com.iflytek.model.TreeNode;

import java.util.List;

/**
 * Created by admin on 2017/4/10.
 */

public interface PermissionDao {
    List<SYS_Permission> findAll();
    List<SYS_Permission> findByUserId(int userId);
    List<TreeNode> getTreesByRoleID(int rid);
    boolean deleteMenuByRoleID(int rid);
    int createMenuAuth(SYS_Permission permission);
    boolean deleteBtnByRoleID(int rid);
}
