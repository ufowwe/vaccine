package com.vaccine.vaccineapi.utils;

/**
 * @author hongye.lv
 * @date 2019/07/22
 **/
public class Constants {

    public static final String LOAD_BALANCED_RESTTEMPLATE = "loadBalancedRestTemplate";

    public static final String VANILLA_RESTTEMPLATE = "vanillaRestTemplate";

    public static final String ACCESS_TOKEN = "accessToken";

    public static final long LOGIN_TIMEOUT = 60*60*24*30;

    public static final String QUALITY_SUCCESS_CODE = "1000";

    public static final String QUALITY_JA_TIMEOUNT_CODE = "1023";

    public static final long APP_ID_VERIFY_CODE_LIMIT_EXPIRES = 60 * 60 * 24;

    public static final long APP_ID_VERIFY_CODE_TIME_LIMIT = 20;

    public static final long VERIFY_CODE_EXPIRES_TIME = 60 * 5;

    public static final long APP_FACE_RECOGNIZE_LIMIT_EXPIRES = 1;

    public static final long APP_FACE_RECOGNIZE_TIME_LIMIT = 5;

    public static final String CREDIT_AUTH_CONTRACT_TYPE = "1";

    public static final String THIRD_AUTH_CONTRACT_TYPE = "2";

    public static final long APP_COMPLETEINFO_EXPIRES_TIME = 10;

}
