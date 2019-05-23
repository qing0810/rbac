package com.scsc.rbac.entity;

import java.io.Serializable;

/**
 * @author qing
 * @date 2019/5/6 9:31
 */
public class LoginResult implements Serializable {
    private Integer userId;
    private String roleName;
    private String roleEnglishName;
    private String userName;
    private String token;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleEnglishName() {
        return roleEnglishName;
    }

    public void setRoleEnglishName(String roleEnglishName) {
        this.roleEnglishName = roleEnglishName;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "userId=" + userId +
                ", roleName='" + roleName + '\'' +
                ", roleEnglishName='" + roleEnglishName + '\'' +
                ", userName='" + userName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
