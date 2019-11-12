package com.vaccine.vaccineapi.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Data
public class BabyInfoDTO {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "出生日期")
    private LocalDateTime birthday;

}
