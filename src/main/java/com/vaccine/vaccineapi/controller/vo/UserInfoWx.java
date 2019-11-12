package com.vaccine.vaccineapi.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Data
@ApiModel(value = "微信用户信息")
public class UserInfoWx {

    @ApiModelProperty(value = "用户昵称")
    @NotBlank(message = "用户昵称为空")
    private String nickName;

    @ApiModelProperty(value = "用户性别")
    @NotNull(message = "用户性别为空")
    private Integer sex;

    @ApiModelProperty(value = "用户头像")
    @NotBlank(message = "用户头像为空")
    private String avatar;



}
