package com.scsc.rbac.controller;

import com.scsc.rbac.common.ResultBuilder;
import com.scsc.rbac.entity.*;
import com.scsc.rbac.service.LogService;
import com.scsc.rbac.service.TokenService;
import com.scsc.rbac.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public Map login(@RequestBody LoginUser loginUser){
        if (StringUtils.isEmpty(loginUser.getUsername()) || StringUtils.isEmpty(loginUser.getPassword())){
            return ResultBuilder.buildError("用户名或密码不能为空！");
        }
        // TODO: 2019/5/7  需要ip
        //无论登录成功或失败都存入数据库中
        Log log = new Log();
        log.setUserName(loginUser.getUsername());
        log.setLoginTime(System.currentTimeMillis());
        log.setIp("172.16.2.121");


        User user = userService.findByUserName(loginUser.getUsername());
        if (null == user){

            log.setSuccess(false);

            logService.addLog(log);
            return ResultBuilder.buildError("用户不存在!");
        }
        if (!loginUser.getPassword().equals(user.getPassword())){

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
        LoginResultToken loginResultToken = new LoginResultToken();
        loginResultToken.setToken(tokenStr);
        return ResultBuilder.buildSuccess(loginResultToken);
    }

    @GetMapping("/getUserInfo")
    public Map getUserInfo(@RequestParam String  token){
        int userId;
        int timeInterval = 60 * 60 * 12;
        try {
            Claims claims = Jwts.parser().setSigningKey("qing").parseClaimsJws(token).getBody();
            String tokenUserId = (String)claims.get("userId");
             userId = Integer.parseInt(tokenUserId);
            Date tokenDate = claims.getExpiration();
            int overTime = (int)(System.currentTimeMillis() - tokenDate.getTime())/1000;
            if (overTime > timeInterval){
                return ResultBuilder.buildError("Toke过期,请从新登陆");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultBuilder.buildError("....");
        }

        List<Resource> lists = userService.getUserInfo(userId);

        LoginResult loginResult = userService.login(userId);
        UserInfo userInfo = new UserInfo();
        userInfo.setAvatar(loginResult.getAvatar());
        userInfo.setUserId(loginResult.getUserId());
        userInfo.setName(loginResult.getName());
        userInfo.setResources(lists);
        userInfo.setUserName(loginResult.getUserName());
        String []roles= {loginResult.getRoles()};
        userInfo.setRoles(roles);
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
                .setExpiration(new Date(date.getTime() + 1000 * 60 * 60 * 12))
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
