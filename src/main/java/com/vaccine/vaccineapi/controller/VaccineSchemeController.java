package com.vaccine.vaccineapi.controller;


import com.vaccine.vaccineapi.controller.vo.scheme.GetSchemeReq;
import com.vaccine.vaccineapi.controller.vo.scheme.SchemeInfo;
import com.vaccine.vaccineapi.domain.BaseResponse;
import com.vaccine.vaccineapi.service.IVaccineSchemeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 疫苗方案 前端控制器
 * </p>
 *
 * @author admin
 * @since 2019-11-18
 */
@Slf4j
@Api(tags = {"疫苗方案"})
@RestController
@RequestMapping("/api/vaccineScheme")
public class VaccineSchemeController {

    @Resource
    private IVaccineSchemeService service;

    @ApiOperation("获取基础方案")
    @PostMapping("/getScheme")
    public BaseResponse getSchemeBase(@Valid @RequestBody GetSchemeReq req) {
        boolean rs = true;
        List<SchemeInfo> schemeInfoList = service.getScheme(req.getSchemeType(), req.getProvinceId());
        if (rs) {
            return BaseResponse.success("查询成功", schemeInfoList);
        } else {
            return BaseResponse.failed("查询失败", schemeInfoList);
        }
    }

}
