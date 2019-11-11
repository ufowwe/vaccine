package com.vaccine.vaccineapi.controller;


import com.vaccine.vaccineapi.controller.vo.scheme.GetSchemeReq;
import com.vaccine.vaccineapi.domain.BaseResponse;
import com.vaccine.vaccineapi.service.IVaccineSchemeBaseService;
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
 * 疫苗基础方案 前端控制器
 * </p>
 *
 * @author admin
 * @since 2019-11-11
 */
@Slf4j
@Api(tags = {"疫苗基础方案"})
@RestController
@RequestMapping("/api/vaccineSchemeBase")
public class VaccineSchemeBaseController {

    @Resource
    private IVaccineSchemeBaseService service;

    @ApiOperation("获取基础方案")
    @PostMapping("/getSchemeBase")
    public BaseResponse getSchemeBase(@Valid @RequestBody GetSchemeReq req) {
        boolean rs = true;
        service.getSchemeBase(req.getSchemeType());
        if (rs) {
            return BaseResponse.success("更新成功");
        } else {
            return BaseResponse.failed("更新失败");
        }
    }

}
