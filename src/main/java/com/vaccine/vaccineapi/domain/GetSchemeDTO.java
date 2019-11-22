package com.vaccine.vaccineapi.domain;

import lombok.Data;

import java.math.BigDecimal;

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
    private Integer diseaseNum;
    private Integer freeStatus;
    private BigDecimal price;
    private Long vaccineSchemeId;
    private Integer times;
    private String vaccinationAge;
    private Double monthNumS;
    private Double monthNumE;
    private Integer immunityProgram;
    private Integer status;
    private Integer schemeType;
    private Integer provinceId;

}
