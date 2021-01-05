package com.summersec.shiroctf.bean;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Description: TODO
 * @Author: Summer
 * @Date: 2020/12/23 13:35
 * @Version: v1.0.0
 * @Description:
 **/
public class User implements Serializable {
    private static final long serialVersionUID = -5459292817832458554L;
    private String UserName;
    private int ID;
    private String PassWord;



    public User() {
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
}
