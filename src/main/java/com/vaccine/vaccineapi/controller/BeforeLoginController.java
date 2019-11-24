package com.vaccine.vaccineapi.controller;


import com.vaccine.vaccineapi.controller.vo.record.VaccineRecordInfo;
import com.vaccine.vaccineapi.controller.vo.scheme.GetSchemeNoLoginReq;
import com.vaccine.vaccineapi.controller.vo.scheme.SchemeInfo;
import com.vaccine.vaccineapi.domain.BaseResponsePlus;
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
    private IVaccineSchemeService vaccineSchemeService;

    @ApiOperation("获取基础方案，未登录")
    @PostMapping("/getSchemeNoLogin")
    public BaseResponsePlus<SchemeInfo> getSchemeNoLogin(@Valid @RequestBody GetSchemeNoLoginReq req) {
        SchemeInfo schemeInfo = vaccineSchemeService.getSchemeBase(req.getSchemeType(), req.getProvinceId());
        BaseResponsePlus<SchemeInfo> rs = new BaseResponsePlus<>();
        rs.success("查询成功", schemeInfo);
        return rs;
    }

    @ApiOperation("获取接种证集合，未登录")
    @PostMapping("/getRecordNoLogin")
    public BaseResponsePlus<VaccineRecordInfo> getRecordNoLogin() {
        VaccineRecordInfo recordNoLogin = vaccineSchemeService.getRecordNoLogin(0, 1l);
        BaseResponsePlus<VaccineRecordInfo> rs = new BaseResponsePlus<>();
        rs.success("查询成功", recordNoLogin);
        return rs;
    }

}
