package com.scsc.rbac.entity;

import java.io.Serializable;

/**
 * @author qing
 * @date 2019/5/7 15:03
 */
public class Log implements Serializable {
    private String ip;
    private String userName;
    private long loginTime;
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "Log{" +
                "ip='" + ip + '\'' +
                ", userName='" + userName + '\'' +
                ", loginTime=" + loginTime +
                ", success=" + success +
                '}';
    }
}
