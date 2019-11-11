package com.vaccine.vaccineapi.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author yaoheling
 * @date 2019/5/27 21:33
 */
public class DateUtil {

    public static final String HMS = "yyyy-MM-dd HH:mm:ss";
    public static final SimpleDateFormat HH_MM_SS = getDateFormat(HMS);

    public static final String YYYYMMDD = "yyyy/MM/dd";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 获取SimpleDateFormat
     *
     * @param parttern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    public static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {
        return new SimpleDateFormat(parttern);
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date 日期
     * @return 日期字符串
     */
    public static String dateToStringYMDHMS(Date date) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = HH_MM_SS.format(date);
            } catch (Exception e) {
            }
        }
        return dateString;
    }



    public static String formatDate(Date date, String formatter) {
        DateFormat df = new SimpleDateFormat(formatter);
        return df.format(date);
    }

    public static String formatNow(String formatter) {
        DateFormat df = new SimpleDateFormat(formatter);
        return df.format(new Date());
    }

    public static LocalDateTime dateToLocalDateTime(Date time) {
        Instant instant = time.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

}
