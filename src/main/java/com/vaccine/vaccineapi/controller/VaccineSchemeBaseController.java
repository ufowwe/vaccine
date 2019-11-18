package com.vaccine.vaccineapi.controller;


import com.vaccine.vaccineapi.service.IVaccineSchemeBaseService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

}
