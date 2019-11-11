package com.vaccine.vaccineapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vaccine.vaccineapi.controller.vo.scheme.SchemeInfo;
import com.vaccine.vaccineapi.entity.VaccineSchemeBase;

import java.util.List;

/**
 * <p>
 * 疫苗基础方案 服务类
 * </p>
 *
 * @author admin
 * @since 2019-11-11
 */
public interface IVaccineSchemeBaseService extends IService<VaccineSchemeBase> {

    /**
     * 查询基础服务
     * @param schemeType
     * @return
     */
    List<SchemeInfo> getSchemeBase(Integer schemeType);

}
