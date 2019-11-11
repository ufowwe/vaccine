package com.vaccine.vaccineapi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

/**
 * @author lvhongye
 * @date 2019/07/23
 **/

@Slf4j
@Service
public class CodeService {

    public String UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public String getNumber(int num) {
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int n = random.nextInt(10);
            password.append(n);
        }
        return password.toString();
    }

}

