package com.vaccine.vaccineapi.controller.vo.scheme;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Data
@ApiModel(value = "方案单元格信息")
public class SchemeCell {

    @ApiModelProperty(value = "疫苗明细ID")
    private Long vaccineDetailId;

    @ApiModelProperty(value = "疫苗剂次ID")
    private Long vaccineSchemeId;

    @ApiModelProperty(value = "剂次")
    private Integer times;

    @ApiModelProperty(value = "接种年龄")
    private String vaccinationAge;

    @ApiModelProperty(value = "最小月")
    private Double monthNumS;

    @ApiModelProperty(value = "最大月")
    private Double monthNumE;

    @ApiModelProperty(value = "免疫程序，1：基础免疫；2：加强免疫")
    private Integer immunityProgram;

    @ApiModelProperty(value = "选取状态，0：未选择；1：已选择")
    private Integer status;

    @ApiModelProperty(value = "方案类型，1：国家免费方案；2：常规推荐方案；3：最优推荐方案；4：自定义方案")
    private Integer schemeType;

    @ApiModelProperty(value = "所属省ID")
    private Integer provinceId;

}
