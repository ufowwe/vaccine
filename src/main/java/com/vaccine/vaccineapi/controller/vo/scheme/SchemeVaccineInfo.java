package com.vaccine.vaccineapi.controller.vo.scheme;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Data
@ApiModel(value = "基础方案疫苗信息")
public class SchemeVaccineInfo {

    @ApiModelProperty(value = "疫苗名称")
    private String vaccineName;

    @ApiModelProperty(value = "疫苗明细ID")
    private Long vaccineDetailId;

    @ApiModelProperty(value = "同效疫苗，逗号分隔")
    private String sameEffect;

    @ApiModelProperty(value = "相关疫苗，逗号分隔")
    private String relevant;

    @ApiModelProperty(value = "预防疾病种类")
    private Integer diseaseNum;

    @ApiModelProperty(value = "总剂次数")
    private Integer dosageTimes = 0;

    @ApiModelProperty(value = "0：未选择；1：已选择")
    private Integer status;

    @ApiModelProperty(value = "单个疫苗累计接种疫苗种数")
    private Integer vaccineNum;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "疫苗剂次信息")
    private Map<String, SchemeCell> cellMap = new LinkedHashMap<>();


}
