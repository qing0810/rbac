package com.scsc.rbac.mapper;

import com.scsc.rbac.entity.LoginResult;
import com.scsc.rbac.entity.Resource;
import com.scsc.rbac.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qing
 * @date 2019/5/5 14:10
 */
@Repository
public interface UserMapper {
    /**
     * 登录
     * @param userId
     * @return LoginResult
     */
    LoginResult login(int userId);

    /**
     * 获取用户权限信息
     * @param userId
     * @return User
     */
    List<Resource> getUserInfo(int userId);

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User findByUserName(String username);
}
