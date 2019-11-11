package com.vaccine.vaccineapi.config;

import com.google.common.base.Predicates;
import com.vaccine.vaccineapi.utils.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hongye.lv
 * @date 2019/07/26
 **/

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket customDocket(){
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name(Constants.ACCESS_TOKEN).description("用户认证")
            .modelRef(new ModelRef("string")).parameterType("header")
            .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数

        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(Predicates.or(PathSelectors.regex("/api/.*"),
                PathSelectors.regex("/login"),
                PathSelectors.regex("/logoutUser"),
                PathSelectors.regex("/getVerifyCode"),
                PathSelectors.regex("/verifyCode"),
                PathSelectors.regex("/modifyPassword"),
                PathSelectors.regex("/data/manual/sign"),
                PathSelectors.regex("/app/version/need/upgrade")))
            .build()
            .globalOperationParameters(pars)
            .apiInfo(apiInfo());
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("api swagger document")
            .description("app swagger api document")
            .version("0.0.1")
            .build();
    }

}
