package com.vaccine.vaccineapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vaccine.vaccineapi.controller.vo.record.VaccineRecordInfo;
import com.vaccine.vaccineapi.controller.vo.scheme.SchemeInfo;
import com.vaccine.vaccineapi.controller.vo.scheme.VaccineRecordReq;
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
    SchemeInfo getScheme(Integer schemeType, Integer provinceId);

    /**
     * 保存方案
     * @param babyId
     * @param vaccineRecordReqList
     * @return
     */
    boolean saveScheme(Long babyId, List<VaccineRecordReq> vaccineRecordReqList);

    /**
     * 查询未登录、没有有宝宝时的接种证
     * @param schemeType
     * @return
     */
    VaccineRecordInfo getRecordNoLogin(Integer schemeType, Integer provinceId);

    /**
     * 查询登录后的接种证
     * @param schemeType
     * @return
     */
    VaccineRecordInfo getRecord(Long babyId, Integer schemeType, Integer provinceId);


}
