package com.vaccine.vaccineapi.controller;


import com.vaccine.vaccineapi.controller.vo.record.VaccineRecordInfo;
import com.vaccine.vaccineapi.controller.vo.scheme.GetRecordReq;
import com.vaccine.vaccineapi.domain.BaseResponsePlus;
import com.vaccine.vaccineapi.service.IVaccineRecordService;
import com.vaccine.vaccineapi.service.IVaccineSchemeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 疫苗方案 前端控制器
 * </p>
 *
 * @author admin
 * @since 2019-11-20
 */
@Slf4j
@Api(tags = {"接种证"})
@RestController
@RequestMapping("/api/vaccineRecord")
public class VaccineRecordController {

    @Resource
    private IVaccineRecordService service;

    @Resource
    private IVaccineSchemeService vaccineSchemeService;

    @ApiOperation("获取接种证集合，已登录")
    @PostMapping("/getRecord")
    public BaseResponsePlus<VaccineRecordInfo> getRecord(GetRecordReq req) {
        VaccineRecordInfo recordNoLogin = vaccineSchemeService.getRecord(req.getBabyId(), 0, 1);
        BaseResponsePlus<VaccineRecordInfo> rs = new BaseResponsePlus<>();
        rs.success("查询成功", recordNoLogin);
        return rs;
    }

}
