package com.vaccine.vaccineapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回码枚举类
 * 1xxx 平台校验失败
 * 2xxx 平台数据库错误
 * 3xxx 平台内部服务错误
 * 4xxx 三方接口错误
 *
 * @author yaoheling
 * @date 2019/5/17 14:34
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {

    SUCCESS("0000", "成功"),
    Failed("0001","失败"),
    PROCESSING("9999", "处理中"),
    ARGUMENT_CHECK_FAILED("0027", "参数校验不通过"),
    FORBIDDEN("0403", "没有访问权限"),
    INTERNAL_SERVER_ERROR("3000", "平台内部服务异常"),
    HTTP_RESPONSE_NULL("4002", "三方接口响应信息为空");

    private String code;
    private String msg;

}
