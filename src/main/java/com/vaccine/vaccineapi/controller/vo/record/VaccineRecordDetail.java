package com.vaccine.vaccineapi.controller.vo.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Data
@ApiModel(value = "接种证明细")
public class VaccineRecordDetail {

    @ApiModelProperty(value = "疫苗名称")
    private String name;

    @ApiModelProperty(value = "免费标识，1：一类，免费；2：二类，不免费")
    private Integer freeStatus;

    @ApiModelProperty(value = "当前第几剂次")
    private Integer currTimes;

    @ApiModelProperty(value = "总共几剂次")
    private Integer totalTimes;

    @ApiModelProperty(value = "接种日期")
    private LocalDateTime vaccinationDate;

    @ApiModelProperty(value = "接种状态，0：未选择；1：已选未接种；2：已选同效苗；3：已种同效苗；4：已部分接种；5：已完成接种；6：遗漏接种")
    private Integer vaccinationStatus;

}
