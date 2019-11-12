package com.vaccine.vaccineapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vaccine.vaccineapi.controller.vo.UserInfo;
import com.vaccine.vaccineapi.controller.vo.UserInfoWx;
import com.vaccine.vaccineapi.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author admin
 * @since 2019-10-31
 */
public interface IUserService extends IService<User> {

    boolean updateUserByWx(UserInfoWx userInfo);

    boolean updateUser(UserInfo userInfo);

    User getByOpenid(String openid);

    User getUser();

    Long getUserId();

}
