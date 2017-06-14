package com.iflytek.dao;

import com.iflytek.model.SYS_Menu;

import java.util.List;

/**
 * Created by admin on 2017/4/19.
 */


public interface MenuDao {
    public SYS_Menu findById(int mid);
    public List<SYS_Menu> getNoChoiceMenuAuth(int rid);
    public List<SYS_Menu> getChoiceMenuAuth(int rid);
    public List<SYS_Menu> findByAccount(String account);
}
