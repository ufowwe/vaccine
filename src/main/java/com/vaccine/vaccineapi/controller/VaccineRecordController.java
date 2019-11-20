package com.vaccine.vaccineapi.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.vaccine.vaccineapi.service.IVaccineRecordService;

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
@Api(tags = {"疫苗方案"})
@RestController
@RequestMapping("/api/vaccine-record")
public class VaccineRecordController {

    @Resource
    private IVaccineRecordService service;

}
