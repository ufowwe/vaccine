package com.vaccine.vaccineapi.domain;

import lombok.Data;

/**
 * @author hongye.lv
 * @date 2019/11/11
 **/
@Data
public class GetSchemeBaseDTO {

    private String vaccineName;
    private Long vaccineDetailId;
    private Long vaccineTimesId;
    private Integer status;
    private Integer schemeType;
    private Integer monthNumS;
    private Integer monthNumE;

}
