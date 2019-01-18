package com.obo.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @Auther: Double
 * @Date: 2019/01/18 15:58
 * @Description:
 */
@SpringBootApplication
public class SpitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringApplication.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
