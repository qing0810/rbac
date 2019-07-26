package com.scsc.rbac.entity;

import java.io.Serializable;

/**
 * @author qing
 * @date 2019/5/6 9:31
 */
public class LoginResult implements Serializable {
    private String name;
    private Integer userId;
//    private String roleName;
    private String roles;
    private String userName;
    private String token;
    private String avatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

//    public String getRoleName() {
//        return roleName;
//    }
//
//    public void setRoleName(String roleName) {
//        this.roleName = roleName;
//    }


    @Override
    public String toString() {
        return "LoginResult{" +
                "name='" + name + '\'' +
                ", userId=" + userId +
                ", roles='" + roles + '\'' +
                ", userName='" + userName + '\'' +
                ", token='" + token + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
