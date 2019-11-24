package com.vaccine.vaccineapi.controller;


import com.vaccine.vaccineapi.controller.vo.scheme.GetSchemeReq;
import com.vaccine.vaccineapi.controller.vo.scheme.SaveSchemeReq;
import com.vaccine.vaccineapi.controller.vo.scheme.SchemeInfo;
import com.vaccine.vaccineapi.domain.BaseResponse;
import com.vaccine.vaccineapi.domain.BaseResponsePlus;
import com.vaccine.vaccineapi.exception.BusinessException;
import com.vaccine.vaccineapi.service.IVaccineSchemeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
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
 * @since 2019-11-18
 */
@Slf4j
@Api(tags = {"疫苗方案"})
@RestController
@RequestMapping("/api/vaccineScheme")
public class VaccineSchemeController {

    @Resource
    private IVaccineSchemeService service;

    @ApiOperation("获取基础方案，已登录")
    @PostMapping("/getScheme")
    public BaseResponsePlus<SchemeInfo> getScheme(@Valid @RequestBody GetSchemeReq req) {
        SchemeInfo schemeInfo = service.getScheme(req.getSchemeType(), req.getBabyId());
        BaseResponsePlus<SchemeInfo> rs = new BaseResponsePlus<>();
        rs.success("查询成功", schemeInfo);
        return rs;
    }

    @ApiOperation("保存方案")
    @PostMapping("/saveScheme")
    public BaseResponse saveScheme(@Valid @RequestBody SaveSchemeReq req) {
        if (CollectionUtils.isEmpty(req.getVaccineRecordReqList())) {
            throw new BusinessException("疫苗方案信息为空");
        }
        boolean rs = service.saveScheme(req.getBabyId(), req.getVaccineRecordReqList());
        if (rs) {
            return BaseResponse.success("保存成功");
        }
        return BaseResponse.failed("保存失败");
    }

}
