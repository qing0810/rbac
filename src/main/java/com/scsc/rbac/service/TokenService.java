package com.scsc.rbac.service;

import com.scsc.rbac.entity.Token;

/**
 * @author qing
 * @date 2019/5/7 9:07
 */
public interface TokenService {
    /**
     * 根据用户id获取token
     * @param userId
     * @return Token
     */
    Token getToken(int userId);

    /**
     * 新增tToken
     * @param token
     */
    void addToken(Token token);

    /**
     * 修改token
     * @param token
     */
    void updateToken(Token token);
}
