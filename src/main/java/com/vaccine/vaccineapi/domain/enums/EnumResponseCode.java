package com.vaccine.vaccineapi.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumResponseCode {

    SUCCESS("0000", "成功"),
    ERROR("0001", "失败"),
    TIME_OUT("0002", "登陆超时");

    private String code;

    private String desc;

}
