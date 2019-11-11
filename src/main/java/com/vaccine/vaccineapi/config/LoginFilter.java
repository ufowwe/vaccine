package com.vaccine.vaccineapi.config;

import com.alibaba.fastjson.JSONObject;
import com.vaccine.vaccineapi.config.redis.RedisService;
import com.vaccine.vaccineapi.controller.vo.SessionDTO;
import com.vaccine.vaccineapi.domain.BaseResponse;
import com.vaccine.vaccineapi.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 是否登录用户过滤器
 */
@Order(2)
@WebFilter(urlPatterns = { "/api/*" }, filterName = "LoginFilter")
@Slf4j
public class LoginFilter implements Filter {

    public static final String INVALID_MSG = "登陆超时";

    @Autowired
    private RedisService redisService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                filterConfig.getServletContext());
    }

    /**
     * 从request中获取token，验证是否已登录
     *      已登录条件：  token不为空，token没有过期
     *      未登录：    返回超时
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //登录过滤器，使用
        boolean isLogin = false;
//        String token = servletRequest.getParameter(Constants.ACCESS_TOKEN);
        String token = request.getHeader(Constants.ACCESS_TOKEN);
        if (StringUtils.isBlank(token)) {
            BaseResponse invalid = BaseResponse.invalid(INVALID_MSG, "");
            response.setContentType("text/JavaScript;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(invalid));
            return;
        }
        SessionDTO res = redisService.get(token, SessionDTO.class);
        if(res != null){
            isLogin = true;
        }
        if(isLogin){ //已登录
            redisService.set(token, res, Constants.LOGIN_TIMEOUT);
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            BaseResponse invalid = BaseResponse.invalid(INVALID_MSG, "");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(invalid));
        }
    }

    @Override
    public void destroy() {

    }
}
