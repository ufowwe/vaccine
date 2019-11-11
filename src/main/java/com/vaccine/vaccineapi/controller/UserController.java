package com.vaccine.vaccineapi.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vaccine.vaccineapi.domain.BaseResponse;
import com.vaccine.vaccineapi.entity.User;
import com.vaccine.vaccineapi.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("新增")
    @PostMapping("/save")
    public BaseResponse save(@Valid @RequestBody User entity) {
        boolean rs = service.save(entity);
        if (rs) {
            return BaseResponse.success("保存成功");
        } else {
            return BaseResponse.failed("保存失败");
        }
    }

    @ApiOperation("更新")
    @PostMapping("/update")
    public BaseResponse updateById(@Valid @RequestBody User entity) {
        boolean rs = service.updateById(entity);
        if (rs) {
            return BaseResponse.success("更新成功");
        } else {
            return BaseResponse.failed("更新失败");
        }
    }

    @ApiOperation("查询集合")
    @GetMapping("/getList")
    public BaseResponse getList() {
        Page page = new Page<>();
        User user = service.getUser();
        return BaseResponse.success("success");
    }

}
