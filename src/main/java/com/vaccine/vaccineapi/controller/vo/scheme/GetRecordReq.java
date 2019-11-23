package com.vaccine.vaccineapi.controller.vo.scheme;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Data
@ApiModel(value = "获取接种证参数")
public class GetRecordReq {

    @ApiModelProperty(value = "宝宝ID")
    private Long babyId;

}
