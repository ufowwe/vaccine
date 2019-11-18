package com.vaccine.vaccineapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vaccine.vaccineapi.controller.vo.scheme.SchemeInfo;
import com.vaccine.vaccineapi.domain.GetSchemeDTO;
import com.vaccine.vaccineapi.entity.VaccineSchemeBase;
import com.vaccine.vaccineapi.mapper.VaccineSchemeBaseMapper;
import com.vaccine.vaccineapi.service.IVaccineSchemeBaseService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 疫苗基础方案 服务实现类
 * </p>
 *
 * @author admin
 * @since 2019-11-11
 */
@Service
public class VaccineSchemeBaseServiceImpl extends ServiceImpl<VaccineSchemeBaseMapper, VaccineSchemeBase> implements IVaccineSchemeBaseService {

    @Override
    public List<SchemeInfo> getSchemeBase(Integer schemeType) {
        /*List<GetSchemeDTO> schemeBaseList = this.baseMapper.getSchemeBase(schemeType);
        List<SchemeInfo> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(schemeBaseList)) {
            return null;
        }
        SchemeCell cell = null;
        Map<Long, SchemeInfo> schemeInfoMap = new LinkedHashMap<>();
        SchemeInfo schemeInfo = null;
        for (GetSchemeDTO schemeBaseDTO : schemeBaseList) {
            schemeInfo = schemeInfoMap.get(schemeBaseDTO.getVaccineDetailId());
            if (schemeInfo == null) {
                schemeInfo = new SchemeInfo();
            }
            schemeInfo.setVaccineName(schemeBaseDTO.getVaccineName());
            schemeInfo.setVaccineDetailId(schemeBaseDTO.getVaccineDetailId());

            cell = new SchemeCell();
            cell.setVaccineDetailId(schemeBaseDTO.getVaccineDetailId());
            cell.setVaccineTimesId(schemeBaseDTO.getVaccineTimesId());
            cell.setStatus(schemeBaseDTO.getStatus());
            cell.setSchemeType(schemeBaseDTO.getSchemeType());
            schemeInfo.getCellMap().put(schemeBaseDTO.getVaccineDetailId() + "_"
                    + schemeBaseDTO.getMonthNumS(), cell);

            schemeInfoMap.put(schemeBaseDTO.getVaccineDetailId(), schemeInfo);
        }
        for (Long key : schemeInfoMap.keySet()) {
            list.add(schemeInfoMap.get(key));
        }
        return list;*/
        return null;
    }

    @Override
    public List<SchemeInfo> getSchemeBaseNoBaby() {
        List<GetSchemeDTO> schemeBaseList = this.baseMapper.getSchemeBase(5);
        List<SchemeInfo> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(schemeBaseList)) {
            return null;
        }
        for (GetSchemeDTO schemeBaseDTO : schemeBaseList) {

        }
        return null;
    }
}
