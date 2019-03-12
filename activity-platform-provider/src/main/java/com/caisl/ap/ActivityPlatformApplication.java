package com.caisl.ap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.caisl.ap"})
@MapperScan("com.caisl.ap.common.dao.mapper")
public class ActivityPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityPlatformApplication.class, args);
    }

}

