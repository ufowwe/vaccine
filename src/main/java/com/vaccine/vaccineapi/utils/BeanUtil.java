package com.vaccine.vaccineapi.utils;

import org.springframework.beans.BeanUtils;

/**
 * @author hongye.lv
 * @date 2019/07/26
 **/
public class BeanUtil {

    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

}
