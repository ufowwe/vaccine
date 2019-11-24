package com.vaccine.vaccineapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.vaccine.vaccineapi.controller.vo.record.VaccineRecordDetail;
import com.vaccine.vaccineapi.controller.vo.record.VaccineRecordGroup;
import com.vaccine.vaccineapi.controller.vo.record.VaccineRecordInfo;
import com.vaccine.vaccineapi.controller.vo.scheme.*;
import com.vaccine.vaccineapi.domain.GetSchemeDTO;
import com.vaccine.vaccineapi.entity.Baby;
import com.vaccine.vaccineapi.entity.VaccineRecord;
import com.vaccine.vaccineapi.entity.VaccineScheme;
import com.vaccine.vaccineapi.mapper.VaccineSchemeMapper;
import com.vaccine.vaccineapi.service.*;
import com.vaccine.vaccineapi.utils.BeanUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

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

    @Resource
    private IVaccineRecordService vaccineRecordService;

    @Resource
    private IBabyService babyService;

    @Resource
    private IUserBabyService userBabyService;

    @Resource
    private IUserService userService;

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
        int times = 0;
        for (GetSchemeDTO schemeDTO : schemeList) {
            schemeVaccineInfo = schemeVaccineInfoMap.get(schemeDTO.getVaccineDetailId());
            if (schemeVaccineInfo == null) {
                schemeVaccineInfo = new SchemeVaccineInfo();
            }
            schemeVaccineInfo.setVaccineName(schemeDTO.getVaccineName());
            schemeVaccineInfo.setVaccineDetailId(schemeDTO.getVaccineDetailId());
            //同效疫苗
            schemeVaccineInfo.setSameEffect(schemeDTO.getSameEffect());
            //相关疫苗
            schemeVaccineInfo.setRelevant(schemeDTO.getRelevant());
            //预防疾病种类
            schemeVaccineInfo.setDiseaseNum(schemeDTO.getDiseaseNum());
            //疫苗价格
            schemeVaccineInfo.setPrice(schemeDTO.getPrice());

            cell = new SchemeCell();
            cell.setVaccineDetailId(schemeDTO.getVaccineDetailId());
            cell.setVaccineSchemeId(schemeDTO.getVaccineSchemeId());
            cell.setTimes(schemeDTO.getTimes());
            cell.setVaccinationAge(schemeDTO.getVaccinationAge());
            cell.setMonthNumS(schemeDTO.getMonthNumS());
            cell.setMonthNumE(schemeDTO.getMonthNumE());
            cell.setImmunityProgram(schemeDTO.getImmunityProgram());
            cell.setStatus(schemeDTO.getStatus());
            cell.setSchemeType(schemeDTO.getSchemeType());
            cell.setProvinceId(schemeDTO.getProvinceId());
            schemeVaccineInfo.getCellMap().put(schemeDTO.getVaccineDetailId() + "_"
                    + schemeDTO.getMonthNumS(), cell);
            //累计剂次数
            schemeVaccineInfo.setDosageTimes(schemeVaccineInfo.getDosageTimes() + 1);
            //选中状态
            schemeVaccineInfo.setStatus(cell.getStatus());

            schemeVaccineInfoMap.put(schemeDTO.getVaccineDetailId(), schemeVaccineInfo);
        }
        int diseaseNum = 0;
        for (Long key : schemeVaccineInfoMap.keySet()) {
            SchemeVaccineInfo temp = schemeVaccineInfoMap.get(key);
            //计算单个疫苗累计接种疫苗种数
//            int vaccineNum = temp.getStatus() * temp.getDosageTimes() * temp.getDiseaseNum();
//            temp.setVaccineNum(vaccineNum);
            list.add(temp);
        }
        computeDiseaseNum(list);
        //疫苗剂次信息
        schemeInfo.setSchemeVaccineInfoList(list);

        if (schemeType == 1 || schemeType == 2 || schemeType == 3) {
            //去接种点次数
            Integer hospitalTimes = getBaseMapper().getHospitalTimes(schemeType, provinceId);
            schemeInfo.setHospitalTimes(hospitalTimes - 1);
            //累计接种剂次
            Integer totalDosageNum = getBaseMapper().getTotalDosageNum(schemeType, provinceId);
            schemeInfo.setTotalDosageNum(totalDosageNum);
            //接种疫苗种数
            Integer vaccineNum = getBaseMapper().getVaccineNum(schemeType, provinceId);
            schemeInfo.setVaccineNum(vaccineNum);
            //预防疾病种数
            diseaseNum = getBaseMapper().getDiseaseNum(schemeType, provinceId);
            schemeInfo.setDiseaseNum(diseaseNum);
        }
        //获取表头
        List<SchemeColumn> columnList = getBaseMapper().getColumn(schemeType, provinceId);
        schemeInfo.setColumnList(columnList);

        return schemeInfo;
    }

    private int computeDiseaseNum(List<SchemeVaccineInfo> list) {
        int diseaseNum = 0;
        for (SchemeVaccineInfo info : list) {
            diseaseNum += info.getStatus() * info.getDiseaseNum();
        }
        return 0;
    }

    @Override
    public boolean saveScheme(Long babyId, List<VaccineRecordReq> vaccineRecordReqList) {
        //查询宝宝信息
        Baby baby = babyService.getById(babyId);
        LocalDateTime birthday = baby.getBirthday();
        LocalDateTime birthdayTemp = null;
        VaccineRecord record = null;
        List<VaccineRecord> recordList = new ArrayList<>();
        for (VaccineRecordReq recordReq : vaccineRecordReqList) {
            birthdayTemp = birthday;
            record = new VaccineRecord();
            BeanUtil.copyProperties(recordReq, record);
            record.setBabyId(babyId);
            //计算推荐接种时间
            int month = (int) recordReq.getMonthNumS().doubleValue();
            //如果相等，则month为整数，否则为小数，小数都为0.5，按15天计算
            birthdayTemp = birthdayTemp.plusMonths(month);
            if (recordReq.getMonthNumS().doubleValue() != month) {
                birthdayTemp = birthdayTemp.plusDays(15);
            }
            record.setVaccinationDate(birthdayTemp);
            record.setVaccinationStatus(0);
            recordList.add(record);
        }
        List<VaccineRecord> vaccineRecordList = getByBabyId(babyId);
        if (!CollectionUtils.isEmpty(vaccineRecordList)) {
            //如果已存在，则删除
            deleteByBabyId(babyId);
        }
        return vaccineRecordService.saveBatch(recordList);
    }

    private List<VaccineRecord> getByBabyId(Long babyId) {
        QueryWrapper<VaccineRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(VaccineRecord::getBabyId, babyId);
        return vaccineRecordService.getBaseMapper().selectList(queryWrapper);
    }

    private void deleteByBabyId(Long babyId) {
        QueryWrapper<VaccineRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(VaccineRecord::getBabyId, babyId);
        vaccineRecordService.remove(queryWrapper);
    }

    @Override
    public VaccineRecordInfo getRecordNoLogin(Integer schemeType, Integer provinceId) {
        List<GetSchemeDTO> vaccineSchemeList = getBaseMapper().getScheme(schemeType, provinceId);
        return getRecordBase(vaccineSchemeList);
    }

    @Override
    public VaccineRecordInfo getRecord(Long babyId, Integer schemeType, Integer provinceId) {
        /*QueryWrapper<UserBaby> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserBaby::getUserId, userService.getUserId());
        List<UserBaby> userBabyList = userBabyService.getBaseMapper().selectList(queryWrapper);
        if (CollectionUtils.isEmpty(userBabyList)) {*/
        if (babyId == null) {
            //无宝宝，和未登录相同返回全部疫苗
            return getRecordNoLogin(schemeType, provinceId);
        }
        //有宝宝，则判断是否已生成宝宝出生到当前年龄可打疫苗，如果有则直接返回，如果无则生成
        QueryWrapper<VaccineRecord> recordQueryWrapper = new QueryWrapper<>();
        recordQueryWrapper.lambda().eq(VaccineRecord::getBabyId, babyId);
        Integer recordNum = vaccineRecordService.getBaseMapper().selectCount(recordQueryWrapper);
        if (recordNum != null && recordNum > 0) {
            //存在接种记录，则直接查询并返回
            List<GetSchemeDTO> recordList = getBaseMapper().getRecord(babyId);
            return getRecordBase(recordList);
        }
        //不存在接种记录，需要生成并返回
        Baby baby = babyService.getById(babyId);
        //宝宝生日距今多少个月
        Long monthInterval = baby.getBirthday().toLocalDate().until(LocalDateTime.now().toLocalDate(), ChronoUnit.MONTHS);
        List<VaccineScheme> vaccineSchemeList = getBySchemeTypeAndProvinceIdAndMonthS(1,
                baby.getVaccineProvinceId(), monthInterval.doubleValue());
        LocalDateTime birthday = baby.getBirthday();
        LocalDateTime birthdayTemp = null;
        VaccineRecord record = null;
        List<VaccineRecord> recordList = new ArrayList<>();
        for (VaccineScheme scheme : vaccineSchemeList) {
            birthdayTemp = birthday;
            record = new VaccineRecord();
            BeanUtil.copyProperties(scheme, record);
            record.setBabyId(babyId);
            //计算推荐接种时间
            int month = (int) scheme.getMonthNumS().doubleValue();
            //如果相等，则month为整数，否则为小数，小数都为0.5，按15天计算
            birthdayTemp = birthdayTemp.plusMonths(month);
            if (scheme.getMonthNumS().doubleValue() != month) {
                birthdayTemp = birthdayTemp.plusDays(15);
            }
            record.setVaccinationDate(birthdayTemp);
            record.setVaccinationStatus(0);
            recordList.add(record);
        }
        vaccineRecordService.saveBatch(recordList);
        //存在接种记录，则直接查询并返回
        List<GetSchemeDTO> records = getBaseMapper().getRecord(babyId);
        return getRecordBase(records);
    }

    private VaccineRecordInfo getRecordBase(List<GetSchemeDTO> vaccineSchemeList) {
        //统计每个疫苗的总剂次数
        Map<Long, Integer> timesMap = new HashMap<>();
        for (GetSchemeDTO vaccineScheme : vaccineSchemeList) {
            if (timesMap.get(vaccineScheme.getVaccineDetailId()) == null) {
                timesMap.put(vaccineScheme.getVaccineDetailId(), 0);
            }
            timesMap.put(vaccineScheme.getVaccineDetailId(), timesMap.get(vaccineScheme.getVaccineDetailId()) + 1);
        }
        Multimap<String, VaccineRecordDetail> rsMap = ArrayListMultimap.create();
        VaccineRecordDetail detail = null;
        for (GetSchemeDTO vaccineScheme : vaccineSchemeList) {
            detail = new VaccineRecordDetail();
            detail.setVaccineSchemeId(vaccineScheme.getVaccineSchemeId());
            detail.setName(vaccineScheme.getVaccineName());
            detail.setFreeStatus(vaccineScheme.getFreeStatus());
            detail.setMonthNumS(vaccineScheme.getMonthNumS());
            detail.setCurrTimes(vaccineScheme.getTimes());
            detail.setTotalTimes(timesMap.get(vaccineScheme.getVaccineDetailId()));
            detail.setVaccinationDate(vaccineScheme.getVaccinationDate());
            detail.setVaccinationStatus(vaccineScheme.getVaccinationStatus());
            rsMap.put(vaccineScheme.getVaccinationAge() + "," +vaccineScheme.getMonthNumS(), detail);
        }
        VaccineRecordInfo vaccineRecordInfo = new VaccineRecordInfo();
        VaccineRecordGroup group = null;
        for (String age : rsMap.keySet()) {
            group = new VaccineRecordGroup();
            String[] ageArr = age.split(",");
            group.setVaccinationAge(ageArr[0]);
            group.setMonthNumS(Double.valueOf(ageArr[1]));
            group.setVaccineRecordDetailList(rsMap.get(age));
            vaccineRecordInfo.getVaccineRecordGroupList().add(group);
        }
        vaccineRecordInfo.getVaccineRecordGroupList().sort((o1, o2)->o1.getMonthNumS().compareTo(o2.getMonthNumS()));
//        vaccineRecordInfo.getVaccineRecordGroupList().sort(Comparator.comparing(VaccineRecordGroup::getMonthNumS));
        return vaccineRecordInfo;
    }

    private List<VaccineScheme> getBySchemeTypeAndProvinceId(Integer schemeType, Long provinceId) {
        QueryWrapper<VaccineScheme> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(VaccineScheme::getSchemeType, schemeType).eq(VaccineScheme::getProvinceId, provinceId);
        return getBaseMapper().selectList(queryWrapper);
    }

    private List<VaccineScheme> getBySchemeTypeAndProvinceIdAndMonthS(Integer schemeType, Long provinceId, Double monthNumS) {
        QueryWrapper<VaccineScheme> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(VaccineScheme::getSchemeType, schemeType)
                .eq(VaccineScheme::getProvinceId, provinceId)
                .eq(VaccineScheme::getMonthNumS, monthNumS);
        return getBaseMapper().selectList(queryWrapper);
    }

    public static void main(String[] args) {
        Double a = 6.0;
        int b = (int)a.doubleValue();
        System.out.println(a == b);
        a = 6.5;
        b = (int)a.doubleValue();
        System.out.println(a == b);
    }

}
