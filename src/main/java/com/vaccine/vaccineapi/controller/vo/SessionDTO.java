package com.vaccine.vaccineapi.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDTO {

    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 会话密钥
     */
    private String sessionKey;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 用户ID
     */
    private Long id;

}
