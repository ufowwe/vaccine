package com.vaccine.vaccineapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vaccine.vaccineapi.controller.vo.scheme.SchemeCell;
import com.vaccine.vaccineapi.controller.vo.scheme.SchemeInfo;
import com.vaccine.vaccineapi.entity.VaccineSchemeBase;
import com.vaccine.vaccineapi.mapper.VaccineSchemeBaseMapper;
import com.vaccine.vaccineapi.service.IVaccineSchemeBaseService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Map<String, Object>> schemeBaseList = this.baseMapper.getSchemeBase(schemeType);
        List<SchemeInfo> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(schemeBaseList)) {
            return null;
        }
        SchemeCell cell = null;
        Map<Long, SchemeInfo> schemeInfoMap = new HashMap<>();
        SchemeInfo schemeInfo = null;
        for (Map<String, Object> map : schemeBaseList) {
            schemeInfo = schemeInfoMap.get((Long) map.get("vaccine_detail_id"));
            if (schemeInfo == null) {
                schemeInfo = new SchemeInfo();
            }
            schemeInfo.setVaccineName(map.get("vaccine_detail_id").toString());
            schemeInfo.setVaccineDetailId((Long) map.get("vaccine_detail_id"));

            cell = new SchemeCell();
            cell.setVaccineDetailId((Long) map.get("vaccine_detail_id"));
            cell.setVaccineTimesId((Long) map.get("vaccine_times_id"));
            cell.setStatus((Integer) map.get("status"));
            cell.setSchemeType((Integer) map.get("scheme_type"));
            schemeInfo.getCellMap().put(map.get("vaccine_detail_id") + "_" + map.get("vaccine_times_id"), cell);

            schemeInfoMap.put((Long) map.get("vaccine_detail_id"), schemeInfo);
        }

        return list;
    }
}
