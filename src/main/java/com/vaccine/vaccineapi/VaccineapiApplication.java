package com.vaccine.vaccineapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//开启事务
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.vaccine.vaccineapi.mapper")
public class VaccineapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VaccineapiApplication.class, args);
    }

}
