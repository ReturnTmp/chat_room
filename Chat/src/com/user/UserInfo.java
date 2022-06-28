package com.user;

public class UserInfo {
    private int id;
    private int pwd;
    private String nickName;
    private boolean beonline=false;

    public UserInfo(int id, int pwd, String nickName) {
        this.id = id;
        this.pwd = pwd;
        this.nickName = nickName;
    }
    public UserInfo(int id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public boolean isBeonline() {
        return beonline;
    }

    public void setBeonline(boolean beonline) {
        this.beonline = beonline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPwd() {
        return pwd;
    }

    public void setPwd(int pwd) {
        this.pwd = pwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
