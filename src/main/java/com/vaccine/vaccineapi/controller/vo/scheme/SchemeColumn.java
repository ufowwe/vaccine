package com.vaccine.vaccineapi.controller.vo.scheme;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Data
@ApiModel(value = "方案表头信息")
public class SchemeColumn {

    @ApiModelProperty(value = "列名称")
    private String vaccinationAge;

    @ApiModelProperty(value = "最小月")
    private Integer monthNumS;

    @ApiModelProperty(value = "最大月")
    private Integer monthNumE;

}
