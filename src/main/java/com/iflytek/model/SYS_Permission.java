package com.iflytek.model;

/**
 * Created by admin on 2017/4/6.
 */
public class SYS_Permission {

    private int roleID;
    private String permissionAccess;
    private int permissionAccessValue;
    private Boolean permissionOperation;
    private Boolean status;
    private int createUserID;
    private String createTime;
    private int updateUserID;
    private String updateTime;


    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getPermissionAccess() {
        return permissionAccess;
    }

    public void setPermissionAccess(String permissionAccess) {
        this.permissionAccess = permissionAccess;
    }

    public int getPermissionAccessValue() {
        return permissionAccessValue;
    }

    public void setPermissionAccessValue(int permissionAccessValue) {
        this.permissionAccessValue = permissionAccessValue;
    }

    public Boolean getPermissionOperation() {
        return permissionOperation;
    }

    public void setPermissionOperation(Boolean permissionOperation) {
        this.permissionOperation = permissionOperation;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getCreateUserID() {
        return createUserID;
    }

    public void setCreateUserID(int createUserID) {
        this.createUserID = createUserID;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getUpdateUserID() {
        return updateUserID;
    }

    public void setUpdateUserID(int updateUserID) {
        this.updateUserID = updateUserID;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}
