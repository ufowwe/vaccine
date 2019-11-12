package com.vaccine.vaccineapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vaccine.vaccineapi.domain.BabyInfoDTO;
import com.vaccine.vaccineapi.entity.Baby;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 宝宝表 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2019-10-31
 */
public interface BabyMapper extends BaseMapper<Baby> {

    List<BabyInfoDTO> getBabyInfoDTO(@Param("userId") Long userId);

}
