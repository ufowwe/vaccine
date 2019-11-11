package com.vaccine.vaccineapi.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Data
@ApiModel(value = "登陆信息")
public class LoginReqVO {

    @ApiModelProperty(value = "微信编码")
    @NotBlank(message = "微信编码为空")
    private String code;

}
