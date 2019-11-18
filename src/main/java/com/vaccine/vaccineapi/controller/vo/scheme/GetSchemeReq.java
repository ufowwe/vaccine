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

    @ApiModelProperty(value = "方案类型")
    @NotNull(message = "方案类型为空")
    private Integer schemeType;

    @ApiModelProperty(value = "所属省")
    @NotNull(message = "所属省为空")
    private Integer provinceId;

}
