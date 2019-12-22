package com.muker.filter;

import com.alibaba.fastjson.JSON;
import com.muker.utils.RedisUtil;
import com.muker.vo.R;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName SmsFilter
 * @Description
 * @Author JavaQ
 * @Date 2019/12/19 11:50
 **/

public class SmsFilter implements Filter {
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
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String phone = request.getParameter("phone");
        String minute = redisUtil.get("sendMinuteCount:" + phone,0);
        String hour = String.valueOf(redisUtil.keys("sendHourCount:" + phone+"*").size());
        String day = redisUtil.get("sendDayCount:" + phone,0);
        System.out.println(hour);
        if ("10".equals(day)) {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(R.fail("一天只能发送10次")));
        }
        else if ("5".equals(hour)) {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(R.fail("一小时只能发送5次")));
        }
        else if (minute != null) {
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSON.toJSONString(R.fail("一分钟只能发送1条")));
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
