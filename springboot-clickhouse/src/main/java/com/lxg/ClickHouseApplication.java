package com.lxg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LXG
 * @date 2022-4-15
 */
@SpringBootApplication
@MapperScan("com.lxg.mapper")
public class ClickHouseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClickHouseApplication.class, args);
    }
}
