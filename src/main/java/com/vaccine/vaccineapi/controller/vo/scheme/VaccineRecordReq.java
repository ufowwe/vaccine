package com.vaccine.vaccineapi.controller.vo.scheme;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 疫苗方案
 * </p>
 *
 * @author admin
 * @since 2019-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="VaccineRecord对象", description="疫苗方案")
public class VaccineRecordReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "疫苗明细ID")
    private Long vaccineDetailId;

    @ApiModelProperty(value = "剂次")
    private Integer times;

    @ApiModelProperty(value = "接种年龄")
    private String vaccinationAge;

    @ApiModelProperty(value = "对应开始月数")
    private Double monthNumS;

    @ApiModelProperty(value = "对应结束约束")
    private Double monthNumE;

    @ApiModelProperty(value = "免疫程序，1：基础免疫；2：加强免疫")
    private Integer immunityProgram;

    @ApiModelProperty(value = "选择状态，0：未选择；1：已选择")
    private Integer status;

    @ApiModelProperty(value = "方案类型，1：国家免费方案；2：常规推荐方案；3：最优推荐方案；4：自定义方案；")
    private Integer schemeType;

    @ApiModelProperty(value = "省ID")
    private Long provinceId;


}
