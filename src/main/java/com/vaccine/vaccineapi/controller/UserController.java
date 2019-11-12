package com.vaccine.vaccineapi.controller;


import com.vaccine.vaccineapi.controller.vo.UserInfo;
import com.vaccine.vaccineapi.controller.vo.UserInfoWx;
import com.vaccine.vaccineapi.domain.BaseResponse;
import com.vaccine.vaccineapi.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author admin
 * @since 2019-10-31
 */
@Slf4j
@Api(tags = {"用户"})
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private IUserService service;

    @ApiOperation("微信用户信息更新到用户")
    @PostMapping("/updateUserByWx")
    public BaseResponse updateUserByWx(@Valid @RequestBody UserInfoWx entity) {
        boolean rs = service.updateUserByWx(entity);
        if (rs) {
            return BaseResponse.success("保存成功");
        } else {
            return BaseResponse.failed("保存失败");
        }
    }

    @ApiOperation("更新用户信息")
    @PostMapping("/updateUser")
    public BaseResponse updateUser(@Valid @RequestBody UserInfo entity) {
        boolean rs = service.updateUser(entity);
        if (rs) {
            return BaseResponse.success("保存成功");
        } else {
            return BaseResponse.failed("保存失败");
        }
    }

}
