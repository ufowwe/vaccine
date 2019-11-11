package com.vaccine.vaccineapi.controller;


import com.vaccine.vaccineapi.controller.vo.LoginReqVO;
import com.vaccine.vaccineapi.controller.vo.LoginResVO;
import com.vaccine.vaccineapi.domain.BaseResponse;
import com.vaccine.vaccineapi.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 宝宝 前端控制器
 * </p>
 *
 * @author admin
 * @since 2019-10-31
 */
@Slf4j
@Api(tags = {"登陆相关"})
@RestController
//@RequestMapping("/")
public class LoginController {

    @Resource
    private LoginService loginService;

    @ApiOperation("登陆")
    @PostMapping("/login")
    public BaseResponse login(@Valid @RequestBody LoginReqVO entity) {
        LoginResVO loginResVO = loginService.login(entity.getCode());
        return BaseResponse.success("登陆成功", loginResVO);
    }

}
