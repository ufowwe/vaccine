package com.vaccine.vaccineapi.controller.vo.baby;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Data
@ApiModel(value = "删除宝宝信息")
public class DeleteBabyReq {

    @ApiModelProperty(value = "主键")
    @NotNull(message = "主键为空")
    private Long id;



}
