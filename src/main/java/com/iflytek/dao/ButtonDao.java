package com.iflytek.dao;

import com.iflytek.model.SYS_Button;

import java.util.List;


public interface ButtonDao {
    public SYS_Button findById(int bid);
    public List<SYS_Button> getNoChoiceBtnAuth(int rid);
    public List<SYS_Button> getChoiceBtnAuth(int rid);
}
