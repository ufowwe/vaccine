package com.vaccine.vaccineapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vaccine.vaccineapi.controller.vo.scheme.SchemeCell;
import com.vaccine.vaccineapi.controller.vo.scheme.SchemeInfo;
import com.vaccine.vaccineapi.domain.GetSchemeDTO;
import com.vaccine.vaccineapi.entity.VaccineScheme;
import com.vaccine.vaccineapi.mapper.VaccineSchemeMapper;
import com.vaccine.vaccineapi.service.IVaccineSchemeService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 疫苗剂次 服务实现类
 * </p>
 *
 * @author admin
 * @since 2019-11-18
 */
@Service
public class VaccineSchemeServiceImpl extends ServiceImpl<VaccineSchemeMapper, VaccineScheme> implements IVaccineSchemeService {

    @Override
    public List<SchemeInfo> getScheme(Integer schemeType, Integer provinceId) {
        List<GetSchemeDTO> schemeList = getBaseMapper().getScheme(schemeType, provinceId);
        List<SchemeInfo> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(schemeList)) {
            return null;
        }
        SchemeCell cell = null;
        Map<Long, SchemeInfo> schemeInfoMap = new LinkedHashMap<>();
        SchemeInfo schemeInfo = null;
        for (GetSchemeDTO schemeDTO : schemeList) {
            schemeInfo = schemeInfoMap.get(schemeDTO.getVaccineDetailId());
            if (schemeInfo == null) {
                schemeInfo = new SchemeInfo();
            }
            schemeInfo.setVaccineName(schemeDTO.getVaccineName());
            schemeInfo.setVaccineDetailId(schemeDTO.getVaccineDetailId());
            schemeInfo.setSameEffect(schemeDTO.getSameEffect());
            schemeInfo.setRelevant(schemeDTO.getRelevant());

            cell = new SchemeCell();
            cell.setVaccineDetailId(schemeDTO.getVaccineDetailId());
            cell.setVaccineSchemeId(schemeDTO.getVaccineSchemeId());
            cell.setMonthNumS(schemeDTO.getMonthNumS());
            cell.setMonthNumE(schemeDTO.getMonthNumE());
            cell.setStatus(schemeDTO.getStatus());
            cell.setSchemeType(schemeDTO.getSchemeType());
            cell.setProvinceId(schemeDTO.getProvinceId());
            schemeInfo.getCellMap().put(schemeDTO.getVaccineDetailId() + "_"
                    + schemeDTO.getMonthNumS(), cell);

            schemeInfoMap.put(schemeDTO.getVaccineDetailId(), schemeInfo);
        }
        for (Long key : schemeInfoMap.keySet()) {
            list.add(schemeInfoMap.get(key));
        }
        return list;
    }

}
