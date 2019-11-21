package com.vaccine.vaccineapi.controller.vo.record;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Data
@ApiModel(value = "接种证列表信息")
public class VaccineRecordInfo {

    @ApiModelProperty(value = "按月龄分组集合")
    private List<VaccineRecordGroup> vaccineRecordGroupList = new ArrayList<>();

}
