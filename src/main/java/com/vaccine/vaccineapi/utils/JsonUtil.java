package com.vaccine.vaccineapi.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.TypeUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Json工具包
 *
 * @author yaoheling
 * @time 2018/7/12 18:02
 */
public class JsonUtil {

    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        String result = null;
        try {
            IOUtils.DEFAULT_PROPERTIES.put(IOUtils.FASTJSON_COMPATIBLEWITHJAVABEAN, true);
            TypeUtils.compatibleWithJavaBean = true;
            result = JSONObject.toJSONString(obj);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T parseObject(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        T result = null;
        try {
            IOUtils.DEFAULT_PROPERTIES.put(IOUtils.FASTJSON_COMPATIBLEWITHJAVABEAN, true);
            TypeUtils.compatibleWithJavaBean = true;
            result = JSONObject.parseObject(json, clazz);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public static String toJsonAndFormat(Object obj) {
        String json = toJson(obj);
        json = formatJson(json);
        return json;
    }

    /**
     * 打印日志时格式化输出信息
     */
    public static String formatJson(String jsonStr) {
        try {
            if (null == jsonStr || "".equals(jsonStr))
                return "";
            StringBuilder sb = new StringBuilder();
            char last = '\0';
            char current = '\0';
            int indent = 0;
            boolean isInQuotationMarks = false;
            for (int i = 0; i < jsonStr.length(); i++) {
                last = current;
                current = jsonStr.charAt(i);
                switch (current) {
                    case '"':
                        if (last != '\\') {
                            isInQuotationMarks = !isInQuotationMarks;
                        }
                        sb.append(current);
                        break;
                    case '{':
                    case '[':
                        sb.append(current);
                        if (!isInQuotationMarks) {
                            sb.append('\n');
                            indent++;
                            addIndentBlank(sb, indent);
                        }
                        break;
                    case '}':
                    case ']':
                        if (!isInQuotationMarks) {
                            sb.append('\n');
                            indent--;
                            addIndentBlank(sb, indent);
                        }
                        sb.append(current);
                        break;
                    case ',':
                        sb.append(current);
                        if (last != '\\' && !isInQuotationMarks) {
                            sb.append('\n');
                            addIndentBlank(sb, indent);
                        }
                        break;
                    default:
                        sb.append(current);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            return jsonStr;
        }
    }

    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }

    public static Map<String, Object> beanToMap(Object obj) {
        String temp = JSONObject.toJSONString(obj);
        HashMap<String,Object> hashMap = JSONObject.parseObject(temp, HashMap.class);
        return hashMap;
    }
    /**
     * object转换成map
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, String> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, String> map = new HashMap<String, String>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, String.valueOf(value));
        }
        return map;
    }
}
