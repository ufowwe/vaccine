package com.vaccine.vaccineapi.controller.vo.scheme;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Data
@ApiModel(value = "保存方案参数")
public class SaveSchemeReq {

    @ApiModelProperty(value = "宝宝ID")
    @NotNull(message = "宝宝ID为空")
    private Long babyId;

    @ApiModelProperty(value = "疫苗方案信息")
    @NotNull(message = "疫苗方案信息为空")
    private List<VaccineRecordReq> vaccineRecordReqList;

}
