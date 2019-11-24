package com.vaccine.vaccineapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vaccine.vaccineapi.controller.vo.scheme.SchemeColumn;
import com.vaccine.vaccineapi.domain.GetSchemeDTO;
import com.vaccine.vaccineapi.entity.VaccineScheme;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 疫苗方案 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2019-11-18
 */
public interface VaccineSchemeMapper extends BaseMapper<VaccineScheme> {

    /**
     * 查询基础方案
     * @param schemeType
     * @return
     */
    List<GetSchemeDTO> getScheme(@Param("schemeType") Integer schemeType, @Param("provinceId") Long provinceId);

    List<GetSchemeDTO> getRecord(@Param("babyId") Long babyId);

    Integer getHospitalTimes(@Param("schemeType") Integer schemeType, @Param("provinceId") Long provinceId);

    Integer getTotalDosageNum(@Param("schemeType") Integer schemeType, @Param("provinceId") Long provinceId);

    Integer getVaccineNum(@Param("schemeType") Integer schemeType, @Param("provinceId") Long provinceId);

    Integer getDiseaseNum(@Param("schemeType") Integer schemeType, @Param("provinceId") Long provinceId);

    List<SchemeColumn> getColumn(@Param("schemeType") Integer schemeType, @Param("provinceId") Long provinceId);

}
