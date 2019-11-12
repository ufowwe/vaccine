package com.vaccine.vaccineapi.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.vaccine.vaccineapi.service.IUserBabyService;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 宝宝用户关系 前端控制器
 * </p>
 *
 * @author admin
 * @since 2019-11-12
 */
@Slf4j
@Api(tags = {"宝宝用户关系"})
@RestController
@RequestMapping("/api/user-baby")
public class UserBabyController {

    @Resource
    private IUserBabyService service;

}
