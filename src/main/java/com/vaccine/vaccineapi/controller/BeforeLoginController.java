package com.vaccine.vaccineapi.controller;


import com.vaccine.vaccineapi.controller.vo.scheme.SchemeInfo;
import com.vaccine.vaccineapi.domain.BaseResponse;
import com.vaccine.vaccineapi.service.IVaccineSchemeBaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 登录前 前端控制器
 * </p>
 *
 * @author admin
 * @since 2019-11-11
 */
@Slf4j
@Api(tags = {"登录前"})
@RestController
@RequestMapping("/beforeLogin")
public class BeforeLoginController {

    @Resource
    private IVaccineSchemeBaseService service;

    @ApiOperation("获取接种证集合")
    @PostMapping("/getSchemeBase")
    public BaseResponse getSchemeBase() {
        boolean rs = true;
        List<SchemeInfo> schemeInfoList = service.getSchemeBaseNoBaby();
        if (rs) {
            return BaseResponse.success("查询成功", schemeInfoList);
        } else {
            return BaseResponse.failed("查询失败", schemeInfoList);
        }
    }

}
