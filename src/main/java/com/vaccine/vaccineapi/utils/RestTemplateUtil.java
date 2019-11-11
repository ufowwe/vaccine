package com.vaccine.vaccineapi.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
@Setter
@Component
public class RestTemplateUtil {

    @Autowired
    @Qualifier(Constants.VANILLA_RESTTEMPLATE)
    private RestTemplate restTemplate;

    public <T> T get(String url, ParameterizedTypeReference<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json;charset=UTF-8");
        httpHeaders.add("X-Requested-With", "XMLHttpRequest");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONObject.toJSONString(new HashMap<>()), httpHeaders);
        ResponseEntity<T> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity, responseType);
        T body = exchange.getBody();
        log.info("RestTemplateUtil.get, url:{}, body:{}", url, JSONObject.toJSONString(body));
        return body;
    }

    public <T> T getForObject(String url, Class<T> responseType) {
        T object = restTemplate.getForObject(url, responseType, new HashMap<>());
        log.info("RestTemplateUtil.getForObject, url:{}, body:{}", url, JSONObject.toJSONString(object));
        return object;
    }

    public <T> T postForObject(String url,Object request, Class<T> responseType) {
        return restTemplate.postForObject(url, request,responseType,new HashMap<>());
    }

    /**
     * GET请求调用方式
     *
     * @param url 请求URL
     * @param responseType 返回对象类型
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> get(String url, Class<T> responseType) {
        log.info("RestTemplateUtil.get(url, responseType), url:{}", url);
        return restTemplate.getForEntity(url, responseType);
    }
    /**
     * GET请求调用方式
     *
     * @param url 请求URL
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> get(String url, Class<T> responseType, Map<String, ?> uriVariables) {
        return restTemplate.getForEntity(url, responseType, uriVariables);
    }

    public <T> T post(String url, Object params, ParameterizedTypeReference<T> responseType) {
        Map<String,Object> map = JsonUtil.beanToMap(params);
        return post(url, map, responseType);
    }

    public <T> T post(String url, Map<String,Object> params, ParameterizedTypeReference<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json;charset=UTF-8");
        httpHeaders.add("X-Requested-With", "XMLHttpRequest");
        String pm = JSONObject.toJSONString(params);
        log.info("RestTemplateUtil.post, url:{}, params:{}", url, pm);
        HttpEntity<String> httpEntity = new HttpEntity<>(pm, httpHeaders);
        ResponseEntity<T> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, responseType);
        T body = exchange.getBody();
        log.info("RestTemplateUtil.post, url:{}, body:{}", url, JSONObject.toJSONString(body));
        return body;
    }


    /**
     * POST请求调用方式
     *
     * @param url 请求URL
     * @param requestBody 请求参数体
     * @param responseType 返回对象类型
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> post(String url, Object requestBody, Class<T> responseType) {
        return restTemplate.postForEntity(url, requestBody, responseType);
    }

    /**
     * POST请求调用方式
     *
     * @param url 请求URL
     * @param requestBody 请求参数体
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> post(String url, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
        return restTemplate.postForEntity(url, requestBody, responseType, uriVariables);
    }

    /**
     * 带请求头的POST请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param requestBody 请求参数体
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> post(String url, Map<String, String> headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        return post(url, httpHeaders, requestBody, responseType, uriVariables);
    }

    /**
     * 带请求头的POST请求调用方式
     *
     * @param url 请求URL
     * @param headers 请求头参数
     * @param requestBody 请求参数体
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    private <T> ResponseEntity<T> post(String url, HttpHeaders headers, Object requestBody, Class<T> responseType, Map<String, ?> uriVariables) {
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);
        return post(url, requestEntity, responseType, uriVariables);
    }

    /**
     * 自定义请求头和请求体的POST请求调用方式
     *
     * @param url 请求URL
     * @param requestEntity 请求头和请求体封装对象
     * @param responseType 返回对象类型
     * @param uriVariables URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    private <T> ResponseEntity<T> post(String url, HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables) {
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType, uriVariables);
    }


   public  <T> HttpEntity<T> getHttpEntity(T params){
       HttpHeaders headers = new HttpHeaders();
       MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
       headers.setContentType(type);
       headers.add("Accept", MediaType.APPLICATION_JSON.toString());
       HttpEntity<T> formEntity = new HttpEntity<T>(params, headers);
       return formEntity;
   }

    /**
     *
     * @param url 请求URL
     * @param form 请求参数
     * @param responseType 返回对象类型
     * @return ResponseEntity 响应对象封装类
     */
    public <T> ResponseEntity<T> upload(String url,MultiValueMap<String, Object> form, ParameterizedTypeReference<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(form, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType);
    }

}
