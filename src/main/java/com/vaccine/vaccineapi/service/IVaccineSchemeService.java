package com.vaccine.vaccineapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vaccine.vaccineapi.controller.vo.scheme.SchemeInfo;
import com.vaccine.vaccineapi.entity.VaccineScheme;

import java.util.List;

/**
 * <p>
 * 疫苗方案 服务类
 * </p>
 *
 * @author admin
 * @since 2019-11-18
 */
public interface IVaccineSchemeService extends IService<VaccineScheme> {

    /**
     * 查询系统预设方案
     * @param schemeType
     * @return
     */
    List<SchemeInfo> getScheme(Integer schemeType, Integer provinceId);

}
