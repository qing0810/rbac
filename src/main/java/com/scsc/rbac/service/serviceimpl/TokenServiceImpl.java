package com.scsc.rbac.service.serviceimpl;

import com.scsc.rbac.entity.Token;
import com.scsc.rbac.mapper.TokenMapper;
import com.scsc.rbac.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qing
 * @date 2019/5/7 9:12
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    TokenMapper tokenMapper;
    @Override
    public Token getToken(int userId) {
        return tokenMapper.getToken(userId);
    }

    @Override
    public void addToken(Token token) {
        tokenMapper.addToken(token.getUserId() ,token.getToken() ,token.getUpdateTime());
    }

    @Override
    public void updateToken(Token token) {
        tokenMapper.updateToken(token.getToken() ,token.getUpdateTime() ,token.getTokenId());
    }
}
