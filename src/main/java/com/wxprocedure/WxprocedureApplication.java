package com.wxprocedure;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wxprocedure.mapper")
public class WxprocedureApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxprocedureApplication.class, args);
    }

}
