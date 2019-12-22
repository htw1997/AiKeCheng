package com.muker.filter;

import com.alibaba.fastjson.JSON;
import com.muker.config.RedisKeyConfig;
import com.muker.config.SystemConfig;
import com.muker.utils.JwtUtil;
import com.muker.utils.RedisUtil;
import com.muker.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName AuthFilter
 * @Description
 * @Author JavaQ
 * @Date 2019/12/18 21:00
 **/
@Component
public class AuthFilter implements Filter {
    private RedisUtil redisUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        assert applicationContext != null;
        redisUtil = (RedisUtil) applicationContext.getBean("redisUtil");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("认证过滤器");
//
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String headerToken = request.getHeader(SystemConfig.TOKEN_USER);
//        System.out.println("headers:"+headerToken);
//        String redisToken = redisUtil.get(RedisKeyConfig.TOKEN_KEY+request.getParameter("phone"), 0);
//        System.out.println("redisToken:"+redisToken);
//        if (request.getRequestURI().endsWith("/aimu/user/login.do")){
//            filterChain.doFilter(request,response);
//            return;
//        }
//        if (headerToken == null) {
//            response.getWriter().write(JSON.toJSONString(R.fail("请先登录")));
//            return;
//        }
//        if (redisToken == null) {
//            response.getWriter().write(JSON.toJSONString(R.noau("会话已失效")));
//            return;
//        }
//        if (redisToken.equals(headerToken)){
//            filterChain.doFilter(request,response);
//        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
