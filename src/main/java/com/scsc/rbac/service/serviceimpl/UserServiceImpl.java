package com.scsc.rbac.service.serviceimpl;

import com.scsc.rbac.entity.LoginResult;
import com.scsc.rbac.entity.Resource;
import com.scsc.rbac.entity.User;
import com.scsc.rbac.mapper.UserMapper;
import com.scsc.rbac.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qing
 * @date 2019/5/5 14:07
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public LoginResult login(int userId) {
        return userMapper.login(userId);
    }

    @Override
    public List<Resource> getUserInfo(int userId) {
        return userMapper.getUserInfo(userId);
    }
}
