package com.vaccine.vaccineapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vaccine.vaccineapi.controller.vo.scheme.SchemeCell;
import com.vaccine.vaccineapi.controller.vo.scheme.SchemeInfo;
import com.vaccine.vaccineapi.controller.vo.scheme.SchemeVaccineInfo;
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
 * 疫苗方案 服务实现类
 * </p>
 *
 * @author admin
 * @since 2019-11-18
 */
@Service
public class VaccineSchemeServiceImpl extends ServiceImpl<VaccineSchemeMapper, VaccineScheme> implements IVaccineSchemeService {

    @Override
    public SchemeInfo getScheme(Integer schemeType, Integer provinceId) {
        SchemeInfo schemeInfo = new SchemeInfo();
        List<GetSchemeDTO> schemeList = getBaseMapper().getScheme(schemeType, provinceId);
        List<SchemeVaccineInfo> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(schemeList)) {
            return schemeInfo;
        }
        SchemeCell cell = null;
        Map<Long, SchemeVaccineInfo> schemeVaccineInfoMap = new LinkedHashMap<>();
        SchemeVaccineInfo schemeVaccineInfo = null;
        for (GetSchemeDTO schemeDTO : schemeList) {
            schemeVaccineInfo = schemeVaccineInfoMap.get(schemeDTO.getVaccineDetailId());
            if (schemeVaccineInfo == null) {
                schemeVaccineInfo = new SchemeVaccineInfo();
            }
            schemeVaccineInfo.setVaccineName(schemeDTO.getVaccineName());
            schemeVaccineInfo.setVaccineDetailId(schemeDTO.getVaccineDetailId());
            schemeVaccineInfo.setSameEffect(schemeDTO.getSameEffect());
            schemeVaccineInfo.setRelevant(schemeDTO.getRelevant());

            cell = new SchemeCell();
            cell.setVaccineDetailId(schemeDTO.getVaccineDetailId());
            cell.setVaccineSchemeId(schemeDTO.getVaccineSchemeId());
            cell.setMonthNumS(schemeDTO.getMonthNumS());
            cell.setMonthNumE(schemeDTO.getMonthNumE());
            cell.setStatus(schemeDTO.getStatus());
            cell.setSchemeType(schemeDTO.getSchemeType());
            cell.setProvinceId(schemeDTO.getProvinceId());
            schemeVaccineInfo.getCellMap().put(schemeDTO.getVaccineDetailId() + "_"
                    + schemeDTO.getMonthNumS(), cell);

            schemeVaccineInfoMap.put(schemeDTO.getVaccineDetailId(), schemeVaccineInfo);
        }
        for (Long key : schemeVaccineInfoMap.keySet()) {
            list.add(schemeVaccineInfoMap.get(key));
        }
        schemeInfo.setSchemeVaccineInfoList(list);

        if (schemeType == 1 || schemeType == 2 || schemeType == 3) {
            //去接种点次数
            Integer hospitalTimes = getBaseMapper().getHospitalTimes(schemeType, provinceId);
            //累计接种剂次
            Integer totalDosageNum = getBaseMapper().getTotalDosageNum(schemeType, provinceId);
            //接种疫苗种数
            Integer vaccineNum = getBaseMapper().getVaccineNum(schemeType, provinceId);
            //预防疾病种数
            Integer diseaseNum = getBaseMapper().getVaccineNum(schemeType, provinceId);
            schemeInfo.setHospitalTimes(hospitalTimes - 1);
            schemeInfo.setTotalDosageNum(totalDosageNum);
            schemeInfo.setVaccineNum(vaccineNum);
            schemeInfo.setDiseaseNum(diseaseNum);
        }

        return schemeInfo;
    }

}
