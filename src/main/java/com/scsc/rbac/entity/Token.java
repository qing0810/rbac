package com.scsc.rbac.entity;

import java.io.Serializable;

/**
 * @author qing
 * @date 2019/5/6 16:46
 */
public class Token implements Serializable {
    private Integer tokenId;
    private Integer userId;
    private String token;
    private long updateTime;

    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenId=" + tokenId +
                ", userId=" + userId +
                ", token='" + token + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }
}
