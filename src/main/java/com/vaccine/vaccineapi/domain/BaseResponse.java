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
@ApiModel(value = "BaseResponse", description = "通用响应实体")
public class BaseResponse {

    @ApiModelProperty(value = "响应编码")
    private String code;

    @ApiModelProperty(value = "响应信息文字说明")
    private String responseMsg;

    @ApiModelProperty(value = "响应内容")
    private Object data;

    public BaseResponse() {
    }

    public BaseResponse(ResponseCode responseCode, Object data) {
        this.code = responseCode.getCode();
        this.responseMsg = responseCode.getMsg();
        this.data = data;
    }

    public BaseResponse(ResponseCode responseCode, String responseMsg, Object data) {
        this.code = responseCode.getCode();
        this.responseMsg = responseCode.getMsg() + "|" + (isEmpty(responseMsg) ? "" : responseMsg);
        this.data = data;
    }

    public static BaseResponse success(String message) {
        return success(message, null);
    }

    public static BaseResponse success(Object data) {
        return success(null, data);
    }

    public static BaseResponse success(String message, Object data) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(EnumResponseCode.SUCCESS.getCode());
        baseResponse.setResponseMsg(message);
        baseResponse.setData(data);
        return baseResponse;
    }

    public static BaseResponse failed(String message) {
        return failed(message, null);
    }

    public static BaseResponse failed(Object data) {
        return failed(null, data);
    }

    public static BaseResponse failed(String message, Object data) {
        return failed(EnumResponseCode.ERROR, message, data);
    }

    /**
     * 登录过期、未登录用户
     * @param message
     * @param data
     * @return
     */
    public static BaseResponse invalid(String message, Object data) {
        return failed(EnumResponseCode.TIME_OUT, message, data);
    }

    public static BaseResponse failed(EnumResponseCode code, String message, Object data) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(code.getCode());
        baseResponse.setResponseMsg(message);
        baseResponse.setData(data);
        return baseResponse;
    }

    public static BaseResponse createBaseResponse(ResponseCode responseCode) {
        BaseResponse response = new BaseResponse();
        response.setCode(responseCode.getCode());
        response.setResponseMsg(responseCode.getMsg());
        return response;
    }

    public static BaseResponse createBaseResponse(ResponseCode responseCode, Object data) {
        BaseResponse response = new BaseResponse();
        response.setCode(responseCode.getCode());
        response.setResponseMsg(responseCode.getMsg());
        response.setData(data);
        return response;
    }

    public static BaseResponse createBaseResponse(ResponseCode responseCode, String responseMsg, Object data) {
        return new BaseResponse(responseCode, responseMsg, data);
    }

}
