package com.vaccine.vaccineapi.config;

import com.vaccine.vaccineapi.utils.Constants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Arrays;

/**
 * @author hongye.lv
 * @date 2019/11/04
 **/
@Configuration
public class CommonConfig {

    @Bean
    @Qualifier(Constants.VANILLA_RESTTEMPLATE)
    public RestTemplate vanillaRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(
                Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM,MediaType.TEXT_PLAIN,
                        MediaType.TEXT_HTML,MediaType.parseMediaType("application/x-javascript")));
        return restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(5)).setReadTimeout(Duration.ofSeconds(30)).additionalMessageConverters(mappingJackson2HttpMessageConverter).build();
    }

}
