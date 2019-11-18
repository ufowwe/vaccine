package com.vaccine.vaccineapi.controller.vo.scheme;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Data
@ApiModel(value = "基础方案信息")
public class SchemeInfo {

    @ApiModelProperty(value = "去接种点次数")
    private Integer hospitalTimes;

    @ApiModelProperty(value = "累计接种剂次")
    private Integer totalDosageNum;

    @ApiModelProperty(value = "接种疫苗种数")
    private Integer vaccineNum;

    @ApiModelProperty(value = "预防疾病种数")
    private Integer diseaseNum;

    @ApiModelProperty(value = "疫苗剂次信息")
    private List<SchemeVaccineInfo> schemeVaccineInfoList;


}
