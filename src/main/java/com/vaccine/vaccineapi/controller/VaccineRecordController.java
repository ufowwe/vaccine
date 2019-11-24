package com.vaccine.vaccineapi.controller;


import com.vaccine.vaccineapi.controller.vo.record.UpdateRecordReq;
import com.vaccine.vaccineapi.controller.vo.record.VaccineRecordInfo;
import com.vaccine.vaccineapi.controller.vo.scheme.GetRecordReq;
import com.vaccine.vaccineapi.domain.BaseResponse;
import com.vaccine.vaccineapi.domain.BaseResponsePlus;
import com.vaccine.vaccineapi.entity.VaccineRecord;
import com.vaccine.vaccineapi.service.IVaccineRecordService;
import com.vaccine.vaccineapi.service.IVaccineSchemeService;
import com.vaccine.vaccineapi.utils.BeanUtil;
import com.vaccine.vaccineapi.utils.DateUtil;
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
    public BaseResponsePlus<VaccineRecordInfo> getRecord(@Valid @RequestBody GetRecordReq req) {
        VaccineRecordInfo recordNoLogin = vaccineSchemeService.getRecord(req.getBabyId(), 0, 1l);
        BaseResponsePlus<VaccineRecordInfo> rs = new BaseResponsePlus<>();
        rs.success("查询成功", recordNoLogin);
        return rs;
    }

    @ApiOperation("更新接种证")
    @PostMapping("/updateRecord")
    public BaseResponse updateRecord(@Valid @RequestBody UpdateRecordReq req) {
        VaccineRecord record = new VaccineRecord();
        BeanUtil.copyProperties(req, record);
        record.setVaccinationDate(DateUtil.dateToLocalDateTime(req.getVaccinationDate()));
        record.setVaccinationDateActual(DateUtil.dateToLocalDateTime(req.getVaccinationDateActual()));
        boolean rs = service.updateRecord(record);
        if (rs) {
            return BaseResponse.success("更新成功");
        }
        return BaseResponse.failed("更新失败");
    }

}
