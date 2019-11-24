package com.vaccine.vaccineapi.controller.vo.scheme;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Data
@ApiModel(value = "获取基础方案参数")
public class GetSchemeReq {

    @ApiModelProperty(value = "宝宝ID")
    @NotNull(message = "宝宝ID为空")
    private Long babyId;

    @ApiModelProperty(value = "方案类型")
    @NotNull(message = "方案类型为空")
    private Integer schemeType;

}
