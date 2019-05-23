package com.scsc.rbac.entity;

import java.util.List;

/**
 * @author qing
 * @date 2019/5/16 10:02
 */
public class UserInfo {
    private String userName;
    private String roleName;
    private List<Resource> resources;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
