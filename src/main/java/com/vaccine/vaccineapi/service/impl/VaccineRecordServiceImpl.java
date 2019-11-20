package com.vaccine.vaccineapi.service.impl;

import com.vaccine.vaccineapi.entity.VaccineRecord;
import com.vaccine.vaccineapi.mapper.VaccineRecordMapper;
import com.vaccine.vaccineapi.service.IVaccineRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 疫苗方案 服务实现类
 * </p>
 *
 * @author admin
 * @since 2019-11-20
 */
@Service
public class VaccineRecordServiceImpl extends ServiceImpl<VaccineRecordMapper, VaccineRecord> implements IVaccineRecordService {

}
