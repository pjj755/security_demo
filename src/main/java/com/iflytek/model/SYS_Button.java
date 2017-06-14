package com.iflytek.model;

/**
 * Created by admin on 2017/4/19.
 */
public class SYS_Button {

    private int btnID;
    private String btnName;
    private String btnClass;
    private String btnIcon;
    private int menuID;
    private String url;

    public int getBtnID() {
        return btnID;
    }

    public void setBtnID(int btnID) {
        this.btnID = btnID;
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    public String getBtnClass() {
        return btnClass;
    }

    public void setBtnClass(String btnClass) {
        this.btnClass = btnClass;
    }

    public String getBtnIcon() {
        return btnIcon;
    }

    public void setBtnIcon(String btnIcon) {
        this.btnIcon = btnIcon;
    }

    public int getMenuID() {
        return menuID;
    }

    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
