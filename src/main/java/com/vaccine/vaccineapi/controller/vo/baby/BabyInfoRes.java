package com.vaccine.vaccineapi.controller.vo.baby;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Data
@ApiModel(value = "宝宝集合信息")
public class BabyInfoRes {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "年龄")
    private String age;

}
