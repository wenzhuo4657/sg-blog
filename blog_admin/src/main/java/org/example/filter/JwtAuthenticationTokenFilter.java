package org.example.filter;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import org.example.domain.AppHttpCodeEnum;
import org.example.domain.ResponseResult;
import org.example.domain.enity.LoginUser;
import org.example.utils.JwtUtil;
import org.example.utils.RedisCache;
import org.example.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisCache redisCache;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//1,判断token是否包含实际的文本，该步骤的意义在于过滤掉无用的token，避免浪费解析资源
        String token =request.getHeader("token");
        if (!StringUtils.hasText(token)){
            filterChain.doFilter(request,response);
            return;
        }


//        2,解析token，
        Claims claims=null;
        try{
            claims= JwtUtil.parseJWT(token);
        }catch (Exception E){
            E.printStackTrace();

//             响应给前端登录失败的信息,且注意此处并没有 filterChain.doFilter(request,response);执行下一过滤器
            ResponseResult result=ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }


//        3,从redis中读取完整用户信息，
        String userid=claims.getSubject();
        LoginUser loginUser=redisCache.getCacheObject("ADIM_user: "+userid);
        if (Objects.isNull(loginUser)){
            //            响应给前端登录失败的信息，且注意此处并没有 filterChain.doFilter(request,response);执行下一过滤器
            ResponseResult result=ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }


//        4，token验证成功，将信息存入SecurityContextHolder中并放行
        UsernamePasswordAuthenticationToken token1
                =new UsernamePasswordAuthenticationToken(loginUser,null,null);
        SecurityContextHolder.getContext().setAuthentication(token1);

        filterChain.doFilter(request,response);

    }
}
