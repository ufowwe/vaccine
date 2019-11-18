package com.vaccine.vaccineapi.domain;

import lombok.Data;

/**
 * @author hongye.lv
 * @date 2019/11/11
 **/
@Data
public class GetSchemeDTO {

    private Long vaccineDetailId;
    private String vaccineName;
    private String sameEffect;
    private String relevant;
    private Long vaccineSchemeId;
    private Integer times;
    private Integer monthNumS;
    private Integer monthNumE;
    private Integer status;
    private Integer schemeType;
    private Integer provinceId;

}
