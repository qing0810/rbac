package com.scsc.rbac.controller;

import com.scsc.rbac.common.ResultBuilder;
import com.scsc.rbac.entity.*;
import com.scsc.rbac.service.LogService;
import com.scsc.rbac.service.TokenService;
import com.scsc.rbac.service.UserService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author qing
 * @date 2019/4/30 11:21
 */
@RestController
@RequestMapping("/user")
public class LoginViewController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private LogService logService;

    @PostMapping("/login")
    public Map login(@RequestParam String username , @RequestParam String password , HttpServletRequest request){
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return ResultBuilder.buildError("用户名或密码不能为空！");
        }
        // TODO: 2019/5/7  需要ip
        //无论登录成功或失败都存入数据库中
        Log log = new Log();
        log.setUserName(username);
        log.setLoginTime(System.currentTimeMillis());
        log.setIp("172.16.2.121");


        User user = userService.findByUserName(username);
        if (null == user){

            log.setSuccess(false);

            logService.addLog(log);
            return ResultBuilder.buildError("用户不存在!");
        }
        if (!password.equals(user.getPassword())){

            log.setSuccess(false);

            logService.addLog(log);
            return ResultBuilder.buildError("密码不正确!");
        }

        LoginResult loginResult = userService.login(user.getUserId());

        log.setSuccess(true);

        logService.addLog(log);
        //查询token
        Token token = tokenService.getToken(loginResult.getUserId());

        String tokenStr = "";
        Date date = new Date();
        long nowTime = date.getTime();
        //生成token
        tokenStr = creatToken(loginResult ,date);
        if(null == token){
            //第一次登陆
            token = new Token();
            token.setToken(tokenStr);
            token.setUpdateTime(nowTime);
            token.setUserId(loginResult.getUserId());
            tokenService.addToken(token);
        }else {
            //更新token信息
            token.setToken(tokenStr);
            token.setUpdateTime(nowTime);
            tokenService.updateToken(token);
        }
        loginResult.setToken(tokenStr);
        HttpSession session = request.getSession();
        session .setAttribute("token" ,tokenStr);
        session.setMaxInactiveInterval(30*60);
        return ResultBuilder.buildSuccess(loginResult);
    }

    @PostMapping("/getUserInfo")
    public Map getUserInfo(@RequestParam int userId ,HttpServletRequest request){
        List<Resource> lists = userService.getUserInfo(userId);
        LoginResult loginResult = userService.login(userId);
        UserInfo userInfo = new UserInfo();
        userInfo.setResources(lists);
        userInfo.setUserName(loginResult.getUserName());
        userInfo.setRoleName(loginResult.getRoleName());
        Token token = tokenService.getToken(userId);
        HttpSession session = request.getSession();
        session.setAttribute("token", token.getToken());
        session.setMaxInactiveInterval(30*60);
        return ResultBuilder.buildSuccess(userInfo);
    }

    @PostMapping("/signOut")
    public Map loginOut(HttpServletRequest request){
        request.getSession().invalidate();
        return ResultBuilder.buildSuccess("退出成功");
    }

    /**
     * 生成token
     * @param loginResult
     * @param date
     * @return
     */
    private String creatToken(LoginResult loginResult, Date date) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 设置header
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                // 设置签发时间
                .setHeaderParam("alg", "HS256").setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + 1000 * 60 * 5))
                // 设置内容
                .claim("userId",String.valueOf(loginResult.getUserId() ) )
                // 设置签发人
                .setIssuer("lws")
                // 签名，需要算法和key
                .signWith(signatureAlgorithm, "qing");
        String jwt = builder.compact();
        return jwt;
    }


}
