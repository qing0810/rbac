package com.scsc.rbac.mapper;

import com.scsc.rbac.entity.Token;
import org.springframework.stereotype.Repository;

/**
 * @author qing
 * @date 2019/5/6 17:11
 */
@Repository
public interface TokenMapper {
    /**
     * 根据用户id获取token
     * @param userId
     * @return Token
     */
    Token getToken(int userId);

    /**
     * 新增token
     * @param userId
     * @param token
     * @param updateTime
     */
    void addToken(int userId , String token ,long updateTime);

    /**
     * 修改token
     * @param token
     * @param updateTime
     * @param tokenId
     */
    void updateToken(String token ,long updateTime , int tokenId);
}
