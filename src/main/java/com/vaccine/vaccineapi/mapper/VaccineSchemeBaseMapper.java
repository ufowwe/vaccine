package com.vaccine.vaccineapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vaccine.vaccineapi.domain.GetSchemeBaseDTO;
import com.vaccine.vaccineapi.entity.VaccineSchemeBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 疫苗基础方案 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2019-11-11
 */
public interface VaccineSchemeBaseMapper extends BaseMapper<VaccineSchemeBase> {

    /**
     * 查询基础方案
     * @param schemeType
     * @return
     */
    List<GetSchemeBaseDTO> getSchemeBase(@Param("schemeType") Integer schemeType);

}
