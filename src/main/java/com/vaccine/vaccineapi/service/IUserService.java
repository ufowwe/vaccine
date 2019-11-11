package com.vaccine.vaccineapi.service;

import com.vaccine.vaccineapi.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author admin
 * @since 2019-10-31
 */
public interface IUserService extends IService<User> {

    User getByOpenid(String openid);

    User getUser();

    Long getUserId();

}
