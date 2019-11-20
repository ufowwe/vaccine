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

    @ApiModelProperty(value = "疫苗ID")
    @NotNull(message = "疫苗ID为空")
    private List<Long> vaccineDetailIdList;

    @ApiModelProperty(value = "方案类型，1：国家免费方案；2：常规推荐方案；3：最优推荐方案；4：自定义方案")
    @NotNull(message = "方案类型为空")
    private Integer schemeType;

    @ApiModelProperty(value = "所属省ID")
    @NotNull(message = "所属省ID为空")
    private Long provinceId;

}
