package com.vaccine.vaccineapi.controller.vo.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collection;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Data
@ApiModel(value = "接种证组信息")
public class VaccineRecordGroup {

    @ApiModelProperty(value = "月龄")
    private String vaccinationAge;

    @ApiModelProperty(value = "当前月龄下剂次")
    private Collection<VaccineRecordDetail> VaccineRecordDetailList;

}
