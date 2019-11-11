package com.vaccine.vaccineapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vaccine.vaccineapi.config.redis.RedisService;
import com.vaccine.vaccineapi.controller.vo.SessionDTO;
import com.vaccine.vaccineapi.entity.User;
import com.vaccine.vaccineapi.exception.LogoutException;
import com.vaccine.vaccineapi.mapper.UserMapper;
import com.vaccine.vaccineapi.service.IUserService;
import com.vaccine.vaccineapi.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2019-10-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private RedisService redisService;

    public HttpServletRequest getHttpServletRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public HttpServletResponse getHttpServletResponse(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public String getToken(){
        HttpServletRequest request = getHttpServletRequest();
        return request.getHeader(Constants.ACCESS_TOKEN);
    }

    @Override
    public User getByOpenid(String openid) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(User::getOpenid, openid);
        User u = this.getOne(wrapper);
        return u;
    }

    private SessionDTO getSessionDTO() {
        String msg = "用户已过期，请重新登录";
        String token = getToken();
        if (StringUtils.isBlank(token)) {
            throw new LogoutException(msg);
        }

        SessionDTO bean = redisService.get(token, SessionDTO.class);
        if (bean == null) {
            throw new LogoutException(msg);
        }
        return bean;
    }

    @Override
    public User getUser() {
        return getById(getSessionDTO().getId());
    }

    @Override
    public Long getUserId() {
        return getSessionDTO().getId();
    }
}
