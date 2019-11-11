package com.vaccine.vaccineapi.controller.vo.baby;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Data
@ApiModel(value = "保存宝宝信息")
public class SaveBabyReq {

    @ApiModelProperty(value = "性别")
    @NotNull(message = "性别为空")
    private Integer sex;

    @ApiModelProperty(value = "出生日期")
    @NotNull(message = "出生日期为空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

}
