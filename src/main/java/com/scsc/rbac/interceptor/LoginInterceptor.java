package com.scsc.rbac.interceptor;

import com.scsc.rbac.entity.Token;
import com.scsc.rbac.service.TokenService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author qing
 * @date 2019/4/30 13:58
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    TokenService tokenService;
    /**
     * 在请求被处理之前调用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object token = request.getSession().getAttribute("token");
        Object loginUserId =  request.getSession().getAttribute("userId");
        System.out.println(loginUserId);
        if (StringUtils.isEmpty(token) || !(token instanceof String)) {
            return false;
        }
        try {
            Claims claims = Jwts.parser().setSigningKey("qing").parseClaimsJws(token.toString()).getBody();
            String tokenUserId = (String)claims.get("userId");
            int userId = Integer.parseInt(tokenUserId);
            //去数据库查找token
            Token myToken = tokenService.getToken(userId);
            if (null == myToken){
                return false;
            }
            if (!token.equals(myToken.getToken())){
                return false;
            }
            //判断Toke过期
            Date tokenDate = claims.getExpiration();
            int overTime = (int)(System.currentTimeMillis() - tokenDate.getTime())/1000;
            if (overTime > 60*5){
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 在请求被处理后,视图渲染之前调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束后调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
