package com.caisl.ap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.caisl.ap"})
public class ActivityPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityPlatformApplication.class, args);
    }

}

