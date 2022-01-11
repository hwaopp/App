package com.app.member;

import java.sql.Timestamp;

public class MemberBean extends BasicBean{
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Boolean pass;
    private Timestamp lastUpdateDate;

    public MemberBean(){

    }

    public MemberBean(Integer id, String username, String password, String nickname, Boolean pass, Timestamp lastUpdateDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.pass = pass;
        this.lastUpdateDate = lastUpdateDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
