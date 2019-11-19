package com.vaccine.vaccineapi.domain;

import com.vaccine.vaccineapi.domain.enums.EnumResponseCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * @author hongye.lv
 * @date 2019/5/17 14:30
 */
@Getter
@Setter
@ApiModel(value = "BaseResponsePlus", description = "通用响应实体包含泛型")
public class BaseResponsePlus<T> {

    @ApiModelProperty(value = "响应编码")
    private String code;

    @ApiModelProperty(value = "响应信息文字说明")
    private String responseMsg;

    @ApiModelProperty(value = "响应内容")
    private T data;

    public BaseResponsePlus() {
    }

    public BaseResponsePlus(String responseMsg, T data) {
        this.responseMsg = responseMsg;
        this.data = data;
    }

    public BaseResponsePlus(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.responseMsg = responseCode.getMsg();
        this.data = data;
    }

    public BaseResponsePlus(ResponseCode responseCode, String responseMsg, T data) {
        this.code = responseCode.getCode();
        this.responseMsg = responseCode.getMsg() + "|" + (isEmpty(responseMsg) ? "" : responseMsg);
        this.data = data;
    }

    public void success(String message) {
        success(message, null);
    }

    public void success(T data) {
        success(null, data);
    }

    public void success(String message, T data) {
        this.setCode(EnumResponseCode.SUCCESS.getCode());
        this.setResponseMsg(message);
        this.setData(data);
    }

    public void failed(String message) {
        failed(message, null);
    }

    public void failed(T data) {
        failed(null, data);
    }

    public void failed(String message, T data) {
        failed(EnumResponseCode.ERROR, message, data);
    }

    /**
     * 登录过期、未登录用户
     * @param message
     * @param data
     * @return
     */
    public void invalid(String message, T data) {
        failed(EnumResponseCode.TIME_OUT, message, data);
    }

    public void failed(EnumResponseCode code, String message, T data) {
        this.setCode(code.getCode());
        this.setResponseMsg(message);
        this.setData(data);
    }

}
